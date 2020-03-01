package api.service.impl;

import api.service.ImageService;
import lombok.var;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public InputStreamResource getImage(String path) throws IOException {

        var imgFile = new ClassPathResource(path);

        if(imgFile == null) {
            imgFile = new ClassPathResource("/upload/images/no-image.jpg");
        }

        return new InputStreamResource(imgFile.getInputStream());
    }
}
