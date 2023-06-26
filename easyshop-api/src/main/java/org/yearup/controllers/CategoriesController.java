package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.data.mysql.MySqlCategoryDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

// add the annotations to make this a REST controller
// add the annotation to make this controller the endpoint for the following url
    // http://localhost:8080/categories
// add annotation to allow cross site origin requests
@RestController
public class CategoriesController
{
    private CategoryDao categoryDao;
    private ProductDao productDao;

    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao)
    {
        this.categoryDao = categoryDao;
        this.productDao = productDao;

    }
    @GetMapping(path = "categories")
    public List<Category> getAll()
    {
        return categoryDao.getAllCategories();
    }

    // add the appropriate annotation for a get action
    @GetMapping(path = "categories/{id}")
    public Category getById(@PathVariable int id)
    {
        // get the category by id
        var category = categoryDao.getById(id);
        if(category == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The id: " +id + "does not exist.");
        }
        return category;
    }

    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products
    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        // get a list of product by categoryId
        List<Product>products = new ArrayList<>();
        List<Product> allProducts = productDao.listByCategoryId(categoryId);

        return productDao.listByCategoryId(categoryId);
    }

    // add annotation to call this method for a POST action
    // add annotation to ensure that only an ADMIN can call this function
    public Category addCategory(@RequestBody Category category)
    {
        // insert the category

        return null;
    }

    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(path = "categories/{id}")
    public void updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        // update the category by id
        var currentCategory =  categoryDao.getById(id);

        if(currentCategory == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "That product does not exist.");
        }
        try
        {
            categoryDao.update(id, category);
        }
        catch(Exception ex)
        {}
    }


    // add annotation to call this method for a DELETE action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    public void deleteCategory(@PathVariable int id)
    {
        // delete the category by id
    }
}
