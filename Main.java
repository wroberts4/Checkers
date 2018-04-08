import java.util.ArrayList;
public class Main {
    private PApplet processing; // The processing field used for GUI
    private ArrayList<CheckersGUI> guiObjects; // ArrayList to hold all Dorm Objects
    // Max number of furniture that LoadButton will be allowed to load at once.
    Tile[][] board = new Tile[8][8];
    static Piece[][] locations = new Piece[8][8];
    Piece movePiece;
    
    public void setLocations() {
    	
    }
    
    public Main(PApplet processing) {
        this.processing = processing; // Initialize the processing field
        this.guiObjects = new ArrayList<CheckersGUI>();
        processing.background(0);
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (i % 2 != 0) {
                    board[i][j] = new Tile((i * 70 + 160), (j * 70 + 55), processing, 100);
                    ++j;
                    board[i][j] = new Tile((i * 70 + 160), (j * 70 + 55), processing, 240);
                } else {
                    board[i][j] = new Tile((i * 70 + 160), (j * 70 + 55), processing, 240);
                    ++j;
                    board[i][j] = new Tile((i * 70 + 160), (j * 70 + 55), processing, 100);
                }
            }
        }
//        for (int i = 0; i < 4; i++) {
//            locations[2 * i + 1][0] = new Piece(2, 2 * i + 1, 0);
//            locations[2 * i][1] = new Piece(2, 2 * i, 1);
//            locations[2 * i + 1][2] = new Piece(2, 2 * i + 1, 2);
//            locations[2 * i][5] = new Piece(1, 2 * i, 5);
//            locations[2 * i + 1][6] = new Piece(1, 2 * i + 1, 6);
//            locations[2 * i][7] = new Piece(1, 2 * i, 7);
//        }
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                board[i][j].setPiece(locations[i][j]);
            }
        }
    }
    
    public void update() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                board[i][j].update();
            }
        }
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                board[i][j].setPiece(locations[i][j]);
            }
        }
    }
    public void mouseDown() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                movePiece = board[i][j].mouseDown();
                if (movePiece != null) {
                	Core.piece = locations[i][j];
                    locations[i][j] = null;
                    return;
                }
            }
        }
    }
    public void mouseUp() {
        if (movePiece != null) {
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    if (board[i][j].mouseUp(movePiece)) {
                    	for (int k = 0; k < movePiece.viableMoves.size(); k++) {
                    		if (i == movePiece.viableMoves.get(k).get(0) && movePiece.viableMoves.get(k).get(1) == j) {
                    			Core.playerx = i;
                    			Core.playery = j;
                    			locations[i][j] = new Piece(movePiece.playerNumber, i, j);
                                return;
                    		}
                    	}
                    }
                }
            }
        }
        locations[movePiece.x][movePiece.y] = movePiece;
    }
    public static void main(String[] args) {
        Utility.startApplication(); // call method to start dorm application
    }
}