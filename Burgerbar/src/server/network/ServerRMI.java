package server.network;

import Shared.BurgerBar;
import server.model.burger.BurgerQueue;
import chef.domain.Burger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerRMI implements BurgerBar {
    BurgerQueue burgerQueue;

    public ServerRMI(BurgerQueue burgerQueue) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.burgerQueue = burgerQueue;
    }

    @Override
    public void produceBurger(Burger burger) {

    }

    @Override
    public Burger consumeBurger() {
        return null;
    }
}
