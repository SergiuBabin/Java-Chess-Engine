import java.util.ArrayList;

public class BISHOP extends Piece {

    public BISHOP(Colour colour, int i , int j ) {
        super(colour, i, j);
    }

    @Override
    public boolean canMove(Piece[][] board, String move) {
        return false;
    }

    @Override
    public ArrayList<String> posiblesMoves(Piece[][] board, Piece tpiece) {
        Move t = new Move();
        return t.moveBishop(board, tpiece);
    }

    @Override
    public String getType() {
        return "BISHOP";
    }

    @Override
    public String toString() {
        return "BISHOP" + colour + " " + this.i + " " + this.j + " ";
    }
}
