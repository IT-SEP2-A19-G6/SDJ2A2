package manager.model;

import shared.PropertyChangeSubject;

public interface ManagerModel extends PropertyChangeSubject {

    void changeStatusTo(String status);

}
