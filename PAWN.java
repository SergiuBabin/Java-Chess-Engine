import java.util.ArrayList;

public class PAWN extends Piece {
    public PAWN(Colour colour, int i , int j) {
        super(colour, i, j);
    }

    @Override
    public boolean canMove(Piece[][] board, String move) {
        return false;
    }

    @Override
    public ArrayList<String> posiblesMoves(Piece[][] board, Piece tpiece) {
        Move t = new Move();
        return t.movePawn(board, tpiece);
    }

    @Override
    public String getType() {
        return "PAWN";
    }

    @Override
    public String toString() {
        return "PAWN" + colour + " " + super.i + " " + super.j + " ";
    }
}
