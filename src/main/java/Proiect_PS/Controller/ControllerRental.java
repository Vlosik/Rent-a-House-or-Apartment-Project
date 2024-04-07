package Proiect_PS.Controller;

import Proiect_PS.DTO.RentalData;
import Proiect_PS.Model.Rental;
import Proiect_PS.Service.ServiceRental;
import Proiect_PS.Service.ServiceRentalInterface;
import Proiect_PS.Service.ServiceRental;
import Proiect_PS.Service.ServiceRentalInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class ControllerRental {
    private ServiceRentalInterface serviceRental;

    public ControllerRental(ServiceRental RentalRental){
        this.serviceRental = RentalRental;
    }

    @PostMapping("/insert")
    public void addRental(@RequestBody RentalData RentalData){
        this.serviceRental.insertRental(RentalData);
    }


    @DeleteMapping("/deleteAll")
    public void deleteAllRental(){
        this.serviceRental.deleteAllRental();
    }

    @PutMapping ("/update")
    public void updateRental(@RequestBody RentalData RentalData){
        this.serviceRental.updateRental(RentalData);
    }

    @GetMapping("/findAll")
    public List<Rental> findByTitlePriceAndLocation(){
        return this.serviceRental.findAll();
    }
}
