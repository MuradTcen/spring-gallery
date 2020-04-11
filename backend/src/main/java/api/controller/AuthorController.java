package api.controller;

import api.entity.Author;
import api.repository.AuthorRepository;
import api.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    @GetMapping(value = "/author")
    public Page<Author> read(Pageable pageRequest) {
        return authorService.getAll(pageRequest);
    }

    @GetMapping(value = "/author/{id}")
    public Author one(@PathVariable Long id, HttpServletResponse response) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Author Not Found"));
    }

    @PutMapping(value = "/author/{id}")
    Author update(@RequestBody Author newAuthor, @PathVariable Long id) {

        return authorRepository.findById(id)
                .map(x -> {
                    if (newAuthor.getName() != null) {
                        x.setName(newAuthor.getName());
                    }
                    if (newAuthor.getArticle() != null) {
                        x.setArticle(newAuthor.getArticle());
                    }
                    return authorRepository.save(x);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Author Not Found"));
    }

    @PostMapping("/author")
    Author newAuthor(@Valid @RequestBody Author newAuthor) {
        return authorRepository.save(newAuthor);
    }

    @DeleteMapping("/author/{id}")
    void deleteAuthor(@PathVariable Long id) {
        authorRepository.deleteById(id);
    }
}
