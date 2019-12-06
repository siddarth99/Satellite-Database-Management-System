package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Satellite;
import sample.model.SatelliteDB;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class satelliteController {

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
    private TableColumn<Satellite, String>  name;
    @FXML
    private TableColumn<Satellite, String>  purpose;
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
    private void initialize () {
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
            Thread t = new Thread (runnable);
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

    //Search all satellites
    @FXML
    private void searchSatellites(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all satellite information
            ObservableList<Satellite> satData = SatelliteDB.searchSatellites();

            populateSatellite(satData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting Satellite information from DB.\n" + e);
            throw e;
        }
    }

    //Populate Employees for TableView with MultiThreading (This is for example usage)
    private void fillsatTable(ActionEvent event) throws SQLException, ClassNotFoundException {
        Task<List<Satellite>> task = new Task<List<Satellite>>(){
            @Override
            public ObservableList<Satellite> call() throws Exception{
                return SatelliteDB.searchSatellites();
            }
        };

        task.setOnFailed(e-> task.getException().printStackTrace());
        task.setOnSucceeded(e-> satelliteTable.setItems((ObservableList<Satellite>) task.getValue()));
        exec.execute(task);
    }

    //Populate Satellite
    @FXML
    public void populateSat (Satellite emp) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Satellite> empData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        empData.add(emp);
        //Set items to the employeeTable
        satelliteTable.setItems(empData);
    }

    //Set Satellite information to Text Area
    @FXML
    public void setsatToTextArea ( Satellite emp) {
        resultArea.setText("satelliteName: " + emp.getName() + "\n" +
                "purpose: " + emp.getPurpose());
    }

    //Populate Satellite for TableView and Display Satellite on TextArea
    @FXML
    public void populateAndShowsat(Satellite sat) throws ClassNotFoundException {
        if (sat!= null) {
            populateSat(sat);
            setsatToTextArea(sat);
        } else {
            resultArea.setText("This Satellite does not exist!\n");
        }
    }

    //Populate Employees for TableView
    @FXML
    public void populateSatellite(ObservableList<Satellite> sat) throws ClassNotFoundException {

        satelliteTable.setItems(sat);
    }

    @FXML
    public void updateSatellite (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            String s=newLaunchdate.getText();
            SatelliteDB.updatesatdate(Satellite_name.getText(),s);
            resultArea.setText("LAUNCH DATE has been updated for Satellite: " + Satellite_name.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while updating date: " + e);
        }
    }

    //Insert an employee to the DB
    @FXML
    public void insertSatellite(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        try {
            SatelliteDB.insertsat(nameText.getText(),Purposet.getText(),Lau_date.getText(),
            Dest_date.getText(),Orbit.getText(),LaunchV.getText(),Agency.getText());
            resultArea.setText("Satellite inserted! \n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while inserting Satellite " + e);
            throw e;
        }
    }

    //Delete an employee with a given employee Id from DB
    @FXML
    private void deleteSatellite (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            SatelliteDB.deletesat(Satellite_name.getText());
            resultArea.setText("Satellite deleted! Satellite name: " + Satellite_name.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while deleting satellite " + e);
            throw e;
        }
    }
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
}
