package Shared;

import chef.domain.Burger;

import java.rmi.Remote;

public interface BurgerBar extends Remote {
    void produceBurger(Burger burger);
    Burger consumeBurger();
}
