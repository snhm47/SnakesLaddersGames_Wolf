package model;

public class Square {
	 private SquareType squareType;

	   
		public SquareType getSquareType() {
		return squareType;
	}

	public void setSquareType(SquareType squareType) {
		this.squareType = squareType;
	}
	public Square(SquareType squareType) {
		super();
		this.squareType = squareType;
	}


		// Methods
	    public void checkSquare() {}
}
