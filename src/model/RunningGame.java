package model;

import java.util.ArrayList;
import java.util.HashMap;

public class RunningGame {
	private Games currentGame;
    private ArrayList<Player> players;
    private HashMap<Player, Integer> playerPlacement;

    public Games getCurrentGame() {
		return currentGame;
	}

	public void setCurrentGame(Games currentGame) {
		this.currentGame = currentGame;
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

	public RunningGame(Games currentGame, ArrayList<Player> players, HashMap<Player, Integer> playerPlacement) {
		super();
		this.currentGame = currentGame;
		this.players = players;
		this.playerPlacement = playerPlacement;
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
