package server.network;

import Shared.BurgerBar;
import Shared.BurgerType;
import server.model.burger.BurgerQueue;
import client.domain.Burger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerRMI implements BurgerBar {
    BurgerQueue burgerQueue;

    public ServerRMI(BurgerQueue burgerQueue) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.burgerQueue = burgerQueue;
    }

    @Override
    public String produceBurger(Burger burger) {
        System.out.println("Chef has produced  a new "  + burger.toString()); //TODO handle incoming burger from chef
        return "Burger delivered";
    }

    @Override
    public Burger consumeBurger() throws RemoteException {
        System.out.println("A customer is served");
        return new Burger(BurgerType.CHEESE.name().toLowerCase());
    }

}
