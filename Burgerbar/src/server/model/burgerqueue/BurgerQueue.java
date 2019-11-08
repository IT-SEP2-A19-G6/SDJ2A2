package server.model.burgerqueue;

import shared.Burger;

public interface BurgerQueue {
    void addBurger(Burger burger);
    Burger removeBurger();
}
