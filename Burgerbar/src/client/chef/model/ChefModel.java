package client.chef.model;

import shared.PropertyChangeSubject;

public interface ChefModel extends PropertyChangeSubject {
    void produceBurgers();
    void goHome();
}
