package co.edu.umanizales.tads.controller.dto;

import lombok.Data;

@Data
public class PetDTO {
    private int identification;
    private String name;
    private byte age;
    private char gender;
    private String codeSpecies;
}
