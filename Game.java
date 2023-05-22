/**
 * permet de démarrer le jeu
 *
 * @author  
 * @version 16/04/2023
 */
public class Game
{
    private UserInterface aGui;
    private GameEngine aEngine;

    /**
     * Create the game and initialise its internal map. Create the inerface and link to it.
     */
    public Game() 
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface( this.aEngine );
        this.aEngine.setGui( this.aGui );
    }

    /**
     * Main method to start the game
     */
    public static void main( String[] pArgs )
    {
        Game lGame = new Game();
    }
} // Game
   
    