import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Game engine est le coeur du jeu il permet d'afficher les room, les textes du jeu et de faire tourner le jeu.
 *
 * @author 
 * @version (un numéro de version ou une date)
 */
public class GameEngine
{
    private Parser        aParser;
    private UserInterface aGui;
    private Player aPlayer;
    private HashMap<String, Room> aRooms;
    private HashMap<Room, String> aRoomNames;
    
    /**
     * Constructeur par défaut
     */
    public GameEngine() {
        this.aParser = new Parser();
        this.createRooms();    
    }
    /**
     * Met en place l'interface graphique
     */
    public void setGui (final UserInterface pUserInterface) {
        this.aGui=pUserInterface;
        this.printWelcome();
    }
    /**
     * Texte affiché au du début du jeu
     */
    private void printWelcome ()
    {
        this.aGui.println("Welcome to the Crossing!");
        this.aGui.println("Your objective is to cross a forest without dying, if you do, you will restart at the biginning.");
        
        this.aGui.println("Type 'help' if you need help.");
        this.aGui.print("\n");
        this.aGui.println("You are in front of a forest then you need to cross !!");
        this.aGui.println("Have a great time !");
        this.printLocationInfo();
    }
    /**
     * Initialisation des room, des chemins et des items. 
     */
    private void createRooms()
    {
        Room vSpawn, vDruid, vDruid2, vElf, vTrap, vGobelins, vDragon, vLandslide, vTroll, vEnd;
         vSpawn = new Room (" at the entrance of the forest","Image/vSpawn.jpeg");
         vDruid = new Room (" in a house and you see a staircase","Image/vDruid1.jpeg");
         vDruid2 = new Room("in th laboratory of a druid","Image/vDruid2.jpeg");
         vElf = new Room (" in front of an elf","Image/vElf.jpeg");
         vTrap = new Room (" now in a trap","Image/vTrap.jpg");
         vGobelins = new Room (" in front of a tribe of gobelins ","Image/vGobelins.jpeg");
         vDragon = new Room (" in front now of a dragon","Image/vDragon.jpeg");
         vLandslide = new Room (" in a dead end and many stones fall on you","Image/vLanslide.jpeg");
         vTroll = new Room (" you are ina clearing and you have awakened a troll who now wants to attack you","Image/vTroll.jpeg");
         vEnd = new Room (" at the end of the forest, you've won !!!!","Image/vEnd.jpeg");
        /**Sorties pour chaque pièce
         * 
         */
        vSpawn.setExit("North",vElf);
        vSpawn.setExit("South",vDruid);
        vDruid.setExit("East",vGobelins);
        vDruid.setExit("Up",vDruid2);
        vDruid2.setExit("Down",vDruid);
        vElf.setExit("North",vDragon);
        vElf.setExit("South",vGobelins);
        vElf.setExit("East",vTrap);
        vTrap.setExit("Respawn",vSpawn);
        vGobelins.setExit("South",vTroll);
        vGobelins.setExit("East",vLandslide);
        vLandslide.setExit("Respawn",vSpawn);
        vGobelins.setExit("North",vElf);
        vDragon.setExit("East",vEnd);
        vTroll.setExit("East",vEnd);
        
        /*
        vDruid2.getRandomizer().addRoom(vSpawn);
        vDruid2.getRandomizer().addRoom(vDruid);
        vDruid2.getRandomizer().addRoom(vGobelins);
        vDruid2.getRandomizer().addRoom(vTroll);
        */
        
        Item vPotion= new Item(" this potion is  very precious and will usefull later",0.100 );
        Item vGlasses= new Item(" they have no use expect making you even more beautiful",0.02);
        Beamer vHat=new Beamer(" this magnificent hat is useless",0.4);
        Item magicCookie= new Item ("you can now transport more objects",0.0);
        vDruid2.addItem("potion",vPotion);
        vDruid2.addItem("glasses",vGlasses);
        vSpawn.addItem("hat",vHat);
        vElf.addItem("magiccookie",magicCookie);
        
        this.aPlayer = new Player("Gontrande",vSpawn); 
        this.aPlayer.setCurrentRoom(vSpawn);
        //Initialisation de la HashMap
        this.aRooms = new HashMap<String, Room>();
        this.aRooms.put("Spawn", vSpawn);
        this.aRooms.put("Druid", vDruid);
        this.aRooms.put("Druid2", vDruid2);
        this.aRooms.put("Elf", vElf);
        this.aRooms.put("Trap", vTrap);
        this.aRooms.put("Gobelins", vGobelins);
        this.aRooms.put("Dragon", vDragon);
        this.aRooms.put("Landslide", vLandslide);
        this.aRooms.put("Troll", vTroll);
        this.aRooms.put("End", vEnd);

        this.aRoomNames = new HashMap<Room, String>();
        for (final String vRoomName : this.aRooms.keySet()) {
            this.aRoomNames.put(this.aRooms.get(vRoomName), vRoomName);
        }
        
    }
    /**
     * Interprete les commandes rentrés par le joueur et les interprète
     */
    public void interpretCommand (final String pCommandLine){
        this.aGui.println( "> " + pCommandLine );
        Command vCommand = this.aParser.getCommand( pCommandLine );
        String vCommandWord=vCommand.getCommandWord();
        if ( vCommand.isUnknown() ) {
            this.aGui.println( "I don't know what you mean..." );
            return;
        } else if (vCommandWord.equals("go")){
                this.goRoom(vCommand);
                
        }else if (vCommandWord.equals("quit")){
                this.endGame();
        }else if (vCommandWord.equals("help")){
                this.printHelp();
                
        }else if(vCommandWord.equals("look")) {
                this.look(vCommand);
                
        }else if(vCommandWord.equals("eat")) {
            this.eat(vCommand);
        
        } else if (vCommandWord.equals("back")){
            this.back(vCommand);
        } else if (vCommandWord.equals("Respawn")) {
            
        } else if (vCommandWord.equals("test")) {
            this.test(vCommand);
        } else if (vCommandWord.equals("take")) {
            this.take(vCommand);
        } else if (vCommandWord.equals("drop")) {
            this.drop(vCommand);
        } else if (vCommandWord.equals("inventory")) {
            this.inventory(vCommand);
        } else if (vCommandWord.equals("charge")) {
            this.charge(vCommand);
        } else if (vCommandWord.equals("fire")) {
            this.fire(vCommand);
        }
    }
    
    /**
     * permet au joueur de charger le beamer
     * @param pCommand
     */
    private void charge(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Charge what ?");
            return;
        }
        
        if (this.aPlayer.getInventory().getItem(pCommand.getSecondWord()).equals("null")) {
            this.aGui.println("You don\'t have " + pCommand.getSecondWord());
            return;
        }
        
        Item vItem = this.aPlayer.getInventory().getItem(pCommand.getSecondWord());
        Beamer vBeamer = (Beamer) vItem;
        
        if (vBeamer.getBeamerState() == true) {
            this.aGui.println("Beamer is already charged.");
            return;
        }
        
        vBeamer.charge(this.aPlayer.getCurrentRoom());
        this.aGui.println("Beamer charged at : " + this.aPlayer.getCurrentRoom().getShortDescription());
    }
    
    /**
     * permet au joueur de tirer le beamer
     * @param pCommand
     */
    private void fire(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Charge what ?");
            return;
        }
        
        if (this.aPlayer.getInventory().getItem(pCommand.getSecondWord()).equals("null")) {
            this.aGui.println("You don\'t have " + pCommand.getSecondWord());
            return;
        }
        
        Item vItem = this.aPlayer.getInventory().getItem(pCommand.getSecondWord());
        Beamer vBeamer = (Beamer) vItem;
        
        if (vBeamer.getBeamerState() == false) {
            this.aGui.println("Beamer is not charged.");
            return;
        }
        
        this.aPlayer.setCurrentRoom(vBeamer.getSavedRoom());
        this.printLocationInfo();
        this.aGui.println("Beamer fired at : " + this.aPlayer.getCurrentRoom().getShortDescription());
        vBeamer.fire();
    }
    private void inventory(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            return;
        }
        
        this.aGui.println(this.aPlayer.getInventory().getItemsString());
        }
    /**
     * permet au joueur de prendre un item
     */
    private void take(final Command vCommand) {
        if (!vCommand.hasSecondWord()) {
            this.aGui.println("Take what?");
            return;
        }
        String vItem = vCommand.getSecondWord();
        
        if (this.aPlayer.getCurrentRoom().getItem(vItem) != null) {
            this.aPlayer.take(vItem);
            this.aGui.println("You took " + vItem);
        }     
    }
    /**
     * permet au joueur de lacher un item
     */
    private void drop(final Command vCommand) {
        String vItem = vCommand.getSecondWord();
        
        if (!this.aPlayer.getInventory().hasItem(vItem)) {
            this.aGui.println("You don\'t have " + vItem + ".");
            return;
        }
        if (this.aPlayer.getInventory() != null) {
            this.aPlayer.drop(vItem);
            this.aGui.println("You dropped " + vItem); 
        }
    }
    /**
     * permet de tester un ensemble de commande contenu dans un fichier .txt
     */
    private void test (final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("What do you want to test?");
            return;
        }
        
        String vFileToRead = pCommand.getSecondWord();
        try {
            Scanner vScan = new Scanner(new File("" + vFileToRead + ".txt"));
            while (vScan.hasNextLine()) {
                this.interpretCommand(vScan.nextLine());
            }
        } catch (final FileNotFoundException vE){
            this.aGui.println("There is no such file of testing !");
        }
    }
    private void look(final Command pCommand)
    {
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());    
    }
    /**
     * permet au joueur de manger
     */
    private void eat(final Command pCommand) {
        if(!pCommand.hasSecondWord()) {
           return;
        } 
        
        if (!this.aPlayer.getInventory().hasItem(pCommand.getSecondWord())) {
            return;
        }
        
        String vItem = pCommand.getSecondWord();
        this.aPlayer.eat(vItem);
        this.aGui.println("You ate " + vItem);
        if (vItem.equals("magiccookie")) {
            this.aGui.println("Capacity has been * 2");    
        }
    }
    /**
     * fait passer le joueur d'une room à l'autre
     */
    private void goRoom(final Command pCommand)
    {
        if ( ! pCommand.hasSecondWord() ) {
            // if there is no second word, we don't know where to go...
            this.aGui.println( "Go where?" );
            return;
        }

        String vDirection = pCommand.getSecondWord();

        // Try to leave current room.
        Room vNextRoom = this.aPlayer.getCurrentRoom().getExit( vDirection );
        //this.aGui.println(this.backRoom.getShortDescription());
        if ( vNextRoom == null )
            this.aGui.println( "There is no path!" );
        else if (this.aPlayer.getRemainingMoves() <= 0) {
            this.aGui.println("No moves remaining");
            this.endGame();
        } else if (this.aPlayer.getCurrentRoom().getClass().equals(TransporterRoom.class)) {
            Room vRoom = this.aPlayer.getCurrentRoom();
            TransporterRoom vTRoom = (TransporterRoom) vRoom;
            this.aPlayer.setCurrentRoom(vTRoom.getRandomizer().getRoom());
            this.printLocationInfo();
        } else {
            this.aPlayer.goRoom(vDirection);
            this.printLocationInfo();
        }
        
    }
    /**
     * affiche le texte pour aider le joueur ainsi que toutes les commandes disponibles
     */
    private void printHelp ()
    {
        this.aGui.println("You are lost. You are alone.");
        this.aGui.println("You wander around in the forest."+ "\n");
        this.aGui.println("Your commands are :" + this.aParser.getCommandString());
    }
    /**
     * permet d'arreter de jouer
     */
    private void endGame() {
        this.aGui.println("Thank you for playing. Have a nice day !!");
        this.aGui.enable( false );
    }
    /**
     * affiche la description de la room dans laquelle le joueur arrive
     */
    public void printLocationInfo(){
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        this.printRelevantRoom();
        if (this.aPlayer.getCurrentRoom().getImageName() != null)
            this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
    }
    private void printRelevantRoom() {
        // Trouve la room et le nom correspondant dans laquelle le joueur se trouve
        Room vRoom = this.aPlayer.getCurrentRoom();
        switch (this.aRoomNames.get(vRoom)) {
            
        }
    }
    /**
     * permet de revenir à la room précédente
     */
    private void back(final Command pCommand){
        if(this.aPlayer.getPreviousRoomStack().isEmpty()){
            return;
        }
        if (pCommand.hasSecondWord()){
            this.aGui.println("You can't back in a particular place !");
            return; //Arret prématuré
        }
        if (!this.aPlayer.getPreviousRoomStack().peek().isExit()) {
            this.aGui.println("You can't go back. It\'s a trap !");
            return;
        }
        this.aPlayer.back();
        this.printLocationInfo();
    }
}
