package com.app.finalproject.services;

import com.app.finalproject.dtos.candidats.CandidatReq;
import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.dtos.category.CategoryReq;
import com.app.finalproject.mappers.CandidatMapper;
import com.app.finalproject.models.*;
import com.app.finalproject.repositories.IBootcampRepository;
import com.app.finalproject.repositories.ICandidatRepository;
import com.app.finalproject.repositories.ICategoryRepository;
import com.app.finalproject.repositories.IProcessStateRepository;
import com.app.finalproject.services.candidatS.CandidatService;
import com.app.finalproject.services.candidatS.ICandidatService;
import com.app.finalproject.services.categoryS.CategoryService;
import com.app.finalproject.services.categoryS.ICategoryService;
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
class CategoryServiceTest {

    @Mock
    ICategoryRepository categoryRepository;
    private ICategoryService categoryService;

    @BeforeEach
    void beforeEach() {
        this.categoryService = new CategoryService(categoryRepository);
    }

    public Category createCategory(){
        var category = new Category();
        category.setName("Full");
        category.setId(1L);

        return category;
    }

    @Test
    void getAllShouldReturnAListOfCategories() {
        var category = new Category();
        category.setName("Full");
        category.setId(1L);
        var user = new User();
        user.setId(1L);
        var categoryList = List.of(new Category(), new Category());
        Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);

        var sut = categoryService.getAll(user);

        assertThat (sut.size(), equalTo(2));
    }

    @Test
    void findByIdShouldReturnACategory() {

        var category = this.createCategory();
        var authUser = new User();
        authUser.setId(1L);

        Mockito.when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.of(category));

        var sut = categoryService.findById(1L, authUser);

        assertThat(sut.getName(), equalTo(category.getName()));
    }

    @Test
    void createShouldCreateACategoryUsingCategoryReq() {
        CategoryReq categoryReq = new CategoryReq( "Back");

        var category = createCategory();
        var authUser = new User();


        Mockito.when(categoryRepository.save(any(Category.class))).thenReturn(category) ;

        var sut = categoryService.create(categoryReq, authUser);

        assertThat(sut.getName(), equalTo(category.getName()));
    }

    @Test
    void updateACategoryShoulsUpdateFromCategoryReq() {
        CategoryReq categoryReq = new CategoryReq( "Back");
        Long id = 1L;

        var user = new User();
        user.setId(1L);

        Category category = this.createCategory();
        Mockito.when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository.save(any(Category.class))).thenReturn(category);

        var sut = categoryService.updateACategory(categoryReq, id, user);
        assertThat(sut.getName(), equalTo(categoryReq.getName()));
    }

    @Test
    void deleteCategoryShouldReturnACategoryDeleted() {
        Long id = 1L;
        var authUser = new User();
        authUser.setId(1L);
        Category category = createCategory();
        Mockito.when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.of(category));
        var sut = categoryService.deleteCategory(id, authUser) ;
        assertThat(sut.getName(), equalTo(category.getName()));
    }
}