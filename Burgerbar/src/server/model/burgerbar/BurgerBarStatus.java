package server.model.burgerbar;

import Shared.PropertyChangeSubject;

public interface BurgerBarStatus extends PropertyChangeSubject {
    void setBurgerBarStatus(String status);
    String getBurgerBarStatus();
}
