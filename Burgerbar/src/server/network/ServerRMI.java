package server.network;

import Shared.BurgerBar;
import Shared.BurgerType;
import client.domain.Burger;
import server.model.burger.BurgerQueue;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerRMI implements BurgerBar {
    BurgerQueue burgerQueue;
    public ServerRMI(BurgerQueue burgerQueue) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.burgerQueue= burgerQueue;

    }

    @Override
    public void produceBurger(Burger burger) throws RemoteException {
        burgerQueue.addBurger(burger);
        System.out.println("Chef has produced  a new "  + burger.toString()); //TODO handle incoming burger from chef

    }

    @Override
    public Burger consumeBurger() throws RemoteException {
        System.out.println("A customer is served");
        return burgerQueue.removeBurger();
    }


}
