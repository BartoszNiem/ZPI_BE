package com.example.zpi_be.controller;

import com.example.zpi_be.model.*;
import com.example.zpi_be.service.ImageService;
import com.example.zpi_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/images")
@CrossOrigin
public class ImageController {
    @Autowired
    ImageService imageService;
    @Autowired
    UserService userService;

    @GetMapping("")
    public List<ImageResponse> getAllImages() throws IOException {
        List<ImageResponse> listOfImages = new ArrayList<>();
        for (Image image : imageService.getAllImages()) {
            ImageResponse imageResponse = new ImageResponse();
            imageResponse.setId(image.getId());
            imageResponse.setUsername(image.getUsername());
            imageResponse.setCategory(image.getCategory());
            imageResponse.setDate(image.getDate());
            imageResponse.setImageData(imageService.downloadImageFromFileSystem(image.getName()));
            imageResponse.setDescription(image.getDescription());
            imageResponse.setName(image.getName());
            imageResponse.setOwnerId(image.getOwnerId());
            imageResponse.setCurrentRating(image.getCurrentRating());
            imageResponse.setNumberOfRatings(image.getNumberOfRatings());
            listOfImages.add(imageResponse);
        }
        return listOfImages;
    }

    @GetMapping("/{user_id}")
    public List<ImageResponse> getAllUsersImages(@PathVariable Long user_id) throws IOException {
        List<ImageResponse> listOfImages = new ArrayList<>();
        for (Image image : imageService.getAllUsersImagesById(user_id)) {
            ImageResponse imageResponse = new ImageResponse();
            imageResponse.setId(image.getId());
            imageResponse.setUsername(image.getUsername());
            imageResponse.setCategory(image.getCategory());
            imageResponse.setDate(image.getDate());
            imageResponse.setImageData(imageService.downloadImageFromFileSystem(image.getName()));
            imageResponse.setDescription(image.getDescription());
            imageResponse.setName(image.getName());
            imageResponse.setOwnerId(image.getOwnerId());
            imageResponse.setCurrentRating(image.getCurrentRating());
            imageResponse.setNumberOfRatings(image.getNumberOfRatings());
            listOfImages.add(imageResponse);
        }
        return listOfImages;
    }

    @GetMapping("/imagedata/{image_id}")
    public ImageResponse getImageData(@PathVariable Long image_id) throws IOException {
        Image image = imageService.getImageById(image_id);
        ImageResponse imageResponse = new ImageResponse();
        imageResponse.setId(image.getId());
        imageResponse.setUsername(image.getUsername());
        imageResponse.setCategory(image.getCategory());
        imageResponse.setDate(image.getDate());
        imageResponse.setImageData(imageService.downloadImageFromFileSystem(image.getName()));
        imageResponse.setDescription(image.getDescription());
        imageResponse.setName(image.getName());
        imageResponse.setOwnerId(image.getOwnerId());
        imageResponse.setCurrentRating(image.getCurrentRating());
        imageResponse.setNumberOfRatings(image.getNumberOfRatings());

        return imageResponse;
    }

//    @GetMapping("/{offset}/{pageSize}")
//    public Page<ImageResponse> getAllImagesPagination(@PathVariable Integer offset, @PathVariable Integer pageSize) throws IOException {
//        List<ImageResponse> listOfImages = new ArrayList<>();
//        for (Image image: imageService.getAllImages()) {
//            ImageResponse imageResponse = new ImageResponse();
//            imageResponse.setId(image.getId());
//            imageResponse.setUsername(image.getUsername());
//            imageResponse.setCategory(image.getCategory());
//            imageResponse.setDate(image.getDate());
//            imageResponse.setImageData(imageService.downloadImageFromFileSystem(image.getName()));
//            imageResponse.setDescription(image.getDescription());
//            imageResponse.setName(image.getName());
//            imageResponse.setOwnerId(image.getOwnerId());
//            listOfImages.add(imageResponse);
//        }
//        final int start = offset;
//        final int end = Math.min((start + pageSize), listOfImages.size());
//        final Page<ImageResponse> page = new PageImpl<>(listOfImages.subList(start, end), new PageRequest(offset, pageSize, new Sort(Sort.Direction.ASC, "date" ) ), listOfImages.size());
//        return listOfImages;
//    }


    @PostMapping("/newImage/{user_id}/{category}/{description}")
    public Image saveNewImage(@RequestParam("image") MultipartFile file, @PathVariable Long user_id, @PathVariable Integer category, @PathVariable String description) {
        try {
            User dbUser = userService.getUserById(user_id);
            return imageService.saveNewImage(file, dbUser, category, description);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/add_comment")
//    ImageComment addComment(@RequestBody ImageComment comment) {
//        ZonedDateTime date = LocalDateTime.now().atZone(ZoneId.of("CET"));
//        comment.setDate(date);
//        imageService.addComment(comment);
//        return comment;
//    }
    ImageComment addComment(@RequestBody ImageCommentRequest comment){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ZonedDateTime date = LocalDateTime.now().atZone(ZoneId.of("CET"));
        String email = userDetails.getUsername();
        User user = userService.getUserByEmail(email);
        ImageComment imageComment = new ImageComment();
        imageComment.setContent(comment.getContent());
        imageComment.setImageId((comment.getImageId()));
        imageComment.setUsername(user.getUsername());
        imageComment.setUserId(user.getId());
        imageComment.setDate(date);
        imageService.addComment(imageComment);
        return imageComment;
    }
    @GetMapping("/get_comments/{image_id}")
    List<ImageComment> getPostComments(@PathVariable Long image_id) {
        return imageService.getImageComments(image_id);
    }


    @PutMapping("/add_rating")
    ImageRating saveImageRating(@RequestBody ImageRating imageRating) {
        Image dbImage = imageService.getImageById(imageRating.getImageId());
        Double currentRating = dbImage.getCurrentRating();
        Integer numberOfRatings = dbImage.getNumberOfRatings();
        ImageRating dbRating = imageService.getImageRating(imageRating.getOwnerId(), imageRating.getImageId());
        if (dbRating != null) {
            Double current_rating_mod = (currentRating * numberOfRatings) - dbRating.getRating();
            current_rating_mod += imageRating.getRating();
            current_rating_mod /= numberOfRatings;
            dbImage.setCurrentRating(current_rating_mod);

            imageService.updateImage(dbImage);
            dbRating.setRating(imageRating.getRating());
            imageService.saveRating(dbRating);
            return dbRating;
        } else {
            Double current_rating_mod = (currentRating * numberOfRatings);
            current_rating_mod += imageRating.getRating();
            numberOfRatings++;
            current_rating_mod /= numberOfRatings;
            dbImage.setCurrentRating(current_rating_mod);
            dbImage.setNumberOfRatings(numberOfRatings);

            imageService.updateImage(dbImage);
            imageService.saveRating(imageRating);
            return imageRating;
        }

    }

    @DeleteMapping("delete_image_comment/{comment_id}")
    ImageComment deletePostCommentById(@PathVariable Long comment_id) {
        ImageComment dbComment = imageService.getCommentById(comment_id);
        if (dbComment != null) {
            imageService.deleteCommentById(comment_id);
        }
        return dbComment;
    }

    @DeleteMapping("delete_image/{image_id}")
    Image deleteImageById(@PathVariable Long image_id) {
        Image dbImage = imageService.getImageById(image_id);
        if (dbImage != null) {
            imageService.deleteImageById(image_id);
            if(imageService.getImageComments(image_id).size() != 0){
                imageService.deleteCommentsByImageId(image_id);
            }
        }
        return dbImage;
    }

}
