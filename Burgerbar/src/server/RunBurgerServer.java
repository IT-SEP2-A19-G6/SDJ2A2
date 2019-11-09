package server;

import server.model.burgerbar.BurgerBarStatus;
import server.model.burgerbar.BurgerBarStatusImpl;
import server.model.burgerqueue.BurgerQueue;
import server.model.burgerqueue.BurgerQueueHandler;
import server.network.ServerRMI;
import shared.sout;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RunBurgerServer {
    public static void main(String[] args){
        BurgerQueue burgerQueue = new BurgerQueueHandler();
        BurgerBarStatus burgerBarStatus = new BurgerBarStatusImpl();

        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            ServerRMI server = new ServerRMI(burgerQueue, burgerBarStatus);
            reg.bind("burgerServer", server);
            sout.write(new RunBurgerServer(),"Server is running...");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
