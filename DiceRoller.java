package diceRoller;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * A simple GUI that will simulate one or more die rolls. Each DiceRoller class has
 * an ArrayList of Dice which can thus be rolled and their totals displayed.
 * Dice can be added and removed from the ArrayList via two buttons on the GUI.
 * 
 * @author Tara Crittenden September 2017
 */

public class DiceRoller extends Frame implements ActionListener, WindowListener {
    
    private Frame frame;
    private Button addDice,rollDice;
    private ArrayList<Dice> dice;
    private Label resultLabel = new Label("");
    
    /**
     * Bare bones constructor that adds the 'Add' and 'Roll' buttons.
     */
    public DiceRoller() {
        //Set up the basics of the Frame
        frame = new Frame();
        frame.setLayout(new FlowLayout());
        frame.setTitle("Dice Roller");
        frame.setSize(350, 200);
        frame.addWindowListener(this);
        
        //Add the two main buttons
        addDice = new Button("Add Dice");
        addDice.addActionListener(this);
        frame.add(addDice);
        rollDice = new Button("Roll Dice");
        rollDice.addActionListener(new RollDice());
        frame.add(rollDice);
        
        frame.setVisible(true);
        
        //initate the ArrayList of Dice
        dice = new ArrayList();
    }
    
    /**
     * Initiates the DiceRoller GUI.
     * @param args unused in this case
     */
    public static void main(String[] args) {
        DiceRoller dr = new DiceRoller();
    }
    
    /**
     * Helper method to ensure only active Dice are within the ArrayList. 
     * Usually called when adding new die or rolling existing ones.
     */
    private void cleanUp() {
        for(int i=0; i<dice.size(); i++) {
            if(dice.get(i).getPanel() == null) {
                dice.remove(i);
                i--;
            }
        }
    }
    
    /**
     * Adds a simple Dice to the ArrayList. The dice is then displayed on the 
     * GUI and can be manipulated like all the rest.
     */
    protected void addDice() {
        cleanUp();
        Dice temp = new Dice();
        dice.add(temp);
        frame.add(dice.get(dice.indexOf(temp)).getPanel());
        frame.setVisible(true);
    }
    
    /**
     * List of override methods for the ActionListener and WindowListener 
     * interfaces. Currently, only actionPerformed and windowClosing has proper
     * functionality, the former adding Dice and the latter exiting the program.
     * @param e an event
     */
    @Override public void actionPerformed(ActionEvent e) { addDice(); }
    @Override public void windowDeactivated(WindowEvent e) {}
    @Override public void windowActivated(WindowEvent e) {}
    @Override public void windowIconified(WindowEvent e) {}
    @Override public void windowDeiconified(WindowEvent e) {}
    @Override public void windowClosed(WindowEvent e) {}
    @Override public void windowOpened(WindowEvent e) {}
    @Override public void windowClosing(WindowEvent e) {System.exit(0);}
    
    
    /**
     * Nested helper class that rolls the Dice in the ArrayList.
     */
    private class RollDice implements ActionListener {
        
        /**
         * Rolls the die in the ArrayList by calling the roll() function for 
         * die. The total die rolls are then displayed via a Label.
         */
        protected void rollDice() {
            cleanUp();
            String result = "";
            if(dice.size()>0) {
                //Construct the label text and get the rolls for each dice.
                result = "You rolled a ";
                for(int i=0;i<dice.size();i++) {
                    result+= dice.get(i).roll();
                    if(i==dice.size()-2) { result+= " and "; }                 
                    else if(i!=dice.size()-1) { result+= ", "; } 
                } 
                result+=".";
            }
            else { result = "No dice to roll"; }
            resultLabel.setText(result);
            frame.add(resultLabel);
            frame.setVisible(true);
        }
        /**
         * When the Roll button is pressed, this method is called to roll the 
         * die.
         * @param e an event
         */
        @Override public void actionPerformed(ActionEvent e) {
            rollDice();
        }
    }
}