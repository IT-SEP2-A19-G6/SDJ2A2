package client.network;

import Shared.BurgerBar;
import client.domain.Burger;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI {
    BurgerBar burgerBar;

    public ClientRMI() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        burgerBar = (BurgerBar) registry.lookup("burgerServer");
        System.out.println("Client is connected");
    }

    public void addBurgerToQueue(Burger burger){
        try {
            burgerBar.produceBurger(burger);
            System.out.println("The chef has produced a " + burger.toString() );
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void consumeBurgerFromQueue(){
        try {
            System.out.println("Client has consumed a " + burgerBar.consumeBurger().toString() );
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }



}