package com.lissenok88.vehicle.directory.dto;

import com.lissenok88.vehicle.directory.Identifiable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public abstract class BaseDTO implements Identifiable {
    @Schema(hidden = true)
    protected Long id;

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDTO baseDTO = (BaseDTO) o;
        return id.equals(baseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
