
/**
 * Décrivez votre classe ItemList ici.
 *
 * @author (votre nom)+++
 * @version (un numéro de version ou une date)
 */
import java.util.HashMap;
import java.util.Set;

public class ItemList {
    private HashMap<String, Item> aInventory;

    /**
     * Constructeur d'objets de classe ItemList
     */
    public ItemList() {
        this.aInventory = new HashMap<>();
    }

    /**
     * Ajoute un item à la liste
     * 
     * @param pItem
     */
    public void addItem(final String pName, final Item pItem) {
        this.aInventory.put(pName, pItem);

    }

    /**
     * Retourne le nom de l'item
     * 
     * @param pItemName
     * @return String
     */
    public String getItemName(final String pItemName) {
        return this.aInventory.get(pItemName).getName();
    }

    /**
     * Supprime un item de la liste
     * 
     * @param pItemName
     */
    public void removeItem(final String pItemName) {
        if (this.aInventory.containsKey(pItemName)) {
            this.aInventory.remove(pItemName);
        }
    }

    /**
     * Retourne la liste des items
     * 
     * @return String
     */
    public String getInventory() {
        String items = "Inventaire :";
        Set<String> keys = this.aInventory.keySet();

        for (String itemKey : keys) {
            items += itemKey + " ";
        }
        return items;
    }

    public Item getItem(final String pItemName) {
        return this.aInventory.get(pItemName);
    }

    /*
     * Vérifie si un item est dans la liste
     * 
     * @param pItemName
     * 
     * @return
     */
    public boolean hasItem(final String pItemName) {
        if (this.aInventory.containsKey(pItemName)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Vérifie si la liste est vide
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        if (this.aInventory.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retourne la liste des items en String
     * 
     * @return String
     */
    public String getItemsString() {
        String items = "Items: ";
        Set<String> keys = this.aInventory.keySet();

        for (String itemKey : keys) {
            items += itemKey + " ";
        }

        return items;
    }
}
