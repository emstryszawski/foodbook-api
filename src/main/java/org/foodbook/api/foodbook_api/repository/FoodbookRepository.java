package org.foodbook.api.foodbook_api.repository;

import org.foodbook.api.foodbook_api.repository.model.Foodbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FoodbookRepository extends JpaRepository<Foodbook, UUID> {

}
