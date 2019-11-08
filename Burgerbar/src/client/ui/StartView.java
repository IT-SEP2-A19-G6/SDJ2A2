package client.ui;

import client.ui.model.ManagerModel;
import client.ui.model.ManagerModelIml;
import client.ui.view.ManagerController;
import client.ui.viewmodel.ManagerViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class StartView extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/ManagerView.fxml"));
        Parent root = loader.load();


        ManagerController controller = loader.getController();
        ManagerModel m = new ManagerModelIml();
        ManagerViewModel mvm = new ManagerViewModel(m);
        controller.init(mvm);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

    }


}
