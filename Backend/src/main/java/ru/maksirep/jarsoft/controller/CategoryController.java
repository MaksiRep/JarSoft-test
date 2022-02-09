package ru.maksirep.jarsoft.controller;

import org.springframework.data.annotation.Transient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maksirep.jarsoft.controller.requests.CategoryRequest;
import ru.maksirep.jarsoft.exceptions.*;
import ru.maksirep.jarsoft.model.Category;
import ru.maksirep.jarsoft.service.CategoryServiceImpl;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000/"})
public class CategoryController {

    private final CategoryServiceImpl categoryServiceImpl;

    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @Transient
    @PostMapping(value = "/categories")
    public ResponseEntity<Void> addCategory(@RequestBody CategoryRequest categoryRequest) throws AlreadyExistException, IncorrectInputException {
        categoryServiceImpl.addCategory(categoryRequest.getName(), categoryRequest.getRequestId());
        return ResponseEntity.ok().build();
    }


    @Transient
    @PutMapping(value = "/categories")
    public ResponseEntity<Void> putCategory(@RequestBody CategoryRequest categoryRequest) throws EntityDeletedException, AlreadyExistException, IncorrectInputException {
        categoryServiceImpl.updateCategory(categoryRequest.getId(),
                categoryRequest.getName(),
                categoryRequest.getRequestId());
        return ResponseEntity.ok().build();
    }

    @Transient
    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(name = "id") Integer id) throws NotFoundException, EntityDeletedException, CategoryDeleteException {
        categoryServiceImpl.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @Transient
    @GetMapping(value = "/categories")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categoryList = categoryServiceImpl.getCategories();
        if (categoryList != null && !categoryList.isEmpty()) {
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
