package api.endpoint;

import api.entity.Author;
import api.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import spring_gallery.AuthorInfo;
import spring_gallery.GetAuthorRequest;
import spring_gallery.GetAuthorResponse;


@Endpoint
@Log
@RequiredArgsConstructor
public class AuthorEndpoint {
    private static final String NAMESPACE_URI = "spring-gallery";

    private final AuthorRepository authorRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAuthorRequest")
    @ResponsePayload
    public GetAuthorResponse getAuthor(@RequestPayload GetAuthorRequest request) {
        GetAuthorResponse response = new GetAuthorResponse();

        log.info("Get Author Name: " + request.getName());
        try {
            Author author = authorRepository.findByName(request.getName());
            log.info(String.valueOf("Author id's: " + author.getId()));

            //todo: разобраться с трансформами или подобным MapStruct'у и обработкой ошибки
            AuthorInfo authorInfo = new AuthorInfo();
            authorInfo.setId(author.getId());
            authorInfo.setName(author.getName());
            authorInfo.setArticle(author.getArticle());
            authorInfo.setCreatedAt(author.getCreatedAt().toString());

            response.setAuthorInfo(authorInfo);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info(e.getCause().toString());
        }

        return response;
    }
}