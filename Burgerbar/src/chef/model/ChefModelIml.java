package chef.model;

import Shared.Burger;
import chef.mediator.Recipe;
import chef.mediator.RecipeProvider;
import chef.mediator.RecipeProxy;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

public class ChefModelIml implements ChefModel {

    private RecipeProvider recipeProvider;
    private Burger burger;
    private PropertyChangeSupport support;


    public ChefModelIml(String recipeFileName) {
        recipeProvider = new RecipeProxy(recipeFileName);
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void produceBurgers(String status) {
        while (status.equals("Open")){
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

            support.firePropertyChange("addBurger", null, burger);
        }
    }

    @Override
    public void addPropertyListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }
}
