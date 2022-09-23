package com.app.finalproject.services.categoryS;

import com.app.finalproject.dtos.category.CategoryReq;
import com.app.finalproject.models.Category;
import com.app.finalproject.models.User;

import java.util.List;

public interface ICategoryService {

    List<Category> getAll(User auth);

    Category findById(Long id, User auth);

    Category create(CategoryReq categoryReq, User authUser);


    Category updateACategory(CategoryReq categoryReq, Long id, User authUser);

    Category deleteCategory(Long id, User authUser);


}
