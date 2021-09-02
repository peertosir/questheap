package dev.peertosir.questheap.dto.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatedEntityIdTo {
    private long id;

    @JsonCreator
    public CreatedEntityIdTo(@JsonProperty("id") long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
