import java.util.Random;
import java.util.ArrayList;

/**
 * Décrivez votre classe RoomRandomizer ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class RoomRandomizer
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private ArrayList<Room> aRooms;
    private Random aRandom;
    /**
     * Constructeur d'objets de classe RoomRandomizer
     */
    public RoomRandomizer()
    {
        this.aRandom = new Random();
        this.aRooms = new ArrayList<Room>();
    }
    /**
     * Ajoute une room à la liste
     * @param pRoom
     */
    public void addRoom(final Room pRoom) {
        this.aRooms.add(pRoom);
    }
    /**
     * Retourne une room aléatoire
     * @return
     */
    public Room getRoom() {
        return this.aRooms.get(this.aRandom.nextInt(this.aRooms.size()));
    }
}
