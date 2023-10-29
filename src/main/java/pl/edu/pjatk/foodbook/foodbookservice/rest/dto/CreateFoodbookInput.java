package pl.edu.pjatk.foodbook.foodbookservice.rest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFoodbookInput {
    @NotNull
    @NotEmpty
    private List<UUID> recipesIds;
    @NotNull
    @NotEmpty
    private String title;
}
