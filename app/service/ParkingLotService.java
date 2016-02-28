package service;

import play.*;
import play.mvc.*;
import play.db.jpa.*;
import java.util.List;
import model.ParkingLot;
import dao.ParkingLotDAO;

public class ParkingLotService {
    /**
     * Create an ParkingLot
     *
     * @param ParkingLot data
     *
     * @return ParkingLot
     */
    public static ParkingLot create(ParkingLot data) {
        return ParkingLotDAO.create(data);
    }

    /**
     * Update an ParkingLot
     *
     * @param ParkingLot data
     *
     * @return ParkingLot
     */
    public static ParkingLot update(ParkingLot data) {
        return ParkingLotDAO.update(data);
    }

    /**
     * Find an ParkingLot by id
     *
     * @param Integer id
     *
     * @return ParkingLot
     */
    public static ParkingLot find(Integer id) {
        return ParkingLotDAO.find(id);
    }

    /**
     * Delete an ParkingLot by id
     *
     * @param Integer id
     */
    public static Boolean delete(Integer id) {
        ParkingLot ParkingLot = ParkingLotDAO.find(id);
        if (ParkingLot != null) {
            ParkingLotDAO.delete(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get all ParkingLots
     *
     * @return List<ParkingLot>
     */
    public static List<ParkingLot> all() {
        return ParkingLotDAO.all();
    }

    /**
     * Get the page of ParkingLots
     *
     * @param Integer page
     * @param Integer size
     *
     * @return List<ParkingLot>
     */
    public static List<ParkingLot> paginate(Integer page, Integer size, String name) {
        return ParkingLotDAO.paginate(page, size, name);
    }

    /**
     * Get the number of total of ParkingLots
     *
     * @return Long
     */
    public static Long count() {
        return ParkingLotDAO.count();
    }
}