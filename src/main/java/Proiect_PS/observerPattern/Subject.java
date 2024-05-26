package Proiect_PS.observerPattern;

import Proiect_PS.model.Property;

/**
 * Interfața Subject definește operațiile disponibile pentru subiectul observat în cadrul design pattern-ului Observer.
 */
public interface Subject {
    /**
     * Adaugă un observator la lista de observatori a acestui subiect.
     * @param o observatorul care va fi adăugat
     */
    void addObserver(Observer o);
    /**
     * Elimină un observator din lista de observatori a acestui subiect.
     * @param o observatorul care va fi eliminat
     */
    void removeObserver(Observer o);
    /**
     * Notifică toți observatorii cu un mesaj specific.
     * @param message mesajul care va fi trimis observatorilor
     * @param o observatorul căruia nu trebuie să i se trimită mesajul (poate fi null)
     */
    void notifyObservers(String message, Observer o, Property property);
}
