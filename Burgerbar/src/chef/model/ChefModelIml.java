package chef.model;

import shared.Burger;
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
    private boolean open;


    public ChefModelIml(String recipeFileName) {
        recipeProvider = new RecipeProxy(recipeFileName);
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void produceBurgers() {
        open = true;
        while (open){
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
        System.out.println("Chef is leaving");
    }

    @Override
    public void goHome() {
        open = false;
    }

    @Override
    public void addPropertyListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }
}
