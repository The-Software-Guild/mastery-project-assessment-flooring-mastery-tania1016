package org.example.UserIO;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface UserIO {

    /**
     * @author Tania
     */
    void print(String message);

    String readString(String prompt);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    BigDecimal readBigDecimal(String prompt);

    BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max);

    LocalDate readDate(String prompt);

    LocalDate readDate(String s, LocalDate of, LocalDate of1);

    String formatCurrency(BigDecimal total);
}

