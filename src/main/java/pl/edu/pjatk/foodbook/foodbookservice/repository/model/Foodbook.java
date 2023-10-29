package pl.edu.pjatk.foodbook.foodbookservice.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Foodbook {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @ElementCollection
    private List<UUID> recipesIds;

    private String owner;
}
