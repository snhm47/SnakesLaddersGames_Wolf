package model;

public class Square {
	private static int num = 0;
	private SquareType squareType;
	private int number ;
	private Boolean occupied;

	public Square(SquareType squareType) {
		super();
		this.squareType = squareType;
		this.setNumber(++num); 
		this.setOccupi(false);
	}

	// checking the type of the square once the player get on one for the game to know what to do exactly with it
    public void checkSquare() {
        switch (squareType) {
            case QUESTION:
                System.out.println("This is a question square");
                break;
            case SURPRISE:
                System.out.println("This is a surprise square");
                break;
            case REGULAR:
                break;
            default:
                System.out.println("Unexpected square type.");
                break;
        }
    }

	public SquareType getSquareType() {
		return squareType;
	}

	public void setSquareType(SquareType squareType) {
		this.squareType = squareType;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}


	@Override
	public String toString() {
		return "Square [squareType=" + squareType + ", number=" + number + ", Occupi=" + occupied + "]";
	}

	public Boolean getOccupi() {
		return occupied;
	}

	public void setOccupi(Boolean occupied) {
		this.occupied = occupied;
	}
	
}
