package model;

//observer design pattern subject class

public class PlayerMoveSubject implements PlayerMoveObserver{

	@Override
	public void onPlayerMovement(Player player, int from, int to) {
		// TODO Auto-generated method stub
		System.out.println(player.getNickName()+ " moved from position " + from + " to position " + to);
		player.setPlace(to);
	}

}
