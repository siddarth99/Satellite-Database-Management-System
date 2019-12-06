package sample;
import java.sql.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.DriverManager;
import java.sql.SQLException;

public class login extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Login page");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
