package diceRoller;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

/**
 * A simple GUI dice. Each dice has a global variable for its sides as well as 
 * how many of each die there are, say for example 3 6-sided die. Each dice can
 * be "removed" from the GUI via the 'Remove' button as well.
 * 
 * @author Tara Crittenden September 2017
 */
class Dice implements ActionListener {
    private Panel p;
    private JSpinner numOfDie;
    private JSpinner sidesOfDie;
    private Label sidesLabel;
    private Button removeDie;
    
    /**
     * Constructor for a Dice. Defaults are set to a single 6-sided die, but 
     * can be changed via the JSpinners once created. There is an upper limit
     * of 100 and a lower limit of 0 of the same die. For the number of sides, 
     * the upper limit is 100 and the lower being 2.
     */
    protected Dice() {
        //Set up the panel
        p = new Panel(new FlowLayout());
        sidesOfDie = new JSpinner(new SpinnerNumberModel(6,2,100,1));
        p.add(sidesOfDie);
        sidesLabel = new Label("Sided-Dice");
        p.add(sidesLabel);
        numOfDie = new JSpinner(new SpinnerNumberModel(1,0,100,1));
        p.add(numOfDie);
        removeDie = new Button("Remove");
        removeDie.addActionListener(this);
        p.add(removeDie);
        p.setVisible(true);
    }
    
    /**
     * Creates a new Dice instance based on the defaults.
     * @param args unused in this case
     */
    public static void main(String[] args) {
        Dice d = new Dice();
    }
    
    /**
     * Rolls this Dice and adds the sums together. The rolls are generated via a
     * Random instance invoked each time this method is called.
     * @return the total sum of the die rolls
     */
    protected int roll() {
        Random rand = new Random();
        int total = 0;
        for(int i=1;i<=this.getNumOfDie();i++) {
            total = total + rand.nextInt(this.getSidesOfDie()) + 1;
        }
        return total;
    }
    
    /**
     * Returns the panel for the Dice.
     * @return panel
     */
    public Panel getPanel() {
        return p;
    }
    
    /**
     * Returns the number of sides on the Dice. 
     * @return int
     */
    public int getSidesOfDie() {
        return (Integer) sidesOfDie.getValue();
    }
    
    /**
     * Returns the number of die in this Dice. 
     * @return int
     */
    public int getNumOfDie() {
        return (Integer) numOfDie.getValue();
    }
    
    /**
     * "Removes" the Dice from the GUI. It's not possible to remove this 
     * instance via one of its methods, so a workaround is to set the panel
     * visibility of false and remove the panel reference.
     * @param e an event
     */
    @Override public void actionPerformed(ActionEvent e) {
        p.setVisible(false); 
        p = null;
    }
}
