package customer.network;

import Shared.Consumer;
import Shared.ReplyTo;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class ClientRMI implements ReplyTo {
    private Consumer consumer;
    private String burgerBarStatus;
    private Random random = new Random();


    public ClientRMI() throws RemoteException, NotBoundException {
        UnicastRemoteObject.exportObject(this, 0);
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        consumer = (Consumer) registry.lookup("burgerServer");
        System.out.println("Client is connected");
        sendSelfToServer();
        burgerBarStatus = consumer.getBurgerBarStatus();
        consumeBurgerFromQueue();
    }

    public void consumeBurgerFromQueue(){
        while (burgerBarStatus.equals("Open")){
            try {
                consumer.consumeBurger();
                System.out.println("A customer munched a burger");
                Thread.sleep(random.nextInt(1000)+500);
            } catch (RemoteException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void sendSelfToServer() throws RemoteException {
        consumer.regConsumer(this);
    }

    @Override
    public void burgerBarInBusiness(Boolean bool) throws RemoteException {
        if (bool == true){
            System.out.println("Customer orders burgers");
            burgerBarStatus = "Open";
            consumeBurgerFromQueue();
        } else {
            burgerBarStatus = "Closed";
            System.out.println("Customer is leaving");
        }
    }
}
