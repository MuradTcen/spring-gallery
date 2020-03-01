package api.service.impl;

import api.entity.Author;
import api.repository.AuthorRepository;
import api.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Page<Author> getAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }
}
