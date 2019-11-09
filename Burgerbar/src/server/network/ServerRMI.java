package server.network;

import server.model.burgerbar.BurgerBarStatus;
import server.model.burgerqueue.BurgerQueue;
import shared.*;

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

            sout.write(this, "Announcing to clients that the bar is " + burgerBarStatus.getBurgerBarStatus());
            if (burgerBarStatus.getBurgerBarStatus()){

                for (ReplyTo client : clients){
                        new Thread(()-> {
                            // needs to run in a thread, or clients will be locked
                            // while doing operations (while)
                            try {
                                client.burgerBarOpen();
                            } catch (RemoteException e) {
                                clients.remove(client);
                            }
                        }).start();
                }
            } else {
                for (ReplyTo client : clients){
                    try {
                        client.burgerBarClosed();
                    } catch (RemoteException e) {
                        clients.remove(client);
                    }
                }
            }
    }

    @Override
    public void regProducer(ReplyTo producer) throws RemoteException {
        clients.add(producer);
    }
    @Override
    public void regConsumer(ReplyTo consumer) throws RemoteException {
        clients.add(consumer);
    }
    @Override
    public void regManager(ReplyTo manager) throws RemoteException {
        clients.add(manager);
    }
    @Override
    public void produceBurger(Burger burger) throws RemoteException {
        burgerQueue.addBurger(burger);
    }
    @Override
    public boolean getBurgerBarStatus() {
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
