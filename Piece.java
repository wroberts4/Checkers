import java.util.ArrayList;
public class Piece {
	boolean isKing;
	ArrayList<ArrayList<Integer>> viableMoves;
	final int playerNumber;
	int x;
	int y;
	
	Piece(int playerNumber, int x, int y) {
		viableMoves = new ArrayList<ArrayList<Integer>>();
		this.playerNumber = playerNumber;
		this.x = x;
		this.y = y;
		isKing = false;
	}
	
	boolean move(Piece[][] locations, int x, int y) {
		locations[x][y] = locations[this.x][this.y];
		locations[this.x][this.y] = null;
		this.x = x;
		this.y = y;
		if (!isKing && playerNumber == 1 && this.y == 0) {
			isKing = true;
			return true;
		}
		if (!isKing && playerNumber == 2 && this.y == 7) {
			isKing = true;
			return true;
		}
		return false;
	}
	
	void updateViableMoves(Piece[][] locations) {
		viableMoves = new ArrayList<ArrayList<Integer>>();
		addMove(locations, x - 1, y + 1 - 2 * (playerNumber%2));
		addMove(locations, x + 1, y + 1 - 2 * (playerNumber%2));
		if (isKing) {
			addMove(locations, x - 1, y - 1 + 2 * (playerNumber%2));
			addMove(locations, x + 1, y - 1 + 2 * (playerNumber%2));
		}
	}
	
	private void addMove(Piece[][] locations, int x, int y) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		try {
			if (locations[x][y] == null) {
				temp.add(x);
				temp.add(y);
				viableMoves.add(temp);
		}
			else if (locations[x][y].playerNumber != playerNumber && locations[2*x - this.x][2*y - this.y] == null) {
				temp.add(2*x - this.x);
				temp.add(2*y - this.y);
				viableMoves.add(temp);
			}
		} catch (IndexOutOfBoundsException e) {
		}
	}
	
	public String toString() {
		if (isKing) {
			return "|K" + playerNumber;
		}
		return "|O" + playerNumber;
	}
}
