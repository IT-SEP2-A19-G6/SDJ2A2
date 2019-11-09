package client.chef;

import client.chef.model.ChefModel;
import client.chef.model.ChefModelImpl;
import client.chef.network.ClientRMI;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RunChef {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        // TODO: JH: Added easier access to the file. Not sure Troels wants it this way.
        String fileName = "mediator/recipes.txt";
        String className = RunChef.class.getSimpleName() + ".class";
        String pathToClass = RunChef.class.getResource(className).toString();
        String filePath = pathToClass.replaceAll(className, "").replaceAll("%20", " ").substring(6) + fileName;

        ChefModel chef = new ChefModelImpl(filePath);
        new ClientRMI(chef);
    }

}
