package com.lissenok88.vehicle.directory.controller;

import com.lissenok88.vehicle.directory.dto.VehicleDTO;
import com.lissenok88.vehicle.directory.model.Vehicle;

public class VehicleTestData {

    public static final MatcherFactory.Matcher<VehicleDTO> VEHICLE_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(VehicleDTO.class);
    public static final MatcherFactory.Matcher<Vehicle> VEHICLE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vehicle.class);


    public static final long VEHICLE_ID = 1L;

    public static final VehicleDTO VEHICLE_TO_1 = new VehicleDTO(VEHICLE_ID, "А154ММ54", "Toyota", "Camry", "B", "Седан", "2015", "NO");
    public static final VehicleDTO VEHICLE_TO_2 = new VehicleDTO(VEHICLE_ID + 1, "Л222КМ88", "Toyota", "Corolla", "B", "Седан", "2018", "NO");
    public static final VehicleDTO VEHICLE_TO_3 = new VehicleDTO(VEHICLE_ID + 2, "К888ТТ99", "LADA", "NIVA", "B", "Внедорожник", "2010", "YES");
    public static final VehicleDTO VEHICLE_TO_4 = new VehicleDTO(VEHICLE_ID + 3, "А154ММ54", "Toyota", "Land Cruiser", "B", "Внедорожник", "2018", "NO");
    public static final VehicleDTO VEHICLE_TO_5 = new VehicleDTO(VEHICLE_ID + 4, "А555АА55", "Lexus", "ES", "B", "Седан", "2020", "NO");

    public static VehicleDTO getNew() {
        return new VehicleDTO(null, "А154ЛЛ54", "Toyota", "Camry", "B", "Седан", "2020", "NO");
    }

    public static VehicleDTO getUpdate() {
        return new VehicleDTO(VEHICLE_ID, "А154MM54", "Toyota", "Camry", "B", "Седан", "2016", "NO");
    }
}
