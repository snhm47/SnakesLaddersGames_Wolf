package model;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class RunningGame {
	private static Games currentGame;
    private ArrayList<Player> players;
    private HashMap<Player, Integer> playerPlacement;
    private Boolean endGame ;
    private HashMap<Integer, ImageView> pc ;
    private HashMap<Integer, Player> pp ;
    private HashMap<Player, ImageView>pci ;
    private Board board;
    
    
    public static RunningGame instanceRunningGame;
	public static synchronized RunningGame getInstance() {
		if (instanceRunningGame == null)
			instanceRunningGame = new RunningGame(currentGame);

	    return instanceRunningGame;
	}

    public Games getCurrentGame() {
		return currentGame;
	}

	public static void setCurrentGame(Games currentGame) {
		RunningGame.currentGame = currentGame;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public HashMap<Player, Integer> getPlayerPlacement() {
		return playerPlacement;
	}

	public void setPlayerPlacement(HashMap<Player, Integer> playerPlacement) {
		this.playerPlacement = playerPlacement;
	}

	public RunningGame(Games currentGame) {
		super();
		RunningGame.currentGame = currentGame;
		this.players = new ArrayList<Player>();
		this.playerPlacement = new HashMap<Player, Integer>();
		this.endGame = false;
		this.pc = new HashMap<Integer, ImageView>();
		this.pp = new HashMap<Integer, Player>();
		this.pci = new HashMap<Player, ImageView>();
	}


	public HashMap<Player, ImageView> getPci() {
		return pci;
	}

	public void setPci(HashMap<Player, ImageView> pci) {
		this.pci = pci;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public HashMap<Integer, Player> getPp() {
		return pp;
	}

	public void setPp(HashMap<Integer, Player> pp) {
		this.pp = pp;
	}

	public HashMap<Integer, ImageView> getPc() {
		return pc;
	}

	public void setPc(HashMap<Integer, ImageView> pc) {
		this.pc = pc;
	}

	public Boolean getEndGame() {
		return endGame;
	}

	public void setEndGame(Boolean endGame) {
		this.endGame = endGame;
	}

	public void nextTurn() {}

    public void handleQuestionPrompt(Player currentPlayer) {
      }

    public void placementInGame(Player currentPlayer, int steps) {
     }

    public void checkAndUpdate(Player currentPlayer, int steps) {
    }

    public static final String NEXT_PLAYER_TURN_MESSAGE = "Now it's the next player's turn";

}