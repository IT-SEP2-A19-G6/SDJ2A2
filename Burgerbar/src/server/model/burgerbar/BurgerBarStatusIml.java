package server.model.burgerbar;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BurgerBarStatusIml implements BurgerBarStatus {
    private PropertyChangeSupport support;
    private String barStatus;

    public BurgerBarStatusIml() {
        support = new PropertyChangeSupport(this);
        barStatus = "Closed";
    }

    @Override
    public void setBurgerBarStatus(String status) {
        if (status.equals("Open")){
            barStatus = "Open";
            System.out.println("The burger bar is " + barStatus);
            support.firePropertyChange("ChangedStatus", "Close", status);
        } else if (status.equals("Close")){
            barStatus = "Closed";
            System.out.println("The burger bar is " + barStatus);
            support.firePropertyChange("ChangedStatus", "Open", status);
        }

    }

    @Override
    public String getBurgerBarStatus() {
        return barStatus;
    }

    @Override
    public void addPropertyListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }
}
