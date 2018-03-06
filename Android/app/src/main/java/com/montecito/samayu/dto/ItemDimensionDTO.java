package com.montecito.samayu.dto;

/**
 * Created by NandhiniGovindasamy on 3/6/18.
 */

public class ItemDimensionDTO {
   private int dia,length;
   private String uom;

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }
}
