package client.manager.model;

import shared.sout;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ManagerModelImpl implements ManagerModel {

    private PropertyChangeSupport support;

    public ManagerModelImpl() {
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void changeStatusTo(String status) {

        if (status.equals("Close")) {
            sout.write(this, "The client.manager opens the restaurant");
            support.firePropertyChange("ClientChangedStatus", status, "Open");
        } else {
            sout.write(this, "The client.manager closes the restaurant");
            support.firePropertyChange("ClientChangedStatus", status, "Close");
        }
    }


    @Override
    public void addPropertyListener(String name, PropertyChangeListener listener) {
        sout.write(this, "added listener with name: " + name);
        support.addPropertyChangeListener(name, listener);
    }
}
