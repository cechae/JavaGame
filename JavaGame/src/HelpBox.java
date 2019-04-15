import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HelpBox {
	int result;
	
	public void callHelp() {
		JFrame parent = new JFrame("Information");
		result = JOptionPane.showConfirmDialog(parent, "Information :" + "\n" +
		    " In this game, the objective is to score as many points as possible"
		        + " by correctly knocking off 4 different types of obstacles" 
		    	+ " as pikachu walks through the park. \n"
		        + " When the next closest obstacle is \n " 
		    	+ "* a tree, you should press RIGHT arrow key \n"
				+ "* a mount, you should press DOWN arrow key \n"
		    	+ "* a log, you should press SPACE key \n"
				+ "* a cloud, you should press UP arrow key in order to score. \n"
				+ " You will score 1 point every time you correctly hit the key"
				+ " in response to one of these 4 obstacles above. \n"
				+ " if you miss an obstacle (so that pikachu is hit by an"
				+ " obstacle) or hit a wrong key,"
				+ " you will lose 1 heart as a result. \n"
		    	+ " If you encounter a bomb, you should not press any key"
				+ "until the bomb passes you and disappears. \n"
				+ " If you press any key when the most immediate obstacle is a"
				+ " bomb, you will lose 1 heart as bomb detonates. \n" 
				+ " If you successfully knocks off a CLOUD, it "
				+ " will not only let you score, but also restores 1 heart. \n"
				+ " If you successfully knocks off a LOG, it will let you "
				+ " score 2 points, instead of 1 point. \n"
				+ " You have 10 hearts at start of the game, displayed on the "
				+ "top right of the screen. \n"
				+ " When you have 0 heart left, the game is over. \n"
				+ " Good Luck!",
				"Game Instructions",
				JOptionPane.OK_OPTION
				);
	}
}
