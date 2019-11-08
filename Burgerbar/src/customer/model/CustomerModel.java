package customer.model;

import Shared.PropertyChangeSubject;

import java.beans.PropertyChangeListener;

public interface CustomerModel extends PropertyChangeSubject {
    void consumeBurger();
}
