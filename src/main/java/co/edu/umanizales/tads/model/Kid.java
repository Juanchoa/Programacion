package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.beans.JavaBean;

@Data
@AllArgsConstructor
public class Kid {
    private int identification;
    @Size(min=1,max=30)
    private String name;
    @Min(1)@Max(14)
    private byte age;
    @Pattern(regexp = "[MF]")
    private Gender gender;
    private Location locationDep;
    private Location locationMun;


    public int getIdentification() {
        return identification;
    }

    //ValidatorFactory validator = Validation.buildDefaultValidatorFactory();
}
