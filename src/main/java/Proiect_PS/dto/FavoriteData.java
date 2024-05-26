package Proiect_PS.dto;

import Proiect_PS.model.Property;
import Proiect_PS.model.User;

public class FavoriteData {
    private String user;

    private String propertyTitle;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPropertyTitle() {
        return propertyTitle;
    }

    public void setPropertyTitle(String propertyTitle) {
        this.propertyTitle = propertyTitle;
    }
}
