package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.CircularListDE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class CircularListDEService {

    private CircularListDE pets;
    private SpeciesService spices;

    public CircularListDEService() {pets = new CircularListDE();}
}
