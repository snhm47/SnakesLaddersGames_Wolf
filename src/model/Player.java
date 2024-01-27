package model;

public class Player {
	  private String nickName;
	    private Color colorPlayer;
	    private int winCount;

	    
	    public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public Color getColorPlayer() {
			return colorPlayer;
		}

		public void setColorPlayer(Color colorPlayer) {
			this.colorPlayer = colorPlayer;
		}

		public int getWinCount() {
			return winCount;
		}

		public void setWinCount(int winCount) {
			this.winCount = winCount;
		}

		
		public Player(String nickName, Color colorPlayer, int winCount) {
			super();
			this.nickName = nickName;
			this.colorPlayer = colorPlayer;
			this.winCount = winCount;
		}

		public boolean checkNameExist(String name) {
	         return false;}

	    public boolean checkColorExist(String color) {
	           return false; }

	    // Notifications
	    public static final String NAME_FOUND_CHANGE_MESSAGE = "The name was found, please change it.";
	    public static final String NAME_SUCCESSFULLY_RECEIVED_MESSAGE = "The name was successfully received.";
	    public static final String COLOR_FOUND_CHANGE_MESSAGE = "Please change the color found.";
	    public static final String COLOR_SUCCESSFULLY_RECEIVED_MESSAGE = "The color was successfully received.";

}
