package Proiect_PS.dto;

import java.util.Date;

public class RentalData {
    private String username_user;
    private String property_title;
    private Date startDate;
    private Date endDate;

    public String getUsername_user() {
        return username_user;
    }

    public void setUsername_user(String username_user) {
        this.username_user = username_user;
    }

    public String getProperty_title() {
        return property_title;
    }

    public void setProperty_title(String property_title) {
        this.property_title = property_title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
