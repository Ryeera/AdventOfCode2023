package de.Ryeera.AOC2023.Day04;

public class ScratchCard {

	private int id, count;
	private int[] winning, own;
	
	public ScratchCard(String encoded) {
		id = Integer.parseInt(encoded.substring(5, 8).trim());
		count = 1;
		encoded = encoded.substring(10);
		String[] nums = encoded.split(" \\| ");
		String[] winningS = nums[0].trim().split("\\s+");
		String[] ownS = nums[1].trim().split("\\s+");
		winning = new int[winningS.length];
		own = new int[ownS.length];
		for (int i = 0; i < winningS.length; i++) {
			winning[i] = Integer.parseInt(winningS[i]);
		}
		for (int i = 0; i < ownS.length; i++) {
			own[i] = Integer.parseInt(ownS[i]);
		}
	}
	
	public ScratchCard(int id, int[] winning, int[] own) {
		this.id = id;
		this.count = 1;
		this.winning = winning;
		this.own = own;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[] getWinning() {
		return winning;
	}

	public void setWinning(int[] winning) {
		this.winning = winning;
	}

	public int[] getOwn() {
		return own;
	}

	public void setOwn(int[] own) {
		this.own = own;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void add() {
		this.count++;
	}
	
	public boolean remove() {
		return this.count-- <= 1;
	}

	public boolean isWinning(int num) {
		for (int i : winning) {
			if (num == i) return true;
		}
		return false;
	}
	
	public int getValue() {
		int winnings = getPoints();
		if (winnings == 0) return 0;
		int value = 1;
		for (int i = 1; i < winnings; i++) {
			value *= 2;
		}
		return value;
	}
	
	public int getPoints() {
		int winnings = 0;
		for (int i : own) {
			if (isWinning(i)) winnings++;
		}
		return winnings;
	}
}
