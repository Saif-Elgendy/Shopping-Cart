/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_java_project;

/**
 *
 * @author aminz
 */
import java.util.*;

public class ShopManager {

    private Map<Integer, Product> catalog = new HashMap<>();

    public void addProduct(Product product) {
        catalog.put(product.getId(), product);
    }

    public Product getProduct(int productId) {
        return catalog.get(productId);
    }

    public void showCatalog() {
        System.out.println("Product Catalog:");
        for (Product product : catalog.values()) {
            System.out.println(product);
        }
    }
}
