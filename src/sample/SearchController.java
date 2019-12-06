package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SearchController  {
    @FXML
    private Label Lab2;
    @FXML
    private Button Back;
    @FXML
    private Label Lab3;
    public String s=" ";
    private Executor exec;


    public void setString(String s2)
    {
        s=s2;
    }
    @FXML
    public void init()
    {
        exec = Executors.newCachedThreadPool((runnable) -> {
            Thread t = new Thread (runnable);
            t.setDaemon(true);
            return t;
        });
        Lab3.setText(s);
    }

    @FXML
    public void Back() {
        Back.setOnAction(e -> {
            Stage root = new Stage();
            Main main = new Main();
            Stage r = (Stage) Back.getScene().getWindow();
            try {
                r.hide();
                main.start(root);

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        });

    }


}


