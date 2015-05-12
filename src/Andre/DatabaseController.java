/**
 * Created by Andre on 5/1/2015.
 */
package Andre;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by Andre on 4/30/2015.
 * Class that controls the database.
 */

public class DatabaseController {

    private Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static PreparedStatement ps;

    public DatabaseController() {

    }

    // Connect to the MySQL Database musicdb
    public void connect() throws Exception {
        if (conn != null) {
            return;
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");

        }catch (ClassNotFoundException cnf) {
            throw new Exception("Driver not found");
        }
        // Open the login file, take the credentials out and put them into the connection
        BufferedReader br = new BufferedReader(new FileReader("Login.txt"));
        //Read the credentials out of the file and put them into an array
        String line = br.readLine();
        String credentials[] = line.split(" ");
        String server, password;
        // Remove the unnecessary quotes if exists
        if (credentials[0].startsWith("\"")) {
            server = credentials[0].substring(1, credentials[0].length() - 1);
        }
        else {
            server = credentials[0];
        }
        if (credentials[1].startsWith("\"")) {
            password = credentials[1].substring(1, credentials[1].length() - 1);
        }
        else {
            password = credentials[1];
        }

        // Connect to the database using the credentials.
        String dbURL = "jdbc:mysql://localhost:3306/musicdb";
        conn = DriverManager.getConnection(dbURL, server, password);
        System.out.println("Successfully Connected");
    }


    public ResultSet selectAllAlbums() {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM albums");
            return rs;
        }
        catch (SQLException sqex){
            System.out.println("Could not connect or get data from the database for all albums" + sqex);
            return null;
        }
    }

    public ResultSet selectAllConsignors() {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM consignor");
            return rs;
        }
        catch (SQLException sqex){
            System.out.println("Could not connect or get data from the database for all consignors" + sqex);
            return null;
        }
    }

    public ResultSet selectAllBasement() {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM albums WHERE inBasement = 1");
            return rs;
        }
        catch (SQLException sqex){
            System.out.println("Could not connect or get data from the database for all albums in the basement" + sqex);
            return null;
        }
    }

    public ResultSet selectAlbumByConsignor(int id) {
        String consignorAlbum = "SELECT * FROM albums WHERE consignor = ?";
        try {
            ps = conn.prepareStatement(consignorAlbum);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            return rs;
        }
        catch (SQLException sqex){
            System.out.println("Could not connect or get data from the database for the searched consignor" + sqex);
            return null;
        }
    }

    public ResultSet selectAlbumBySong(String song) {
        String songAlbum = "SELECT * FROM albums WHERE song LIKE ?";
        try {
            ps = conn.prepareStatement(songAlbum);
            ps.setString(1, song + "%");        //Search for song directly or everything similar
            rs = ps.executeQuery();
            return rs;
        }
        catch (SQLException sqex){
            System.out.println("Could not connect or get data from the database for the searched albums" + sqex);
            return null;
        }
    }

    public ResultSet selectAlbumByArtist(String artist) {
        String songAlbum = "SELECT * FROM albums WHERE artist LIKE ?";
        try {
            ps = conn.prepareStatement(songAlbum);
            ps.setString(1, artist + "%");        //Search for song directly or everything similar
            rs = ps.executeQuery();
            return rs;
        }
        catch (SQLException sqex){
            System.out.println("Could not connect or get data from the database for the searched albums" + sqex);
            return null;
        }
    }

    public ResultSet selectConsignorByLast(String last) {
        String songAlbum = "SELECT * FROM consignor WHERE consignorLast LIKE ?";
        try {
            ps = conn.prepareStatement(songAlbum);
            ps.setString(1, last + "%");        //Search for song directly or everything similar
            rs = ps.executeQuery();
            return rs;
        }
        catch (SQLException sqex){
            System.out.println("Could not connect or get data from the database for the searched consignor" + sqex);
            return null;
        }
    }

    public ResultSet selectConsignorById(int id) {
        String consignorAlbum = "SELECT * FROM consignor WHERE idConsignor = ?";
        try {
            ps = conn.prepareStatement(consignorAlbum);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            return rs;
        }
        catch (SQLException sqex){
            System.out.println("Could not connect or get data from the database for the searched consignor" + sqex);
            return null;
        }
    }

    public ResultSet selectBasementByConsignor(int id) {
        String consignorAlbum = "SELECT * FROM albums WHERE consignor = ? AND inBasement = 1";
        try {
            ps = conn.prepareStatement(consignorAlbum);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            return rs;
        }
        catch (SQLException sqex){
            System.out.println("Could not connect or get data from the database for searched albums" + sqex);
            return null;
        }
    }

    public ResultSet selectBasementBySong(String song) {
        String songAlbum = "SELECT * FROM albums WHERE song LIKE ? AND inBasement = 1";
        try {
            ps = conn.prepareStatement(songAlbum);
            ps.setString(1, song + "%");        //Search for song directly or everything similar
            rs = ps.executeQuery();
            return rs;
        }
        catch (SQLException sqex){
            System.out.println("Could not connect or get data from the database for the searched albums" + sqex);
            return null;
        }
    }

    public ResultSet selectBasementByArtist(String artist) {
        String songAlbum = "SELECT * FROM albums WHERE artist LIKE ? AND inBasement = 1";
        try {
            ps = conn.prepareStatement(songAlbum);
            ps.setString(1, artist + "%");        //Search for song directly or everything similar
            rs = ps.executeQuery();
            return rs;
        }
        catch (SQLException sqex){
            System.out.println("Could not connect or get data from the database for the searched albums" + sqex);
            return null;
        }
    }

    public boolean addConsignor(String first, String last, String phone,
                                  String email, float owned, float paid) {
        String addConsignor = "INSERT INTO consignor VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
        boolean x = true;
        try {
            ps = conn.prepareStatement(addConsignor);
            ps.setString(1, first);        //Search for song directly or everything similar
            ps.setString(2, last);
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.setFloat(5, owned);
            ps.setFloat(6, paid);
            x = ps.execute();
            return x;       //returns false
        }
        catch (SQLException sqex){
            System.out.println("Could not connect or insert data into the database for the consignor" + sqex);
            return x;
        }
    }

    public boolean addAlbum(String artist, String song, String album, int consignor, float price) {
        //Set the received date to the current date
        String addConsignor = "INSERT INTO albums VALUES (DEFAULT, ?, ?, ?, CURDATE(), DEFAULT, ?, ?)";
        boolean x = true;
        try {
            ps = conn.prepareStatement(addConsignor);
            ps.setString(1, artist);        //Search for song directly or everything similar
            ps.setString(2, song);
            ps.setString(3, album);
            ps.setInt(4, consignor);
            ps.setFloat(5, price);
            x = ps.execute();
            return x;       //returns false
        }
        catch (SQLException sqex) {
            System.out.println("Could not connect or insert data into the database for the album" + sqex);
            return x;
        }
    }

    public ResultSet basementDue() {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM albums WHERE receivedDate < CURDATE() - 30 AND inBasement = 0");
            return rs;
        }
        catch (SQLException sqex){
            System.out.println("Could not connect or get data from the database for the basement dues" + sqex);
            return null;
        }
    }

    public void toBasement() {
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE albums SET inBasement = 1 WHERE receivedDate < CURDATE() - 30 AND inBasement = 0");
        }
        catch (SQLException sqex){
            System.out.println("Could not connect or update data into the database for the albums going into the basement" + sqex);
        }
    }

    // method to search the query for the number of consignors in the database
    public int countConsignors() {
        String songAlbum = "SELECT count(idConsignor) AS num FROM musicdb.consignor";
        Integer consignor = 0;
        try {
            ps = conn.prepareStatement(songAlbum);
            rs = ps.executeQuery();
            //get the resulting count
            while (rs.next()) {
                consignor = rs.getInt(1);
            }
            return consignor;
        }
        catch (SQLException sqex) {
            System.out.println("Could not connect to the database to retrieve the consigner number" + sqex);
            return 3;
        }
    }

}