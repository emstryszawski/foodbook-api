package org.foodbook.api.foodbook_api.rest;

import lombok.RequiredArgsConstructor;
import org.foodbook.api.foodbook_api.repository.FoodbookRepository;
import org.foodbook.api.foodbook_api.repository.model.Foodbook;
import org.foodbook.api.foodbook_api.rest.dto.CreateFoodbookInput;
import org.foodbook.api.foodbook_api.rest.dto.FoodbookRepresentation;
import org.foodbook.api.swagger.recipe.api.RecipeControllerApi;
import org.foodbook.api.swagger.recipe.model.GetRecipe;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.foodbook.api.foodbook_api.rest.exception.FoodbookNotFoundException;

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
