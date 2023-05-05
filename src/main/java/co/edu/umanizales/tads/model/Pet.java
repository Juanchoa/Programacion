package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class Pet {
    @NotNull(message = "No se puede agregar una mascota sin identificación")
    @Positive(message = "La identificaion solo admite valores positivos ")
    private int identification;
    @Size(min=1,max=30,message = "El nombre debe tener minimo 1 carecter y maximo 30 caracteres")
    @NotBlank(message = "No se puede agregar una mascota sin nombre")
    private String name;
    @Min(value = 1,message = "La edad tiene que ser mayor a 0")@Max(value = 14,message = "La edad tiene que ser menor de 15")
    @NotNull(message = "No se puede agregar una mascota sin edad")
    private byte age;
    @Pattern(regexp = "[MF]",message = "El genero solo puede ser ´F´ (masculino) o ´F´ (femenino)")
    private String gender;
    @NotNull(message = "No se puede agregar una mascota sin especie")
    private Species species;
}
