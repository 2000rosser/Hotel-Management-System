package service.model;
import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.Id;  
import javax.persistence.Table; 
import java.time.LocalDate;

@Entity
@Table(name = "BOOKINGS")
public class Bookings {

    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

    @Id
    @Column(name = "booking_ref")
    private int bookingRef;

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

    // getters and setters
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setBookingRef(int bookingRef) {
        this.bookingRef = bookingRef;
    }

    public int getBookingRef() {
        return bookingRef;
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

    public void setBedSize(double bedSize) {
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
}
