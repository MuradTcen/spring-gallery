package api.controller;

import api.entity.Author;
import api.exception.NotFoundException;
import api.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/author")
    public Page<Author> read(Pageable pageRequest) {
        return  authorService.getAll(pageRequest);
    }

    @GetMapping(value = "/author/{id}")
    public Author one(@PathVariable Long id, HttpServletResponse response) {
        try {
            Author author = authorService.getById(id);
            if(author==null) {
                throw new NotFoundException();
            }
            return author;
        }
        catch (NotFoundException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Author Not Found", exc);
        }
    }
}
