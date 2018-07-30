import java.awt.Dimension;

import javax.swing.JFrame;

public class main{
	/**
	 * Jarrod De Ferrari - @imteachable
	 */
	public static void main(String args[]){

		final JFrame frame = new JFrame("DataRipper"); //create JFrame
		frame.getContentPane().add(new Display()); //add a new content pane(Display)
        frame.setVisible(true); //make it visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set close operation to close
        frame.setResizable(false); //unresizable
        Dimension d = frame.getPreferredSize();
        d.setSize(d.width, d.height);
        frame.setSize(d);
//      frame.pack();
	}
	
}