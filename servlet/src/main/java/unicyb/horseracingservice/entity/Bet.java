package unicyb.horseracingservice.entity;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "idUser", "idRace", "idHorse","price"})
public class Bet {
    private int id;
    private int idUser;
    private int idRace;
    private int idHorse;
    private float price;
}
