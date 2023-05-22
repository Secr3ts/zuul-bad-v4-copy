package v1;

/**
 * Moteur de jeu principal
 *
 * @author  Hadrien GRADVOHL
 * @version 13/02/2023
 */
public class Game
{
    private Room aCurrentRoom;
    private Parser aParser;
    
    /**
     * Constructeur par défaut
     */
    public Game ()
    {
        this.createRooms();
        this.aParser = new Parser();
    }
    
    /**
     * Creation des différentes pièces du jeu
     */
    private void createRooms()
    {
        //Création des pièces
        
        Room vSpawn = new Room ("You are at the entrance of the forest");
        Room vDruid = new Room ("You arrive in the house of a druid");
        Room vElf = new Room ("You arrive in front of an elf");
        Room vTrap = new Room ("You are now in a trap");
        Room vGobelins = new Room ("A tribe of gobelin appears in front of you");
        Room vDragon = new Room ("You find yourself in front of a dragon");
        Room vLandslide = new Room ("Many stones fall on you chock");
        Room vTroll = new Room ("You have awakened a troll who now wants to attack you");
        Room vEnd = new Room ("You've won the game");
        
        
        //Affectation des sorties à chaque pièce
        vSpawn.setExits(vElf,vDruid,null,null);
        vDruid.setExits(vElf,null,vGobelins,null);
        vElf.setExits(vDragon,vDruid,vTrap,null);
        vTrap.setExits(null,null,null,null);
        vGobelins.setExits(vDragon,vTroll,vLandslide,null);
        vDragon.setExits(null,null,vEnd,null);
        vLandslide.setExits(null,null,null,null);
        vTroll.setExits(null,null,vEnd,null);
        vEnd.setExits(null,null,null,null);
        
        //Initialisation du jeu
        this.aCurrentRoom = vSpawn;
    }
    
    /**
     * Permet de se déplacer de pièces en pièces
     * 
     * @param pInstruction indique la direction dans laquelle aller 
     */
    private void goRoom(final Command pInstruction)
    {
        //Partie A
        if (!pInstruction.hasSecondWord()){
            System.out.println("Go where ?");
            return;
        }
        
        //Partie B
        Room vNextRoom = null;
        String vDirection = pInstruction.getSecondWord();
        if (vDirection.equals("north")){
            vNextRoom = this.aCurrentRoom.aNorthExit;
        }else if (vDirection.equals("south")){
            vNextRoom = this.aCurrentRoom.aSouthExit;
        }else if (vDirection.equals("east")){
            vNextRoom = this.aCurrentRoom.aEastExit;
        }else if (vDirection.equals("west")){
            vNextRoom = this.aCurrentRoom.aWestExit;
        }else{
            System.out.println("Unknown direction!");
            return;
        }
        
        //Partie C
        if (vNextRoom == null){
            System.out.println("There is no door !");
            return;
        }
        
        //Partie D
        this.aCurrentRoom = vNextRoom;
        System.out.println(this.aCurrentRoom.getDescription());
        System.out.print("Exits: ");
        if (this.aCurrentRoom.aNorthExit != null){
            System.out.print("north ");
        }
        if (this.aCurrentRoom.aSouthExit != null){
            System.out.print("south ");
        }
        if (this.aCurrentRoom.aEastExit != null){
            System.out.print("east ");
        }
        if (this.aCurrentRoom.aWestExit != null){
            System.out.print("west ");
        }
        System.out.println("");
    }
    
    /**
     * Affiche les messages lors du début du jeu
     */
    private void printWelcome ()
    {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are outside the main entrance of the university");
        System.out.println("Exits: east south west");
    }
    
    /**
     * Affiche les commandes possibles
     */
    private void printHelp ()
    {
        System.out.println("You are lost. You are alone.");
        System.out.println("You wander around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("  go quit help");
    }
    
    /**
     * Permet de quitter le jeu
     * 
     * @param pInstruction mot de commande ecrit par l'utilisateur
     * @return valeur vraie ou faux si il faut arrêter le jeu
     */
    private boolean quit (final Command pInstruction)
    {
        if (pInstruction.hasSecondWord()){
            System.out.println("Quit what?");
            return(false);
        }else{
            return(true);
        }
    }
    
    /**
     * Appelle la méthode souhaitée par l'utilisateur
     * 
     * @param pCommande Commande écrite par l'utilisateur
     * @return booléen permettant de savoir si l'on continue à jouer ou non
     */
    private boolean processCommand (final Command pCommande)
    {
        if (pCommande.isUnknown()){
            System.out.println("I don't know what you mean...");
            return(false);
        }else{
            if (pCommande.getCommandWord().equals("go")){
                goRoom(pCommande);
                return(false);
            }else if (pCommande.getCommandWord().equals("quit")){
                return(this.quit(pCommande));
            }else if (pCommande.getCommandWord().equals("help")){
                printHelp();
                return(false);
            }else{
                System.out.println("Unknown command!");
                return(false);
            }
        }
    }
    
    /**
     * Moteur de jeu principal qui tourne jusqu'a ce que l'utilisateur décide de quitter
     */
    public void play ()
    {
        printWelcome();
        boolean vFinished = false;
        Command vCommande;
        while (vFinished == false){
            vCommande = this.aParser.getCommand(); //lis la commande de l'utilisateur
            vFinished = processCommand(vCommande);
        }
        System.out.println("Thank you for playing. Good bye.");
    }
} // Game