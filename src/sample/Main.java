package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.Satellite;
import sample.model.SatelliteDB;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("SES");

        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }
    public void stop(Stage primaryStage)throws Exception
    {
        primaryStage.hide();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
