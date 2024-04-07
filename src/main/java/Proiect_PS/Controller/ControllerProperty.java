package Proiect_PS.Controller;

import Proiect_PS.DTO.*;
import Proiect_PS.Model.Property;
import Proiect_PS.Model.User;
import Proiect_PS.Service.ServiceProperty;
import Proiect_PS.Service.ServicePropertyInterface;
import Proiect_PS.Service.ServiceUser;
import Proiect_PS.Service.ServiceUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/property")
public class ControllerProperty {

    private ServicePropertyInterface serviceProperty;

    public ControllerProperty(ServiceProperty propertyService){
        this.serviceProperty = propertyService;
    }

    @PostMapping("/insert")
    public void addUser(@RequestBody PropertyData propertyData){
        this.serviceProperty.insertProperty(propertyData);
    }


    @DeleteMapping ("/delete")
    public void deleteUser(@RequestBody PropertyTitleData propertyTitleData){
        this.serviceProperty.deleteProperty(propertyTitleData);
    }

    @PutMapping ("/update")
    public void updateUser(@RequestBody PropertyData propertyData){
        this.serviceProperty.updateUser(propertyData);
    }

    @GetMapping("/findByTitlePriceAndLocation")
    public List<Property> findByTitlePriceAndLocation(@RequestBody PropertyTrioData propertyTrioData){
        return this.serviceProperty.findByTitlePriceAndLocation(propertyTrioData);
    }
    @GetMapping("/findByTitle")
    public Property findByTitle(@RequestBody PropertyTitleData propertytitleData){
        return this.serviceProperty.findByTitle(propertytitleData.getTitle());
    }
    @GetMapping("/findAll")
    public List<Property> findAllProperty(){
        return this.serviceProperty.findAll();
    }
    @GetMapping("/findAvailables")
    public List<Property> findAvailables(){
        return this.serviceProperty.findAvailables();
    }
}
