package api.service.impl;

import api.dto.CategoryDTO;
import api.entity.Category;
import api.repository.CategoryRepository;
import api.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Page<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category saveFromDTO(CategoryDTO category) {
        Category newCategory = new Category();

        newCategory.setName(category.getName());
        newCategory.setSort(category.getSort());
        newCategory.setActive(category.isActive());

        return categoryRepository.save(newCategory);
    }
}
