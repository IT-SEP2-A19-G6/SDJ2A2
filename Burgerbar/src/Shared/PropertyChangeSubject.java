package Shared;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSubject {


    void addPropertyListener(String name, PropertyChangeListener listener);
}
