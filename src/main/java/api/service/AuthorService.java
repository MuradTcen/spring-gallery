package api.service;

import api.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {
    Author addAuthor(Author author);
    void delete(long id);
    Author getByName(String name);
    Author getById(long id);
    Author editAuthor(Author author);
    Page<Author> getAll(Pageable pageable);
}
