package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    @Autowired
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories()
    {
        List<Category> categories = new ArrayList<>();

        String sql = "SELECT * FROM categories";

        try(Connection connection = getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        )
        {
            ResultSet row = statement.executeQuery();

            while(row.next())
            {
                int categoryId = row.getInt("category_id");
                String name = row.getString("name");
                String description = row.getString("description");

                Category category = new Category()
                {{
                    setCategoryId(categoryId);
                    setName(name);
                    setDescription(description);
                }};

                categories.add(category);
            }
        }
        catch (SQLException e)
        {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }

        return categories;
    }

    @Override
    public Category getById(int categoryId)
    {
        // get category by id
        String sql = """
                SELECT category_id
                      	,name
                      FROM categories
                      WHERE category_id = ?;
                """;
        try(Connection connection = getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        )
        {
            statement.setInt(1,categoryId);

            ResultSet row = statement.executeQuery();
            while(row.next())
            {
                int id = row.getInt("category_id");
                String name = row.getString("name");
                Category category = new Category()
                {{
                    setCategoryId(categoryId);
                    setName(name);
                }};
                return category;
            }

        }catch (SQLException e)
        {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }
        return null;
    }

    @Override
    public Category create(Category category)
    {
        // create a new category
        String sql = """
                INSERT INTO categories(category_id,name,description)
                VALUES(?,?,?);
                """;
        try(Connection connection = getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        )
        {
            statement.setInt(1,category.getCategoryId());
            statement.setString(2,category.getName());
            statement.setString(3,category.getDescription());

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }

        return null;
    }

    @Override
    public void update(int categoryId, Category category)
    {
        // update category
        String sql = """
                UPDATE Categories
                SET name = ?
                WHERE category_id = ?;
                """;

        try(Connection connection = getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
           statement.setString(1,category.getName());
            statement.setInt(2, categoryId);

            statement.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    @Override
    public void delete(int categoryId)
    {
        String sql = """
                DELETE FROM categories
                WHERE category_id = ?;
                """;

        try (Connection connection = getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        )
        {
            statement.setInt(1, categoryId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();

        }
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}
