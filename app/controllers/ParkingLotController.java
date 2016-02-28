package controllers;

import java.util.List;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.libs.Json;
import play.libs.Json.*;
import play.data.Form;
import play.db.jpa.*;

import model.ParkingLot;
import service.ParkingLotService;
import views.html.*;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ParkingLotController extends Controller {
    static Form<ParkingLot> parkingLotForm = Form.form(ParkingLot.class);

    /**
     * Get the index page
     *
     * @return Result
     */
    public Result index() {
        return ok(index.render("API REST for JAVA Play Framework"));
    }
    /**
     * Add the content-type json to response
     *
     * @param Result httpResponse
     *
     * @return Result
     */
    public Result jsonResult(Result httpResponse) {
        response().setContentType("application/json; charset=utf-8");
        return httpResponse;
    }

    /**
     * Get the ParkingLots with pagination
     *
     * @param Integer page
     * @param Integer size
     *
     * @return Result
     */
    @Transactional(readOnly = true)
    public Result list(Integer page, Integer size, String name) {
        List models = ParkingLotService.paginate(page-1, size, name);
        Long count = ParkingLotService.count();

        ObjectNode result = Json.newObject();
        result.put("data", Json.toJson(models));
        result.put("total", count);
        if (page > 1) result.put("link-prev", routes.ParkingLotController.list(page-1, size, name).toString());
        if (page*size < count) result.put("link-next", routes.ParkingLotController.list(page+1, size, name).toString());
        result.put("link-self", routes.ParkingLotController.list(page, size, name).toString());

        return jsonResult(ok(result));
    }

    /**
     * Get one ParkingLot by id
     *
     * @param Integer id
     *
     * @return Result
     */
    @Transactional(readOnly = true)
    public Result get(Integer id) {
        ParkingLot parkingLot = ParkingLotService.find(id);
        if (parkingLot == null ) {
            ObjectNode result = Json.newObject();
            result.put("error", "Not found " + id);
            return jsonResult(notFound(result));
        }
        return jsonResult(ok(Json.toJson(parkingLot)));
    }

    /**
     * Create an ParkingLot with the data of request
     *
     * @return Result
     */
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        Form<ParkingLot> parkingLot = parkingLotForm.bindFromRequest();
        if (parkingLot.hasErrors()) {
            return jsonResult(badRequest(parkingLot.errorsAsJson()));
        }
        ParkingLot newParkingLot = ParkingLotService.create(parkingLot.get());
        return jsonResult(created(Json.toJson(newParkingLot)));
    }

    /**
     * Update an ParkingLot with the data of request
     *
     * @return Result
     */
    @Transactional
    public Result update() {
        Form<ParkingLot> parkingLot = parkingLotForm.bindFromRequest();
        if (parkingLot.hasErrors()) {
            return jsonResult(badRequest(parkingLot.errorsAsJson()));
        }
        ParkingLot updatedParkingLot = ParkingLotService.update(parkingLot.get());
        return jsonResult(ok(Json.toJson(updatedParkingLot)));
    }

    /**
     * Delete an ParkingLot by id
     *
     * @param Integer id
     *
     * @return Result
     */
    @Transactional
    public Result delete(Integer id) {
        if (ParkingLotService.delete(id)) {
            ObjectNode result = Json.newObject();
            result.put("msg", "Deleted " + id);
            return jsonResult(ok(result));
        }
        ObjectNode result = Json.newObject();
        result.put("error", "Not found " + id);
        return jsonResult(notFound(result));
    }
}
