package model;

import javax.persistence.*;
import play.data.validation.Constraints;
import play.data.format.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class ParkingLot {
    public static String TABLE = ParkingLot.class.getSimpleName();

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer id;

    
    public String address2;
    public String hoursOfOperation;
    public String rates;
    public Date dateCreated = new Date();

    @Constraints.Required
    public String name;
    public String address1;
    public String city;
    public String state;
    public String zip;
    public double longitude;
    public double latitude;
    public long totalSpots;

    


    public ParkingLot() {}
    public ParkingLot(String name 
         , String address1, String address2, 
         String city, String state, String zip, Double longitude, Double latitude, Long totalSpots,
        String hoursOfOperation, String rates
    ) { 
        this.name = name;
        this.address1 = address1; 
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.longitude = longitude; 
        this.latitude = latitude; 
        this.totalSpots = totalSpots; 
        this.hoursOfOperation = hoursOfOperation;
        this.rates = rates;
        this.dateCreated = new Date();

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Set all empty values to null
     */
    public void emptyToNull() {
        if (name != null && name.isEmpty()) name = null;
        if (address1 != null && address1.isEmpty()) address1 = null;
        if (address2 != null && address2.isEmpty()) address2 = null;
        if (city != null && city.isEmpty()) city = null;
        if (state != null && state.isEmpty()) state = null;
        if (zip != null && zip.isEmpty()) zip = null;
        if (hoursOfOperation != null && hoursOfOperation.isEmpty()) hoursOfOperation = null;
        if (rates != null && rates.isEmpty()) rates = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        ParkingLot aux = (ParkingLot) obj;

        if (id != null && aux.id != null)
            return (id == aux.id);
        else
            return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return this.name;
    }
}