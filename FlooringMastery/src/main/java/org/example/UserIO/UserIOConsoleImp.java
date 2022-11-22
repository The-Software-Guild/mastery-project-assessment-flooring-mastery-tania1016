package org.example.UserIO;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserIOConsoleImp implements UserIO{
    Scanner sc;

    public UserIOConsoleImp()
    {
        sc = new Scanner(System.in);
    }

    public void print(String message)
    {
        System.out.println(message);
    }

    public String readString(String prompt)
    {
        System.out.println(prompt);
        return sc.nextLine();
    }

    public int readInt(String prompt)
    {
        System.out.println(prompt);
        return Integer.parseInt(sc.nextLine());
    }

    public int readInt(String prompt, int min, int max)
    {
        int num;
        do {
            System.out.println(prompt);
            num = Integer.parseInt(sc.nextLine());
        }
        while(num < min || num > max);

        return num;
    }

    public double readDouble(String prompt)
    {
        System.out.println(prompt);
        return Double.parseDouble(sc.nextLine());
    }

    public double readDouble(String prompt, double min, double max)
    {
        double num;
        do {
            System.out.println(prompt);
            num = sc.nextInt();
        }
        while(num < min || num > max);

        return num;
    }

    public float readFloat(String prompt)
    {
        System.out.println(prompt);
        return sc.nextFloat();
    }

    public float readFloat(String prompt, float min, float max)
    {
        float num;
        do {
            System.out.println(prompt);
            num = sc.nextFloat();
        }
        while(num < min || num > max);

        return num;
    }

    public long readLong(String prompt)
    {
        System.out.println(prompt);
        return sc.nextLong();
    }

    public long readLong(String prompt, long min, long max)
    {
        long num;
        do {
            System.out.println(prompt);
            num = sc.nextLong();
        }
        while(num < min || num > max);

        return num;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        System.out.println(prompt);
        return new BigDecimal(sc.nextLine());
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal num;
        do {
            System.out.println(prompt);
            num = new BigDecimal(sc.nextLine());
        }
        while(num.doubleValue() < min.doubleValue() || num.doubleValue() > max.doubleValue() || num==null);

        return num;
    }

    @Override
    public LocalDate readDate(String prompt) {
        boolean valid = false;
        LocalDate result = null;
        do {
            String value = null;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                value = readString(prompt);
                result = LocalDate.parse(value, formatter);
                valid = true;
            } catch (DateTimeParseException ex) {
                System.out.printf("The value '%s' is not a valid date. (MM-DD-YYYY)\n", value);
            }
        } while (!valid);

        return result;
    }

    @Override
    public LocalDate readDate(String prompt, LocalDate min, LocalDate max) {
        boolean valid = false;
        LocalDate result = null;
        do {
            result = readDate(prompt);
            if (result.isAfter(min) && result.isBefore(max)) {
                valid = true;
            } else {
                System.out.printf("Please choose a date within bounds. (%s to %s)\n", min, max);
            }
        } while (!valid);

        return result;
    }
    @Override
    public String formatCurrency(BigDecimal amount) {

        return NumberFormat.getCurrencyInstance().format(amount);
    }

}

