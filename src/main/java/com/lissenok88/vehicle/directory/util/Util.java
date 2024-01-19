package com.lissenok88.vehicle.directory.util;

import com.lissenok88.vehicle.directory.error.NotFoundException;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class Util {
    public static <T> T checkExist(long id, Optional<T> opt) {
        return opt.orElseThrow(() -> new NotFoundException("Entity with id=" + id + " not found"));
    }
}
