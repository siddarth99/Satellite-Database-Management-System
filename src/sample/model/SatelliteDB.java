package sample.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import sample.util.DBUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SatelliteDB {
    @FXML
    private Label Sattel;
    @FXML
    private Label Agen;
    @FXML

    private Label Launc;
    @FXML
    private Alert alert;

    @FXML
    //*******************************
    //SELECT an Satellite
    //*******************************
    public static Satellite searchSatellite(String satname) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM satellite WHERE name='"+satname+"'";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get satellite object
            Satellite satellite = getSatelliteFromResultSet(rsEmp);

            //Return satellite object
            return satellite;
        } catch (SQLException e) {
            System.out.println("While searching an Satellite with " + satname + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }


    //Use ResultSet from DB as parameter and set Satellite Object's attributes and return satellite object.
    private static Satellite getSatelliteFromResultSet(ResultSet rs) throws SQLException
    {
        Satellite emp = null;
        if (rs.next()) {
            emp = new Satellite();
            emp.setName(rs.getString(1));
            emp.setPurpose(rs.getString(2));
            emp.setLaunch_date(rs.getDate(3));
            emp.setDestruct_date(rs.getDate(4));
            emp.setOrbit(rs.getString(5));
            emp.setLaunchv(rs.getString(6));
            emp.setAgency(rs.getString(7));
        }
        return emp;
    }

    //*******************************
    //SELECT Satellite
    //*******************************
    public static ObservableList<Satellite> searchSatellites() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM satellite";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmps = DBUtil.dbExecuteQuery1(selectStmt);

            //Send ResultSet to the getSatelliteslist method object
            ObservableList<Satellite> satList = getSatelliteslist(rsEmps);
            //Return object
            return satList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    public static ObservableList<Satellite> searchSatelliteLaunches() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM satellite WHERE launch_date>CONVERT(date,getdate())";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getSatelliteslist method and get object
            ObservableList<Satellite> satList = getSatelliteslist(rsEmps);
            //Return employee object
            return satList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    public static ObservableList<Satellite> searchSatelliteLaunches1() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM satellite WHERE launch_date<CONVERT(date,getdate())";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getSatelliteslist method and get employee object
            ObservableList<Satellite> satList = getSatelliteslist(rsEmps);
            //Return employee object
            return satList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    //Select * from satellites operation
    private static ObservableList<Satellite> getSatelliteslist(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Satellite objects
        ObservableList<Satellite> satList = FXCollections.observableArrayList();

        while (rs.next()) {
            Satellite sat = new Satellite();
            sat = new Satellite();
            sat.setName(rs.getString(1));
            sat.setPurpose(rs.getString(2));
            sat.setLaunch_date(rs.getDate(3));
            sat.setDestruct_date(rs.getDate(4));
            sat.setOrbit(rs.getString(5));
            sat.setLaunchv(rs.getString(6));
            sat.setAgency(rs.getString(7));
            //Add employee to the ObservableList
            satList.add(sat);
        }
        //return satList (ObservableList of Employees)
        return satList;
    }

    //*************************************
    //UPDATE satellites date
    //*************************************
    public static void updatesatdate(String satname, String vehicle) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE satellite\n" +
                        "      SET LaunchVehicle_name= '" + vehicle + "'\n" +
                        "    WHERE name ='" + satname + "';\n" +
                        "   COMMIT;\n" +
                        "END;";

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    //*************************************
    //DELETE an employee
    //*************************************
    public static void deletesat(String name) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt = "DELETE FROM satellite" +
                        " WHERE name ='"+ name +"';" +
                        "   COMMIT;";

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }


    //*************************************
    //INSERT an employee
    //*************************************
    public static void insertsat(String name, String purpose, String Launch, String Destruct, String Orbit, String launchv
            , String agency) throws SQLException, ClassNotFoundException {

        String updateStmt ="INSERT INTO satellite" +
                        "(name" +
                        "      ,purpose" +
                        "      ,launch_date" +
                        "      ,destruct_date" +
                        "      ,orbit_name" +
                        "      ,LaunchVehicle_name" +
                        "      ,Agency_name)" +
                        "VALUES" +
                        "('"+name+"', '"+purpose+"','"+Launch+"','"+Destruct+"','"+Orbit+"','"+launchv+"','"+agency+"');";
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while inserting Operation: " + e);
            throw e;
        }
    }
}
