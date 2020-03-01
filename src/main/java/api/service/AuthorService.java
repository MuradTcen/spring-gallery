package api.service;

import api.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    Page<Author> getAll(Pageable pageable);
}
