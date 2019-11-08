package client.mediator;

import client.domain.Burger;
import client.domain.Recipe;
import client.network.ClientRMI;

import java.util.Random;

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
            Random r = new Random();
            int random = (int) Math.floor((Math.random()*3) + 1);

            Recipe recipe = null;

            try {
                recipe = recipeProvider.getRecipeById(random +"");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                burger = recipe.createBurger();
                Thread.sleep(r.nextInt(1500)+500);
                System.out.println("Chef has produced a burger");
            } catch (Exception e) {
                e.printStackTrace();
            }

            clientRMI.addBurgerToQueue(burger);
        }
    }

}
