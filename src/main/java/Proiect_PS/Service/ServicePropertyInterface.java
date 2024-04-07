package Proiect_PS.Service;


import Proiect_PS.DTO.PropertyData;
import Proiect_PS.DTO.PropertyTitleData;
import Proiect_PS.DTO.PropertyTrioData;
import Proiect_PS.Model.Property;

import java.util.List;

public interface ServicePropertyInterface {
    void insertProperty(PropertyData propertyData);

    void deleteProperty(PropertyTitleData propertyData);
    void updateProperty(PropertyData propertyData);
    List<Property> findByTitlePriceAndLocation(PropertyTrioData propertyTrioData);
    List<Property> findAll();
    Property findByTitle(String title);
    List<Property> findAvailables();
}
