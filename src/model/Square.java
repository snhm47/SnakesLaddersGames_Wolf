package model;

public class Square {
	private static int num = 0;
	private SquareType squareType;
	private int Number ;
	private Boolean Occupied;

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
		return Number;
	}

	public void setNumber(int number) {
		this.Number = number;
	}


	@Override
	public String toString() {
		return "Square [squareType=" + squareType + ", number=" + Number + ", Occupi=" + Occupied + "]";
	}

	public Boolean getOccupi() {
		return Occupied;
	}

	public void setOccupi(Boolean occupied) {
		Occupied = occupied;
	}
	
}
