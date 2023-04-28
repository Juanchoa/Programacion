package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.AgeRanges;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AgeRangesDTO {
    private AgeRanges range;
    int quantity;
}
