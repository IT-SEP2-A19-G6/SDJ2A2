package manager.network;

import Shared.Manager;
import manager.model.ManagerModel;

import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI {
    private Manager burgerBar;

    public ClientRMI(ManagerModel m) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        burgerBar = (Manager) registry.lookup("burgerServer");
        System.out.println("Client is connected");
        m.addPropertyListener("ChangedStatus", this::changeBurgerBarStatus);
    }

    public void changeBurgerBarStatus(PropertyChangeEvent propertyChangeEvent){
        try {
            burgerBar.burgerBarStatus(propertyChangeEvent.getNewValue().toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
