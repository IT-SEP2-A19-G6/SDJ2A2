package server.model.burger;

import client.domain.Burger;

public interface BurgerQueue {
    void addBurger(Burger burger);
    Burger removeBurger();
}
