package server.model.burgerbar;

import shared.PropertyChangeSubject;

public interface BurgerBarStatus extends PropertyChangeSubject {
    void setBurgerBarStatus(String status);
    boolean getBurgerBarStatus();
}
