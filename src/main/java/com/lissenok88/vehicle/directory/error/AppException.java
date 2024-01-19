package com.lissenok88.vehicle.directory.error;

import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
public class AppException extends RuntimeException {

    public AppException(@NonNull String message) {
        super(message);
    }
}
