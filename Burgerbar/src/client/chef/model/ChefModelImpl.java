package client.chef.model;

import client.chef.mediator.Recipe;
import client.chef.mediator.RecipeProvider;
import client.chef.mediator.RecipeProxy;
import shared.Burger;
import shared.sout;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

public class ChefModelImpl implements ChefModel {

    private RecipeProvider recipeProvider;
    private Burger burger;
    private PropertyChangeSupport support;
    private boolean open;


    public ChefModelImpl(String recipeFileName) {
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
                sout.write(this,"Chef has produced a burger");
            } catch (Exception e) {
                e.printStackTrace();
            }

            support.firePropertyChange("addBurger", null, burger);
        }
        sout.write(this,"Chef is leaving");
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
