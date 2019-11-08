package customer.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CustomerModelIml implements CustomerModel {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void consumeBurger() {

    }

    @Override
    public void addPropertyListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }
}
