/**
 * Created by Andre on 5/1/2015.
 */
package Andre;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

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

    public void createTable(){
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("CREATE Table BOB (id INT)");
            System.out.println("There is BOB");



        }
        catch (Exception ex) {
            System.out.println("HERE" + ex);
        }
    }

    public ResultSet selectAllAlbums() {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM albums");
            return rs;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public ResultSet selectAllConsignors() {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM consignor");
            return rs;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public ResultSet selectAllBasement() {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM albums WHERE inBasement = 1");
            return rs;
        }
        catch (Exception e){
            System.out.println(e);
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
        catch (Exception e){
            System.out.println(e);
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
        catch (Exception e){
            System.out.println(e);
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
        catch (Exception e){
            System.out.println(e);
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
        catch (Exception e){
            System.out.println(e);
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
        catch (Exception e){
            System.out.println(e);
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
        catch (Exception e){
            System.out.println(e);
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
        catch (Exception e){
            System.out.println(e);
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
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}