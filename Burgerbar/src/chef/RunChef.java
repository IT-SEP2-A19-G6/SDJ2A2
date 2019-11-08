package chef;

import chef.model.ChefModel;
import chef.model.ChefModelIml;
import chef.network.ClientRMI;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RunChef {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        ChefModel chef = new ChefModelIml("Burgerbar/src/chef/mediator/recipes.txt");
        new ClientRMI(chef);
    }

}
