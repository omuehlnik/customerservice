package de.omuehlnikel.tutorials.customerservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Customer {

    private String id;
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private Integer age;

}
