package pl.edu.pjatk.foodbook.foodbookservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.foodbook.foodbookservice.repository.FoodbookRepository;
import pl.edu.pjatk.foodbook.foodbookservice.repository.model.Foodbook;
import pl.edu.pjatk.foodbook.foodbookservice.rest.dto.CreateFoodbookInput;
import pl.edu.pjatk.foodbook.foodbookservice.rest.dto.FoodbookRepresentation;
import pl.edu.pjatk.foodbook.foodbookservice.rest.exception.FoodbookNotFoundException;
import pl.edu.pjatk.foodbook.foodbookservice.swagger.recipe.api.RecipeControllerApi;
import pl.edu.pjatk.foodbook.foodbookservice.swagger.recipe.model.GetRecipe;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodbookService {
    private final FoodbookRepository foodbookRepository;
    private final RecipeControllerApi recipeApi;

    public FoodbookRepresentation createFoodbook(CreateFoodbookInput input) {
        Foodbook foodbook = Foodbook.builder()
            .title(input.getTitle())
            .recipesIds(input.getRecipesIds())
            .owner(SecurityContextHolder.getContext().getAuthentication().getName())
            .build();
        foodbook = foodbookRepository.save(foodbook);
        return build(foodbook);
    }

    private List<GetRecipe> fetchRecipes(List<UUID> recipesIds) {
        return recipesIds.stream()
            .map(this::fetchRecipe)
            .filter(Objects::nonNull)
            .toList();
    }

    private GetRecipe fetchRecipe(UUID recipeId) {
        ResponseEntity<GetRecipe> response = recipeApi.getRecipe(recipeId);
        if (response != null && response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        }
        return null;
    }

    public FoodbookRepresentation getFoodbookById(UUID foodbookId) {
        return foodbookRepository
            .findById(foodbookId)
            .map(this::build)
            .orElseThrow(() -> new FoodbookNotFoundException("Foodbook not found with id " + foodbookId));
    }

    private FoodbookRepresentation build(Foodbook foodbook) {
        return FoodbookRepresentation.builder()
            .id(foodbook.getId())
            .title(foodbook.getTitle())
            .owner(foodbook.getOwner())
            .recipes(
                fetchRecipes(foodbook.getRecipesIds())
            ).build();
    }

    public List<FoodbookRepresentation> getAllFoodbooks() {
        List<Foodbook> foodbooks = foodbookRepository.findAll();
        return foodbooks.stream()
            .map(this::build)
            .collect(Collectors.toList());
    }
}
