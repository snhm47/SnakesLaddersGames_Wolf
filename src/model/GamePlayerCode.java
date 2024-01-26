package model;

import java.util.List;

public class GamePlayerCode {
	private Game currentGame;
    private List<Player> players;
    private int[] playerPlacement;

    public Game getCurrentGame() {
		return currentGame;
	}

	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public int[] getPlayerPlacement() {
		return playerPlacement;
	}

	public void setPlayerPlacement(int[] playerPlacement) {
		this.playerPlacement = playerPlacement;
	}

	public GamePlayerCode(Game currentGame, List<Player> players, int[] playerPlacement) {
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
