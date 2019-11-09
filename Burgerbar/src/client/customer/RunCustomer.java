package client.customer;

import client.customer.network.ClientRMI;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;



public class RunCustomer {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        new ClientRMI();
    }
}
