package client;

import Shared.BurgerType;
import client.domain.Burger;
import client.network.ClientRMI;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RunChef {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        ClientRMI chefRMI = new ClientRMI();
        int count = 0;
        while(true){
            chefRMI.addBurgerToQueue(new Burger(BurgerType.VEGGIE.name().toLowerCase()));

            try {
                Thread.sleep(1500);
                count++;
                System.out.println("Chef made a burger... " + count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
