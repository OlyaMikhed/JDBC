package com.company;

public class Brand extends Entity {

    private String brandName;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String name) {
        brandName = brandName;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brandName='" + brandName + '\'' +
                '}';
    }
}
