package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String connectionString = "jdbc:mysql://localhost:3306/JDBC?serverTimezone=UTC";

        try (Connection connection = DriverManager.getConnection(
                connectionString,
                System.getenv("DB_USERNAME"),
                System.getenv("DB_PASSWORD"))) {

            ProductDAO productDao = new ProductDAO(connection);

            //find all products
            /*for (var item : productDao.findAll()) {
                System.out.println(item);
            }*/

            //find product by id
            //System.out.println(productDao.findEntityById(2));

            //delete product by id
            //System.out.println(productDao.delete(2));

            //insert product
            /*Product newProduct = new Product("MacBook", 3759.05, 1);
            System.out.println(productDao.create(newProduct));*/

            //update product
            /*Product productToUpdate = new Product("MacBook", 3796.50, 1);
            productToUpdate.setId(4);
            System.out.println(productDao.update(productToUpdate));*/

            //find product with the max price
            //System.out.println(productDao.findMaxPrice());

            //find products by brand
            /*for (var item : productDao.findProductsByBrand("Apple")) {
                System.out.println(item);
            }*/

            // find products in the price range
            /*for (var item : productDao.getAllProductsInPriceRange(1000.00, 2500.00)) {
                System.out.println(item);
            }*/

            // find products with a brand
            /*for (var item : productDao.findProductsWithABrand()) {
                System.out.println(item);
            }*/
        }
    }
}
