package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.model.*;
import co.edu.umanizales.tads.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(new ResponseDTO(
                200,listDEService.getPets().seePets(),null), HttpStatus.OK);
    }

    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert(){
        listDEService.getPets().invert();
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha invertido la lista.",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        listDEService.getPets().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se han intercambiado los extremos.",
                null), HttpStatus.OK);
    }

    @PostMapping(path = "/add_pet_to_start")
    public ResponseEntity<ResponseDTO> addPetToStar(@RequestBody PetDTO petDTO){
        Species species = speciesService.getSpeciesByCode(petDTO.getCodeSpecies());
        int number = listDEService.getPets().checkIdentificationPet(petDTO.getIdentification());
        if(species == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La especie no existe.",
                    null), HttpStatus.OK);
        }
        if(number == 1){
            return new ResponseEntity<>(new ResponseDTO(
                    400,"La mascota ya existe.",
                    null), HttpStatus.OK);
        }
        listDEService.getPets().addPetToStart(
                new Pet(petDTO.getIdentification(),
                        petDTO.getName(), petDTO.getAge(),
                        petDTO.getGender(),species));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado la mascota.",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/add_pet_to_end")
    public ResponseEntity<ResponseDTO> addKidToEnd(@RequestBody PetDTO petDTO){
        Species species = speciesService.getSpeciesByCode(petDTO.getCodeSpecies());
        int number = listDEService.getPets().checkIdentificationPet(petDTO.getIdentification());
        if(species == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La especie no existe.",
                    null), HttpStatus.OK);
        }
        if(number == 1){
            return new ResponseEntity<>(new ResponseDTO(
                    400,"La mascota ya existe.",
                    null), HttpStatus.OK);
        }
        listDEService.getPets().addPetToEnd(
                new Pet(petDTO.getIdentification(),
                        petDTO.getName(), petDTO.getAge(),
                        petDTO.getGender(),species));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado la mascota.",
                null), HttpStatus.OK);
    }

    @PostMapping(path = "/add_in_position/{number}")
    public ResponseEntity<ResponseDTO> addInPosition(@RequestBody PetDTO petDTO,@PathVariable int number){
        Species species = speciesService.getSpeciesByCode(petDTO.getCodeSpecies());
        int idPet = listDEService.getPets().checkIdentificationPet(petDTO.getIdentification());
        if(species == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La especie no existe.",
                    null), HttpStatus.OK);
        }
        if(idPet == 1){
            return new ResponseEntity<>(new ResponseDTO(
                    400,"La mascota ya existe.",
                    null), HttpStatus.OK);
        }
        listDEService.getPets().addPetInPosition(number,
                new Pet(petDTO.getIdentification(),
                        petDTO.getName(), petDTO.getAge(),
                        petDTO.getGender(),species));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado la mascota.",
                null), HttpStatus.OK);

    }
    @GetMapping(path = "/delete_pet_by_identification/{number}")
    public ResponseEntity<ResponseDTO> deleteKidIdentification(@PathVariable int number){

        int iden = listDEService.getPets().checkIdentificationPet(number);

        if(iden == 0){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"El la mascota no existe.",
                    null), HttpStatus.OK);
        }
        if(iden == 1){
            listDEService.getPets().deletePetById(number);
            return new ResponseEntity<>(new ResponseDTO(
                    200,"Se ha eliminado la mascota.",
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                404,"La mascota no existe.",
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/mix_pets")
    public ResponseEntity<ResponseDTO> mixKids(){

        if(listDEService.getPets().getHead().getData() == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"No hay datos que mezclar.",
                    null), HttpStatus.OK);
        }

        listDEService.getPets().mixPets();
        return new ResponseEntity<>(new ResponseDTO(
                200,"se han mezclado las mascotas.",
                null), HttpStatus.OK);
    }
    @GetMapping(path="/boys_first_girls_at_the_end")
    public ResponseEntity<ResponseDTO> boyStartGirlsLast(){
        listDEService.getPets().orderBoysToStart();
        return new ResponseEntity<>(new ResponseDTO(200, "Las macotas macho se han movido al inicio.",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/delete_pets_by_age/{age}")
    public ResponseEntity<ResponseDTO> deletePetsByAge(@PathVariable int age){
        listDEService.getPets().deletePetByAge(age);
        return new ResponseEntity<>(new ResponseDTO(200, "Se han eliminado todas las mascotas que tienen "+ age +" años.",
                null), HttpStatus.OK);
    }


    @GetMapping(path = "/lose_positions/{identification}/{positions}")
    public ResponseEntity<ResponseDTO> losePositions(@PathVariable int identification,@PathVariable int positions){

        int iden = listDEService.getPets().checkIdentificationPet(identification);

        if(iden == 0){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La mascota no existe",
                    null), HttpStatus.OK);
        }
        if(iden == 1){
            listDEService.getPets().losePositions(identification,positions);
            return new ResponseEntity<>(new ResponseDTO(
                    200,"La mascota ha perdido "+ positions +" posiciones.",
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                404,"La mascota no existe",
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/win_positions/{identification}/{positions}")
    public ResponseEntity<ResponseDTO> winPositions(@PathVariable int identification, @PathVariable int positions){

        int iden = listDEService.getPets().checkIdentificationPet(identification);

        if(iden == 0){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La mascota no existe",
                    null), HttpStatus.OK);
        }
        if(iden == 1){
            listDEService.getPets().winPositions(identification,positions);
            return new ResponseEntity<>(new ResponseDTO(
                    200,"La mascota ha ganado "+ positions +" posiciones.",
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                404,"La mascota no existe",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/pets_species_count")
    public ResponseEntity<ResponseDTO> getKidsMunByLocation(){
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
        listDEService.getPets().addPetAtTheEndByInicialName(letter);
        return new ResponseEntity<>(new ResponseDTO(200,
                "Se han adicionado al final las mascotas que empiezan por la letra "+letter, null), HttpStatus.OK);
    }

    @GetMapping(path = "/get_pets_range_by_age")

    public ResponseEntity<ResponseDTO> getRangeByKids() {

        List<AgeRangesDTO> ageRangesDTOList = new ArrayList<>();

        for (AgeRanges ranges : ageRangesService.getRanges()) {
            int quantity = listDEService.getPets().getPetsRangeAge(ranges.getMin(), ranges.getMax());
            if(quantity>0){
                ageRangesDTOList.add(new AgeRangesDTO(ranges, quantity));}
        }
        return new ResponseEntity<>(new ResponseDTO(200, ageRangesDTOList, null),
                HttpStatus.OK);

    }




}
