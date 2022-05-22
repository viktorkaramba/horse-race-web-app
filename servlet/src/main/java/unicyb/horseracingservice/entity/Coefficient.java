package unicyb.horseracingservice.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"ID", "IDRA", "IDHO", "value"})
public class Coefficient {
    private int ID;
    private int IDRA;
    private int IDHO;
    private float value;
}
