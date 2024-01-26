package model;

public class Snakes {
	  private SnakeType snakeType;
	    private int position;
	    private int startSnake;
	    private int endSnake;

	    public SnakeType getSnakeType() {
			return snakeType;
		}

		public void setSnakeType(SnakeType snakeType) {
			this.snakeType = snakeType;
		}

		public int getPosition() {
			return position;
		}

		public void setPosition(int position) {
			this.position = position;
		}

		public int getStartSnake() {
			return startSnake;
		}

		public void setStartSnake(int startSnake) {
			this.startSnake = startSnake;
		}

		public int getEndSnake() {
			return endSnake;
		}

		public void setEndSnake(int endSnake) {
			this.endSnake = endSnake;
		}
		
		public Snakes(SnakeType snakeType, int position, int startSnake, int endSnake) {
			super();
			this.snakeType = snakeType;
			this.position = position;
			this.startSnake = startSnake;
			this.endSnake = endSnake;
		}

		// Methods
	    public void buildSnake() {
	       }

	    public boolean checkPlayerSnake(int playerPosition) {
	         return false; }

	    public void movePlayerBack(Player player) {
	       }

	    // Notifications
	    public static final String SNAKE_ENCOUNTERED_MESSAGE = "Oh no! You encountered a snake and moved back.";

}
