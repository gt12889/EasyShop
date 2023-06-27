package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);

    ShoppingCart addCart(ShoppingCart shoppingCart);
    void update(int productId,ShoppingCart shoppingCart);
    void delete(int userId);
    // add additional method signatures here
}
