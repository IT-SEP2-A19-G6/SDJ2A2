package manager.network;

import Shared.BurgerBar;
import Shared.Burger;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI {
    private BurgerBar burgerBar;


    public ClientRMI() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        burgerBar = (BurgerBar) registry.lookup("burgerServer");
        System.out.println("Client is connected");
    }

    public void addBurgerToQueue(Burger burger){
        try {
            burgerBar.produceBurger(burger);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void consumeBurgerFromQueue(){
        try {
            burgerBar.consumeBurger();
            System.out.println("Client has consumed a burger...");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }



}
