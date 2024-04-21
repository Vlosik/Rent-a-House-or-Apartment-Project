package Proiect_PS.observerPattern;
/**
 * Interfața Observer este utilizată în cadrul design pattern-ului Observer pentru a notifica obiectele observatoare
 * despre modificările survenite în subiectul observat.
 */
public interface Observer {
    /**
     * Metoda update este apelată de către subiectul observat pentru a notifica obiectul observator despre o modificare.
     * @param message mesajul care conține informațiile despre modificare
     */
    void update(String message);
}
