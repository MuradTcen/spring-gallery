package api.service.impl;

import api.ServiceProperties;
import api.entity.File;
import api.exception.FileStorageException;
import api.exception.MyFileNotFoundException;
import api.repository.FileRepository;
import api.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(ServiceProperties.class)
public class ImageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;
    private final ServiceProperties serviceProperties;
    @Autowired
    private FileRepository fileRepository;

    @Override
    public InputStreamResource getImageById(Long id) throws IOException {
        File file = fileRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "File Not Found"));

        ClassPathResource imgFile = new ClassPathResource(serviceProperties.getDirectory() + "/" + file.getPath());

        if (imgFile == null) {
            imgFile = new ClassPathResource(serviceProperties.getDirectory() + "/no-image.jpg");
        }

        return new InputStreamResource(imgFile.getInputStream());
    }


    @Autowired
    public ImageServiceImpl(ServiceProperties serviceProperties) {
        this.serviceProperties=serviceProperties;

        this.fileStorageLocation = Paths.get(serviceProperties.getDirectory())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!"  , ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}


