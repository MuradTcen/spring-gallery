package api.service;

import api.dto.CategoryDTO;
import api.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<Category> getAll(Pageable pageable);

    Category saveFromDTO(CategoryDTO category);
}
