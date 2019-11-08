package server;

import server.model.burger.BurgerQueue;
import server.model.burger.BurgerQueueHandler;
import server.network.ServerRMI;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RunBurgerServer {
    public static void main(String[] args){
        BurgerQueue burgerQueue = new BurgerQueueHandler();

        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            ServerRMI server = new ServerRMI(burgerQueue);
            reg.bind("burgerServer", server);
            System.out.println("Server is running");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
