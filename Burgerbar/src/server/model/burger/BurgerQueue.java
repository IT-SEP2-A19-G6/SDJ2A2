package server.model.burger;

import client.domain.Burger;

public interface BurgerQueue {

    public void addBurger(Burger burger);

    public void removeBurger();
}
