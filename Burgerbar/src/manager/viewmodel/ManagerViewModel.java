package manager.viewmodel;

import manager.model.ManagerModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.sout;

import java.beans.PropertyChangeEvent;

public class ManagerViewModel {


    private StringProperty status;
    private ManagerModel model;


    public ManagerViewModel(ManagerModel model) {
        this.model = model;
        status = new SimpleStringProperty("Close");

        setupListeners();
    }

    private void setupListeners() {
        model.addPropertyListener("ClientChangedStatus", this::changeStatusTo);
    }

    private void changeStatusTo(PropertyChangeEvent evt) {

        sout.write(this, "Got from event: " + evt.getNewValue());
        String stat = (String) evt.getNewValue();
        status.setValue(stat);


    }

    public StringProperty statusProperty() {
        return status;
    }

    public void changeStatus() {
        // send to server new status request
        model.changeStatusTo(status.getValue());
    }
}
