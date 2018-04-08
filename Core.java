import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class Core {
	static Piece piece;
	static int playerx;
	static int playery;

	public static void main(String[] args) {
		//Piece[][] locations = new Piece[8][8];
		ArrayList<Piece> player1 = new ArrayList<Piece>();
		ArrayList<Piece> player2 = new ArrayList<Piece>();
		int playerTurn = 0;
		
		Main.main(args);
		
		/* Initialize board and player pieces. */
		for (int i = 0; i < 4; i++) {
			Main.locations[2 * i + 1][0] = new Piece(2, 2 * i + 1, 0);
			player2.add(Main.locations[2 * i + 1][0]);
			Main.locations[2 * i][1] = new Piece(2, 2 * i, 1);
			player2.add(Main.locations[2 * i][1]);
			Main.locations[2 * i + 1][2] = new Piece(2, 2 * i + 1, 2);
			player2.add(Main.locations[2 * i + 1][2]);

			Main.locations[2 * i][5] = new Piece(1, 2 * i, 5);
			player1.add(Main.locations[2 * i][5]);
			Main.locations[2 * i + 1][6] = new Piece(1, 2 * i + 1, 6);
			player1.add(Main.locations[2 * i + 1][6]);
			Main.locations[2 * i][7] = new Piece(1, 2 * i, 7);
			player1.add(Main.locations[2 * i][7]);
		}

		Random rng = new Random();
		while (player1.size() > 0 && player2.size() > 0) {
			System.out.println(player1.size() + ", " + player2.size());
			printBoard(Main.locations);
			if (playerTurn + 1 == 2) {
				piece = player2.get(rng.nextInt(player2.size()));
				piece.updateViableMoves(Main.locations);

				while (piece.viableMoves.size() == 0) {
					piece = player2.get(rng.nextInt(player2.size()));
					piece.updateViableMoves(Main.locations);
				}
				movePiece(playerTurn + 1,piece, Main.locations, player1, player2);
			} else {
				piece = null;
				while (piece == null) {
					//System.out.println(piece);
				}
				piece.updateViableMoves(Main.locations);

				while (piece.viableMoves.size() == 0) {
					piece = player1.get(rng.nextInt(player1.size()));
					piece.updateViableMoves(Main.locations);
				}
				movePiece(playerTurn + 1,piece, Main.locations, player1, player2);
			}
			playerTurn = (playerTurn + 1) % 2;

		}
		System.out.println("Player Turn: " + (playerTurn + 1));
		printBoard(Main.locations);
	}

	private static void movePiece(int playerTurn, Piece piece, Piece[][] board, ArrayList<Piece> player1, ArrayList<Piece> player2) {
		boolean hasJump = false;
		ArrayList<Integer> jumps = new ArrayList<Integer>();
		Random rng = new Random();
		
		if (piece.viableMoves.size() == 0) {
			return;
		}
		
		/* Finds a double jump if possible */
		for (int i = 0; i < piece.viableMoves.size(); i++) {
			if (Math.abs(piece.viableMoves.get(i).get(0) - piece.x) == 2) {
				jumps.add(i);
				hasJump = true;
			}
			else if (i == piece.viableMoves.size() - 1) {
				hasJump = false;
			}
		}
		
		if (playerTurn == 2) {
			int move = rng.nextInt(piece.viableMoves.size());
			if (jumps.size() > 0) {
				move = jumps.get(rng.nextInt(jumps.size()));
			}
			
			if (Math.abs(piece.viableMoves.get(move).get(0) - piece.x) == 2
					&& Math.abs(piece.viableMoves.get(move).get(1) - piece.y) == 2) {
				if (board[piece.x + (piece.viableMoves.get(move).get(0) - piece.x) / 2][piece.y
						+ (piece.viableMoves.get(move).get(1) - piece.y) / 2] != null) {
					if (board[piece.x + (piece.viableMoves.get(move).get(0) - piece.x) / 2][piece.y
							+ (piece.viableMoves.get(move).get(1) - piece.y) / 2].playerNumber == 1) {
						player1.remove(board[piece.x + (piece.viableMoves.get(move).get(0) - piece.x) / 2][piece.y
								+ (piece.viableMoves.get(move).get(1) - piece.y) / 2]);
					} else {
						player2.remove(board[piece.x + (piece.viableMoves.get(move).get(0) - piece.x) / 2][piece.y
								+ (piece.viableMoves.get(move).get(1) - piece.y) / 2]);
					}
					board[piece.x + (piece.viableMoves.get(move).get(0) - piece.x) / 2][piece.y
							+ (piece.viableMoves.get(move).get(1) - piece.y) / 2] = null;
				}
	
			}
			if (piece.move(board, piece.viableMoves.get(move).get(0), piece.viableMoves.get(move).get(1))) {
				return;
			}
		}
		else {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(playerx);
			temp.add(playery);
			int move = -1;
			while (move == -1) {
				for (int i = 0; i < piece.viableMoves.size(); i++) {
					if (piece.viableMoves.get(i).get(0) == playerx && piece.viableMoves.get(i).get(1) == playery) {
						move = i;
					}
				}
			}
			if (jumps.size() > 0) {
				move = jumps.get(rng.nextInt(jumps.size()));
			}
			
			if (Math.abs(piece.viableMoves.get(move).get(0) - piece.x) == 2
					&& Math.abs(piece.viableMoves.get(move).get(1) - piece.y) == 2) {
				if (board[piece.x + (piece.viableMoves.get(move).get(0) - piece.x) / 2][piece.y
						+ (piece.viableMoves.get(move).get(1) - piece.y) / 2] != null) {
					if (board[piece.x + (piece.viableMoves.get(move).get(0) - piece.x) / 2][piece.y
							+ (piece.viableMoves.get(move).get(1) - piece.y) / 2].playerNumber == 1) {
						player1.remove(board[piece.x + (piece.viableMoves.get(move).get(0) - piece.x) / 2][piece.y
								+ (piece.viableMoves.get(move).get(1) - piece.y) / 2]);
					} else {
						player2.remove(board[piece.x + (piece.viableMoves.get(move).get(0) - piece.x) / 2][piece.y
								+ (piece.viableMoves.get(move).get(1) - piece.y) / 2]);
					}
					board[piece.x + (piece.viableMoves.get(move).get(0) - piece.x) / 2][piece.y
							+ (piece.viableMoves.get(move).get(1) - piece.y) / 2] = null;
				}
	
			}
			if (piece.move(board, piece.viableMoves.get(move).get(0), piece.viableMoves.get(move).get(1))) {
				return;
			}
		}
		
		piece.updateViableMoves(board);
		if (hasJump) {
			/* Finds a double jump if possible */
			for (int i = 0; i < piece.viableMoves.size(); i++) {
				if (Math.abs(piece.viableMoves.get(i).get(0) - piece.x) == 2) {
					hasJump = true;
					break;
				}
				else if (i == piece.viableMoves.size() - 1) {
					hasJump = false;
				}
			}
		}
		if (hasJump == true) {
			movePiece(playerTurn, piece, board, player1, player2);
		}
	}

	private static void printBoard(Piece[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[j][i] == null) {
					System.out.print("|__");
				} else {
					System.out.print(board[j][i]);
				}
			}
			System.out.println("|");
		}
		System.out.println();
	}

}
