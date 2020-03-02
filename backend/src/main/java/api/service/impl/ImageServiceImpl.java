package api.service.impl;

import api.entity.File;
import api.repository.FileRepository;
import api.service.ImageService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private Environment env;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public InputStreamResource getImageById(Long id) throws IOException {
        File file = fileRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "File Not Found"));

        var imgFile = new ClassPathResource(env.getProperty("images.directory") + "/" + file.getPath());

        if (imgFile == null) {
            imgFile = new ClassPathResource("/upload/images/no-image.jpg");
        }

        return new InputStreamResource(imgFile.getInputStream());
    }
}
