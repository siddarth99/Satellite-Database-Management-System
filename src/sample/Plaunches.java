package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.LaunchControl;

public class Plaunches extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Launches.fxml"));
        Parent root=loader.load();
        LaunchControl launchControl=loader.getController();

        launchControl.fillsatTable1();
        primaryStage.setTitle("SES");

        primaryStage.setScene(new Scene(root, 1000, 600));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
