package unicyb.horseracingservice.database;

public class SQLQuery {
    public static final String SQL_SELECT_ALL_RACES="SELECT *FROM RACES";
    public static final String SQL_SELECT_RACES_BY_ID="SELECT * FROM RACES WHERE ID = ?";
    public static final String SQL_INSERT_RACE="INSERT INTO RACES VALUES(?, ?, ?, ?, ?)";
    public static final String SQL_DELETE_RACE="DELETE FROM RACES WHERE ID = ?";
    public static final String SQL_UPDATE_RACE="UPDATE RACES SET ISOVER = ? WHERE ID = ?";
    public static final String SQL_SELECT_ALL_MEMBERS="SELECT * FROM MEMBERS";
    public static final String SQL_SELECT_MEMBERS_BY_ID="SELECT * FROM MEMBERS WHERE ID = ?";
    public static final String SQL_SELECT_MEMBERS_BY_RACE="SELECT * FROM MEMBERS WHERE ID_RA = ?";
    public static final String SQL_INSERT_MEMBER="INSERT INTO MEMBERS VALUES(?, ?, ?)";
    public static final String SQL_DELETE_MEMBER="DELETE FROM MEMBERS WHERE ID = ?";
    public static final String SQL_SELECT_ALL_HORSES="SELECT *FROM HORSES";
    public static final String SQL_SELECT_HORSES_BY_ID="SELECT * FROM HORSES WHERE ID = ?";
    public static final String SQL_INSERT_HORSE="INSERT INTO HORSES VALUES(?, ?, ?, ?)";
    public static final String SQL_DELETE_HORSE="DELETE FROM HORSES WHERE ID = ?";
    public static final String SQL_SELECT_ALL_BETS="SELECT *FROM BETS";
    public static final String SQL_SELECT_BETS_BY_ID="SELECT * FROM BETS WHERE ID = ?";
    public static final String SQL_INSERT_BETS="INSERT INTO BETS (ID_US, ID_RA, ID_HO, PRICE, TOP) VALUES(?, ?, ?, ?, ?)";
    public static final String SQL_DELETE_BETS="DELETE FROM BETS WHERE ID = ?";
    public static final String SQL_SELECT_WIN_BETS_BY_RACE_HORSE="SELECT * FROM BETS WHERE ID_RA = ? AND ID_HO = ?";
    public static final String SQL_SELECT_ALL_USERS="SELECT *FROM USERS";
    public static final String SQL_SELECT_USER_BY_ID="SELECT * FROM USERS WHERE ID = ?";
    public static final String SQL_SELECT_USER_BY_USERNAME="SELECT * FROM USERS WHERE USERNAME = ?";
    public static final String SQL_INSERT_USER="INSERT INTO USERS VALUES(?, ?, ?)";
    public static final String SQL_DELETE_USER="DELETE FROM USERS WHERE ID = ?";
    public static final String SQL_UPDATE_USER="UPDATE USERS SET BALANCE = ? WHERE ID = ?";
    public static final String SQL_SELECT_COEFFICIENTS_BY_RACE_HORSE="SELECT * FROM COEFFICIENTS WHERE ID_RA = ? AND ID_HO = ?";
    public static final String SQL_SELECT_ALL_COEFFICIENTS="SELECT * FROM COEFFICIENTS";
    public static final String SQL_INSERT_COEFFICIENT="INSERT INTO COEFFICIENTS (ID_RA, ID_HO, VALUE) VALUES(?, ?, ?)";

}
