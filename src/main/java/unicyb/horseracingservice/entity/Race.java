package unicyb.horseracingservice.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Vector;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "place", "date", "prize", "horses"})
public class Race {
    private int id;
    private String name;
    private String place;
    private Timestamp date;
    private float prize;
    private Vector<Horse> horses;
}
