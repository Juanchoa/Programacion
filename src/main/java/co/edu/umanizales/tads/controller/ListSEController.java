package co.edu.umanizales.tads.controller;
import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.errors.Validation;
import co.edu.umanizales.tads.model.AgeRanges;
import co.edu.umanizales.tads.model.Gender;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.AgeRangesService;
import co.edu.umanizales.tads.service.GenderService;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/listse")

public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private GenderService genderService;
    @Autowired
    private AgeRangesService ageRangesService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids(),null), HttpStatus.OK);

    }

    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert(){
        try {
            listSEService.invert();
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
            listSEService.getKids().changeExtremes();
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se han intercambiado los extremos.",
                null), HttpStatus.OK);
    }




    @PostMapping(path = "/add_kid_to_start")
    public ResponseEntity<ResponseDTO> addKidToStar(@RequestBody @Valid KidDTO kidDTO){
        Location locationDep = null;
        try {
            locationDep = locationService.getLocationByCode(kidDTO.getCodeLocationDep());
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        Location locationMun = null;
        try {
            locationMun = locationService.getLocationByCode(kidDTO.getCodeLocationMun());
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        Gender gender = null;
        try {
            gender = genderService.getGenderByGenderCode(kidDTO.getGenderCode());
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }

        try {
            listSEService.getKids().addToStart(
                    new Kid(kidDTO.getIdentification(),
                            kidDTO.getName(), kidDTO.getAge(),
                            gender, locationDep,locationMun));
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón.",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/add_kid_to_end")
    public ResponseEntity<ResponseDTO> addKidToEnd(@RequestBody @Valid KidDTO kidDTO){
        Location locationDep = null;
        try {
            locationDep = locationService.getLocationByCode(kidDTO.getCodeLocationDep());
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        Location locationMun = null;
        try {
            locationMun = locationService.getLocationByCode(kidDTO.getCodeLocationMun());
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        Gender gender = null;
        try {
            gender = genderService.getGenderByGenderCode(kidDTO.getGenderCode());
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        try {
            listSEService.getKids().addToEnd(
                    new Kid(kidDTO.getIdentification(),
                            kidDTO.getName(), kidDTO.getAge(),
                            gender, locationDep,locationMun));
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón.",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/add_in_position/{number}")
    public ResponseEntity<ResponseDTO> addInPosition(@RequestBody @Valid KidDTO kidDTO,@PathVariable int number){
        Location locationDep = null;
        try {
            locationDep = locationService.getLocationByCode(kidDTO.getCodeLocationDep());
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        Location locationMun = null;
        try {
            locationMun = locationService.getLocationByCode(kidDTO.getCodeLocationMun());
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        Gender gender = null;
        try {
            gender = genderService.getGenderByGenderCode(kidDTO.getGenderCode());
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        try {
            listSEService.getKids().addInPosition(number,
                    new Kid(kidDTO.getIdentification(),
                            kidDTO.getName(), kidDTO.getAge(),
                            gender, locationDep, locationMun));
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado el petacón.",
                    null), HttpStatus.OK);

    }
    @GetMapping(path = "/delete_kid_by_identification/{number}")
    public ResponseEntity<ResponseDTO> deleteKidIdentification(@PathVariable int number){

        try {
            listSEService.getKids().deleteKidByIdentification(number);
        } catch (Validation e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new ResponseDTO(
                    200,"Se ha eliminado el pealo.",
                    null), HttpStatus.OK);
    }
    @GetMapping(path = "/mix_kids")
    public ResponseEntity<ResponseDTO> mixKids(){

        try {
            listSEService.getKids().mixKids();
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"se han mezclado los niños.",
                null), HttpStatus.OK);
    }
    @GetMapping(path="/boys_first_girls_at_the_end")
    public ResponseEntity<ResponseDTO> boyStartGirlsLast(){
        try {
            listSEService.getKids().orderBoysToStart();
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "Los niños se han movido al inicio y las niñas al final de la lista.",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/delete_kid_by_age/{age}")
    public ResponseEntity<ResponseDTO> deleteKidByAge(@PathVariable int age){
        try {
            listSEService.getKids().deleteKidByAge(age);
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "Se han eliminado todos los niños que tienen "+ age +" años.",
                null), HttpStatus.OK);
    }


    @GetMapping(path = "/lose_positions/{identification}/{positions}")
    public ResponseEntity<ResponseDTO> losePositions(@PathVariable int identification,@PathVariable int positions){

        try {
            listSEService.getKids().losePositions(identification,positions);
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                    200,"el pelao ha perdido "+ positions +" posiciones.",
                    null), HttpStatus.OK);
    }
    @GetMapping(path = "/win_positions/{identification}/{positions}")
    public ResponseEntity<ResponseDTO> winPositions(@PathVariable int identification, @PathVariable int positions){

        try {
            listSEService.getKids().winPositions(identification,positions);
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                    200,"el pelao ha ganado "+ positions +" posiciones.",
                    null), HttpStatus.OK);
    }

    @GetMapping(path = "/kids_dep_by_locations")
    public ResponseEntity<ResponseDTO> getKidsDepByLocation(){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listSEService.getKids().getCountKidsDepByLocationCode(loc.getCode());

            if(count>0){
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,kidsByLocationDTOList,
                null), HttpStatus.OK);
    }


    @GetMapping(path = "/kids_mun_by_locations")
    public ResponseEntity<ResponseDTO> getKidsMunByLocation(){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listSEService.getKids().getCountKidsMunByLocationCode(loc.getCode());
            if(count>0){
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,kidsByLocationDTOList,
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/kids_mun_total_by_gender/{age}")
    public ResponseEntity<ResponseDTO> getKidsMunTotalByGenderAndAge(@PathVariable int age){
        List<KidsByLocationAndGenderDTO> kidsByLocationAndGenderDTOList = new ArrayList<>();

        for(Location loc: locationService.getLocationsByCodeSize(8)){

            int count = listSEService.getKids().getCountKidsMunByLocationCodeAndAge(loc.getCode(),age);

            if(count>0) {

                List<KidsTotalByGenderAndLocationDTO> kidsTotalByGenderAndLocationDTOList = new ArrayList<>();

                    int countM = listSEService.getKids().getCountKidsLocationByGenderAndAge(loc.getCode(),"1",age);
                    int countF = listSEService.getKids().getCountKidsLocationByGenderAndAge(loc.getCode(),"2",age);
                Gender genderM = null;
                try {
                    genderM = genderService.getGenderByGenderCode("1");
                } catch (Validation e) {
                    return new ResponseEntity<>(new ResponseDTO(
                            200,e.getMessage(),
                            null), HttpStatus.OK);
                }
                Gender genderF = null;
                try {
                    genderF = genderService.getGenderByGenderCode("2");
                } catch (Validation e) {
                    return new ResponseEntity<>(new ResponseDTO(
                            200,e.getMessage(),
                            null), HttpStatus.OK);
                }
                if(countM>0){
                    kidsTotalByGenderAndLocationDTOList.add(new KidsTotalByGenderAndLocationDTO(genderM,countM));}
                    if(countF>0){
                    kidsTotalByGenderAndLocationDTOList.add(new KidsTotalByGenderAndLocationDTO(genderF,countF ));}
                    kidsByLocationAndGenderDTOList.add(new KidsByLocationAndGenderDTO(loc, kidsTotalByGenderAndLocationDTOList, count));

            }
        }

        return new ResponseEntity<>(new ResponseDTO(
                200,kidsByLocationAndGenderDTOList,
                null), HttpStatus.OK);
    }
    @GetMapping(path="/average_kids_age")
    public ResponseEntity<ResponseDTO> averageAge(){
        return new ResponseEntity<>(new ResponseDTO(200,
                listSEService.getKids().averageKidsAge(), null), HttpStatus.OK);
    }
    @GetMapping(path="/add_at_the_end_by_initial_letter/{letter}")
    public ResponseEntity<ResponseDTO> addAtTheEndByInitialLetter(@PathVariable String letter){
        try {
            listSEService.getKids().addAtTheEndByInicialName(letter);
        } catch (Validation e) {
            return new ResponseEntity<>(new ResponseDTO(
                    200,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,
                "Se han adicionado al final los niños que empiezan por la letra "+letter, null), HttpStatus.OK);
    }
    @GetMapping(path = "/get_kids_range_by_age")

    public ResponseEntity<ResponseDTO> getRangeByKids() {

        List<AgeRangesDTO> ageRangesDTOList = new ArrayList<>();

        for (AgeRanges ranges : ageRangesService.getRanges()) {
            int quantity = listSEService.getKids().getKidsRangeByAge(ranges.getMin(), ranges.getMax());
            if(quantity>0){
            ageRangesDTOList.add(new AgeRangesDTO(ranges, quantity));}
        }
        return new ResponseEntity<>(new ResponseDTO(200, ageRangesDTOList, null),
                HttpStatus.OK);

    }
}
