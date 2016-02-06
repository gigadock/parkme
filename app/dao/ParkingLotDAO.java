package dao;

import play.*;
import play.mvc.*;
import play.db.jpa.*;
import java.util.List;
import java.util.Date;
import model.ParkingLot;

import javax.persistence.*;

public class ParkingLotDAO {
    /**
     * Create an ParkingLot
     *
     * @param ParkingLot model
     *
     * @return ParkingLot
     */
    public static ParkingLot create (ParkingLot model) {
        model.emptyToNull();
        JPA.em().persist(model);
        // Flush and refresh for check
        JPA.em().flush();
        JPA.em().refresh(model);
        return model;
    }

    /**
     * Find an ParkingLot by id
     *
     * @param Integer id
     *
     * @return ParkingLot
     */
    public static ParkingLot find(Integer id) {
        return JPA.em().find(ParkingLot.class, id);
    }

    /**
     * Update an ParkingLot
     *
     * @param ParkingLot model
     *
     * @return ParkingLot
     */
    public static ParkingLot update(ParkingLot model) {
        return JPA.em().merge(model);
    }

    /**
     * Delete an ParkingLot by id
     *
     * @param Integer id
     */
    public static void delete(Integer id) {
        ParkingLot model = JPA.em().getReference(ParkingLot.class, id);
        JPA.em().remove(model);
    }

    /**
     * Get all ParkingLots
     *
     * @return List<ParkingLot>
     */
    public static List<ParkingLot> all() {
        return (List<ParkingLot>) JPA.em().createQuery("SELECT m FROM " + ParkingLot.TABLE + " m ORDER BY id").getResultList();
    }

    /**
     * Get the page of ParkingLots
     *
     * @param Integer page
     * @param Integer size
     *
     * @return List<ParkingLot>
     */
    public static List<ParkingLot> paginate(Integer page, Integer size) {
        return (List<ParkingLot>) JPA.em().createQuery("SELECT m FROM " + ParkingLot.TABLE + " m ORDER BY id").setFirstResult(page*size).setMaxResults(size).getResultList();
    }

    /**
     * Get the number of total row
     *
     * @return Long
     */
    public static Long count() {
        return (Long) JPA.em().createQuery("SELECT count(m) FROM " + ParkingLot.TABLE + " m").getSingleResult();
    }
}