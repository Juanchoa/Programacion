package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.AgeRanges;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Data
public class AgeRangesService {

    private List<AgeRanges> ranges;

    public AgeRangesService(){
        ranges=new ArrayList<>();
        ranges.add(new AgeRanges((byte) 1,(byte)3));
        ranges.add(new AgeRanges((byte) 4,(byte)6));
        ranges.add(new AgeRanges((byte) 7,(byte)9));
        ranges.add(new AgeRanges((byte) 10,(byte)13));
        ranges.add(new AgeRanges((byte) 12,(byte)15));
    }
}
