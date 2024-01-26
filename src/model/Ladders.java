package model;

public class Ladders {
	 private LadderType ladderType;
	    private int startLadder;
	    private int endLadder;

	    public LadderType getLadderType() {
			return ladderType;
		}

		public void setLadderType(LadderType ladderType) {
			this.ladderType = ladderType;
		}

		public int getStartLadder() {
			return startLadder;
		}

		public void setStartLadder(int startLadder) {
			this.startLadder = startLadder;
		}

		public int getEndLadder() {
			return endLadder;
		}

		public void setEndLadder(int endLadder) {
			this.endLadder = endLadder;
		}
		
		public Ladders(LadderType ladderType, int startLadder, int endLadder) {
			super();
			this.ladderType = ladderType;
			this.startLadder = startLadder;
			this.endLadder = endLadder;
		}

		public void buildLadder() {
			
	    }

	    public void movePlayerUp(Player player){
	    	
	    }

	    // Notifications
	    public static final String LADDER_CLIMBED_MESSAGE = "Congratulations! You climbed a ladder and moved forward.";

}
