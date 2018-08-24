package io.github.squarespheres.oslobikes.jsonmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@ToString
public abstract class Station {

    @Getter
    @JsonProperty("id")
    protected int id;

}
