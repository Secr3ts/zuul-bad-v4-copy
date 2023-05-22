
/**
 * Décrivez votre classe TransporterRoom ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class TransporterRoom extends Room
{
    private RoomRandomizer aRandomRoom;

    /**
     * Constructeur d'objets de classe TransporterRoom
     */
    public TransporterRoom( final String pDescription, final String pImageName)
    {
        super(pDescription, pImageName);
        this.aRandomRoom = new RoomRandomizer();
    }
    /**
     * Retourne le randomizer
     * @return RoomRandomizer
     */
    public RoomRandomizer getRandomizer() {
        return this.aRandomRoom;
    }
}
