package fr.uge.jee.springmvc.reststudents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Student(
    @JsonIgnore long uid,
    @JsonProperty("first_name") String firstName,
    @JsonProperty("last_name") String lastName
    ){}