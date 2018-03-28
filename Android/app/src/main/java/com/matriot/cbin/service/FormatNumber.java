package com.matriot.cbin.service;

import java.text.NumberFormat;

/**
 * Created by NandhiniGovindasamy on 2/7/18.
 */

public class FormatNumber {


    private static NumberFormat numberFormat=NumberFormat.getNumberInstance();

    public static NumberFormat getNumberFormat() {

        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);

        return numberFormat;
    }
}