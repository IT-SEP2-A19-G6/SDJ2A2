package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Consumer extends Remote {
    void regConsumer(ReplyTo consumer) throws RemoteException;
    String getBurgerBarStatus() throws  RemoteException;
    Burger consumeBurger() throws RemoteException;
}
