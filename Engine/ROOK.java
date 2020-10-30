import java.util.ArrayList;

public class ROOK extends Piece {

    public ROOK(Colour colour,int i , int j) {
        super(colour, i, j);
    }

    @Override
    public boolean canMove(Piece[][] board, String move) {
        return false;
    }

    @Override
    public ArrayList<String> posiblesMoves(Piece[][] board, Piece tpiece) {
        Move t = new Move();
        return t.moveRook(board,tpiece);
    }

    @Override
    public String getType() {
        return "ROOK";
    }

    @Override
    public String toString() {
        return "ROOK" + colour + " " + this.i + " " + this.j + " ";
    }
}
