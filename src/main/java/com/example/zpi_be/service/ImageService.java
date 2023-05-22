package com.example.zpi_be.service;

import com.example.zpi_be.model.Image;
import com.example.zpi_be.model.User;
import com.example.zpi_be.repository.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepo imageRepo;

    private final String FOLDER_PATH="C:/Users/bniem/ZPI_FILES/";
    public List<Image> getAllImages(){
        return imageRepo.findAll();
    }

    public Image saveNewImage(MultipartFile file, User user, Integer category, String description) throws IOException {
        String filePath = FOLDER_PATH+file.getOriginalFilename();
        Image image = new Image();
        image.setOwnerId(user.getId());
        image.setCategory(category);
        image.setDescription(description);
        image.setUsername(user.getUsername());
        image.setName(file.getOriginalFilename());
        image.setFilePath(filePath);
        imageRepo.save(image);
        file.transferTo(new File(filePath));
        return image;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<Image> image = Optional.ofNullable(imageRepo.getImageByName(fileName));
        String filePath=image.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }


}
