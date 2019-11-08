package client;

import Shared.BurgerType;
import client.domain.Burger;
import client.mediator.Producer;
import client.network.ClientRMI;
import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RunChef {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        ClientRMI clientRMI = new ClientRMI();
        Producer producer = new Producer(clientRMI, "Burgerbar/src/client/mediator/recipes.txt");
        producer.produceBurgers();
    }

}
