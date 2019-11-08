package server.network;

import Shared.*;
import server.model.burgerbar.BurgerBarStatus;
import server.model.burgerqueue.BurgerQueue;


import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerRMI implements Manager, Producer, Consumer {
    private BurgerQueue burgerQueue;
    private BurgerBarStatus burgerBarStatus;
    private ArrayList<ReplyTo> clients;

    public ServerRMI(BurgerQueue burgerQueue, BurgerBarStatus burgerBarStatus) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.burgerQueue = burgerQueue;
        this.burgerBarStatus = burgerBarStatus;
        clients = new ArrayList<>();
        burgerBarStatus.addPropertyListener("ChangedStatus", this::announceStatus);
    }

    private void announceStatus(PropertyChangeEvent propertyChangeEvent) {
        String event = propertyChangeEvent.getNewValue().toString();
        if (event.equals("Open")){
            for (ReplyTo client : clients){
                try {
                    client.burgerBarInBusiness(true);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        } else {
            for (ReplyTo client : clients){
                try {
                    client.burgerBarInBusiness(false);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void regProducer(ReplyTo producer) throws RemoteException {
        clients.add(producer);
    }

    @Override
    public void produceBurger(Burger burger) throws RemoteException {
        burgerQueue.addBurger(burger);
    }

    @Override
    public void regConsumer(ReplyTo consumer) throws RemoteException {
        clients.add(consumer);
    }

    @Override
    public String getBurgerBarStatus() {
        return burgerBarStatus.getBurgerBarStatus();
    }

    @Override
    public Burger consumeBurger() throws RemoteException {
        return burgerQueue.removeBurger();
    }

    @Override
    public void burgerBarStatus(String status) throws RemoteException {
        burgerBarStatus.setBurgerBarStatus(status);
    }
}
