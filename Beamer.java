
/**
 * Décrivez votre classe Beamer ici.
 *
 * @author Hadrien Gradvhol
 * @version 21.05.23
 */
public class Beamer extends Item
{
    private Room aSavedRoom;
    private boolean aState;
    
    
    /**
     * Constructeur d'objets de classe Beamer
     */
    public Beamer(final String pDescription, final double pPoids)
    {
        super(pDescription, pPoids);

        this.aState = false;        
    }
    

    /**
     * Charge le beamer avec la salle actuelle
     * @param pRoom la salle actuelle
     */
    public void charge(final Room pRoom) {
        this.aSavedRoom = pRoom;
        this.aState = true;
    }
    
    /**
     * Téléporte le joueur dans la salle sauvegardée
     */
    public void fire() {
        this.aSavedRoom = null;
        this.aState = false;
    }
    
    /**
     * Retourne la salle sauvegardée
     * @return la salle sauvegardée
     */
    public Room getSavedRoom() {
        return this.aSavedRoom;
    }
    
    /**
     * Retourne l'état du beamer
     * @return l'état du beamer
     */
    public boolean getBeamerState() {
        return this.aState;
    }
}
