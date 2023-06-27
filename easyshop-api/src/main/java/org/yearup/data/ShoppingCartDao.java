package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);

    ShoppingCart addCart(ShoppingCart shoppingCart);
    ShoppingCart update(int userId,ShoppingCart shoppingCart);
    ShoppingCart delete(int userId);
    // add additional method signatures here
}
