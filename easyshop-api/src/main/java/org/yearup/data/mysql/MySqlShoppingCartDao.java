package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao
{

    @Autowired
    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }

    public ShoppingCart addCart(int userId, int productId)
    {

        String sql = """
                INSERT INTO shopping_cart(user_id, product_id,quantity)
                VALUES (?,?,?);
                """;
        try(Connection connection = getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        )
        {
            statement.setInt(1,userId);
            statement.setInt(2,productId);
            statement.setInt(3,1);
            statement.executeUpdate();
//            if(ResultSet.next())
//            {
//                int quantity = ResultSet.getInt("quantity")+1;
            //update(userId,productId)
//
//            }

        }
        catch (SQLException e)
        {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }
        return getByUserId(userId);
    }

    @Override
    public ShoppingCart getByUserId(int userId)
    {

        ShoppingCart shoppingCart = new ShoppingCart();
        // get cart by id:
        String sql = """
                SELECT user_id
                	,cart.product_id
                	,quantity
                    ,p.name
                FROM shopping_cart AS cart
                INNER JOIN products as p
                 ON p.product_id = cart.product_id
                """;

        try(Connection connection = getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        )
        {
            statement.setInt(1,userId);

            ResultSet row = statement.executeQuery();
            while(row.next())
            {
                int id = row.getInt("user_id");
                int prod_id = row.getInt("product_id");
                int quantity = row.getInt("quantity");
                String name = row.getString("name");
                // create a product
                Product product = new Product();


                // create a shoppingCartItem
                ShoppingCartItem item = new ShoppingCartItem();
                item.setProduct(product);
                item.setQuantity(quantity);
                // add it to the shoppingCart
                shoppingCart.add(item);
            }
        }
        catch (SQLException e)
        {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }
        return shoppingCart;

    }

    @Override
    public void update(int userId, ShoppingCart shoppingCart)
    {
        String sql = """
                UPDATE shopping_cart
                	SET product_id = 12
                    WHERE user_id = ?;
                """;

        try(Connection connection = getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
//            statement.setInt(1,shoppingCart.getUserId());
//            statement.setInt(2,shoppingCart.getProductId());
//            statement.setInt(3,shoppingCart.getQuantity());
//
//            statement.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }

    }

    @Override
    public void delete(int userId)
    {
        String sql = """
                DELETE FROM shopping_cart
                WHERE user_id = ?;
                """;
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        )
        {
            statement.setInt(1, userId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();

        }

    }


}
