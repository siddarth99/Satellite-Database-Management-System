package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Stage;
import sample.model.Satellite;
import sample.model.SatelliteDB;

import sample.util.DBUtil;

import javax.print.DocFlavor;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import sample.controller.satelliteController;
public class Controller {
    @FXML
    private Button Register;
    @FXML
    private TextArea SearchText;
    @FXML
    private TextArea SearchText1;
    @FXML
    private Button Log;
    @FXML
    private Button Back;
    @FXML
    private TextField SEARCHBAR;
    @FXML
    private Button Search;
    @FXML
    private Button UpLaunch;
    @FXML
    private Button PLaunch;
    @FXML
    private Label Lab2;
    @FXML
    private Label resultArea;
    @FXML
    public void OnButtonClick() {
        Register.setOnAction(e -> {
            login login = new login();
            Stage root = new Stage();
            Stage ma = (Stage) Register.getScene().getWindow();
            try {
                ma.hide();
                login.start(root);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

    }


    @FXML
    public void Admin() {
        Log.setOnAction(e -> {
            satmain empmain = new satmain();
            login login = new login();
            Stage stage = (Stage) Log.getScene().getWindow();
            Stage root = new Stage();
            try {
                stage.hide();
                empmain.start(root);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
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
    @FXML
    public void Searchh(){
        Search.setOnAction(e->{
            String sear=SEARCHBAR.getText();
            String s2=" ";
            try {
                //Get Satellite information
                    satelliteController satt = new satelliteController();
                    Satellite sat = SatelliteDB.searchSatellite(sear);
                    //Populate Satellite on TableView and Display on TextArea
                    SearchText.setText("Satellite Name :" + sat.getName() + "\n" +
                            "Purpose :" + sat.getPurpose() + "\n" +
                            "Destruct Date :" + sat.getDestruct_date() + "\n" +
                            "Launch Vehicle" + sat.getLaunchv());
                    SearchText1.setText(
                            "Launch Date :" + sat.getLaunch_date() + "\n" + "Orbit :" + sat.getOrbit() + "\n" +
                                    "Agency :" + sat.getAgency());

            }
            catch (Exception e2) {
                e2.printStackTrace();
                resultArea.setText("Error occurred while getting satellite information from DB.\n" + e);
            }
        });
    }
    @FXML
    public void OnLaunches()
    {
        UpLaunch.setOnAction(e -> {
            Stage root = new Stage();
            ULaunches uLaunches=new ULaunches();
            Stage r = (Stage) UpLaunch.getScene().getWindow();
            try {
                r.hide();
                uLaunches.start(root);

            } catch (Exception e2) {
                e2.printStackTrace();
            }

        });
    }
    @FXML
    public void onPlaunches()
    {
        PLaunch.setOnAction(e -> {
            Stage root = new Stage();
            Plaunches main = new Plaunches();
            Stage r = (Stage)PLaunch.getScene().getWindow();
            try {
                r.hide();
                main.start(root);

            } catch (Exception e2) {
                e2.printStackTrace();
            }

        });
    }


}

