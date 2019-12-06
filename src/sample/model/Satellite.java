package sample.model;

import javafx.beans.property.*;
import java.sql.Date;

/**
 * Created by ONUR BASKIRT on 27.02.2016.
 */
public class Satellite {
    //Declare Employees Table Columns
    private StringProperty name;
    private StringProperty purpose;
    private SimpleObjectProperty<Date> Launch_date;
    private SimpleObjectProperty<Date> Destruct_date;
    private StringProperty Orbit;
    private StringProperty launchv;
    private StringProperty agency;


    //Constructor
    public Satellite() {
        this.name = new SimpleStringProperty();
        this.purpose = new SimpleStringProperty();
        this.Launch_date = new SimpleObjectProperty<>();
        this.Destruct_date = new SimpleObjectProperty<>();
        this.Orbit = new SimpleStringProperty();
        this.launchv=new SimpleStringProperty();
        this.agency=new SimpleStringProperty();
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPurpose() {
        return purpose.get();
    }

    public StringProperty purposeProperty() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose.set(purpose);
    }

    public Date getLaunch_date() {
        return Launch_date.get();
    }

    public SimpleObjectProperty<Date> launch_dateProperty() {
        return Launch_date;
    }

    public void setLaunch_date(Date launch_date) {
        this.Launch_date.set(launch_date);
    }

    public Date getDestruct_date() {
        return Destruct_date.get();
    }

    public SimpleObjectProperty<Date> destruct_dateProperty() {
        return Destruct_date;
    }

    public void setDestruct_date(Date destruct_date) {
        this.Destruct_date.set(destruct_date);
    }

    public String getOrbit() {
        return Orbit.get();
    }

    public StringProperty orbitProperty() {
        return Orbit;
    }

    public void setOrbit(String orbit) {
        this.Orbit.set(orbit);
    }

    public String getLaunchv() {
        return launchv.get();
    }

    public StringProperty launchvProperty() {
        return launchv;
    }

    public void setLaunchv(String launchv) {
        this.launchv.set(launchv);
    }

    public String getAgency() {
        return agency.get();
    }

    public StringProperty agencyProperty() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency.set(agency);
    }
}
