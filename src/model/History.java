package model;

import java.sql.Date;

public class History {
	private String winnerName;
    private Date gameDate;
    private int gameTime;
    private int numberOfPlayers;
    
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

	public History(String winnerName, Date gameDate, int gameTime, int numberOfPlayers) {
		super();
		this.winnerName = winnerName;
		this.gameDate = gameDate;
		this.gameTime = gameTime;
		this.numberOfPlayers = numberOfPlayers;
	}

    public void showData() {
     }
}
