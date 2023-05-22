package com.example.zpi_be.service;

import com.example.zpi_be.model.Image;
import com.example.zpi_be.model.User;
import com.example.zpi_be.repository.ImageRepo;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.midi.SysexMessage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepo imageRepo;

    private String FOLDER_PATH="";

    public List<Image> getAllImages(){
        return imageRepo.findAll();
    }

    public Page<Image> getAllImages(int offset, int pageSize){
        return imageRepo.findAll(PageRequest.of(offset, pageSize));
    }

    public Image saveNewImage(MultipartFile file, User user, Integer category, String description) throws IOException {
        this.FOLDER_PATH = new File("").getAbsolutePath() + "/images/";
        System.out.println(this.FOLDER_PATH);
        String filePath = FOLDER_PATH+file.getOriginalFilename();
        Image image = new Image();
        image.setOwnerId(user.getId());
        image.setCategory(category);
        image.setDescription(description);
        image.setUsername(user.getUsername());
        image.setName(file.getOriginalFilename());
        image.setFilePath(filePath);

        ZonedDateTime date= LocalDateTime.now().atZone(ZoneId.of("GMT"));
        image.setDate(date);
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