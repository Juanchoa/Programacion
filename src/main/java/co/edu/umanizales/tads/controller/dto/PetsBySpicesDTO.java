package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Species;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetsBySpicesDTO {
    private Species species;
    private int quantity;
}
