package com.lissenok88.vehicle.directory.web;

import com.lissenok88.vehicle.directory.mapper.VehicleMapper;
import com.lissenok88.vehicle.directory.model.Vehicle;
import com.lissenok88.vehicle.directory.repository.VehicleRepository;
import com.lissenok88.vehicle.directory.to.VehicleTo;
import com.lissenok88.vehicle.directory.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lissenok88.vehicle.directory.util.JsonUtil.writeValue;
import static com.lissenok88.vehicle.directory.web.VehicleTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VehicleControllerTest extends AbstractControllerTest{

    private static final String REST_URL = VehicleController.REST_URL;

    @Autowired
    private VehicleRepository repository;
    @Autowired
    private VehicleMapper mapper;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + VEHICLE_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VEHICLE_TO_MATCHER.contentJson(VEHICLE_TO_1));
    }

    @Test
    void getAllByCategory() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/by-category")
                .param("category", "B"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VEHICLE_TO_MATCHER.contentJson(VEHICLE_TO_1, VEHICLE_TO_2, VEHICLE_TO_3, VEHICLE_TO_4, VEHICLE_TO_5));

    }

    @Test
    void getAllByModel() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/by-model")
                .param("model", "Corolla"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VEHICLE_TO_MATCHER.contentJson(List.of(VEHICLE_TO_2)));
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VEHICLE_TO_MATCHER.contentJson(VEHICLE_TO_1, VEHICLE_TO_2, VEHICLE_TO_3, VEHICLE_TO_4, VEHICLE_TO_5));
    }

    @Test
    void getByFilter() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/filter")
                .param("make", "Toyota"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VEHICLE_TO_MATCHER.contentJson(VEHICLE_TO_1, VEHICLE_TO_2, VEHICLE_TO_4));
    }

    @Test
    void update() throws Exception {
        VehicleTo updated = getUpdate();
        perform(MockMvcRequestBuilders.put(REST_URL + "/" + VEHICLE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        VEHICLE_TO_MATCHER.assertMatch(mapper.toTo(repository.getExisted(VEHICLE_ID)), getUpdate());
    }

    @Test
    @Transactional
    void createWithLocation()throws Exception {
        VehicleTo newTo = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newTo)))
                .andExpect(status().isCreated());

        Vehicle created = VEHICLE_MATCHER.readFromJson(action);
        long newId = created.id();
        Vehicle newVehicle = new Vehicle(newId, newTo.getStateNumber(), newTo.getMake(), newTo.getModel(), newTo.getCategory(), newTo.getType(), newTo.getYear(), newTo.getTrailer());
        VEHICLE_MATCHER.assertMatch(created, newVehicle);
        VEHICLE_MATCHER.assertMatch(repository.getExisted(created.id()), newVehicle);
    }

    @Test
    @Transactional
    void createSameStateNumber() throws Exception{
        VehicleTo newTo = new VehicleTo(null, VEHICLE_TO_1.getStateNumber(), "Toyota", "Camry", "B", "Седан", "2020", "NO");
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newTo)))
                .andExpect(status().isUnprocessableEntity());
    }
}