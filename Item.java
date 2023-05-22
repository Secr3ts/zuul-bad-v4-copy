
/**
 * Décrivez votre classe Item ici.
 *
 * @author Hadrien Gradvohl
 * @version 16.04.2023
 */
public class Item
{
    private String aName;
    private String aDescription;
    private double aPoids;
    /**
     * constructeur par défaut
     */
    
    public Item(final String pDescription, final double pPoids ) {
        this.aDescription=pDescription;
        this.aPoids=pPoids;
    }
    /**
     * setter de la Desciption des item
     */
    public void setDescription( final String pDescription) {
        this.aDescription= pDescription;
    }
    /**
     * setter du Poids des item
     */
    public void setPoids(final double pPoids) {
        this.aPoids=pPoids;
    }
    /**
     * getter de la description des item
     */
    public String getDescriptionItem(){
        return this.aDescription;
    }
    /**
     * getter du Poids d'un item
     */
    public double getPoidsItem(){
        return this.aPoids;
    }
    /**
     * getter du nom d'un item
     */
    public String getName() {
        return this.aName;
    }
    
}
