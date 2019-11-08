package manager.model;

import Shared.PropertyChangeSubject;

public interface ManagerModel extends PropertyChangeSubject {

    void changeStatusTo(String status);
    void showStatus(String status);


}
