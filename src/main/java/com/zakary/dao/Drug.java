package com.zakary.dao;

public class Drug {
    private Integer drugid;
    private String drugname;
    private double drugprice;

    public Integer getDrugid() {
        return drugid;
    }

    public void setDrugid(Integer drugid) {
        this.drugid = drugid;
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public double getDrugprice() {
        return drugprice;
    }

    public void setDrugprice(double drugprice) {
        this.drugprice = drugprice;
    }
}
