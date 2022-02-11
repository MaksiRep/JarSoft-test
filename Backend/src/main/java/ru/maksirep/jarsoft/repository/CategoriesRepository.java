package ru.maksirep.jarsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Integer> {

    List<Category> findAllByRequestIdIn(List<String> id);

    Category findById(int id);

    Category findByNameAndRequestId (String name, String requestId);

    List<Category> findCategoriesByDeletedFalse ();

    Category findByRequestId (String requestId);

    Category findByName (String name);
}
