package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
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
        return null;
    }

    @Override
    public Category create(Category category)
    {
        // create a new category
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
            statement.setInt(5, categoryId);

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
