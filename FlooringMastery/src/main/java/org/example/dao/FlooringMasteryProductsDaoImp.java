package org.example.dao;

import org.example.model.Products;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlooringMasteryProductsDaoImp implements FlooringMasteryProductsDao{


    private static final String PRODUCTS_FILE = "Products.txt";
    private static final String DELIMITER = ",";

    @Override
    public Products getProduct(String productType) throws DataPersistenceException {
        List<Products> products = loadProducts();
        if (products == null) {
            return null;
        } else {
            Products chosenProduct = products.stream()
                    .filter(p -> p.getProductType().equalsIgnoreCase(productType))
                    .findFirst().orElse(null);
            return chosenProduct;
        }
    }
    private List<Products> loadProducts() throws DataPersistenceException {
        Scanner scanner;
        List<Products> products = new ArrayList<>();

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCTS_FILE)));
        } catch (FileNotFoundException e) {
            throw new DataPersistenceException(
                    "-_- Could not load products data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;
        scanner.nextLine();//Skips scanning document headers
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            if (currentTokens.length == 3) {
                Products currentProduct = new Products();
                currentProduct.setProductType(currentTokens[0]);
                currentProduct.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
                currentProduct.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));
                // Put currentProduct into the map using productType as the key
                products.add(currentProduct);
            } else {
                //Ignores line if delimited wrong or empty.
            }
        }
        scanner.close();

        if (!products.isEmpty()) {
            return products;
        } else {
            return null;
        }
    }

}
