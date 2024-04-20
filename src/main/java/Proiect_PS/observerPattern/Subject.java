package Proiect_PS.observerPattern;

public interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(String message,Observer o);
}
