package client;

import Shared.BurgerType;
import client.domain.Burger;
import client.network.ClientRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RunCustomer {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        ClientRMI chefRMI = new ClientRMI();

        for (int i = 0; i < 25; i++) {
            chefRMI.consumeBurgerFromQueue();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
