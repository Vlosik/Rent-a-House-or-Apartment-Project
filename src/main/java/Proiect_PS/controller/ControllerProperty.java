package Proiect_PS.controller;

import Proiect_PS.dto.*;
import Proiect_PS.model.Property;
import Proiect_PS.service.ServiceProperty;
import Proiect_PS.service.ServicePropertyInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/property")
public class ControllerProperty {

    private ServicePropertyInterface serviceProperty;

    public ControllerProperty(ServiceProperty propertyService){
        this.serviceProperty = propertyService;
    }
    /**
     * Adaugă o nouă proprietate în sistem.
     * Această metodă primește date despre o nouă proprietate prin metoda HTTP POST
     * și o adaugă în sistem folosind serviciul de proprietăți.
     *
     * @param propertyData Datele proprietății ce urmează a fi adăugată, incluzând titlu, descriere,
     *                     preț, tip, și alte informații relevante.
     */
    @PostMapping("/insert")
    public void addProperty(@RequestBody PropertyData propertyData){
        this.serviceProperty.insertProperty(propertyData);
    }
    /**
     * Șterge o proprietate din sistem.
     * Utilizează metoda HTTP DELETE pentru a elimina o proprietate specificată prin titlu.
     *
     * @param propertyTitleData Datele necesare pentru identificarea proprietății de șters,
     *                          incluzând titlul acesteia.
     */
    @DeleteMapping ("/delete")
    public void deleteProperty(@RequestBody PropertyTitleData propertyTitleData){
        this.serviceProperty.deleteProperty(propertyTitleData);
    }
    /**
     * Actualizează o proprietate existentă în sistem.
     * Această metodă folosește metoda HTTP PUT pentru a actualiza detaliile unei proprietăți
     * existente, bazându-se pe titlul acesteia.
     *
     * @param propertyData Datele actualizate ale proprietății, incluzând titlu, descriere,
     *                     preț, și alte detalii relevante.
     */
    @PutMapping ("/update")
    public void updateProperty(@RequestBody PropertyData propertyData){
        this.serviceProperty.updateProperty(propertyData);
    }
    /**
     * Caută proprietăți bazate pe titlu, preț și locație.
     * Utilizează metoda HTTP GET pentru a returna o listă de proprietăți care corespund
     * criteriilor specificate.
     *
     * @param propertyTrioData Datele pentru căutare, incluzând titlu, preț și locație.
     * @return Lista de proprietăți care corespund criteriilor de căutare.
     */
    @GetMapping("/findByTitlePriceAndLocation")
    public List<Property> findByTitlePriceAndLocation(@RequestBody PropertyTrioData propertyTrioData){
        return this.serviceProperty.findByTitlePriceAndLocation(propertyTrioData);
    }
    /**
     * Caută o proprietate specifică bazată pe titlul său.
     * Această metodă utilizează metoda HTTP GET pentru a găsi și returna o proprietate
     * care corespunde titlului dat.
     *
     * @param propertytitleData Datele necesare pentru căutarea proprietății, incluzând titlul acesteia.
     * @return Proprietatea care corespunde titlului specificat.
     */
    @GetMapping("/findByTitle")
    public Property findByTitle(@RequestBody PropertyTitleData propertytitleData){
        return this.serviceProperty.findByTitle(propertytitleData.getTitle());
    }
    /**
     * Returnează o listă cu toate proprietățile din sistem.
     * Această metodă folosește metoda HTTP GET pentru a recupera toate proprietățile înregistrate.
     *
     * @return Lista tuturor proprietăților din sistem.
     */
    @GetMapping("/findAll")
    public List<Property> findAllProperty(){
        return this.serviceProperty.findAll();
    }
    /**
     * Returnează o listă de proprietăți disponibile pentru închiriere.
     * Utilizează metoda HTTP GET pentru a identifica și returna proprietăți care sunt marcate ca disponibile.
     *
     * @return Lista proprietăților disponibile pentru închiriere.
     */
    @GetMapping("/findAvailables")
    public List<Property> findAvailables(){
        return this.serviceProperty.findAvailables();
    }
}
