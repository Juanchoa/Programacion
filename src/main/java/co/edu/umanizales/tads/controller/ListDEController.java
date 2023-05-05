package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.errors.Validation;
import co.edu.umanizales.tads.model.*;
import co.edu.umanizales.tads.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;
    @Autowired
    private AgeRangesService ageRangesService;
    @Autowired
    private SpeciesService speciesService;


    @GetMapping
    public ResponseEntity<ResponseDTO> getPets(){
        try {
            return new ResponseEntity<>(new ResponseDTO(
                    200,listDEService.getPets().seePets(),null), HttpStatus.OK);
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
    }

    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert(){
        try {
            listDEService.getPets().invert();
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha invertido la lista.",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        try {
            listDEService.getPets().changeExtremes();
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se han intercambiado los extremos.",
                null), HttpStatus.OK);
    }

    @PostMapping(path = "/add_pet_to_start")
    public ResponseEntity<ResponseDTO> addPetToStar(@RequestBody @Valid PetDTO petDTO){
        Species species = null;
        try {
            species = speciesService.getSpeciesByCode(petDTO.getCodeSpecies());
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        try {
            listDEService.getPets().addPetToStart(
                    new Pet(petDTO.getIdentification(),
                            petDTO.getName(), petDTO.getAge(),
                            petDTO.getGender(),species));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(
                    400,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado la mascota.",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/add_pet_to_end")
    public ResponseEntity<ResponseDTO> addPetToEnd(@RequestBody @Valid PetDTO petDTO){
        Species species = null;
        try {
            species = speciesService.getSpeciesByCode(petDTO.getCodeSpecies());
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        try {
            listDEService.getPets().addPetToEnd(
                    new Pet(petDTO.getIdentification(),
                            petDTO.getName(), petDTO.getAge(),
                            petDTO.getGender(),species));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(
                    400,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado la mascota.",
                null), HttpStatus.OK);
    }

    @PostMapping(path = "/add_in_position/{number}")
    public ResponseEntity<ResponseDTO> addInPosition(@RequestBody @Valid PetDTO petDTO,@PathVariable int number){
        Species species = null;
        try {
            species = speciesService.getSpeciesByCode(petDTO.getCodeSpecies());
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        if(species == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La especie no existe.",
                    null), HttpStatus.OK);
        }

        try {
            listDEService.getPets().addPetInPosition(number,
                    new Pet(petDTO.getIdentification(),
                            petDTO.getName(), petDTO.getAge(),
                            petDTO.getGender(),species));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(
                    400,e.getMessage(),
                    null), HttpStatus.OK);

        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado la mascota.",
                null), HttpStatus.OK);

    }
    @GetMapping(path = "/delete_pet_by_identification/{number}")
    public ResponseEntity<ResponseDTO> deletePetIdentification(@PathVariable int number){

            try {
                listDEService.getPets().deletePetById(number);
            } catch (Validation e) {
                return new ResponseEntity<>(new ResponseDTO(
                        200,e.getMessage(),
                        null), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ResponseDTO(
                    200,"Se ha eliminado la mascota.",
                    null), HttpStatus.OK);

    }
    @GetMapping(path = "/mix_pets")
    public ResponseEntity<ResponseDTO> mixPets(){
        try {
            listDEService.getPets().mixPets();
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"se han mezclado las mascotas.",
                null), HttpStatus.OK);
    }
    @GetMapping(path="/boys_first_girls_at_the_end")
    public ResponseEntity<ResponseDTO> boyStartGirlsLast(){
        try {
            listDEService.getPets().orderBoysToStart();
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "Las macotas macho se han movido al inicio.",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/delete_pets_by_age/{age}")
    public ResponseEntity<ResponseDTO> deletePetsByAge(@PathVariable int age){
        try {
            listDEService.getPets().deletePetByAge(age);
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "Se han eliminado todas las mascotas que tienen "+ age +" años.",
                null), HttpStatus.OK);
    }


    @GetMapping(path = "/lose_positions/{identification}/{positions}")
    public ResponseEntity<ResponseDTO> losePositions(@PathVariable int identification,@PathVariable int positions){

        try {
            listDEService.getPets().losePositions(identification,positions);
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    400,e.getMessage(),
                    null), HttpStatus.OK);
            }
        return new ResponseEntity<>(new ResponseDTO(
                    200,"La mascota ha perdido "+ positions +" posiciones.",
                    null), HttpStatus.OK);

    }
    @GetMapping(path = "/win_positions/{identification}/{positions}")
    public ResponseEntity<ResponseDTO> winPositions(@PathVariable int identification, @PathVariable int positions){

        try {
            listDEService.getPets().winPositions(identification,positions);
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    400,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                    200,"La mascota ha ganado "+ positions +" posiciones.",
                    null), HttpStatus.OK);
    }

    @GetMapping(path = "/pets_species_count")
    public ResponseEntity<ResponseDTO> getPetsSpecies(){
        List<PetsBySpicesDTO> petsBySpicesDTOList = new ArrayList<>();
        for(Species species: speciesService.getSpecies()){
            int count = listDEService.getPets().getCountPetsSpicesByCode(species.getCode());
            if(count>0){
                petsBySpicesDTOList.add(new PetsBySpicesDTO(species,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,petsBySpicesDTOList,
                null), HttpStatus.OK);
    }

    @GetMapping(path="/average_pets_age")
    public ResponseEntity<ResponseDTO> averageAge(){
        return new ResponseEntity<>(new ResponseDTO(200,
                listDEService.getPets().averagePetsAge(), null), HttpStatus.OK);
    }
    @GetMapping(path="/add_at_the_end_by_initial_letter/{letter}")
    public ResponseEntity<ResponseDTO> addAtTheEndByInitialLetter(@PathVariable String letter){
        try {
            listDEService.getPets().addPetAtTheEndByInicialName(letter);
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    400,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,
                "Se han adicionado al final las mascotas que empiezan por la letra "+letter, null), HttpStatus.OK);
    }

    @GetMapping(path = "/get_pets_range_by_age")

    public ResponseEntity<ResponseDTO> getRangePetsByAge() {

        List<AgeRangesDTO> ageRangesDTOList = new ArrayList<>();

        for (AgeRanges ranges : ageRangesService.getRanges()) {
            int quantity = 0;
            try {
                quantity = listDEService.getPets().getPetsRangeAge(ranges.getMin(), ranges.getMax());
            } catch (Validation e) {
                return new ResponseEntity<>(new ResponseDTO(
                        400,e.getMessage(),
                        null), HttpStatus.OK);
            }
            if(quantity>0){
                ageRangesDTOList.add(new AgeRangesDTO(ranges, quantity));}
        }
        return new ResponseEntity<>(new ResponseDTO(200, ageRangesDTOList, null),
                HttpStatus.OK);

    }




}
