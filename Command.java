 

/**
 * Classe qui analyse la commande de l'utilisateur
 *
 * @author  Hadrien GRADVOHL
 * @version 12/02/2023
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    /**
     * Constructeur naturel
     * 
     * @param pFirstWord 1er mot
     * @param pSecondWord 2eme mot
     */
    public Command (final String pFirstWord, final String pSecondWord)
    {
        this.aCommandWord = pFirstWord;
        this.aSecondWord = pSecondWord;
    }
    
    /**
     * Getter du mot de commande
     * 
     * @return l'attribut de commande
     */
    public String getCommandWord ()
    {
        return this.aCommandWord;
    }
    
    /**
     * Getter de la description
     * 
     * @return l'attribut du 2eme mot
     */
    public String getSecondWord ()
    {
        return this.aSecondWord;
    }
    
    /**
     * Méthode indiquant si l'objet possède un second therme
     * 
     * @return retourne vrai ou faux
     */
    public boolean hasSecondWord ()
    {
        return (this.aSecondWord != null);
        // if (this.getSecondWord() == null){
            // return(false);
        // }else{
            // return(true);
        // }
    }
    
    /**
     * Méthode indiquant si l'objet possède une direction indiquée inconnue
     * 
     * @return retourne vrai ou faux
     */
    public boolean isUnknown ()
    {
        return (this.aCommandWord == null);
        
    }
} // Command