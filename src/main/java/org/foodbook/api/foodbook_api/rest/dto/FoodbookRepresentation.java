package org.foodbook.api.foodbook_api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.foodbook.api.swagger.recipe.model.GetRecipe;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodbookRepresentation {
    private UUID id;
    private List<GetRecipe> recipes;
    private String title;
    private String owner;
}
