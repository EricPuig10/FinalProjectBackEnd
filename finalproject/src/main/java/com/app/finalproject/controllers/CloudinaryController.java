package com.app.finalproject.controllers;

import com.app.finalproject.dtos.Message;
import com.app.finalproject.dtos.image.ImageResDto;
import com.app.finalproject.models.Image;
import com.app.finalproject.services.ICloudinaryService;
import com.app.finalproject.services.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin
public class CloudinaryController {


    ICloudinaryService cloudinaryService;
    IImageService imageService;

    @Autowired
    public CloudinaryController(ICloudinaryService cloudinaryService, IImageService imageService) {
        this.cloudinaryService = cloudinaryService;
        this.imageService = imageService;
    }

    @GetMapping("/images")
    public ResponseEntity<List<Image>> getAll(){
        List<Image> images = imageService.getAllImages();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null) {
            return new ResponseEntity(new Message("Imagen no válida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        Image image =
                new Image(result.get("original_filename").toString(),
                        result.get("url").toString(),
                        result.get("public_id").toString());
        imageService.save(image);
        return new ResponseEntity(new ImageResDto("Imagen cargada", image.getImgUrl(), image.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws IOException {
        /*if(!imageService.exists(id))
            return new ResponseEntity(new Message("Doesnt exists"), HttpStatus.NOT_FOUND);*/
        Image image = imageService.findById(id);
        Map result = cloudinaryService.delete(image.getImgId());
        imageService.delete(id);
        return new ResponseEntity(new ImageResDto("Imagen eliminada", image.getImgUrl(), image.getId()), HttpStatus.OK);
    }

}
