package com.example.electrochromicfilmapplication;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class GPS extends Activity {

    private Geocoder geocoder;
    private LocationManager locationManager;
    private Location location;

    private List<Address> addresses;

    private int frontSidePercent;
    private int backSidePercent;
    private int rearPercent;

    private double longitude;
    private double latitude;

    private Address retrievedAddress;
    private Context mContext;
    private String state;

    public GPS(Context mContext) {
        this.mContext = mContext;
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();

    }

    private void setStateAndTint() {

        geocoder = new Geocoder(mContext, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            state = addresses.get(0).getAdminArea();
            setTintLevels();
            /*if(addresses.size() > 0) {
                retrievedAddress = addresses.get(0);
                StringBuilder strAddress = new StringBuilder();
                for(int i = 0; i < retrievedAddress.getMaxAddressLineIndex(); i++) {
                    strAddress.append(retrievedAddress.getAddressLine(i)).append(" ");
                }
            }*/
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void setTintLevels() throws SQLException {
        ArrayList<String> stateList = new ArrayList<>();
        //DriverManager.registerDriver(new com.mysql.jbdc.Driver());
        String mySqlUrl = "url";
        Connection connection = DriverManager.getConnection(mySqlUrl, "root", "password");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("Obtaining States");

        //Sets Front Percent Tint Level
        rs = statement.executeQuery("SELECT FrontSidePercent FROM TintRestrictions WHERE State = " + state);
        while(rs.next()) frontSidePercent = rs.getInt("FrontSidePercent");
        //Sets Back Percent Tint Level
        rs = statement.executeQuery("SELECT BackSidePercent FROM TintRestrictions WHERE State = " + state);
        while(rs.next()) backSidePercent = rs.getInt("BackSidePercent");
        //Sets Rear Percent Tint Level
        rs = statement.executeQuery("SELECT RearPercent FROM TintRestrictions WHERE State = " + state);
        while(rs.next()) rearPercent = rs.getInt("RearPercent");
    }

    public Boolean stateChanged(String stateName) throws SQLException {
        if(!stateName.equals(state)) {
            setStateAndTint();
            return true;
        }
        return false;

    }
    public int getFrontSidePercent() {
        return frontSidePercent;
    }

    public int getBackSidePercent() {
        return backSidePercent;
    }

    public int getBackPercent() {
        return rearPercent;
    }
    public String getState() {
        return state;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

}
