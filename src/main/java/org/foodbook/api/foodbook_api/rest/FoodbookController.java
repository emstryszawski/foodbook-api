package org.foodbook.api.foodbook_api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.foodbook.api.foodbook_api.rest.dto.CreateFoodbookInput;
import org.foodbook.api.foodbook_api.rest.dto.FoodbookRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class FoodbookController {

    private final FoodbookService foodbookService;

    @PostMapping
    public ResponseEntity<FoodbookRepresentation> createFoodbook(@RequestBody @Valid CreateFoodbookInput input) {
        return new ResponseEntity<>(foodbookService.createFoodbook(input), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FoodbookRepresentation>> getAllFoodbooks() {
        return ResponseEntity.ok(foodbookService.getAllFoodbooks());
    }

    @GetMapping("/{foodbookId}")
    public ResponseEntity<FoodbookRepresentation> getFoodbookById(@PathVariable UUID foodbookId) {
        return ResponseEntity.ok(foodbookService.getFoodbookById(foodbookId));
    }
}
