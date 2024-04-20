package Proiect_PS.DTO;

import Proiect_PS.Model.PropertyType;

import java.math.BigDecimal;

public class PropertyTrioData {
    private String title;
    private BigDecimal price;
    private String location;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
