package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Manager extends Remote {
    void burgerBarStatus(String status) throws RemoteException;
}
