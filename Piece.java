import java.util.ArrayList;

enum Colour {Black, White}; //Pentru culoarea pieselor

abstract class Piece {
    public int i , j, nrMoves;
    public Colour colour;

    public Piece(Colour colour){
        this.colour = colour;
     }

    public Piece(Colour colour,int i , int j) {
        this.i = i;
        this.j = j;
        this.colour = colour;
        nrMoves = 0;
     }

    public void setPos(int i ,int j){
        this.i = i;
        this.j = j;
     }
    
    //Verifica daca poate sa faca miscarea (move)
    public abstract boolean canMove(Piece[][] board, String move); 
    
    //Cauta si intoarce toate miscarile posibile sub forma de ArrayList<String>
    public abstract ArrayList<String> posiblesMoves(Piece[][] board, Piece tpiece );
    
    //Intoarce tipul piesei
    public abstract String getType();
    
    //Intoarce tipul piesei+culoarea
    public abstract String toString();
}
