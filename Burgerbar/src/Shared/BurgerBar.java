package Shared;

import server.model.domain.Burger;

import java.rmi.Remote;

public interface BurgerBar extends Remote {
    void produceBurger(Burger burger);
    Burger consumeBurger();
}
