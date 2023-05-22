package com.example.zpi_be.controller;

import com.example.zpi_be.model.Image;
import com.example.zpi_be.model.ImageResponse;
import com.example.zpi_be.model.User;
import com.example.zpi_be.service.ImageService;
import com.example.zpi_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        for (Image image: imageService.getAllImages()) {
            ImageResponse imageResponse = new ImageResponse();
            imageResponse.setId(image.getId());
            imageResponse.setUsername(image.getUsername());
            imageResponse.setCategory(image.getCategory());
            imageResponse.setDate(image.getDate());
            imageResponse.setImageData(imageService.downloadImageFromFileSystem(image.getName()));
            imageResponse.setDescription(image.getDescription());
            imageResponse.setName(image.getName());
            imageResponse.setOwnerId(image.getOwnerId());
            listOfImages.add(imageResponse);
        }
        return listOfImages;
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
    public Image saveNewImage(@RequestParam("image") MultipartFile file, @PathVariable Long user_id, @PathVariable Integer category, @PathVariable String description){
        try {
            User dbUser = userService.getUserById(user_id);
            return imageService.saveNewImage(file, dbUser, category, description);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
