package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.Gender;
import co.edu.umanizales.tads.model.Location;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class GenderService {
    private List<Gender> genders;

    public GenderService(){

        genders= new ArrayList<>();
        genders.add(new Gender('M',"1"));
        genders.add(new Gender('F',"2"));
    }

    public Gender getGenderByGenderCode(String genderCode){

        for(Gender gen: genders){
            if(gen.getCodeGender().equals(genderCode)){
                return gen;
            }
        }
        return null;
    }
}
