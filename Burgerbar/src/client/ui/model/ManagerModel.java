package client.ui.model;

public interface ManagerModel extends PropertyChangeSubject {

    void changeStatusTo(String status);
    void showStatus(String status);


}
