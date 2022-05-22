package unicyb.horseracingservice.entity;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"ID", "IDUS", "IDRA", "IDHO","price"})
public class Bet {
    private int ID;
    private int IDUS;
    private int IDRA;
    private int IDHO;
    private float price;
}
