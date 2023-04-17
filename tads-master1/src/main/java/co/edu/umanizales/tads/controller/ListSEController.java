package co.edu.umanizales.tads.controller;
import co.edu.umanizales.tads.controller.dto.KidDTO;
import co.edu.umanizales.tads.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
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

    @PostMapping(path = "/addKid")
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO){
        Location locationDep = locationService.getLocationByCode(kidDTO.getCodeLocationDep());
        Location locationMun = locationService.getLocationByCode(kidDTO.getCodeLocationMun());
        int iden = listSEService.getKids().comprobarIdentificaion(kidDTO.getIdentification());

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
                        kidDTO.getGender(), locationDep,locationMun));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/addKidToStart")
    public ResponseEntity<ResponseDTO> addKidToStar(@RequestBody KidDTO kidDTO){
        Location locationDep = locationService.getLocationByCode(kidDTO.getCodeLocationDep());
        Location locationMun = locationService.getLocationByCode(kidDTO.getCodeLocationMun());
        int iden = listSEService.getKids().comprobarIdentificaion(kidDTO.getIdentification());

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
                        kidDTO.getGender(), locationDep,locationMun));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/addKidToEnd")
    public ResponseEntity<ResponseDTO> addKidToEnd(@RequestBody KidDTO kidDTO){
        Location locationDep = locationService.getLocationByCode(kidDTO.getCodeLocationDep());
        Location locationMun = locationService.getLocationByCode(kidDTO.getCodeLocationMun());
        int iden = listSEService.getKids().comprobarIdentificaion(kidDTO.getIdentification());

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
                        kidDTO.getGender(), locationDep,locationMun));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/addInPosition")
    public ResponseEntity<ResponseDTO> addInPosition(@RequestBody int numero,KidDTO kidDTO){
        Location locationDep = locationService.getLocationByCode(kidDTO.getCodeLocationDep());
        Location locationMun = locationService.getLocationByCode(kidDTO.getCodeLocationMun());
        int iden = listSEService.getKids().comprobarIdentificaion(kidDTO.getIdentification());

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
                        kidDTO.getGender(), locationDep,locationMun));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón",
                null), HttpStatus.OK);

    }
    @GetMapping(path = "/DeleteKidByIdentification")
    public ResponseEntity<ResponseDTO> deleteKidIdentification(@RequestBody int numero){

        int iden = listSEService.getKids().comprobarIdentificaion(numero);

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

    @GetMapping(path = "/losePositions")
    public ResponseEntity<ResponseDTO> losePositions(@RequestBody int position,int identification){

        int iden = listSEService.getKids().comprobarIdentificaion(identification);

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

    @GetMapping(path = "/mixKids")
    public ResponseEntity<ResponseDTO> mixKids(){

         listSEService.getKids().getHead().getData();
        if(listSEService.getKids().getHead().getData() == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"No hay datos que mezclar ",
                    null), HttpStatus.OK);
        }

        listSEService.getKids().mixKids();
        return new ResponseEntity<>(new ResponseDTO(
                404,"se han mezclado los niños",
                null), HttpStatus.OK);
    }









    @GetMapping(path = "/kidsDepbylocations")
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


    @GetMapping(path = "/kidsMunbylocations")
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










}
