package api.service;

import org.springframework.core.io.InputStreamResource;

import java.io.IOException;

public interface ImageService {
    InputStreamResource getImage(String path) throws IOException;
}
