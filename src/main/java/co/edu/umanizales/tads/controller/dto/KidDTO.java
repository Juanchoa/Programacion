package co.edu.umanizales.tads.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class KidDTO {
    @NotNull(message = "No se puede agregar un niño sin identificación")
    @Positive(message = "La identificaion solo admite valores positivos ")
    private int identification;
    @Size(min=1,max=30,message = "El nombre debe tener minimo 1 carecter y maximo 30 caracteres")
    @NotBlank(message = "No se puede agregar un niño sin nombre")
    private String name;
    @Min(value = 1,message = "La edad tiene que ser mayor a 0")@Max(value = 14,message = "La edad tiene que ser menor de 15")
    @NotNull(message = "No se puede agregar un niño sin edad")
    private byte age;
    @NotNull(message = "No se puede agregar un niño son codigo de genero")
    @NotBlank
    private String genderCode;
    @NotNull(message = "No se puede agregar un niño sin departamento")
    @NotBlank
    private String codeLocationDep;
    @NotNull(message = "No se puede agregar un niño sin municipio")
    @NotBlank
    private String codeLocationMun;

}
