package com.app.finalproject.services;


import com.app.finalproject.dtos.category.CategoryReq;
import com.app.finalproject.exceptions.NotFoundException;
import com.app.finalproject.models.Category;
import com.app.finalproject.models.User;
import com.app.finalproject.repositories.ICategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements  ICategoryService{

    private ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll(User auth) {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id, User auth) {
        var category = categoryRepository.findById(id);
        if(category.isEmpty()) throw new NotFoundException("Etiqueta no encontrada", "C-404");
        return category.get();
    }

    @Override
    public Category create(CategoryReq categoryReq, User authUser) {
        if(categoryRepository.findAll().stream().anyMatch(t-> t.getName().equals(categoryReq.getName()))){
            return categoryRepository.findAll().stream().filter(t-> t.getName().equals(categoryReq.getName())).findFirst().get();
        }
        return categoryRepository.save(new Category(categoryReq.getName()));
    }

    @Override
    public Category updateACategory(CategoryReq categoryReq, Long id, User authUser) {
        var category = categoryRepository.findById(id);

        if(category.isEmpty()) throw new NotFoundException("Categoría no encontrada", "C-404");

        category.get().setName(categoryReq.getName());

        categoryRepository.save(category.get());

        return category.get();

    }

    @Override
    public Category deleteCategory(Long id, User authUser) {
        Category category = this.categoryRepository.findById(id).get();

        this.categoryRepository.delete(category);
        return category;
    }
}
