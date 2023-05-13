package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class Pet {

    private int identification;

    private String name;

    private byte age;

    private String gender;

    private Species species;

    private boolean clean;

    public boolean isClean() {
        return clean;
    }

    public void setClean(boolean clean) {
        this.clean = clean;
    }
}
