//package org.yearup.controllers;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//import org.yearup.data.*;
//import org.yearup.data.mysql.MySqlCategoryDao;
//import org.yearup.models.Category;
//import org.yearup.models.Product;
//import org.yearup.models.ShoppingCart;
//import org.yearup.models.User;
//
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
//@RestController
//public class ProfileController {
//    private ProfileDao profileDao;
//    private UserDao userDao;
//    private ProductDao productDao;
//
//
//
//    @Autowired
//    public ProfileController(ProfileDao profileDao) {
//      this.profileDao = profileDao;
//    }
//
//    // each method in this controller requires a Principal object as a parameter
//    @GetMapping(path="profile")
//    public profile getProfile(int userId)
//    {
//        try
//        {
//            // get the currently logged-in username
//            String userName = principal.getName();
//            // find database user by userId
//            User user = profileDao.getByUserId(userName);
//            int userId = user.getId();
//
//            // use the shoppingcartDao to get all items in the cart and return the cart
//            var profile = profileDao.getByUserId(userId);
//            if(profile == null)
//            {
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The profile:" + profile.toString() +"does not exist.");
//
//            }
//            return profile;
//        }
//        catch(Exception e)
//        {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
//        }
//    }
//}
