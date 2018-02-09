package com.montecito.samayu.service;

import java.text.NumberFormat;

/**
 * Created by NandhiniGovindasamy on 2/7/18.
 */

public class GlobalUsage {


    private static NumberFormat numberFormat=NumberFormat.getNumberInstance();

    public static NumberFormat getNumberFormat() {

        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);

        return numberFormat;
    }
}