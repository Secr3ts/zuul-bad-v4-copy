
/**
 * Décrivez votre classe UserInterface ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.*;

/**
 * This class implements a simple graphical user interface with a 
 * text entry area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003) DB edited (2023)
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JButton aButtonNorth;
    private JButton aButtonSouth;
    private JButton aButtonEast;
    private JButton aButtonWest;
    private JButton aButtonEat;
    private JButton aButtonDown;
    private JButton aButtonUp;
    private JPanel  aPanelEast;
    private JPanel aPanelWest;
    private JButton aButtonRespawn;
    
    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param pGameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL);
            Image image = vIcon.getImage(); 
            image = image.getScaledInstance(512, 512, Image.SCALE_SMOOTH);
            vIcon = new ImageIcon(image);
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the entry field.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( pOnOff ) { // enable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor blink
            this.aEntryField.removeActionListener( this ); // reacts to entry
        }
        this.aButtonNorth.setEnabled(pOnOff);
        this.aButtonSouth.setEnabled(pOnOff);
        this.aButtonEast.setEnabled(pOnOff);
        this.aButtonWest.setEnabled(pOnOff);
        this.aButtonEat.setEnabled(pOnOff);
        this.aButtonUp.setEnabled(pOnOff);
        this.aButtonDown.setEnabled(pOnOff);
        this.aButtonRespawn.setEnabled(pOnOff);
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "The Crossing" ); // change the title !
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        this.aImage = new JLabel();
        JPanel vPanel = new JPanel();
        
        vPanel.setLayout( new BorderLayout() ); // ==> only five places
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        
        this.aPanelEast = new JPanel();
        this.aPanelEast.setLayout(new GridLayout(2,3));
        
        this.aButtonNorth = new JButton ("North");
        this.aButtonNorth.addActionListener( this );
        this.aButtonNorth.setBackground(Color.black);
        this.aPanelEast.add(this.aButtonNorth);
        
        this.aButtonSouth = new JButton ("South");
        this.aButtonSouth.addActionListener( this );
        this.aButtonSouth.setBackground(Color.black);
        this.aPanelEast.add(this.aButtonSouth);
        
        this.aButtonEast = new JButton ("East");
        this.aButtonEast.addActionListener( this );
        this.aButtonEast.setBackground(Color.black);
        this.aPanelEast.add(this.aButtonEast);
        
        this.aButtonWest = new JButton ("West");
        this.aButtonWest.addActionListener( this );
        this.aButtonWest.setBackground(Color.black);
        this.aPanelEast.add(this.aButtonWest);
        
        this.aButtonEat = new JButton ("Eat");
        this.aButtonEat.addActionListener( this );
        this.aButtonEat.setBackground(Color.black);
        this.aPanelEast.add(this.aButtonEat);
        
        this.aButtonUp = new JButton ("Up");
        this.aButtonUp.addActionListener( this );
        this.aButtonUp.setBackground(Color.black);
        this.aPanelEast.add(this.aButtonUp);
        
        this.aButtonDown = new JButton ("Down");
        this.aButtonDown.addActionListener( this );
        this.aButtonDown.setBackground(Color.black);
        this.aPanelEast.add(this.aButtonDown);
        
        this.aButtonRespawn = new JButton ("Respawn");
        this.aButtonRespawn.addActionListener( this );
        this.aButtonRespawn.setBackground(Color.black);
        this.aPanelEast.add(this.aButtonRespawn);
        
        vPanel.add(this.aPanelEast, BorderLayout.EAST);
        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

        // add some event listeners to some components
        this.aEntryField.addActionListener( this );

        // to end program when window is closed
        this.aMyFrame.addWindowListener(
            new WindowAdapter() { // anonymous class
                @Override public void windowClosing(final WindowEvent pE)
                {
                    System.exit(0);
                }
        } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     */
    @Override public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment
        // because there is only one possible action (text input) :
        // this.processCommand();
        if(pE.getSource() == this.aButtonNorth){// never suppress this line
        this.aEngine.interpretCommand("go North");
    } // actionPerformed(.)
    else if (pE.getSource() == this.aButtonSouth){
        this.aEngine.interpretCommand("go South");
    }
    else if (pE.getSource() == this.aButtonEast){
        this.aEngine.interpretCommand("go East");
    }
    else if (pE.getSource() == this.aButtonWest){
        this.aEngine.interpretCommand("go West");
    }
    else if (pE.getSource() == this.aButtonEat){
        this.aEngine.interpretCommand("eat");
    }
    else if (pE.getSource() == this.aButtonUp){
        this.aEngine.interpretCommand("go Up");
    }
    else if (pE.getSource() == this.aButtonDown){
        this.aEngine.interpretCommand("go Down");
    }
    else if (pE.getSource() == this.aButtonRespawn) {
        this.aEngine.interpretCommand("go Respawn");
    }
    else this.processCommand();
}

    /**
     * A command has been entered in the entry field.  
     * Read the command and do whatever is necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
    
} // UserInterface 
