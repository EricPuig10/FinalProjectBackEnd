package com.app.finalproject.services;

import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.dtos.bootcamp.BootcampReqDto;
import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.Category;
import com.app.finalproject.models.User;
import com.app.finalproject.repositories.IBootcampRepository;
import com.app.finalproject.repositories.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BootcampServiceTest {

    @Mock
    IBootcampRepository bootcampRepository;
    @Mock
    IAuthenticationFacade authenticationFacade;
    @Mock
    ICategoryRepository categoryRepository;

    private IBootcampService bootcampService;

    @BeforeEach
    void beforeEach() {
        this.bootcampService = new BootcampService(bootcampRepository, authenticationFacade, categoryRepository);
    }

    private Bootcamp createBootcamp() {
        var authUser = new User();
        authUser.setId(1L);

        var category = new Category();
        category.setId(1L);

        var bootcamp = new Bootcamp();
        bootcamp.setId(1L);
        bootcamp.setBootcampName("bootcampName");
        bootcamp.setDuration("duration");
        bootcamp.setFormer("former");
        bootcamp.setCategory(category);

        return bootcamp;
    }

    @Test
    void getAllReturnListOfBootcamp() {
        var bootcampService = new BootcampService(bootcampRepository, authenticationFacade, categoryRepository);
        var bootcampList = List.of(new Bootcamp(), new Bootcamp());
        var authUser = new User();
        authUser.setId(1L);

        Mockito.when(bootcampRepository.findAll()).thenReturn(bootcampList);

        var sut = bootcampService.getAll();
        assertThat(sut.size(), equalTo(2));
    }

    @Test
    void findByIdShouldReturnABootcampWithSameParamId() {
        var bootcamp = this.createBootcamp();
        var authUser = new User();
        authUser.setId(1L);

        Mockito.when(bootcampRepository.findById(any(Long.class))).thenReturn(Optional.of(bootcamp));

        var sut = bootcampService.findById(1L, authUser);

        assertThat(sut.getBootcampName(), equalTo(bootcamp.getBootcampName()));
    }

    @Test
    void createShouldSaveABootcampFromBootcampReqDto() {

        var bootcampRequest = new BootcampReqDto("name", "category", "300", "type", "former", "coformer", new Date(), new Date());
        System.out.println(bootcampRequest.getInitialDate());
        var bootcamp = createBootcamp();
        var authUser = new User();
        var category = new Category("category");

        Mockito.when(categoryRepository.findByName(any(String.class))).thenReturn(Optional.of(category));
        Mockito.when(bootcampRepository.save(any(Bootcamp.class))).thenReturn(bootcamp);

        var sut = bootcampService.createBootcamp(bootcampRequest, authUser);

        assertThat(sut.getCategory(), equalTo(bootcamp.getCategory()));
    }


}