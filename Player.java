import java.util.Stack;
import java.util.HashMap;

/**
 * Décrivez votre classe Player ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Player
{
   private String aName;
   private Room aCurrentRoom;
   private Stack<Room> backRooms;
   private ItemList aInventory;
   private double poidsMaximum;
   private double poidsTotal;
   private int aTotalMoves;
   
    /**
     * Constructeur d'objets de classe Player
     */
   public Player(final String pName, final Room pCurrentRoom) {
        this.aName = pName;
        this.aCurrentRoom = pCurrentRoom;
        this.backRooms = new Stack();
        this.aInventory = new ItemList();
        this.poidsMaximum=100;
        this.poidsTotal=0;
        this.aTotalMoves = 9999;
    }
    
    /**
     * Affecte le poids maximum
     * @param pMax
     */
    public void setPoidsMaximum(final double pMax) {
        this.poidsMaximum = pMax;
    }

    /**
     * Retourn le nom du joueur
     * @return String
     */
    public String getName() {
        return this.aName;    
    }
    

    /**
     * Retourne la salle actuelle
     * @return Room
     */
    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    }
    
    /**
     * Retourne la pile des salles précédentes
     * @return Stack
     */
    public Stack<Room> getPreviousRoomStack() {
        return this.backRooms;
    }
    
    /**
     * Permet au joueur d'aller dans une salle
     * @param pNextRoom
     */
    public void goRoom(final String pNextRoom) {
        this.backRooms.push(this.aCurrentRoom);
        Room vNextRoom = this.aCurrentRoom.getExit(pNextRoom);
        this.aCurrentRoom = vNextRoom;
        this.aTotalMoves--;
    }
    
    /**
     * Affecte la salle actuelle
     * @param pRoom
     */
    public void setCurrentRoom(final Room pRoom) {
        this.aCurrentRoom = pRoom;
    }

    /**
     * Retourne à la salle précédente
     */
    public void back() {
        this.aCurrentRoom = this.backRooms.pop();
    }

    /**
     * Retourne l'inventaire
     * @return ItemList
     */
    public ItemList getInventory() {
        return this.aInventory;
    }
    
    /**
     * Permet au joueur de manger un item
     * @param pItem
     */
    public void eat(final String pItem) {
        Item vItem = this.aInventory.getItem(pItem);
        
        if (pItem.equals("magiccookie")) {
            this.poidsMaximum *= 2;    
        }
            
        this.aInventory.removeItem(pItem);
    }
    
    /**
     * Permet au joueur d'ajouter un item à l'inventaire
     * @param pItem
     */
    public void setInventory(final Item pItem) {
        this.aInventory.addItem(pItem.getName(), pItem);
    }

    /**
     * Permet au joueur de prendre un item
     * @param pItemName
     */
    public void take(final String pItemName) {
     this.aInventory.addItem(pItemName, this.getCurrentRoom().getItem(pItemName));
     this.aCurrentRoom.removeItem(pItemName);
     this.poidsTotal += this.aInventory.getItem(pItemName).getPoidsItem();
    }
    
    /**
     * Permet au joueur de déposer un item
     * @param pItemName
     */
    public void drop(final String pItemName) {
        this.aCurrentRoom.addItem(pItemName, this.aInventory.getItem(pItemName));
        this.aInventory.removeItem(pItemName);
        
    }

    /**
     * Retourne le poids maximum
     * @return double
     */
    public double getPoidsTotal() {
        return this.poidsTotal;    
    }

    /**
     * Affecte le poids total
     * @param pPoidsTotal
     */
    public void setPoidsTotal(final double pPoidsTotal) {
        this.poidsTotal=pPoidsTotal;
        
    }

    /**
     * Retourne les mouvements restants
     * @return int
     */
    public int getRemainingMoves() {
        return this.aTotalMoves;
    }
}
