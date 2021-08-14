package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends AbstractDAO<Integer, Product> {

    private final Connection connection;

    private final static String SELECT_ALL_PRODUCTS = "select * from Product";
    private final static String FIND_BY_ID = "select * from Product where id = ?";
    private final static String DELETE_BY_ID = "delete from Product where id = ?";
    private final static String INSERT_ENTITY = "insert into Product(title, costProduct, brand_id) values(?, ?, ?)";
    private final static String UPDATE_ENTITY
            = "update Product set title = ?, costProduct = ?, brand_id = ? where id = ?";
    private final static String SELECT_MAX_PRICE
            = "select * from Product where costProduct=(select MAX(costProduct) from Product)";
    private final static String SELECT_PRODUCTS_BY_BRAND
            = "select * from Product p JOIN Brand b ON p.brand_id=b.id where b.brandName = ?";
    private final static String ALL_PRODUCTS_IN_PRICE_RANGE =
            "select * from Product where costProduct between ? and ?";
    private final static String ALL_PRODUCTS_WITH_A_BRAND = "select * from Product where brand_id is not null";

    public ProductDAO(final Connection connection) {
        this.connection = connection;
    }

    public Product findMaxPrice() {
        Product result = new Product();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_MAX_PRICE)) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result.setId(rs.getInt("id"));
                result.setTitle(rs.getString("title"));
                result.setCost(rs.getDouble("costProduct"));
                result.setBrand_id(rs.getInt("brand_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Product> findProductsByBrand(String brandName) {
        List<Product> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCTS_BY_BRAND)) {
            statement.setString(1, brandName);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product model = new Product();
                model.setId(rs.getInt("id"));
                model.setTitle(rs.getString("title"));
                model.setCost(rs.getDouble("costProduct"));
                model.setBrand_id(rs.getInt("brand_id"));
                result.add(model);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Product> getAllProductsInPriceRange(Double price1, Double price2) {
        List<Product> result = new ArrayList<>();
        try (PreparedStatement statement = connection
                .prepareStatement(ALL_PRODUCTS_IN_PRICE_RANGE)) {
            statement.setDouble(1, price1);
            statement.setDouble(2, price2);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product model = new Product();
                model.setId(rs.getInt("id"));
                model.setTitle(rs.getString("title"));
                model.setCost(rs.getDouble("costProduct"));
                model.setBrand_id(rs.getInt("brand_id"));
                result.add(model);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Product> findProductsWithABrand() {
        List<Product> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(ALL_PRODUCTS_WITH_A_BRAND)) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product model = new Product();
                model.setId(rs.getInt("id"));
                model.setTitle(rs.getString("title"));
                model.setCost(rs.getDouble("costProduct"));
                model.setBrand_id(rs.getInt("brand_id"));
                result.add(model);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Product> findAll() {
        List<Product> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product model = new Product();
                model.setId(rs.getInt("id"));
                model.setTitle(rs.getString("title"));
                model.setCost(rs.getDouble("costProduct"));
                model.setBrand_id(rs.getInt("brand_id"));
                result.add(model);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Product findEntityById(Integer id) {
        Product result = new Product();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result.setId(rs.getInt("id"));
                result.setTitle(rs.getString("title"));
                result.setCost(rs.getDouble("costProduct"));
                result.setBrand_id(rs.getInt("brand_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            int rs = statement.executeUpdate();
            System.out.println("Completed successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean create(Product entity) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ENTITY)) {
            statement.setString(1, entity.getTitle());
            statement.setDouble(2, entity.getCost());
            statement.setInt(3, entity.getBrand_id());
            statement.setInt(4, entity.getId());
            int rs = statement.executeUpdate();
            System.out.println("Completed successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public Product update(Product entity) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ENTITY)) {
            statement.setString(1, entity.getTitle());
            statement.setDouble(2, entity.getCost());
            statement.setInt(3, entity.getBrand_id());
            statement.setInt(4, entity.getId());
            int rs = statement.executeUpdate();
            System.out.println("Completed successfully.");
            System.out.print(rs + " the number of columns affected by the query");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return entity;
    }
}
