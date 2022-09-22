package com.app.finalproject.controllers;

import com.app.finalproject.auth.facade.AuthenticationFacade;
import com.app.finalproject.dtos.category.CategoryReq;
import com.app.finalproject.models.Category;
import com.app.finalproject.models.User;
import com.app.finalproject.services.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class CategoryController {

    ICategoryService categoryService;
    AuthenticationFacade authenticationFacade;

    public CategoryController(ICategoryService categoryService, AuthenticationFacade authenticationFacade) {
        this.categoryService = categoryService;
        this.authenticationFacade = authenticationFacade;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/categorias")
    List<Category> getAll(){
        User auth = authenticationFacade.getAuthUser();
        return categoryService.getAll(auth);
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/categorias/{id}")
    Category getCategoryById(@PathVariable Long id){
        User auth = authenticationFacade.getAuthUser();
        var category = this.categoryService.findById(id, auth);
        return category;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/categorias")
    Category createCategory(@RequestBody CategoryReq categoryReq){
        var authUser = authenticationFacade.getAuthUser();
        return categoryService.create(categoryReq, authUser);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/categorias/{id}")
    Category updateCandidat(@PathVariable Long id, @RequestBody CategoryReq categoryReq) {
        User authUser = authenticationFacade.getAuthUser();
        return categoryService.updateACategory(categoryReq, id, authUser);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/categorias/{id}")
    ResponseEntity<Category> deleteCandidat(@PathVariable Long id) {
        User authUser = authenticationFacade.getAuthUser();
        var category =  categoryService.deleteCategory(id, authUser);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}
