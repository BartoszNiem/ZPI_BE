package com.example.zpi_be.service;

import com.example.zpi_be.model.Image;
import com.example.zpi_be.repository.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    ImageRepo imageRepo;

    List<Image> getAllImages(){
        return imageRepo.findAll();
    }

    Image saveNewImage(Image image){
        imageRepo.save(image);
        return image;
    }


}
