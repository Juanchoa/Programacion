package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class Pet {
    private int identification;
    @Size(min=1,max=30)
    private String name;
    @Min(1)@Max(14)
    private byte age;
    @Pattern(regexp = "[MF]")
    private Gender gender;
    @NotEmpty
    private Race race;
    //private Location locationDep;
    //private Location locationMun;
}
