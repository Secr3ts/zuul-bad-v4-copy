import java.util.HashMap;
import java.util.Set;
 

/**
 * Room est une classe qui définit les sorties et les entrées disponibles au joueur
 */
public class Room
{
    private String aDescription;
    private String aImageName;
    private HashMap<String,Room> aExits;
    private ItemList aItem;
    private boolean isATrap;
    
    public Room( final String pDescription, final String pImageName){
        this.aDescription=pDescription;
        this.aExits = new HashMap();
        this.aItem=new ItemList();
        this.aImageName=pImageName;
        this.isATrap = false;
    }
    
    /**
     * Donne au programme toutes les sorties disponibles dans chaque room
     */
   
    public Room getExit (String direction)
    {
        return this.aExits.get(direction);
    }
    /** affiche au joueur les sorties disponibles dans chaque room
     * 
     */
    
    public String getExitString() {
        StringBuilder returnString = new StringBuilder( "Exits:" );
        for ( String vS : this.aExits.keySet() )
            returnString.append( " " + vS );
        return returnString.toString();
    }
    /** permet au code de savoir quelles sorties sont disponibles pour chaque room
     * afin de déplacer le personnage d'une Room à l'autre.
     
     */
    public void setExit(String direction, Room neighbor){
        this.aExits.put(direction, neighbor);
    }

    /**
     * Retourne la description de la room
     * @return the aDescription
     */
    public String getLongDescription()
    {
        
          return "You are in "+this.aDescription + ". \n" + this.getExitString()+"\n"+this.getItemString();
      
       
    }
    /**
     * Retourne la description courte de la room
     * @return the aDescription
     */
    public String getShortDescription(){
        return this.aDescription;
    }

    /**
     * Retourne le nom de l'image de la room
     * @return the aImageName
     */
    public String getImageName(){
        return this.aImageName;
    }

    /**
     * Retourne la liste des items de la room
     * @return String
     */
    public String getItemString(){
        return this.aItem.getItemsString();
    }
    /**
     * Ajoute un item à la Room
     * @param pName
     * @param pItem
     */
    public void addItem(final String pName, final Item pItem){
        this.aItem.addItem(pName, pItem);
        
    }

    /**
     * Retourne l'item
     * @param pItemName
     * @return Item
     */
    public Item getItem(final String pItemName) {
        if (this.aItem.getItem(pItemName) == null) {
            return null;
        } else {
            return this.aItem.getItem(pItemName);
        }
        }

    /**
     * Supprime un item de la Room
     * @param pItemName
     */
    public void removeItem(final String pItemName) {
        if (this.aItem.getItemName(pItemName) == null) {
            return;
        } else {
            this.aItem.removeItem(this.aItem.getItemName(pItemName));
            return;
        }
    }
    /**
     * Retourne true si la salle est une trap
     * @return boolean
     */
    public boolean isExit() {
        return !this.isATrap;
    }
    /**
     * Change l'état de la trap
     */
    public void setTrapState() {
        this.isATrap = !this.isATrap;
    }
} // Room
