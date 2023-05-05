package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.errors.Validation;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Node;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ListSEService {
    private ListSE kids;
    private LocationService location;

    public ListSEService() {
        kids = new ListSE();
    }

    public void invert()throws Validation {
        kids.invert();
    }


}
