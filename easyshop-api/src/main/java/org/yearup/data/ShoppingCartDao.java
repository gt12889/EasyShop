package org.yearup.data;

import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);

    ShoppingCart addCart(int userID, int productID);

    void update(int productId,  ShoppingCartItem shoppingCartItem);
    void delete(int userId);
    // add additional method signatures here
}
