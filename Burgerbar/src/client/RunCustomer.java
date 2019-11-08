package client;


import client.network.ClientRMI;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RunCustomer {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        ClientRMI customerRMI = new ClientRMI();

        int count=0;
        while (true){
            customerRMI.consumeBurgerFromQueue();

            try {
                Thread.sleep(1500);
                count++;
                System.out.println("Customer ate a burger... " + count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
