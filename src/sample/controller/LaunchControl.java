package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Satellite;
import sample.model.SatelliteDB;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LaunchControl {
    @FXML
    private TextField Agency;
    @FXML
    private TextField newLaunchdate;
    @FXML
    private TextArea resultArea;
    @FXML
    private TextField Orbit;
    @FXML
    private TextField nameText;
    @FXML
    private TextField Purposet;
    @FXML
    private TextField Lau_date;
    @FXML
    private TextField Dest_date;
    @FXML
    private TextField LaunchV;
    @FXML
    private TextField Satellite_name;

    @FXML
    private TableView satelliteTable;
    @FXML
    private TableColumn<Satellite, String> name;
    @FXML
    private TableColumn<Satellite, String> purpose;
    @FXML
    private TableColumn<Satellite, Date> launch_date;
    @FXML
    private TableColumn<Satellite, Date> destruct_date;
    @FXML
    private TableColumn<Satellite, String> orbit;
    @FXML
    private TableColumn<Satellite, String> launchv;
    @FXML
    private TableColumn<Satellite, String> agency;
    @FXML
    private Button Back;
    //For MultiThreading
    private Executor exec;

    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void Back()
    {
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
    private void initialize() {
        /*
        The setCellValueFactory(...) that we set on the table columns are used to determine
        which field inside the Satellite objects should be used for the particular column.
        The arrow -> indicates that we're using a Java 8 feature called Lambdas.
        (Another option would be to use a PropertyValueFactory, but this is not type-safe

        We're only using StringProperty values for our table columns in this example.
        When you want to use IntegerProperty or DoubleProperty, the setCellValueFactory(...)
        must have an additional asObject():
        */

        //For multithreading: Create executor that uses daemon threads:
        exec = Executors.newCachedThreadPool((runnable) -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        purpose.setCellValueFactory(cellData -> cellData.getValue().purposeProperty());
        launch_date.setCellValueFactory(cellData -> cellData.getValue().launch_dateProperty());
        destruct_date.setCellValueFactory(cellData -> cellData.getValue().destruct_dateProperty());
        orbit.setCellValueFactory(cellData -> cellData.getValue().orbitProperty());
        launchv.setCellValueFactory(cellData -> cellData.getValue().launchvProperty());
        agency.setCellValueFactory(cellData -> cellData.getValue().agencyProperty());

    }


    //Search an employee
    @FXML
    public void searchsat(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Satellite information
            Satellite sat = SatelliteDB.searchSatellite(Satellite_name.getText());
            //Populate Satellite on TableView and Display on TextArea
            populateAndShowsat(sat);
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error occurred while getting satellite information from DB.\n" + e);
            throw e;
        }
    }

    //Search all employees
    @FXML
    private void searchSatellites(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Employees information
            ObservableList<Satellite> satData = SatelliteDB.searchSatelliteLaunches();
            //Populate Employees on TableView
            populateSatellite(satData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting Satellite information from DB.\n" + e);
            throw e;
        }
    }

    public void fillsatTable() throws SQLException, ClassNotFoundException {
        Task<ObservableList<Satellite>>task = new GetallSat();
        satelliteTable.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();

        }


    public void fillsatTable1() throws SQLException, ClassNotFoundException {
        Task<ObservableList<Satellite>>task = new GetallSat1();
        satelliteTable.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();

    }



    //Populate Satellite
    @FXML
    public void populateSat(Satellite emp) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Satellite> empData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        empData.add(emp);
        //Set items to the employeeTable
        satelliteTable.setItems(empData);
    }

    //Set Satellite information to Text Area
    @FXML
    public void setsatToTextArea(Satellite emp) {
        resultArea.setText("satelliteName: " + emp.getName() + "\n" +
                "purpose: " + emp.getPurpose());
    }

    //Populate Satellite for TableView and Display Satellite on TextArea
    @FXML
    public void populateAndShowsat(Satellite sat) throws ClassNotFoundException {
        if (sat != null) {
            populateSat(sat);
            setsatToTextArea(sat);
        } else {
            resultArea.setText("This Satellite does not exist!\n");
        }
    }

    //Populate Employees for TableView
    @FXML
    public void populateSatellite(ObservableList<Satellite> sat) throws ClassNotFoundException {
        //Set items to the employeeTable
        satelliteTable.setItems(sat);
    }

}
    class GetallSat extends Task{
    @Override
    public ObservableList<Satellite> call() throws Exception{
        return FXCollections.observableArrayList( SatelliteDB.searchSatelliteLaunches());
    }
}
class GetallSat1 extends Task{
    @Override
    public ObservableList<Satellite> call() throws Exception{
        return FXCollections.observableArrayList( SatelliteDB.searchSatelliteLaunches1());
    }
}
