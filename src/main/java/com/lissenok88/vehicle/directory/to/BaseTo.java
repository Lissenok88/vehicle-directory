package com.lissenok88.vehicle.directory.to;

import com.lissenok88.vehicle.directory.HasId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public abstract class BaseTo implements HasId {
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
        BaseTo baseTo = (BaseTo) o;
        return id.equals(baseTo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
