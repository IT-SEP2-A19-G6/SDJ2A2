package client.manager;

import client.manager.model.ManagerModel;
import client.manager.model.ManagerModelImpl;
import client.manager.network.ClientRMI;
import client.manager.view.ManagerController;
import client.manager.viewmodel.ManagerViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.rmi.NotBoundException;

public class StartManagerApp extends Application {

    @Override
    public void start(Stage stage) throws IOException, NotBoundException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/ManagerView.fxml"));
        Parent root = loader.load();


        ManagerController controller = loader.getController();
        ManagerModel m = new ManagerModelImpl();
        ManagerViewModel mvm = new ManagerViewModel(m);
        controller.init(mvm);
        ClientRMI clientRMI = new ClientRMI(m);


        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();


    }


}
