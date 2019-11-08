package server.model.burgerbar;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BurgerBarStatusIml implements BurgerBarStatus {
    private PropertyChangeSupport support;
    private boolean barOpen;

    public BurgerBarStatusIml() {
        support = new PropertyChangeSupport(this);
        barOpen = true;
    }

    @Override
    public void setBurgerBarStatus(String status) {
        if (status.equals("Open")){
            barOpen = true;
            System.out.println("The burger bar is open");
            support.firePropertyChange("ChangedStatus", "Close", status);
        } else if (status.equals("Close")){
            barOpen = false;
            System.out.println("The burger bar is closed");
            support.firePropertyChange("ChangedStatus", "Open", status);
        }

    }

    @Override
    public boolean getBurgerBarStatus() {
        return barOpen;
    }

    @Override
    public void addPropertyListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }
}
