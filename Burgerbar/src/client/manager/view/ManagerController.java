package client.manager.view;

import client.manager.viewmodel.ManagerViewModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import shared.sout;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class ManagerController {

    @FXML
    public Label statusLabel;
    @FXML
    public ImageView statusImage;
    @FXML
    public Button statusButton;
    @FXML
    public ImageView burgerImage;
    @FXML
    AnchorPane parent;

    double x = 0, y = 0;
    private ManagerViewModel vm;


    public void init(ManagerViewModel vm) {
        this.vm = vm;
        // run methods
        setupListeners();
        makeDragable();

        // bindings
        statusLabel.textProperty().bind(vm.statusProperty());


    }

    private void setupListeners() {


        statusLabel.textProperty().addListener((ov, oldVal, newVal) -> {
            try {

                URL closedPath = ClassLoader.getSystemClassLoader().getResource("client/manager/view/img/status_closed.png");
                URL openPath = ClassLoader.getSystemClassLoader().getResource("client/manager/view/img/status_closed.png");

                if (newVal.equals("Open")) {
                    statusLabel.setStyle("-fx-text-fill: Green");

                    Image img = new Image(new File(Objects.requireNonNull(openPath).toURI()).toURI().toString());
                    statusImage.setImage(img);
                    burgerImage.setVisible(true);

                } else {
                    statusLabel.setStyle("-fx-text-fill: Red");
                    Image img = new Image(new File(Objects.requireNonNull(openPath).toURI()).toURI().toString());
                    statusImage.setImage(img);
                    burgerImage.setVisible(false);
                }

                if (oldVal.isEmpty()) {
                    oldVal = "Open";
                }
                statusButton.setText(oldVal + (oldVal.equals("Close") ? " down" : " up") + " burger bar");

            } catch (URISyntaxException e) {
                sout.write(this,"there was an error loading gui images.");
                e.printStackTrace();
            }
        });
    }

    private void makeDragable() {
        parent.setOnMousePressed(((event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        }));

        parent.setOnMouseDragged(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.5f);
        }));

        parent.setOnDragDone(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));

        parent.setOnMouseReleased(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);

        }));
    }


    public void onCloseButtonAction() {
        sout.write(this,"Closing the gui application.");
        System.exit(0);

    }

    public void onRestaurantButtonClick() {
        vm.changeStatus();
    }
}
