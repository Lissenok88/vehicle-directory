package com.lissenok88.vehicle.directory.util;

import com.lissenok88.vehicle.directory.Identifiable;
import com.lissenok88.vehicle.directory.error.InvalidVehicleDTOException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {

    public static void checkNew(Identifiable bean) {
        if (!bean.isNew()) {
            throw new InvalidVehicleDTOException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(Identifiable bean, long id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new InvalidVehicleDTOException(bean.getClass().getSimpleName() + " must has id=" + id);
        }
    }

    public static void checkModification(int count, int id) {
        if (count == 0) {
            throw new InvalidVehicleDTOException("Entity with id=" + id + " not found");
        }
    }
}
