package server.model.burgerbar;

import shared.sout;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BurgerBarStatusImpl implements BurgerBarStatus {
    private PropertyChangeSupport support;
    private boolean barOpen;

    public BurgerBarStatusImpl() {
        support = new PropertyChangeSupport(this);
        barOpen = false;
    }

    @Override
    public void setBurgerBarStatus(String status) {
        sout.write(this, "Burger bar new status: " + status);

        if (status.equals("Open")){
            barOpen = true;
            support.firePropertyChange("ChangedStatus", "Close", status);
        } else if (status.equals("Close")){
            barOpen = false;
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
