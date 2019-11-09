package client.chef.network;

import client.chef.model.ChefModel;
import shared.Burger;
import shared.Producer;
import shared.ReplyTo;
import shared.sout;

import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ClientRMI implements ReplyTo {
    private Producer producer;
    private boolean burgerBarOpen;
    private ChefModel chefModel;

    public ClientRMI(ChefModel chef) throws RemoteException, NotBoundException {
        UnicastRemoteObject.exportObject(this, 0);
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        producer = (Producer) registry.lookup("burgerServer");
        this.chefModel = chef;
        chefModel.addPropertyListener("addBurger", this::addBurgerToQueue);
        sout.write(this,"Client is connected");
        sendSelfToServer();
        burgerBarOpen = producer.getBurgerBarStatus();
        if (burgerBarOpen){
            burgerBarOpen();
        }
    }

    private void addBurgerToQueue(PropertyChangeEvent propertyChangeEvent) {
        try {
            producer.produceBurger((Burger) propertyChangeEvent.getNewValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendSelfToServer() throws RemoteException {
        producer.regProducer(this);
    }


    @Override
    public void burgerBarOpen() {

        sout.write(this, "Bar has been announced opened. Chefs going to start working...");
        chefModel.produceBurgers();


    }

    @Override
    public void burgerBarClosed() {
        sout.write(this, "Bar has been announced closed. Chefs going home...");
        chefModel.goHome();


    }
}
