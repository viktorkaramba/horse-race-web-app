package unicyb.horseracingservice.entity;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "idRace", "price"})
public abstract class Bet {
    private int id;
    private int idRace;
    private float price;
}
