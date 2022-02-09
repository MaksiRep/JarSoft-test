package ru.maksirep.jarsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.maksirep.jarsoft.model.Banner;
import ru.maksirep.jarsoft.model.Category;

import java.util.List;
import java.util.Set;

@Repository
public interface BannersRepository extends JpaRepository<Banner, Integer> {

    Banner findById(int id);

    List<Banner> findBannersByDeletedFalseAndCategories (Category category);

    Set<Banner> findBannersByDeletedFalseAndCategoriesIn (List<Category> categories);

    Banner findBannerByName (String name);

    List<Banner> findBannersByDeletedFalse ();

}
