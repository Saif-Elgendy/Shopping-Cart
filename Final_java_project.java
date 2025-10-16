/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.final_java_project;

/**
 *
 * @author aminz
 */
import java.io.*;
import java.util.*;

public class Final_java_project {

    public static void main(String[] args) {
        ShopManager shopManager = new ShopManager();
        Cart cart = new Cart();
        Scanner scanner = new Scanner(System.in);

        // Add products to the catalog
        shopManager.addProduct(new Product(1, "Laptop", 1200, 10));
        shopManager.addProduct(new Product(2, "Phone", 800, 15));
        shopManager.addProduct(new Product(3, "Tablet", 400, 20));

        while (true) {
            System.out.println("\n1. View Catalog");
            System.out.println("2. Add to Cart");
            System.out.println("3. Remove from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Save Cart to File");
            System.out.println("6. Load Cart from File");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    shopManager.showCatalog();
                    break;
                case 2:
                    System.out.print("Enter product ID: ");
                    int productId = scanner.nextInt();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    Product product = shopManager.getProduct(productId);
                    if (product != null && product.getStock() >= quantity) {
                        cart.addItem(product, quantity);
                        product.setStock(product.getStock() - quantity);
                    } else {
                        System.out.println("Invalid product ID or insufficient stock.");
                    }
                    break;
                case 3:
                    System.out.print("Enter product ID to remove: ");
                    int removeId = scanner.nextInt();
                    cart.removeItem(removeId);
                    break;
                case 4:
                    System.out.println("Cart Items:");
                    for (CartItem item : cart.getItems()) {
                        System.out.println(item);
                    }
                    System.out.println("Total: $" + cart.calculateTotal());
                    break;
                case 5:
                    saveCartToFile(cart, "cart.txt");
                    break;
                case 6:
                    loadCartFromFile(cart, "cart.txt");
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void saveCartToFile(Cart cart, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (CartItem item : cart.getItems()) {
                writer.write(item.getProduct().getId() + "," + item.getQuantity());
                writer.newLine();
            }
            System.out.println("Cart saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving cart: " + e.getMessage());
        }
    }

    private static void loadCartFromFile(Cart cart, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int productId = Integer.parseInt(parts[0]);
                int quantity = Integer.parseInt(parts[1]);
                // Product lookup logic can be enhanced
                cart.addItem(new Product(productId, "Loaded Product", 0, 0), quantity);
            }
            System.out.println("Cart loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading cart: " + e.getMessage());
        }
    }
}
