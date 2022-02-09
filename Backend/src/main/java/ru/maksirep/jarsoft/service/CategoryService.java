package ru.maksirep.jarsoft.service;

import ru.maksirep.jarsoft.exceptions.*;
import ru.maksirep.jarsoft.model.Category;

import java.util.List;

public interface CategoryService {

    void addCategory(String name, String requestId) throws AlreadyExistException, IncorrectInputException;

    void updateCategory(int id, String name, String requestId) throws EntityDeletedException, AlreadyExistException, IncorrectInputException;

    void deleteCategory(int id) throws NotFoundException, EntityDeletedException, CategoryDeleteException;

    List<Category> getCategories();
}
