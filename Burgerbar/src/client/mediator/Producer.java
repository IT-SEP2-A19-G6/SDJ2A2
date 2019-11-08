package client.mediator;

import client.domain.Burger;
import client.domain.Recipe;
import client.network.ClientRMI;

public class Producer {

    private RecipeProvider recipeProvider;
    private Burger burger;
    private ClientRMI clientRMI;


    public Producer(ClientRMI clientRMI, String recipeFileName) {
        recipeProvider = new RecipeProxy(recipeFileName);
        this.clientRMI = clientRMI;
    }

    public void produceBurgers() {
        while (true){
            int random = (int) Math.floor((Math.random()*3) + 1);

            Recipe recipe = null;

            try {
                recipe = recipeProvider.getRecipeById(random +"");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                burger = recipe.createBurger();
                Thread.sleep(1500);
                System.out.println("Chef has produced a burger");
            } catch (Exception e) {
                e.printStackTrace();
            }

            clientRMI.addBurgerToQueue(burger);
        }
    }

}
