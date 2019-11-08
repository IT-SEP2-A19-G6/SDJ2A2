package manager.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ManagerModelIml implements ManagerModel {

    private PropertyChangeSupport support;

    public ManagerModelIml() {
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void changeStatusTo(String status) {
        if (status.equals("Close")) {
            System.out.println("The manager opens the restaurant");
            support.firePropertyChange("ChangedStatus", status, "Open");
        } else {
            System.out.println("The manager closes the restaurant");
            support.firePropertyChange("ChangedStatus", status, "Close");
        }
    }

    @Override
    public void addPropertyListener(String name, PropertyChangeListener listener) {
        System.out.println("added listener with name: " + name);
        support.addPropertyChangeListener(name, listener);
    }
}
