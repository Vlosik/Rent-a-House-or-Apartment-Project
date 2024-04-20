package Proiect_PS.service;


import Proiect_PS.dto.PropertyData;
import Proiect_PS.dto.PropertyTitleData;
import Proiect_PS.dto.PropertyTrioData;
import Proiect_PS.model.Property;

import java.util.List;
/**
 * Interfața ServicePropertyInterface definește operațiunile necesare pentru gestionarea proprietăților
 * în cadrul unui sistem de închiriere.
 */
public interface ServicePropertyInterface {
    /**
     * Inserează o nouă proprietate în sistem bazată pe datele furnizate.
     *
     * @param propertyData Datele noii proprietăți.
     */
    void insertProperty(PropertyData propertyData);
    /**
     * Șterge o proprietate din sistem bazată pe titlul acesteia.
     *
     * @param propertyData Datele necesare pentru identificarea proprietății de șters.
     */
    void deleteProperty(PropertyTitleData propertyData);
    /**
     * Actualizează detaliile unei proprietăți existente în sistem.
     *
     * @param propertyData Datele actualizate ale proprietății.
     */
    void updateProperty(PropertyData propertyData);
    /**
     * Caută proprietăți bazându-se pe titlu, preț și locație.
     *
     * @param propertyTrioData Datele necesare pentru căutare.
     * @return Lista de proprietăți care corespund criteriilor de căutare.
     */
    List<Property> findByTitlePriceAndLocation(PropertyTrioData propertyTrioData);
    /**
     * Returnează toate proprietățile din sistem.
     *
     * @return Lista tuturor proprietăților.
     */
    List<Property> findAll();
    /**
     * Caută o proprietate specifică bazându-se pe titlul acesteia.
     *
     * @param title Titlul proprietății căutate.
     * @return Proprietatea care corespunde titlului.
     */
    Property findByTitle(String title);
    /**
     * Returnează proprietățile disponibile pentru închiriere.
     *
     * @return Lista de proprietăți disponibile.
     */
    List<Property> findAvailables();
}
