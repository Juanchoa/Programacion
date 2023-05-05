package co.edu.umanizales.tads.service;


import co.edu.umanizales.tads.errors.Validation;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Species;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class SpeciesService {
    private List<Species> species;

    public SpeciesService(){

        species = new ArrayList<>();
        species.add(new Species("1","Mamiferos"));
        species.add(new Species("2","Aves"));
        species.add(new Species("3","Reptiles"));
        species.add(new Species("4","Peces"));
    }

    public Species getSpeciesByCode(String code)throws Validation {

        for(Species i: species){
            if(i.getCode().equals(code)){
                return i;
            }

        }
        throw new Validation("La especie no existe");
    }

}
