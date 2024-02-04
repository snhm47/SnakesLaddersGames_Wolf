package model;

import java.util.Random;

import Utils.Color;

public class Snakes {
	private Color snakeType;
	private int startSnake;
	private int endSnake;

	public Snakes(Color snakeType, int startSnake, int endSnake) {
		super();
		this.snakeType = snakeType;
		this.startSnake = startSnake;
		this.endSnake = endSnake;
	}

	public Snakes buildSnake(Integer BoardSize) {

		Random random = new Random();

		// Get a random type from the SnakeType enum
		Color randomType = Color.values()[random.nextInt(Color.values().length)];

		// NOTE :
		// WE SHOULD CREATE A FUNCTION IN THE GAME CLASS that has the following :
		// public void generatesSnakes(Integer BoardSize,Integer difLvl): and inside it
		// should be a while loop
		// i=0, while(i<diflvl){... i++); so by that we generate the number of snakes
		// that we need for the lvl of the game
		//int randomPosition = random.nextInt(BoardSize) + 1; not needed
		
		//the randomStartSnake and random endsnake is to build the snake from head to tail on the board
		// the-1 to ensure that it doesnt happen to be build on the last square 
		int randomStartSnake = random.nextInt(BoardSize-1) + 1;
		int randomEndSnake = random.nextInt(BoardSize-1) + 1;
		
		setEndSnake(randomEndSnake);
		setStartSnake(randomStartSnake);
		
		return new Snakes(randomType, randomStartSnake, randomEndSnake);
	}

	public boolean checkPlayerSnake(int playerPosition, Snakes snake) {
		// checks if the player is on the head of the snake
		return playerPosition == snake.getStartSnake();
	}

	public void movePlayerBack(RunningGame rg , Player player, Snakes snake) {
		// Check if the player is on the head of the snake
		if (checkPlayerSnake(rg.getPlayerPlacement().get(player), snake)) {
			// Move the player back to the end of the snake
			rg.getPlayerPlacement().put(player , snake.endSnake);
			System.out.println("Player moved back to the  snake tail!");
		}
	}

	public Color getSnakeType() {
		return snakeType;
	}

	public void setSnakeType(Color snakeType) {
		this.snakeType = snakeType;
	}

	@Override
	public String toString() {
		return "Snakes [snakeType=" + snakeType + ", startSnake=" + startSnake + ", endSnake=" + endSnake + "]";
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

	// Notifications
	public static final String SNAKE_ENCOUNTERED_MESSAGE = "Oh no! You encountered a snake and moved back.";

}
