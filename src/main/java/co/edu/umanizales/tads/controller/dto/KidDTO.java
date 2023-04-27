package co.edu.umanizales.tads.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class KidDTO {
    private int identification;
    private String name;
    private byte age;
    private String genderCode;
    private String codeLocationDep;
    private String codeLocationMun;

}
