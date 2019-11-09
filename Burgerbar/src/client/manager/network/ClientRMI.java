package client.manager.network;

import client.manager.model.ManagerModel;
import shared.Manager;
import shared.sout;

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
        sout.write(this, "Client connected");

        m.addPropertyListener("ClientChangedStatus", this::changeBurgerBarStatus);
    }

    private void changeBurgerBarStatus(PropertyChangeEvent propertyChangeEvent){
        sout.write(this, "Status of the bar changed");
            try {

                burgerBar.burgerBarStatus(propertyChangeEvent.getNewValue().toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
    }

}
