package com.app.finalproject.services;



import com.app.finalproject.models.Image;

import java.util.List;

public interface IImageService {
    List<Image> getAllImages();
    boolean exists(Long id);
    Image findById(Long id);
    Image save(Image image);
    boolean delete(Long id);


}
