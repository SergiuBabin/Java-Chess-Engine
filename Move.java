import java.util.*;
// Clasa folosita la generarea move-urilor tuturor pieselor
public class Move {
// Initializarea matricilor utilizate la evaluarea board-ului
// pentru minimax 
int[][] pawnEvalWhite = {
	{ 0,  0,  0,  0,  0,  0,  0,  0},
	{50, 50, 50, 50, 50, 50, 50, 50},
	{10, 10, 20, 30, 30, 20, 10, 10},
	{ 5,  5, 10, 25, 25, 10,  5,  5},
	{ 0,  0,  0, 20, 20,  0,  0,  0},
	{ 5, -5,-10,  0,  0,-10, -5,  5},
	{ 5, 10, 10,-20,-20, 10, 10,  5},
	{ 0,  0,  0,  0,  0,  0,  0,  0}
 };

int[][] pawnEvalBlack = reverseMatrix(pawnEvalWhite);

int[][] pawnEvalBlackEnd = {
	{90, 90, 90, 90, 90, 90, 90, 90},
	{50, 50, 50, 50, 50, 50, 50, 50},
	{10, 10, 20, 30, 30, 20, 10, 10},
	{ 5,  5, 10, 25, 25, 10,  5,  5},
	{ 0,  0,  0, 20, 20,  0,  0,  0},
	{ 5, 15, 10,  0,  0, 10, 15,  5},
	{ 5, 10, 10,-20,-20, 10, 10,  5},
	{ 0,  0,  0,  0,  0,  0,  0,  0}
 };

int[][] pawnEvalWhiteEnd = reverseMatrix(pawnEvalBlackEnd);

int[][] knightEvalWhite = {
	{-50,-40,-30,-30,-30,-30,-40,-50},
	{-40,-20,  0,  0,  0,  0,-20,-40},
	{-30,  0, 10, 15, 15, 10,  0,-30},
	{-30,  5, 15, 20, 20, 15,  5,-30},
	{-30,  0, 15, 20, 20, 15,  0,-30},
	{-30,  5, 10, 15, 15, 10,  5,-30},
	{-40,-20,  0,  5,  5,  0,-20,-40},
	{-50,-40,-30,-30,-30,-30,-40,-50}
 };

int[][] knightEvalBlack = reverseMatrix(knightEvalWhite); 

int[][] bishopEvalWhite = {
	{-20,-10,-10,-10,-10,-10,-10,-20},
	{-10,  0,  0,  0,  0,  0,  0,-10},
	{-10,  0,  5, 10, 10,  5,  0,-10},
	{-10,  5,  5, 10, 10,  5,  5,-10},
	{-10,  0, 10, 10, 10, 10,  0,-10},
	{-10, 10, 10, 10, 10, 10, 10,-10},
	{-10,  5,  0,  0,  0,  0,  5,-10},
	{-20,-10,-10,-10,-10,-10,-10,-20}
 };

int[][] bishopEvalBlack = reverseMatrix(bishopEvalWhite);

int[][] rookEvalWhite = {
	{  0,  0,  0,  0,  0,  0,  0,  0},
	{  5, 10, 10, 10, 10, 10, 10,  5},
	{ -5,  0,  0,  0,  0,  0,  0, -5},
	{ -5,  0,  0,  0,  0,  0,  0, -5},
	{ -5,  0,  0,  0,  0,  0,  0, -5},
	{ -5,  0,  0,  0,  0,  0,  0, -5},
	{ -5,  0,  0,  0,  0,  0,  0, -5},
	{  0,  0,  0,  5,  5,  0,  0,  0}
 };

int[][] rookEvalBlack = reverseMatrix(rookEvalWhite);

int[][] queenEvalWhite = {
	{-20,-10,-10, -5, -5,-10,-10,-20},
	{-10,  0,  0,  0,  0,  0,  0,-10},
	{-10,  0,  5,  5,  5,  5,  0,-10},
	{ -5,  0,  5,  5,  5,  5,  0, -5},
	{  0,  0,  5,  5,  5,  5,  0, -5},
	{-10,  5,  5,  5,  5,  5,  0,-10},
	{-10,  0,  5,  0,  0,  0,  0,-10},
	{-20,-10,-10, -5, -5,-10,-10,-20}
 };

int[][] queenEvalBlack = reverseMatrix(queenEvalWhite);

int[][] kingEvalWhite = {
	{-30,-40,-40,-50,-50,-40,-40,-30},
	{-30,-40,-40,-50,-50,-40,-40,-30},
	{-30,-40,-40,-50,-50,-40,-40,-30},
	{-30,-40,-40,-50,-50,-40,-40,-30},
	{-20,-30,-30,-40,-40,-30,-30,-20},
	{-10,-20,-20,-20,-20,-20,-20,-10},
	{ 20, 20,  0,  0,  0,  0, 20, 20},
	{ 20, 30, 10,  0,  0, 10, 30, 20}
 };

int[][] kingEvalBlack = reverseMatrix(kingEvalWhite);

int[][] kingEvalWhiteEnd = {
	{-50,-40,-30,-20,-20,-30,-40,-50},
	{-30,-20,-10,  0,  0,-10,-20,-30},
	{-30,-10, 20, 30, 30, 20,-10,-30},
	{-30,-10, 30, 40, 40, 30,-10,-30},
	{-30,-10, 30, 40, 40, 30,-10,-30},
	{-30,-10, 20, 30, 30, 20,-10,-30},
	{-30,-30,  0,  0,  0,  0,-30,-30},
	{-50,-30,-30,-30,-30,-30,-30,-30}
 };

// Functie utilizata pentru inversarea matricilor
// de evaluare -> evaluarea celeilalte culori
public int[][] reverseMatrix(int[][] matrix) {
	int rows = matrix.length;
	int cols = matrix[0].length;
		
	for(int x = 0; x < rows / 2; x++) {
		for(int y = 0; y < cols; y++) {
			int temp = matrix[x][y];
			matrix[x][y] = matrix[rows - x - 1][y];
			matrix[rows - x - 1][y] = temp;
		}
	}
	return matrix;
 }

// Functie pentru transformarea move-urilor din int in string
public static String moveToString(int a, int b, int c, int d) {
	return ""+(char)(a+96)+(char)(b+48)+(char)(c+96)+(char)(d+48);
 }

// Utilizata la promovarea pionului si enpassant.
public static String moveToString(int a, int b, int c, int d, String e) {
	if(e != null)
		return ""+(char)(a+96)+(char)(b+48)+(char)(c+96)+(char)(d+48)+e;
	return moveToString(a, b, c, d);
 }

// Verificarea daca un move este legal pentru king si knight.
public String moveVerifyAdd(Piece[][] board, Piece tpiece, int i, int j) {
	if(i < 1 || i > 8 || j < 1 || j > 8)
		return null;
	if(board[i][j] != null){
		if(!board[i][j].colour.equals(tpiece.colour))
			return moveToString(tpiece.j,tpiece.i, j, i);
	} else return moveToString(tpiece.j,tpiece.i, j, i);
	return null;
 }

// Generarea move-urilor pentru pawn, in functie de culoarea pe care o are.
public ArrayList<String> movePawn(Piece[][] board,Piece tpiece) {
	int i = tpiece.i; 
	int j = tpiece.j;
	ArrayList<String> s = new ArrayList<>();
	// Verific toate miscarile posibile care le poat face cu PAWN Back 
	// Enpassant
	if(board[i][j].colour.equals(Colour.Black) && (i - 1) != 0) {
		if((j+1) <= 8 && board[i][j+1] != null)
			if (i == 4 && board[i][j+1].getType().equals("PAWN") && (board[i][j+1].nrMoves == 1)) {
				if (board[i-1][j+1] == null && board[i-2][j+1] == null)
					s.add(moveToString(j,i,j+1,i-1,"e"));
			}
		if((j-1)>=1 && board[i][j-1] != null)
			if (i == 4 && board[i][j-1].getType().equals("PAWN") && (board[i][j-1].nrMoves == 1)) {
				if (board[i-1][j-1] == null && board[i-2][j-1] == null)
					s.add(moveToString(j,i,j-1,i-1,"e"));
			}
	// Mutarea obisnuite pentru pawn black + promovare.
		if (i == 7)
			if (board[5][j] == null && board[6][j] == null) {
				s.add(moveToString(j, 7, j, 5));
			}
		String k = (i-1 == 1)? "q" : null;
		if (board[i - 1][j] == null)
			s.add(moveToString(j, i, j, i-1, k));
		if((j+1) != 9  && (board[i-1][j+1] != null) && (board[i-1][j+1].colour.equals(Colour.White)))
			s.add(moveToString(j, i, j+1, i-1, k));
		if((j-1) != 0 && (board[i-1][j-1] != null) && (board[i-1][j-1].colour.equals(Colour.White)))
			s.add(moveToString(j, i, j-1, i-1, k));
	}

	// Verific toate miscarile posibile care le poat face cu PAWN White
	// Enpassant
	if(board[i][j].colour.equals(Colour.White) && (i+1) != 9) {
		if((j+1) <= 8 && board[i][j+1] != null)
			if (i == 5 && board[i][j+1].getType().equals("PAWN") && (board[i][j+1].nrMoves == 1)) {
				if (board[i+1][j+1] == null && board[i+2][j+1] == null)
					s.add(moveToString(j, i, j+1, i+1, "e"));

			}
		if((j-1)>=1 && board[i][j-1] != null)
			if (i == 5 && board[i][j-1].getType().equals("PAWN") && (board[i][j-1].nrMoves == 1)) {
				if (board[i+1][j-1] == null && board[i+2][j-1] == null)
					s.add(moveToString(j, i, j-1, i+1, "e"));
			}
		// Mutarea obisnuite pentru pawn white + promovare.
		if (i == 2)
			if (board[4][j] == null && board[3][j] == null) {
				s.add(moveToString(j, 2, j, 4));
			}
		String k = (i+1 == 8) ? "q" : null;
		if (board[i + 1][j] == null)
			s.add(moveToString(j, i, j, i+1, k));
		if((j+1) != 9  && (board[i+1][j+1] != null) && (board[i+1][j+1].colour.equals(Colour.Black)))
			s.add(moveToString(j, i, j+1, i+1, k));
		if((j-1) != 0 && (board[i+1][j-1] != null) && (board[i+1][j-1].colour.equals(Colour.Black)))
			s.add(moveToString(j, i, j-1, i+1, k));
	}
	// Daca nu am gasit miscari posibile reintorc null altfel reintorc lista de miscari
	if(s.size() == 0)
		return null;
	return s;
 }

// Generare mutari rook
public ArrayList<String> moveRook(Piece[][] board,Piece tpiece) {
	ArrayList<String> moves = new ArrayList<String>();
	for(int i = tpiece.i+1, j = tpiece.j; i <= 8 ; i++) {
		if(board[i][j] != null) {
			if(!board[i][j].colour.equals(tpiece.colour))
				moves.add(moveToString(tpiece.j, tpiece.i, tpiece.j, i));
			break;
		}
		moves.add(moveToString(tpiece.j, tpiece.i, tpiece.j, i));
	}
	for(int i = tpiece.i-1, j = tpiece.j; i >= 1 ; i--) {
		if(board[i][j] != null) {
			if(!board[i][j].colour.equals(tpiece.colour))
				moves.add(moveToString(tpiece.j, tpiece.i, tpiece.j, i));
			break;
		}
		moves.add(moveToString(tpiece.j, tpiece.i, tpiece.j, i));
	}
	for(int j = tpiece.j+1, i = tpiece.i ; j <= 8 ; j++) {
		if(board[i][j] != null) {
			if(!board[i][j].colour.equals(tpiece.colour))
				moves.add(moveToString(tpiece.j, tpiece.i, j, tpiece.i));
			break;
		}
		moves.add(moveToString(tpiece.j, tpiece.i, j, tpiece.i));
	}	
	for(int j = tpiece.j-1, i = tpiece.i ; j >= 1 ; j--) {
		if(board[i][j] != null){
			if(!board[i][j].colour.equals(tpiece.colour))
				moves.add(moveToString(tpiece.j, tpiece.i, j, tpiece.i));
			break;
		}
		moves.add(moveToString(tpiece.j, tpiece.i, j, tpiece.i));
	}
	if(moves.size() == 0)
		return null;
	return moves;
 }

// Generare move-uri bishop.
public ArrayList<String> moveBishop(Piece[][] board, Piece tpiece) {
	ArrayList<String> moves = new ArrayList<String>();
	for(int i = tpiece.i+1 , j = tpiece.j+1 ; i <= 8 && j <= 8; i++, j++) {
		if(board[i][j] != null){
			if(!board[i][j].colour.equals(tpiece.colour))
				moves.add(moveToString(tpiece.j, tpiece.i, j, i));
			break;
		}
		moves.add(moveToString(tpiece.j, tpiece.i, j, i));
	}
	for(int i = tpiece.i+1 , j = tpiece.j-1 ; i <= 8 && j >= 1; i++, j--) {
		if(board[i][j] != null){
			if(!board[i][j].colour.equals(tpiece.colour))
				moves.add(moveToString(tpiece.j, tpiece.i, j, i));
			break;
		}
		moves.add(moveToString(tpiece.j, tpiece.i , j, i));
	}
	for(int i = tpiece.i-1 , j = tpiece.j-1 ; i >= 1 && j >= 1; i--, j--) {
		if(board[i][j] != null){
			if(!board[i][j].colour.equals(tpiece.colour))
				moves.add(moveToString(tpiece.j, tpiece.i, j, i));
			break;
		}
		moves.add(moveToString(tpiece.j, tpiece.i, j, i));
	}
	for(int i = tpiece.i-1 , j = tpiece.j+1 ; i >= 1 && j <= 8; i--, j++) {
		if(board[i][j] != null){
			if(!board[i][j].colour.equals(tpiece.colour))
				moves.add(moveToString(tpiece.j ,tpiece.i, j, i));
			break;
		}
		moves.add(moveToString(tpiece.j, tpiece.i, j, i));
	}
	if(moves.size() == 0)
		return null;
	return moves;
 }

//Generarea move-uri knight
public ArrayList<String> moveKnight(Piece[][] board, Piece tpiece) {
	ArrayList<String> moves = new ArrayList<String>();
	int i = tpiece.i;
	int j = tpiece.j;
	moves.add(moveVerifyAdd(board, tpiece, i+1, j+2));
	moves.add(moveVerifyAdd(board, tpiece, i+1, j-2));
	moves.add(moveVerifyAdd(board, tpiece, i-1, j+2));
	moves.add(moveVerifyAdd(board, tpiece, i-1, j-2));
	moves.add(moveVerifyAdd(board, tpiece, i+2, j-1));
	moves.add(moveVerifyAdd(board, tpiece, i+2, j+1));
	moves.add(moveVerifyAdd(board, tpiece, i-2, j+1));
	moves.add(moveVerifyAdd(board, tpiece, i-2, j-1));
	moves.removeAll(Collections.singleton(null));
	if(moves.size() == 0)
		return null;
	return moves;
 }

// Generare move-uri King
public ArrayList<String> moveKing(Piece[][] board, Piece tpiece){
	ArrayList<String> moves = new ArrayList<String>();
	int i = tpiece.i;
	int j = tpiece.j;
	boolean castling = true;
	moves.add(moveVerifyAdd(board, tpiece, i, j+1));
	moves.add(moveVerifyAdd(board, tpiece, i, j-1));
	moves.add(moveVerifyAdd(board, tpiece, i+1, j));
	moves.add(moveVerifyAdd(board,tpiece, i-1, j));
	moves.add(moveVerifyAdd(board,tpiece, i+1, j-1));
	moves.add(moveVerifyAdd(board,tpiece, i+1, j+1));
	moves.add(moveVerifyAdd(board,tpiece, i-1, j+1));
	moves.add(moveVerifyAdd(board,tpiece, i-1, j-1));
	moves.removeAll(Collections.singleton(null));
	if(moves.size() == 0)
		return null;

	return moves;
 }

// Generarea move-uri queen din moverook si movebishop.
public ArrayList<String> moveQueen(Piece[][] board, Piece tpiece) {
	ArrayList<String> moves = new ArrayList<>();
	ArrayList<String> moveDiag = moveBishop(board, tpiece);
	ArrayList<String> moveLin = moveRook(board, tpiece);
	if(moveDiag != null)
		moves.addAll(moveDiag);
	if(moveLin != null)
		moves.addAll(moveLin);
	if(moves.size() == 0)
		return null;

	return moves;
 }

// Evaluarea board-ului(toate piesele) pentru minimax
public int evalBoardic(Piece[][] board1, Colour colour) {
	int score = 0;
	int nrPieces = 0;

	for (int i = 1; i <= 8; i++) {
		for (int j = 1; j <= 8; j++) {
			if(board1[i][j] != null){
				int a = (colour.equals(board1[i][j].colour)) ? getPieceValue(board1[i][j]): -getPieceValue(board1[i][j]);
				score += a; 
				nrPieces++;
			}
		}
	}
	// Dupa ce se pierd mai multe piese, evaluarea se face pentru o alta matrice de
	// evaluarea a king-ului.
	if(nrPieces < 14){
		kingEvalWhite = Arrays.stream(kingEvalWhiteEnd).map(int[]::clone).toArray(int[][]::new);
		pawnEvalWhite = Arrays.stream(pawnEvalWhiteEnd).map(int[]::clone).toArray(int[][]::new);
	}
	return score;
 }

// Obtinerea valorii fiecarei piese in functie de ce pozitie are.
public int getPieceValue(Piece tpiece) {
	int x = tpiece.j-1;
	int y = tpiece.i-1;
	boolean col = (tpiece.colour.equals(Colour.White)) ? true : false; 
	switch(tpiece.getType()){
		case "PAWN":
			return 100 + ( col ? pawnEvalWhite[y][x] : pawnEvalBlack[y][x]);
		case "ROOK":
			return 500 + ( col ? rookEvalWhite[y][x] : rookEvalBlack[y][x]);
		case "KNIGHT":
			return 320 + ( col ? knightEvalWhite[y][x] : knightEvalBlack[y][x]);
		case "BISHOP":
			return 330 + ( col ? knightEvalWhite[y][x] : knightEvalBlack[y][x]);
		case "QUEEN":
			return 900 + ( col ? queenEvalWhite[y][x] : queenEvalBlack[y][x]);
		case "KING":
			return 20000 + ( col ? kingEvalWhite[y][x] : kingEvalBlack[y][x]);
	}
	return 0;
 }
}