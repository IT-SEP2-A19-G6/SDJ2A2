package server.model.burgerqueue;

import Shared.Burger;

public interface BurgerQueue {
    void addBurger(Burger burger);
    Burger removeBurger();
}
