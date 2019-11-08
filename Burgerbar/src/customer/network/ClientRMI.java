package customer.network;

import shared.Consumer;
import shared.ReplyTo;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class ClientRMI implements ReplyTo {
    private Consumer consumer;
    private boolean burgerBarOpen;
    private Random random = new Random();


    public ClientRMI() throws RemoteException, NotBoundException {
        UnicastRemoteObject.exportObject(this, 0);
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        consumer = (Consumer) registry.lookup("burgerServer");
        System.out.println("Client is connected");
        sendSelfToServer();
        burgerBarOpen = consumer.getBurgerBarStatus();
        if (burgerBarOpen){
            burgerBarOpen();
        }
    }

    private void consumeBurgerFromQueue(){
        burgerBarOpen = true;
        while (burgerBarOpen){
            try {
                consumer.consumeBurger();
                System.out.println("A customer munched a burger");
                Thread.sleep(random.nextInt(1000)+500);
            } catch (RemoteException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Customer is leaving");
    }


    @Override
    public void sendSelfToServer() throws RemoteException {
        consumer.regConsumer(this);
    }

    @Override
    public void burgerBarOpen() {
        System.out.println("Customer orders some meat");
        consumeBurgerFromQueue();
    }

    @Override
    public void burgerBarClosed() {
        burgerBarOpen = false;
    }

}
