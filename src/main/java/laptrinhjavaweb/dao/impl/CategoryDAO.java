package laptrinhjavaweb.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import laptrinhjavaweb.dao.ICategoryDAO;
import laptrinhjavaweb.model.CategoryNewModel;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAO extends AbtractDAO implements ICategoryDAO {

    @Override
    public List<CategoryNewModel> findAll() {
        List<CategoryNewModel> results = new ArrayList<>();
        String sql = "select * from newcategory";
        Connection con = getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                statement = con.prepareStatement(sql);
                rs = statement.executeQuery();
                while (rs.next()) {
                    CategoryNewModel category = new CategoryNewModel();
                    category.setId(rs.getLong("id"));
                    category.setCode(rs.getString("code"));
                    category.setName(rs.getString("name"));
                    results.add(category);
                }

                return results;
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public CategoryNewModel findOne(long id) {
        String sql = "SELECT * FROM newcategory WHERE id = ?";
        Connection con = getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = con.prepareStatement(sql);
            statement.setLong(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                CategoryNewModel category = new CategoryNewModel();
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));
                category.setCode(rs.getString("code"));
                category.setCreatedDate(rs.getTimestamp("createddate"));
                category.setCreatedBy(rs.getString("createdby"));
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CategoryNewModel findOneByCode(String code) {
        String sql = "SELECT * FROM newcategory WHERE code = ?";
        Connection con = getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, code);
            rs = statement.executeQuery();

            if (rs.next()) {
                CategoryNewModel category = new CategoryNewModel();
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));
                category.setCode(rs.getString("code"));
                category.setCreatedDate(rs.getTimestamp("createddate"));
                category.setCreatedBy(rs.getString("createdby"));
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}