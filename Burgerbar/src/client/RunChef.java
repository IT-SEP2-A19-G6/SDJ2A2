package client;

import client.mediator.Producer;
import client.network.ClientRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RunChef {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        ClientRMI clientRMI = new ClientRMI();
        Producer producer = new Producer(clientRMI, "Burgerbar/src/client/mediator/recipes.txt");
        producer.produceBurgers();
    }

}
