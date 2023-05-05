package co.edu.umanizales.tads.controller.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PetDTO {
    @NotNull(message = "No se puede agregar una mascota sin identificación")
    @Positive(message = "La identificaion solo admite valores positivos ")
    private int identification;
    @Size(min=1,max=30,message = "El nombre debe tener minimo 1 carecter y maximo 30 caracteres")
    @NotBlank(message = "No se puede agregar una mascota sin nombre")
    private String name;
    @Min(value = 1,message = "La edad tiene que ser mayor a 0")
    @Max(value = 14,message = "La edad tiene que ser menor de 15")
    @NotNull(message = "No se puede agregar una mascota sin edad")
    private byte age;
    @NotNull(message = "No se puede agregar una mascota sin codigo de genero")
    @Pattern(regexp = "[MF]",message = "Solo se acepta M(Macho) y F(Hembra).")
    private String gender;
    @NotBlank(message = "No se puede agregar una mascota sin especie")
    private String codeSpecies;
}
