package api.controller;

import api.dto.CategoryDTO;
import api.entity.Category;
import api.repository.CategoryRepository;
import api.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Log
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping(value = "/category")
    public Page<Category> read(Pageable pageRequest) {
        return categoryService.getAll(pageRequest);
    }

    @GetMapping(value = "/category/{id}")
    public Category one(@PathVariable Long id, HttpServletResponse response) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Category Not Found"));
    }

    @PutMapping(value = "/category/{id}")
    Category update(@RequestBody Category newCategory, @PathVariable Long id) {

        return categoryRepository.findById(id)
                .map(x -> {
                    if (newCategory.getName() != null) {
                        x.setName(newCategory.getName());
                    }
                    x.setName(newCategory.getName());
                    x.setActive(newCategory.isActive());
                    return categoryRepository.save(x);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Work Not Found"));
    }

    @PostMapping("/category")
    Category newCategory(@Valid @RequestBody CategoryDTO newCategory) {
        return categoryService.saveFromDTO(newCategory);
    }

    @DeleteMapping("/category/{id}")
    void deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }

}
