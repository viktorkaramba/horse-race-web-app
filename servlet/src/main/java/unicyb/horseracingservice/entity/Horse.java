package unicyb.horseracingservice.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"ID", "number", "name", "breed"})
public class Horse {
    private int ID;
    private int number;
    private String name;
    private String breed;
}
