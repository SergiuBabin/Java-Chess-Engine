import java.util.ArrayList;
import java.util.*;

class Pair<F, S> {
	public F first;
	public S second;

	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}
 }

public class Board {
	//Declarare Board Sah
	Piece[][] board;
	//Initializare pieselor negre de Sah
	Piece rook1 = new ROOK(Colour.Black,8,1); 
	Piece rook2 = new ROOK(Colour.Black,8,8);
	Piece bishop1 = new BISHOP(Colour.Black,8,3);
	Piece bishop2 = new BISHOP(Colour.Black,8,6);
	Piece queen1 = new QUEEN(Colour.Black,8,5);
	Piece king = new KING(Colour.Black,8,4);
	Piece pawn1 = new PAWN(Colour.Black,7,1);
	Piece pawn2 = new PAWN(Colour.Black,7,2);
	Piece pawn3 = new PAWN(Colour.Black,7,3);
	Piece pawn4 = new PAWN(Colour.Black,7,4);
	Piece pawn5 = new PAWN(Colour.Black,7,5);
	Piece pawn6 = new PAWN(Colour.Black,7,6);
	Piece pawn7 = new PAWN(Colour.Black,7,7);
	Piece pawn8 = new PAWN(Colour.Black,7,8);
	Piece knight1 = new KNIGHT(Colour.Black,8,2);
	Piece knight2 = new KNIGHT(Colour.Black,8,7);
	//Initializare pieselor albe de Sah
	Piece rookw1 = new ROOK(Colour.White,1,1);
	Piece rookw2 = new ROOK(Colour.White,1,8);
	Piece bishopw1 = new BISHOP(Colour.White,1,3);
	Piece bishopw2 = new BISHOP(Colour.White,1,6);
	Piece queenw1 = new QUEEN(Colour.White,1,5);
	Piece kingw = new KING(Colour.White,1,4);
	Piece pawnw1 = new PAWN(Colour.White,2,1);
	Piece pawnw2 = new PAWN(Colour.White,2,2);
	Piece pawnw3 = new PAWN(Colour.White,2,3);
	Piece pawnw4 = new PAWN(Colour.White,2,4);
	Piece pawnw5 = new PAWN(Colour.White,2,5);
	Piece pawnw6 = new PAWN(Colour.White,2,6);
	Piece pawnw7 = new PAWN(Colour.White,2,7);
	Piece pawnw8 = new PAWN(Colour.White,2,8);
	Piece knightw1 = new KNIGHT(Colour.White,1,2);
	Piece knightw2 = new KNIGHT(Colour.White,1,7);

	public Board() {
		// Initializare board in starea initiala
		board = new Piece[][] {
			{null, null, null, null, null, null, null, null, null},
			{null, rookw1, knightw1, bishopw1, queenw1, kingw, bishopw2, knightw2, rookw2},
			{null, pawnw1, pawnw2, pawnw3, pawnw4, pawnw5, pawnw6, pawnw7, pawnw8},
			{null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null},
			{null, pawn1, pawn2, pawn3, pawn4, pawn5, pawn6, pawn7, pawn8},
			{null, rook1, knight1, bishop1, queen1, king, bishop2, knight2, rook2},
		};
	 }

	// Obtinerea pieselor de o anume culoare din board.
	public ArrayList<Piece> getPieces(Piece[][] board,Colour culoare) {
		ArrayList<Piece> pieces = new ArrayList<>();
		for(int i = 1 ; i <= 8 ; i++) {
			for(int j = 1; j <= 8 ; j++) {
				if(board[i][j] != null && board[i][j].colour.equals(culoare)) {
					board[i][j].setPos(i, j);
					pieces.add(board[i][j]);
				}
			}
		}
		return pieces;
	 }

	public boolean isAttacked(Colour colour, Piece[][] board1, int x, int y) {
		Colour c = colour;
		if(c.equals(Colour.White)) {
			c = Colour.Black;
		} else {
			c = Colour.White;
		}
		ArrayList<String> moves = new ArrayList<>();
		ArrayList<Piece> pieces = getPieces(board1,c);
		for (int i = 0; i < pieces.size(); i++) {
			if ((moves = pieces.get(i).posiblesMoves(board1, pieces.get(i))) != null) {
				for (String j : moves) {
					char[] move = j.toCharArray();
					int k = (int)move[2] - 96;
					int p = (int)move[3] - 48;
					if(k == y && p == x) {
						return true;
					}
				}                
			}
		}
		return false;
	 }

	// Verificare daca regele de o anume culoare este atacat.
	public boolean kingIsAttacked(Colour colour, Piece[][] board1) {
		int x, y;
		Colour c = colour;
		if(c.equals(Colour.White)) {
			c = Colour.Black;
			x = kingw.i;
			y = kingw.j;
		} else {
			c = Colour.White;
			x = king.i;
			y = king.j;
		}
		ArrayList<String> moves = new ArrayList<>();
		ArrayList<Piece> pieces = getPieces(board1, c);
		for (int i = 0; i < pieces.size(); i++){
			if ((moves = pieces.get(i).posiblesMoves(board1, pieces.get(i))) != null) {
				for (String j:moves) {
					char[] move = j.toCharArray();
					int k = (int)move[2] - 96;
					int p = (int)move[3] - 48;
					if(k == y && p == x){
						return true;
					}
				}                
			}
		}
		return false;
	 }

	// Aplica miscarea transmisa printr-un string pe board
	// in plus se verifica pentru daca miscare primita nu este rocada.
	// are loc incrementarea nrdemove-uri a lui king si pawn pentru
	// rocada si enpassant
	public void move(String nextMove) {
		char[] move = nextMove.toCharArray();
		int i = (int)move[0] - 96;
		int j = (int)move[1] - 48;
		int k = (int)move[2] - 96;
		int p = (int)move[3] - 48;
		if (move.length == 5) {
			if (move[4] == 'q')
				board[j][i] = new QUEEN(board[j][i].colour,i,j);
			else {
				p = (j == 4)?j-1:j+1;
				board[j][k] = null;
			}
		}
		if(board[j][i].getType().equals("KING")){
			switch (nextMove) {
				case "e1g1": 
					board[1][6] = board[1][8];
					board[1][8] = null;
					break;
				case "e1c1":
					board[1][4] = board[1][1];
					board[1][1] = null;
					break;
				case "e8g8":
					board[8][6] = board[8][8];
					board[8][8] = null;
					break;
				case "e8c8":
					board[8][4] = board[8][1];
					board[8][1] = null;
					break;
				default:
					break;
			}
		} 

		if (board[j][i].getType().equals("PAWN") && i != k && board[p][k] == null){
			board[j][k] = null;
		}

		if (board[j][i].getType().equals("PAWN") || board[j][i].getType().equals("KING")) {
			board[j][i].nrMoves++;
		}

		board[p][k] = board[j][i];
		board[j][i] = null;
		board[p][k].setPos(p,k);
	 }

	// functie similara cu move aplicata pe un board auxiliar
	// folosit in negamax
	public void movew(String nextMove, Piece[][] board1) {
		char[] move = nextMove.toCharArray();
		int i = (int)move[0] - 96;
		int j = (int)move[1] - 48;
		int k = (int)move[2] - 96;
		int p = (int)move[3] - 48;
		if (move.length == 5) {
			if (move[4] == 'q')
				board1[j][i] = new QUEEN(board1[j][i].colour, i, j);
			else {
				p = (j == 4) ? j-1 : j+1;
				board1[j][k] = null;
			}
		}

		if(board1[j][i].getType().equals("KING")) {
			switch (nextMove) {
				case "e1g1": 
					board1[1][6] = board1[1][8];
					board1[1][8] = null;
					break;
				case "e1c1":
					board1[1][4] = board1[1][1];
					board1[1][1] = null;
					break;
				case "e8g8":
					board1[8][6] = board1[8][8];
					board1[8][8] = null;
					break;
				case "e8c8":
					board1[8][4] = board1[8][1];
					board1[8][1] = null;
					break;
				default:
					break;

			}
		}
		if (board1[j][i].getType().equals("PAWN") || board1[j][i].getType().equals("KING")) {
			board1[j][i].nrMoves++;
		}
		board1[p][k] = board1[j][i];
		board1[j][i] = null;
		board1[p][k].setPos(p, k);
	 }

	// Functie ce intoarce pozitiile initiale din board ,dupa un move
	// aplicat pe board-ul ajutator in negamax.
	public void undo_move(String move, Piece[][] board1) {
		char[] undo_move = move.toCharArray();
		int i = (int)undo_move[0] - 96;
		int j = (int)undo_move[1] - 48;
		board1[j][i].setPos(j, i);
		if (board1[j][i].getType().equals("PAWN") || board1[j][i].getType().equals("KING")) {
			board1[j][i].nrMoves--;
		}
	 }

	// Functie din care porneste minimax-ul
	// se parcurg toate move-urile posibile culorii cu care jucam,de la
	// care porneste mersul in adancime,apeland functia negamax.
	public Pair<Integer, String> minimaxRoot(int depth, Piece[][] board1, Colour colour) {
		String best_move = null;
		int max_score = -1000000;

		ArrayList<String> all_moves = new ArrayList<>();
		ArrayList<String> moves = new ArrayList<>();
		ArrayList<Piece> pieces = getPieces(board1, colour);
		// obtinerea tuturor move-urilor pentru toate piesele
		for(int i = 0; i < pieces.size(); i++) {
			if((moves = pieces.get(i).posiblesMoves(board1,pieces.get(i))) != null)
				all_moves.addAll(moves);
		}
		// parcurgerea move-urilor si intrarea "plecarea" in adancime depth pentru
		// gasirea celui mai bun move.
		for (String curMove : all_moves) {
			Piece[][] aux = Arrays.stream(board1).map(Piece[]::clone).toArray(Piece[][]::new);
			movew(curMove, aux);

			if(kingIsAttacked(colour, aux)) {
				undo_move(curMove, board1);
				continue;
			}
			// Se seteaza culoarea pentru care se doreste cea miscarea minima.
			Colour col = (colour == Colour.White) ? Colour.Black : Colour.White;
			int score = minimaxAlphaBeta(depth - 1, aux, colour, col, -100000, 100000).first;

			if (score > max_score) {
				best_move = curMove;
				max_score = score;
			}

			undo_move(curMove, board1);
		}
		return new Pair<Integer, String>(max_score, best_move);
	 }

	// Insusi functia ce gaseste cel mai bun move,negamax cu strunctura de minimax+alphabeta
	public Pair<Integer, String> minimaxAlphaBeta(int depth, Piece[][] board1, Colour colour, Colour col, int alpha, int beta) {
		Move m = new Move();

		if (depth == 0) {
			return new Pair<Integer, String>(m.evalBoardic(board1, colour), null);
		}

		String best_move = null;
		ArrayList<String> all_moves = new ArrayList<>();
		ArrayList<String> moves = new ArrayList<>();
		ArrayList<Piece> pieces = getPieces(board1, col);

		for(int i = 0; i < pieces.size(); i++) {
			if((moves = pieces.get(i).posiblesMoves(board1,pieces.get(i))) != null)
				all_moves.addAll(moves);
		}

		// Similar cu functia precedenta pentru culoarea col se cauta toate
		// move-urile si se parcurge fiecare,plecand in functie de == cu colour
		// in max daca este egal si in min daca este diferit.
		// astfel maximizand castigul nostru si minimizand celuilalt, dar
		// datorita alpha-beta se exclud miscarile foarte rele si nu se
		// merge mai adanc pe acele ramuri.
		if (colour.equals(col)) {   
			for (String curMove : all_moves) {
				Piece[][] aux = Arrays.stream(board1).map(Piece[]::clone).toArray(Piece[][]::new);
				movew(curMove, aux);

				if(kingIsAttacked(col, aux)) {
					undo_move(curMove, board1);
					continue;
				}
				
				Colour coli = (col == Colour.White) ? Colour.Black : Colour.White;
				int score = minimaxAlphaBeta(depth - 1, aux, colour, coli, alpha, beta).first;

				if (beta <= alpha) {
					undo_move(curMove, board1);
					return new Pair<Integer, String>(alpha, curMove);
				}

				if (score > alpha) {
					best_move = curMove;
					alpha = score;
				}

				undo_move(curMove, board1);
			}
			return new Pair<Integer, String>(alpha, best_move);
		// Partea de min al functiei.
		} else {    
			for (String curMove : all_moves) {
				Piece[][] aux = Arrays.stream(board1).map(Piece[]::clone).toArray(Piece[][]::new);
				movew(curMove, aux);

				if(kingIsAttacked(col, aux)) {
					undo_move(curMove, board1);
					continue;
				}

				Colour coli = (col == Colour.White) ? Colour.Black : Colour.White;
				int score = minimaxAlphaBeta(depth - 1, aux, colour, coli, alpha, beta).first;

				if (beta <= alpha) {
					undo_move(curMove, board1);
					return new Pair<Integer, String>(beta, curMove);
				}

				if (score < beta) {
					best_move = curMove;
					beta = score;
				}

				undo_move(curMove, board1);
			}
			return new Pair<Integer, String>(beta, best_move);
		}
	 }

	public String toString() {
		String a = "";
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				a += board[i][j] + " ";
			}
			a += "\n";
		}
		return a;
	 }
}

