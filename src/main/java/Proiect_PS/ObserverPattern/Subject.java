package Proiect_PS.ObserverPattern;

public interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(String message,Observer o);
}
