package chef.model;

import Shared.PropertyChangeSubject;

public interface ChefModel extends PropertyChangeSubject {
    void produceBurgers(String ststus);
}
