package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.User;

import java.security.Principal;

// convert this class to a REST controller
// only logged in users should have access to these actions
@RestController
public class ShoppingCartController
{
    // a shopping cart requires
    private ShoppingCartDao shoppingCartDao;
    private UserDao userDao;
    private ProductDao productDao;

    @Autowired
    public ShoppingCartController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }

    // each method in this controller requires a Principal object as a parameter
    @GetMapping(path="shoppingCart")
    public ShoppingCart getCart(Principal principal)
    {
        try
        {
            // get the currently logged-in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            // use the shoppingcartDao to get all items in the cart and return the cart
            var shoppingCart = shoppingCartDao.getByUserId(userId);
            if(shoppingCart == null)
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The user_id:" + userId +"does not exist.");

            }
            return shoppingCart;
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    // add a POST method to add a product to the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be added
//    @PostMapping("/shoppingCart")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ShoppingCart addToCart(@PathVariable("productId") int productId, @RequestBody ShoppingCart shoppingCart)
//    {
//        if(productId == 0)
//        {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The product:" +productId + "does not exist.");
//        }
////        ShoppingCart createdInt = shoppingCartDao.addCart(productId);
//        Product product = productDao.getById(productId);
//        ShoppingCart updatedCart = shoppingCartDao.addCart(shoppingCart);
//        //shoppingCart.addProduct(productId);
//        return updatedCart;
//
//    }

    @PostMapping("/cart/products/{productId}")
    @PreAuthorize("isAuthenticated()")
    public ShoppingCart addToCart(Principal principal, @PathVariable int productId)
    {
        String userName = principal.getName();
        User user = userDao.getByUserName(userName);
        int userId = user.getId();

        return shoppingCartDao.addCart(userId,productId);

    }


    // add a PUT method to update an existing product in the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be updated)
    // the BODY should be a ShoppingCartItem - quantity is the only value that will be updated
    @PutMapping("/cart/products/{productId}")
    @PreAuthorize("isAuthenticated()")
    public void updateByQuantity(Principal principal,@PathVariable int productId,@RequestBody int quantity)
    {
        String userName = principal.getName();
        User user = userDao.getByUserName(userName);
        int userId = user.getId();
        shoppingCartDao.update(productId,);
    }



    // add a DELETE method to clear all products from the current users cart
    // https://localhost:8080/cart
    @DeleteMapping("/cart")
    public void delete(Principal principal)
    {
        String userName = principal.getName();
        User user = userDao.getByUserName(userName);
        int userId = user.getId();
        shoppingCartDao.delete(userId);
    }


}
