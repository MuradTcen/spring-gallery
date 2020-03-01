package api.controller;

import api.entity.Author;
import api.entity.File;
import api.repository.AuthorRepository;
import api.repository.FileRepository;
import api.service.AuthorService;
import api.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorRepository authorRepository;
    private final FileRepository fileRepository;
    private final ImageService imageService;

    @GetMapping(value = "/api/author")
    public Page<Author> read(Pageable pageRequest) {
        return authorService.getAll(pageRequest);
    }

    @GetMapping(value = "/api/author/{id}")
    public Author one(@PathVariable Long id, HttpServletResponse response) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Author Not Found"));
    }

    @PutMapping(value = "/api/author/{id}")
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

    @PostMapping("/api/author")
    Author newAuthor(@Valid @RequestBody Author newAuthor) {
        return authorRepository.save(newAuthor);
    }

    @DeleteMapping("/api/author/{id}")
    void deleteAuthor(@PathVariable Long id) {
        authorRepository.deleteById(id);
    }

    @GetMapping(value = "/api/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImage(@PathVariable Long id) throws IOException {
        File file = fileRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "File Not Found"));

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageService.getImage(file.getPath()));
    }
}
