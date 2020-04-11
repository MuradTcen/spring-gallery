package api.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {
    InputStreamResource getImageById(Long id) throws IOException;
    String storeFile(MultipartFile file);
    Resource loadFileAsResource(String fileName);
}
