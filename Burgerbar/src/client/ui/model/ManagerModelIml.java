package client.ui.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ManagerModelIml implements ManagerModel {

    private PropertyChangeSupport support;

    public ManagerModelIml() {

        support = new PropertyChangeSupport(this);
    }



    @Override
    public void changeStatusTo(String status) {
        System.out.println(status);
        // tell RMI code below

        // The following is dummy code to simulate rmi response...
        // just send it back
        if (status.equals("Close")) {
            System.out.println("fireing closed");
            support.firePropertyChange("ChangedStatus", status, "Open");
        } else {
            System.out.println("fireing opened");
            support.firePropertyChange("ChangedStatus", status, "Close");
        }
    }

    @Override
    public void showStatus(String status) {
        // got from RMI
        support.firePropertyChange("ChangedStatus", null, status);
    }

    @Override
    public void addPropertyListener(String name, PropertyChangeListener listener) {
        System.out.println("added listener with name: " + name);
        support.addPropertyChangeListener(name, listener);
    }
}
