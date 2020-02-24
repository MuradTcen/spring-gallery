package api.service.impl;

import api.entity.Author;
import api.repository.AuthorRepository;
import api.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author addAuthor(Author author) {
        Author savedAuthor = authorRepository.save(author);

        return savedAuthor;
    }

    @Override
    public void delete(long id) {
        authorRepository.delete(id);
    }

    @Override
    public Author getByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public Author editAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author getById(long id) {
        return authorRepository.getAuthorById(id);
    }

    @Override
    public Page<Author> getAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }
}
