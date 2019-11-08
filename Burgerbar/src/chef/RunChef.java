package chef;

import chef.model.ChefModel;
import chef.model.ChefModelIml;
import chef.network.ClientRMI;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RunChef {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        ClientRMI clientRMI = new ClientRMI();
        ChefModel chef = new ChefModelIml(clientRMI, "Burgerbar/src/chef/mediator/recipes.txt");
        chef.produceBurgers();
    }

}
