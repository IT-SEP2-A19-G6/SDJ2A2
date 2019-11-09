package customer.network;

import shared.Consumer;
import shared.ReplyTo;
import shared.sout;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class ClientRMI implements ReplyTo {
    private Consumer consumer;
    private boolean burgerBarOpened;
    private Random random = new Random();


    public ClientRMI() throws RemoteException, NotBoundException {
        UnicastRemoteObject.exportObject(this, 0);
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        consumer = (Consumer) registry.lookup("burgerServer");
        sout.write(this,"Client is connected");
        sendSelfToServer();
        burgerBarOpened = consumer.getBurgerBarStatus();
        if (burgerBarOpened){
            burgerBarOpen();
        }
    }

    private void consumeBurgerFromQueue(){
        burgerBarOpened = true;
        while (burgerBarOpened){
            try {
                consumer.consumeBurger();
                sout.write(this,"A customer munched a burger");
                Thread.sleep(random.nextInt(1000)+500);
            } catch (RemoteException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        sout.write(this,"Customer is leaving");
    }


    @Override
    public void sendSelfToServer() throws RemoteException {
        consumer.regConsumer(this);
    }

    @Override
    public void burgerBarOpen() {
        sout.write(this,"Customer orders some meat");
        consumeBurgerFromQueue();
    }

    @Override
    public void burgerBarClosed() {
        burgerBarOpened = false;
    }

}
