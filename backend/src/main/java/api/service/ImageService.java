package api.service;

import org.springframework.core.io.InputStreamResource;

import java.io.IOException;

public interface ImageService {
    InputStreamResource getImageById(Long id) throws IOException;
}
