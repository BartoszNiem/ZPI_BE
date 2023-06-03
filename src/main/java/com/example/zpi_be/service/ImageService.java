package com.example.zpi_be.service;

import com.example.zpi_be.model.*;
import com.example.zpi_be.repository.ImageCommentRepo;
import com.example.zpi_be.repository.ImageRatingRepo;
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

    @Autowired
    ImageCommentRepo imageCommentRepo;

    private String FOLDER_PATH="";

    @Autowired
    private ImageRatingRepo imageRatingRepo;

    public List<Image> getAllImages(){
        return imageRepo.findAll();
    }
    public List<Image> getAllUsersImagesById(Long user_id){
        return imageRepo.findAll().stream().filter(image -> image.getOwnerId() == user_id).toList();
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

        image.setCurrentRating(5.00);
        image.setNumberOfRatings(1);

        ZonedDateTime date= LocalDateTime.now().atZone(ZoneId.of("CET"));
        image.setDate(date);
        imageRepo.save(image);
        file.transferTo(new File(filePath));
        return image;
    }

    public Image updateImage(Image image){
        imageRepo.save(image);
        return image;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<Image> image = Optional.ofNullable(imageRepo.getImageByName(fileName));
        String filePath=image.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    public ImageComment addComment(ImageComment comment){
        imageCommentRepo.save(comment);
        return comment;
    }

    public List<ImageComment> getImageComments(Long imageId){
        return imageCommentRepo.getCommentsByImageId(imageId);
    }


    public Image getImageById(Long imageId) {
        if( imageRepo.findById(imageId).isPresent()){
            return imageRepo.findById(imageId).get();
        }
        return null;
    }

    public ImageRating getImageRating(Long ownerId, Long imageId) {
        return imageRatingRepo.getImageRatingByOwnerIdAndImageId(ownerId, imageId);
    }

    public ImageRating saveRating(ImageRating dbRating) {
        imageRatingRepo.save(dbRating);
        return dbRating;
    }

    public ImageComment getCommentById(Long commentId) {
        return imageCommentRepo.findById(commentId).get();
    }

    public void deleteCommentById(Long commentId) {
        imageCommentRepo.deleteById(commentId);
    }

    public void deleteImageById(Long imageId) {
        imageRepo.deleteById(imageId);
    }

    public void deleteCommentsByImageId(Long imageId) {
        imageCommentRepo.deleteAllByImageId(imageId);
    }
}