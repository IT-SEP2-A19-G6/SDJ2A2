package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReplyTo extends Remote {
    void sendSelfToServer() throws RemoteException;
    void burgerBarOpen() throws RemoteException;
    void burgerBarClosed() throws RemoteException;
}
