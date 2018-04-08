public interface CheckersGUI {
    public void update(); // Update will draw objects
    public Piece mouseDown(); // MouseDown is called from Main whenever the
                              // mouse is clicked
    public boolean mouseUp(Piece piece); // When the mouse is released, this method gets called
    public boolean isMouseOver(); // This method checks if the mouse is over any GUI object
}