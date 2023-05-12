package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.errors.Validation;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.model.Species;
import co.edu.umanizales.tads.service.CircularListDEService;
import co.edu.umanizales.tads.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/circular_listde")
public class CircularListDEController {
    @Autowired
    private CircularListDEService circularListDEService;
    @Autowired
    private SpeciesService speciesService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPets(){
        return new ResponseEntity<>(new ResponseDTO(200,circularListDEService.getPets().seePets(),null), HttpStatus.OK);
    }

    @PostMapping("/add_pet_to_start")
    public ResponseEntity<ResponseDTO> addPetToStart(@RequestBody @Valid PetDTO petDTO){
        boolean clean = false;
        Species species = null;
        try {
            species = speciesService.getSpeciesByCode(petDTO.getCodeSpecies());
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        try {
            circularListDEService.getPets().addPetToStart(
                    new Pet(petDTO.getIdentification(),
                            petDTO.getName(), petDTO.getAge(),
                            petDTO.getGender(),species,clean));
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    400,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"se ha añadido la mascota al inicio",
                null), HttpStatus.OK);
    }
    @PostMapping("/add_pet_to_end")
    public ResponseEntity<ResponseDTO> addPetToEnd(@RequestBody @Valid PetDTO petDTO){
        boolean clean = false;
        Species species = null;
        try {
            species = speciesService.getSpeciesByCode(petDTO.getCodeSpecies());
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    400,e.getMessage(),
                    null), HttpStatus.OK);
        }
        try {
            circularListDEService.getPets().addPetToEnd(
                    new Pet(petDTO.getIdentification(),
                            petDTO.getName(), petDTO.getAge(),
                            petDTO.getGender(),species,clean));
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    400,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"se ha añadido la mascota al final",null), HttpStatus.OK);
    }
    @PostMapping("/add_pet_in_position/{position}")
    public ResponseEntity<ResponseDTO> addPetInPosition(@RequestBody PetDTO petDTO, @PathVariable int position){
        boolean clean = false;
        Species species = null;
        try {
            species = speciesService.getSpeciesByCode(petDTO.getCodeSpecies());
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    400,e.getMessage(),
                    null), HttpStatus.OK);
        }
        try {
            circularListDEService.getPets().addPetInPosition(position,
                    new Pet(petDTO.getIdentification(),
                            petDTO.getName(), petDTO.getAge(),
                            petDTO.getGender(),species,clean));
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    400,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"se ha añadido la mascota en la posición "+ position +".",null), HttpStatus.OK);
    }


}
