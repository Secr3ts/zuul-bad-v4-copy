 

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2019.09.25
 */
public class CommandWords
{
    // a constant array that will hold all valid command words
    private static final String[] validCommands = {
        "go","quit","help","look","eat", "Respawn", "back", "test", "take", "drop", "inventory", "charge", "fire"};

    /**
     * Check whether a given String is a valid command word. 
     * 
     */
    public CommandWords() {
        
    }
    public boolean isCommand( final String aString )
    {
        for ( int i=0; i<this.validCommands.length; i++ ) {
            if ( this.validCommands[i].equals( aString ) )
                return true;
        } // for
        // if we get here, the string was not found in the commands
        return false;
    } // isCommand()
    /**
     * getter de la liste des commandes
     */
    public String getCommandList() {
       StringBuilder commands = new StringBuilder();
        for (int i = 0; i < validCommands.length; i++) {
            commands.append(validCommands[i] + "  ");
        }
        return commands.toString();
    }
} // CommandWords