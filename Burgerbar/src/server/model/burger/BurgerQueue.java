package server.model.burger;

import Shared.Burger;

public interface BurgerQueue {
    void addBurger(Burger burger);
    Burger removeBurger();
}
