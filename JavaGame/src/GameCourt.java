
import java.util.*;
import java.awt.*;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameCourt extends JPanel {
	private Pikachu pikachu;
	private Background bg;
	LinkedList<Obstacles> listObs = new LinkedList<Obstacles>();
	private JLabel scoreLabel = new JLabel("Score: 0");
	Timer timer;
	private JLabel lifeCount = new JLabel("Life Count");
	private ArrayList<String> input = new ArrayList<>();
	private String[] parts;
	Set<String> set;
	private ArrayList<Integer> scorelist = new ArrayList<>();
	private Map<Integer, Set<String>> map;
	private ArrayList<String> newI = new ArrayList<>();
	private JLabel status;
	public static final int COURT_WIDTH = 900;
	public static final int COURT_HEIGHT = 300;
	public static final int INTERVAL = 30;
	private Random r = new Random();
	Obstacles tree;
	Obstacles mount;
	Obstacles cloud;
	Obstacles log;
	Obstacles bomb;
	boolean firstTime = true;
	boolean playing = false;
	private FileReader read = null;
	private BufferedReader reader = null;
	private Player player;
	private String line;
	private String userInput = null;

	public GameCourt(JLabel status) {
		player = new Player();
		bg = new Background();
		tree = new Tree(550, 150, 80, 100);
		mount = new Mount(650, 150, 80, 100);
		cloud = new Cloud(800, 150, 80, 100);
		log = new Log(900, 150, 80, 100);
		bomb = new Bomb(950, 150, 80, 100);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		scoreLabel.setFont(new Font("Comic Sans", Font.PLAIN, 20));
		lifeCount.setFont(new Font("Comic Sans", Font.PLAIN, 20));

		timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();

			}
		});
		timer.start();
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				Obstacles o = listObs.getFirst();
				if (playing) {
					if (bomb.isInstance(o)) {
						bomb.effects(GameCourt.this);
					} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						if (tree.isInstance(o)) {
							playerScored();
							tree.effects(GameCourt.this);
						} else {
							playerMissedKey();
						}
					} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						if (mount.isInstance(o)) {
							playerScored();
							mount.effects(GameCourt.this);
						} else {
							playerMissedKey();
						}
					} else if (e.getKeyCode() == KeyEvent.VK_UP) {
						if (cloud.isInstance(o)) {
							playerScored();
							cloud.effects(GameCourt.this);

						} else {
							playerMissedKey();
						}
					} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						if (log.isInstance(o)) {
							playerScored(); 
							log.effects(GameCourt.this); 
						} else {
							playerMissedKey();
						}
					}
				} else {
					player.setHeartCount(player.getHeartCount() - 1);
				}
			}
		});
		this.status = status;
		add(scoreLabel); // Score board
		add(lifeCount); // Timer JLabel
	}
	
	public void playerScored() {
		player.setScore(player.getScore() + 1);
		scoreLabel.setText("Score: " + player.getScore());
	}
	public void poll() {
		listObs.poll();
	}
	public void add() {
		listObs.addLast(getRand());
	}
	public void playerMissedKey() {
		player.setHeartCount(player.getHeartCount() - 1);
	}
	public Player getPlayer() {
		return this.player;
	}
	public void addLife() {
		player.setHeartCount(player.getHeartCount() + 1);
	}
	public void addScore() {
		player.setScore(player.getScore() + 1);
		scoreLabel.setText("Score: " + player.getScore());
	}
	public Obstacles getRand() {
		if (r.nextInt(8) == 0) {
			bomb = new Bomb(1000, 150, 80, 100);
			return bomb;
		} else if (r.nextInt(4) == 1) {
			mount = (new Mount(1000, 150, 80, 100));
			return mount;
		} else if (r.nextInt(4) == 2) {
			cloud = (new Cloud(1000, 150, 80, 100));
			return cloud;
		} else if (r.nextInt(4) == 3){
			log = (new Log(1000, 150, 80, 100));
			return log;
		}
		else {
			tree = (new Tree(1000, 150, 80, 100));
			return tree;
		}
	}
	public void help() {
		playing = false;
		status.setText("Instructions Page: Pause");
		requestFocusInWindow();
		timer.stop();
	}
	public void resume() {
		playing = true;
		status.setText("Instructions Page: Pause");
		requestFocusInWindow();
		timer.start();
	}

	public void reset() {
		playing = true;
		listObs.clear();
		tree = new Tree(550, 150, 80, 100);
		mount = new Mount(650, 150, 80, 100);
		cloud = new Cloud(800, 150, 80, 100);
		log = new Log(900, 150, 80, 100);
		listObs.add(tree);
		listObs.add(mount);
		listObs.add(cloud);
		listObs.add(log);
		pikachu = new Pikachu();
		status.setText("Game Running...");
		timer.restart();
		player.setScore(0);
		player.setHeartCount(10);
		scoreLabel.setText("Score: " + player.getScore());
		requestFocusInWindow();
	}
	void tick() {
		if (playing) {
			repaint();
			for (int i = 0; i < listObs.size(); i++) {
				if (listObs.get(i).getPx() <= 100) {
					if (!(listObs.get(i) instanceof Bomb)) {
						playerMissedKey();
					}
					listObs.remove(listObs.get(i));
					listObs.addLast(getRand());
					i--;
				}
			}
			if (player.getHeartCount() <= 0) {
				playing = false;
				status.setText("Game Over!");
				gameOver();
			}
		}
		firstTime = false;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		bg.draw(g);
		pikachu.draw(g);
		listObs.get(0).draw(g);
		listObs.get(1).draw(g);
		listObs.get(2).draw(g);
		listObs.get(3).draw(g);
		for (int i = 0; i < player.getHeartCount(); i++) {
			Heart heart = new Heart(690 + (i * 20));
			heart.draw(g);
		}
		scoreLabel.setLocation(50, 10);
		lifeCount.setLocation(750, 35);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}

	
	public void gameOver() {
		if (!firstTime) {
			timer.stop();
			userInput = JOptionPane.showInputDialog("Please type in your "
					+ "name here");
			while (userInput.trim().isEmpty()) {
				userInput = JOptionPane.showInputDialog("Your input was empty."
						+ " PLEASE type in your name");
			}
			saveUserName(userInput, "HighScore.txt");
			readScores("HighScore.txt");
		}
	}

	public void saveUserName(String userInput, String toWrite) {
		if (userInput.length() > 0 && !userInput.trim().isEmpty()) {
			try {
				FileWriter write = null;
				PrintWriter writer = null;
				try {
					write = new FileWriter(toWrite, true);
					writer = new PrintWriter(write);
					writer.append("\n");
					writer.append(userInput + ":" + String.valueOf(
							player.getScore()));
				} catch (FileNotFoundException e) {
					JFrame b = new JFrame("Error!");
					JOptionPane.showMessageDialog(b, "The file to write score "+
					"was not found.");
				} catch (IOException e) {
					JFrame errorMsg = new JFrame("Error!");
					JOptionPane.showMessageDialog(errorMsg,
							"Could not load the file correctly. " + "Please "
									+ "check the format of the input file.");
				} finally {
					writer.close();
				}
			}
			// Catch exception when the user closed "enter name window" without
			// submitting their name.
			catch (NullPointerException e) {
				System.out.println("Enter Name Window was unexpectedly closed "+
			" without a name entered");
			}
		}
	}
	
	private void writeOrderedScore(String toWrite, String tgt) {
		FileWriter write = null;
		PrintWriter writer = null;
		try {
			write = new FileWriter(toWrite);
			writer = new PrintWriter(write);
			writer.append(tgt);
			displayScores(tgt);
		} catch (FileNotFoundException e) {
			JFrame b = new JFrame("Error!");
			JOptionPane.showMessageDialog(b, "The file to output the ordered "
					+ "scores was not found.");
		} catch (IOException e) {
			JFrame errorMsg = new JFrame("Error!");
			JOptionPane.showMessageDialog(errorMsg,
					"Could not load the file correctly. Please check the format"
					+ " of the input file.");
		} finally {
			writer.close();
		}
	}
	
	public void readScores(String toRead) {
		map = new TreeMap<Integer, Set<String>>();
		try {
			read = new FileReader(toRead);
			reader = new BufferedReader(read);
			line = reader.readLine();
			while (line != null) {
				if (line.trim().isEmpty()) {
					line = reader.readLine();
					continue;
				}
				line = line.trim();
				parts = line.split(":");
				if (parts.length != 2) {
					break;
					// If the file to read is not properly formatted,
					// just regard it as an empty file.
				}
				else {
					input.add(line);
					int i = Integer.parseInt(parts[1].trim());
					String s = parts[0].trim();
					set = new TreeSet<String>();
					if (map.containsKey(i)) {
						set = map.get(i);
						set.add(s);
					} else {
						set.add(s);
					}
					map.put(i, set);
					scorelist.add(i);
					line = reader.readLine();
				}
			}
			if (parts.length == 2) {
				Collections.sort(scorelist, Collections.reverseOrder());
				String newLine = "";
				for (int j = 0; j < scorelist.size(); j++) {
					int x = scorelist.get(j);
					Set<String> name = map.get(x);
					newI.add(name + ": " + String.valueOf(x));
				}
				for (String a : newI) {
					newLine += a + "\n";
				}
				writeOrderedScore("HighScore.txt", newLine);
			}
			else {
				String emptyLine = "";
				writeOrderedScore("HighScore.txt", emptyLine);
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File to read in score could not be found."
					+ "The program will just diplay the saved Score Board.");
			
		}
		  catch (IOException e) {
			  JFrame c = new JFrame("Error!");
				JOptionPane.showMessageDialog(c, "The file to read score cannot"
						+ "properly loaded ");
		} 
	}
	
	public void displayScores(String tgt) {
		if (parts.length == 2) {
			JFrame frame = new JFrame("Score Board");
			JOptionPane.showMessageDialog(frame, "SCOREBOARD: "+"\n"+"\n"+tgt);
		}
	}

}
