package co.edu.umanizales.tads.controller;
import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.model.Gender;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.GenderService;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import com.fasterxml.jackson.core.base.GeneratorBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids(),null), HttpStatus.OK);

    }

    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert(){
        listSEService.invert();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE ha invertido la lista",
                null), HttpStatus.OK);

    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        listSEService.getKids().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE han intercambiado los extremos",
                null), HttpStatus.OK);
    }


    @PostMapping(path = "/add_kid")
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO){
        Location locationDep = locationService.getLocationByCode(kidDTO.getCodeLocationDep());
        Location locationMun = locationService.getLocationByCode(kidDTO.getCodeLocationMun());
        Gender gender = genderService.getGenderByGenderCode(kidDTO.getGenderCode());
        int iden = listSEService.getKids().checkIdentification(kidDTO.getIdentification());


        if(gender == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"El genero no existe",
                    null), HttpStatus.OK);
        }

        if(locationDep == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        if(locationMun == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        if(iden == 1){
            return new ResponseEntity<>(new ResponseDTO(
                    400,"El pelao ya existe",
                    null), HttpStatus.OK);
        }
        listSEService.getKids().add(
                new Kid(kidDTO.getIdentification(),
                        kidDTO.getName(), kidDTO.getAge(),
                        gender, locationDep,locationMun));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/add_kid_to_start")
    public ResponseEntity<ResponseDTO> addKidToStar(@RequestBody KidDTO kidDTO){
        Location locationDep = locationService.getLocationByCode(kidDTO.getCodeLocationDep());
        Location locationMun = locationService.getLocationByCode(kidDTO.getCodeLocationMun());
        Gender gender = genderService.getGenderByGenderCode(kidDTO.getGenderCode());
        int iden = listSEService.getKids().checkIdentification(kidDTO.getIdentification());

        if(locationDep == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        if(locationMun == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        if(iden == 1){
            return new ResponseEntity<>(new ResponseDTO(
                    400,"El pelao ya existe",
                    null), HttpStatus.OK);
        }
        listSEService.getKids().addToStart(
                new Kid(kidDTO.getIdentification(),
                        kidDTO.getName(), kidDTO.getAge(),
                        gender, locationDep,locationMun));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/add_kid_to_end")
    public ResponseEntity<ResponseDTO> addKidToEnd(@RequestBody KidDTO kidDTO){
        Location locationDep = locationService.getLocationByCode(kidDTO.getCodeLocationDep());
        Location locationMun = locationService.getLocationByCode(kidDTO.getCodeLocationMun());
        Gender gender = genderService.getGenderByGenderCode(kidDTO.getGenderCode());
        int iden = listSEService.getKids().checkIdentification(kidDTO.getIdentification());

        if(locationDep == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        if(locationMun == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        if(iden == 1){
            return new ResponseEntity<>(new ResponseDTO(
                    400,"El pelao ya existe",
                    null), HttpStatus.OK);
        }
        listSEService.getKids().addToEnd(
                new Kid(kidDTO.getIdentification(),
                        kidDTO.getName(), kidDTO.getAge(),
                        gender, locationDep,locationMun));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/add_in_position")
    public ResponseEntity<ResponseDTO> addInPosition(@RequestBody int numero,KidDTO kidDTO){
        Location locationDep = locationService.getLocationByCode(kidDTO.getCodeLocationDep());
        Location locationMun = locationService.getLocationByCode(kidDTO.getCodeLocationMun());
        Gender gender = genderService.getGenderByGenderCode(kidDTO.getGenderCode());
        int iden = listSEService.getKids().checkIdentification(kidDTO.getIdentification());

        if(locationDep == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        if(locationMun == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        if(iden == 1){
            return new ResponseEntity<>(new ResponseDTO(
                    400,"El pelao ya existe",
                    null), HttpStatus.OK);
        }
        listSEService.getKids().addInPosition(numero,
                new Kid(kidDTO.getIdentification(),
                        kidDTO.getName(), kidDTO.getAge(),
                        gender, locationDep,locationMun));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón",
                null), HttpStatus.OK);

    }
    @GetMapping(path = "/delete_kid_by_identification")
    public ResponseEntity<ResponseDTO> deleteKidIdentification(@RequestBody int numero){

        int iden = listSEService.getKids().checkIdentification(numero);

        if(iden == 0){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"El pelao no existe",
                    null), HttpStatus.OK);
        }
        if(iden == 1){
            listSEService.getKids().deleteKidByIdentification(numero);
            return new ResponseEntity<>(new ResponseDTO(
                    200,"Se ha eliminado el pealo",
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                404,"El pelao no existe",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/lose_positions")
    public ResponseEntity<ResponseDTO> losePositions(@RequestBody int position,int identification){

        int iden = listSEService.getKids().checkIdentification(identification);

        if(iden == 0){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"El pelao no existe",
                    null), HttpStatus.OK);
        }
        if(iden == 1){
            listSEService.getKids().losePositions(position,identification);
            return new ResponseEntity<>(new ResponseDTO(
                    200,"Se ha eliminado el pealo",
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                404,"El pelao no existe",
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/lose_positions1/{position}/{identification}")
    public ResponseEntity<ResponseDTO> losePositions1(@PathVariable int position, @PathVariable int identification){

        int iden = listSEService.getKids().checkIdentification(identification);

        if(iden == 0){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"El pelao no existe",
                    null), HttpStatus.OK);
        }
        if(iden == 1){
            listSEService.getKids().losePositions1(identification,position);
            return new ResponseEntity<>(new ResponseDTO(
                    200,"el pelao ha perdido "+ position +" posiciones",
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                404,"El pelao no existe",
                null), HttpStatus.OK);
    }


    @GetMapping(path = "/mix_kids")
    public ResponseEntity<ResponseDTO> mixKids(){

        if(listSEService.getKids().getHead().getData() == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"No hay datos que mezclar ",
                    null), HttpStatus.OK);
        }

        listSEService.getKids().mixKids();
        return new ResponseEntity<>(new ResponseDTO(
                200,"se han mezclado los niños",
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
                    Gender genderM = genderService.getGenderByGenderCode("1");
                    Gender genderF = genderService.getGenderByGenderCode("2");
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


    @GetMapping(path = "/prueba/{age}" )
    public ResponseEntity<ResponseDTO> prueba(@PathVariable int age){
        List<KidsTotalByGenderAndLocationDTO> kidsTotalByGenderAndLocationDTOList = new ArrayList<>();
        for(Gender gen: genderService.getGenders()){

            int count = listSEService.getKids().getCountKidsByCodeGender(gen.getCodeGender(),age);
            if(count>0){
                kidsTotalByGenderAndLocationDTOList.add(new KidsTotalByGenderAndLocationDTO(gen,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,kidsTotalByGenderAndLocationDTOList,
                null), HttpStatus.OK);
    }

}
