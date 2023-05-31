package service.model;
import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.Id;  
import javax.persistence.Table; 
import java.time.LocalDate;

@Entity
@Table(name = "ROOMS")
public class Room {

    @Id
    @Column
    private int id;

    @Column
    private String type;

    @Column
    private int beds;

    @Column(name = "BED_SIZE")
    private double bedSize;

    @Column
    private boolean balcony;

    @Column
    private String view;

    @Column
    private boolean accessible;

    @Column(name = "CHECK_IN_DATE")
    private LocalDate checkInDate;

    @Column(name = "CHECK_OUT_DATE")
    private LocalDate checkOutDate;

    @Column
    private double price;

    @Column
    private boolean booked;

    @Column
    private String hotel;

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public double getBedSize() {
        return bedSize;
    }

    public void setBedType(double bedSize) {
        this.bedSize = bedSize;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }
}
