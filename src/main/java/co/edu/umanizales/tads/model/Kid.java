package co.edu.umanizales.tads.model;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.*;
import java.beans.JavaBean;

@Data
@AllArgsConstructor
public class Kid {
    private int identification;
    private String name;
    private byte age;
    private Gender gender;
    private Location locationDep;
    private Location locationMun;


    public int getIdentification() {
        return identification;
    }


}
