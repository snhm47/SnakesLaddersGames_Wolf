package model;

import Utils.DiffLevel;

public class DiceFactory {
	public Dice getDice(DiffLevel dl) {
		
		if(dl == null) {
			return null;
		}
		if(dl.equals(DiffLevel.easy)) {
			EasyGameDice egd = new EasyGameDice();
			return egd;
		}else if(dl.equals(DiffLevel.medium)) {
			MediumGameDice mgd = new MediumGameDice();
			return mgd;
		}else {
			HardGameDice hgd = new HardGameDice();
			return hgd;
		}
	}
}
