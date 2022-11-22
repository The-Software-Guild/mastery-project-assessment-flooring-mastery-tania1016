package org.example.dao;

import org.example.model.Tax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlooringMasteryTaxDaoImp implements FlooringMasteryTaxDao {

    private static final String TAXES_TXT = "Taxes.txt";
    private static final String DELIMITER = ",";

    public List<Tax> loadTaxes() throws DataPersistenceException {
        Scanner scanner;
        List<Tax> taxes = new ArrayList<>();

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(TAXES_TXT)));
        } catch (FileNotFoundException e) {
            throw new DataPersistenceException(
                    "-_- Could not load states data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;
        scanner.nextLine();//Skips scanning document headers
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            if (currentTokens.length == 3) {
                Tax tax = new Tax();
                tax.setStateAbbreviation(currentTokens[0]);
                tax.setStateName(currentTokens[1]);
                tax.setTaxRate(new BigDecimal(currentTokens[2]));
                taxes.add(tax);
            } else {
                //Ignores line if delimited wrong or empty.
            }
        }
        scanner.close();

        if (!taxes.isEmpty()) {
            return taxes;
        } else {
            return null;
        }
    }

    @Override
    public Tax getTax(String state) throws DataPersistenceException {
        List<Tax> taxes = loadTaxes();
        if (taxes == null) {
            return null;
        } else {
            Tax chosenTax = taxes.stream()
                    .filter(s -> s.getStateAbbreviation().equalsIgnoreCase(state))
                    .findFirst().orElse(null);
            return chosenTax;
        }
    }
}
