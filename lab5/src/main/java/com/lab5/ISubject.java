package com.lab5;

public interface ISubject {

    public void addObserver(IObserver obs);
    public void removeObserver(IObserver obs);
    public void notifyObservers();
}
