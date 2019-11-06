package Shared;

import client.domain.Burger;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BurgerBar extends Remote {
    void produceBurger(Burger burger) throws RemoteException;
    Burger consumeBurger() throws RemoteException;
}
