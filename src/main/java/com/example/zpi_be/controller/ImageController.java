package com.example.zpi_be.controller;

import com.example.zpi_be.model.Image;
import com.example.zpi_be.model.ImageResponse;
import com.example.zpi_be.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/images")
@CrossOrigin
public class ImageController {
    @Autowired
    ImageService imageService;

    @GetMapping("")
    public List<ImageResponse> getAllImages() throws IOException {
        List<ImageResponse> listOfImages = new ArrayList<>();
        for (Image image: imageService.getAllImages()) {
            ImageResponse imageResponse = new ImageResponse();
            imageResponse.setId(image.getId());
            imageResponse.setUsername(image.getUsername());
            imageResponse.setCategory(image.getCategory());
            imageResponse.setDate(image.getDate());
            imageResponse.setImageData(imageService.downloadImageFromFileSystem(image.getFilePath()));
            imageResponse.setDescription(image.getDescription());
            imageResponse.setName(image.getName());
            imageResponse.setOwnerId(imageResponse.getOwnerId());
            listOfImages.add(imageResponse);
        }
        return listOfImages;
    }

    @PostMapping("/newImage")
    public Image saveNewImage(@RequestParam("image") MultipartFile file, @RequestBody Image image){
        try {
            return imageService.saveNewImage(image, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
