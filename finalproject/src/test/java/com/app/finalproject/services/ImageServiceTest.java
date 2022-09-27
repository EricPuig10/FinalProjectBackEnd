package com.app.finalproject.services;

import com.app.finalproject.dtos.category.CategoryReq;
import com.app.finalproject.models.Category;
import com.app.finalproject.models.Image;
import com.app.finalproject.models.User;
import com.app.finalproject.repositories.IImageRepository;
import com.app.finalproject.services.categoryS.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ImageServiceTest {
    @Mock
    IImageRepository imageRepository;

    private IImageService imageService;

    public Image createImage(){
        var image = new Image();
        image.setName("Image");
        image.setId(1L);

        return image;
    }

    @BeforeEach
    void beforeEach() {
        this.imageService = new ImageService(imageRepository);
    }

    @Test
    void getAllImagesShouldReturnAListOfImages() {

        var imageList = List.of(new Image(), new Image());
        Mockito.when(imageRepository.findByOrderById()).thenReturn(imageList);

        var sut = imageService.getAllImages();

        assertThat (sut.size(), equalTo(2));
    }

    @Test
    void findByIdShouldReturnAnImageByParamId() {
        var image = this.createImage();
        var authUser = new User();
        authUser.setId(1L);

        Mockito.when(imageRepository.findById(any(Long.class))).thenReturn(Optional.of(image));

        var sut = imageService.findById(1L);

        assertThat(sut.getName(), equalTo(image.getName()));
    }

    @Test
    void saveShouldCreateAImageAndSaveIt() {
        Image imageReq = new Image();
        imageReq.setName("img1");

        var image = createImage();

        Mockito.when(imageRepository.save(any(Image.class))).thenReturn(image) ;

        var sut = imageService.save(imageReq);

        assertThat(sut.getName(), equalTo(image.getName()));
    }

    @Test
    void deleteShouldReturnTrueIfIsDeleted() {
        Long id = 1L;
        Image image = createImage();
        Mockito.when(imageRepository.findById(any(Long.class))).thenReturn(Optional.of(image));
        var sut = imageService.delete(id) ;
        assertThat(sut, equalTo(true));
    }

    @Test
    void existsShouldReturnTrueIfExists() {
        var image = this.createImage();
        var authUser = new User();
        authUser.setId(1L);

        Mockito.when(imageRepository.existsById(any(Long.class))).thenReturn(true);

        var sut = imageService.exists(1L);

        assertThat(sut, equalTo(true));
    }
}