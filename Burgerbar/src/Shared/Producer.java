package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Producer extends Remote {
    void regProducer(ReplyTo producer) throws RemoteException;
    void produceBurger(Burger burger) throws RemoteException;
    boolean getBurgerBarStatus() throws  RemoteException;
}
