package unicyb.horseracingservice.entity;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "idUser", "idRace", "idHorse","price", "top"})
public class Bet {
    private int id;
    private int idUser;
    private int idRace;
    private int idHorse;
    private float price;
    private int top;

    Bet(int idUser, int idRace, int idHorse, float price, int top){
        this.idUser = idUser;
        this.idRace = idRace;
        this.idHorse = idHorse;
        this.price = price;
        this.top = top;
    }
}
