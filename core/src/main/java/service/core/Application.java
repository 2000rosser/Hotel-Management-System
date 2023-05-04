package service.core;

import java.util.ArrayList;

public class Application {
    private static int COUNTER = 1000;
    public int id;
    public RoomInfo info;
    public ArrayList<Quotation> quotations;

    public Application(RoomInfo info) {
        this.id = COUNTER++;
        this.info = info;
        this.quotations = new ArrayList<>();
    }

    public Application() {
    }
}
