package model;

import java.sql.Date;

public class History {
	private String winnerName;
	private Date gameDate;
	private int gameTime;
	private int numberOfPlayers;
	private String Difficulty;

	public String getWinnerName() {
		return winnerName;
	}

	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}

	public Date getGameDate() {
		return gameDate;
	}

	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}

	public int getGameTime() {
		return gameTime;
	}

	public void setGameTime(int gameTime) {
		this.gameTime = gameTime;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public String getDifficulty() {
		return Difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.Difficulty = difficulty;
	}

	public History(String winnerName, Date gameDate, int gameTime, int numberOfPlayers, String difficulty) {
		this.winnerName = winnerName;
		this.gameDate = gameDate;
		this.gameTime = gameTime;
		this.numberOfPlayers = numberOfPlayers;
		this.Difficulty = difficulty;
	}

//display game time and number of players
	public void showData() {
		System.out.println("Game Details:");
		System.out.println("Winner Name: " + winnerName);
		System.out.println("Game Date: " + gameDate);
		System.out.println("Game Time: " + gameTime + " seconds");
		System.out.println("Number Of Players: " + numberOfPlayers);
		System.out.println("Difficulty: " + Difficulty);
		System.out.println("Game Time: " + gameTime + " seconds");
		System.out.println("Number Of Players: " + numberOfPlayers);

	}
}
