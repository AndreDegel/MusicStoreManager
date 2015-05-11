package Andre;

import java.util.Date;

/**
 * Created by Andre on 5/1/2015.
 */
public class Album {

    protected String artist;
    protected String song;
    protected String album;
    protected Date received;
    protected int isBasement;
    protected int consignorId;
    protected float price;
    protected int albumId;
    protected final int NOID = -1; //Value to represent no ID known

    //TODO: May not need this but rather just the other one.
    Album (String artist, String song, String album, Date received, int isBasement,
           int consignorId, float price) {
        this.artist = artist;
        this.song = song;
        this.album = album;
        this.received = received;
        this.isBasement = isBasement;
        this.consignorId = consignorId;
        this.price = price;
        this.albumId = NOID;
    }

    Album (int id, String artist, String song, String album, Date received, int isBasement,
           int consignorId, float price) {
        this(artist, song, album, received, isBasement, consignorId, price);
        this.albumId = id;
    }

    public String getArtist() {
        return artist;
    }

    public String getSong() {
        return song;
    }

    public String getAlbum() {
        return album;
    }

    public Date getReceived() {
        return received;
    }

    public int getIsBasement() {
        return isBasement;
    }

    public int getConsignorId() {
        return consignorId;
    }

    public float getPrice() {
        return price;
    }

    public String toString() {
        String albumData = "ID: " + this.albumId + " Artist: " + this.artist + " Song: " + this.song + " Album: " + this.album + " ReceivedDate: " + this.received
                + " InBasement: " + this.isBasement + " Consignor ID: " + this.consignorId + " Price: " + this.price;
        return albumData;
    }
}
