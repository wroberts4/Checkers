public class Tile implements CheckersGUI {
    private static final int WIDTH = 70; // The buttons height and width
    private static final int HEIGHT = 70;
    protected PApplet processing; // The processing field for GUI
    private int[] position; // The buttons x and y positions
    private int color;
    Piece piece;
    int selected = 0;
    public Tile(int x, int y, PApplet processing, int color) {
        this.processing = processing;
        this.position = new int[2];
        this.position[0] = x; // x and y positions are placed in their respective index of
                              // position[]
        this.position[1] = y;
        this.color = color;
        this.piece = null;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    @Override
    public void update() {
        processing.fill(color);
        processing.rect(position[0] - WIDTH / 2, position[1] - HEIGHT / 2, position[0] + WIDTH / 2,
            position[1] + HEIGHT / 2); // Draw a rectangle centered around its x and y positions
        if (this.piece != null) {
            if (this.piece.playerNumber == 2) {
                processing.fill(0);
            } else {
                processing.fill(245, 0, 0);
            }
            processing.ellipse(position[0], position[1], 55, 55);
            if (this.piece.playerNumber == 2) {
                processing.fill(40);
            } else {
                processing.fill(225, 0, 0);
            }
            processing.ellipse(position[0], position[1], 45, 45);
        }
    }
    @Override
    public Piece mouseDown() {
        Piece piece1 = null;
        if (isMouseOver()) {
            if (this.piece == null) {
            } else {
                piece1 = this.piece;
                this.piece = null;
            }
        }
        return piece1;
    }
    @Override
    public boolean mouseUp(Piece piece) {
        if (isMouseOver()) {
            this.piece = piece;
            this.piece.x = piece.x;
            this.piece.y = piece.y;
            return true;
        }
        return false;
    }
    @Override
    public boolean isMouseOver() {
        boolean isOver = false; // Flag for if mouse is in the bounds of the tile
        if (processing.mouseX > (position[0] - 70 / 2)
            && processing.mouseX < (position[0] + 70 / 2)) {
            if (processing.mouseY > (position[1] - 70 / 2)
                && processing.mouseY < (position[1] + 70 / 2)) {
                isOver = true; // If mouse is within the objects bounds, return true
            }
        }
        return isOver;
    }
}