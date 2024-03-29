package com.lissenok88.vehicle.directory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;

public interface Identifiable {
    Long getId();

    void setId(Long id);

    @JsonIgnore
    default boolean isNew() {
        return getId() == null;
    }

    default long id() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}
