import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.io.PrintWriter;

class Main {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Colour culoare = null;	//Culoarea cu care joaca Xboard-ul
        Colour ourColour = null; //Culoarea cu care joaca Engine-ul nostri
        Board xboard = null;

        // Verific fiecare comanda primita de la Xboard si trimit un raspuns potrivit
        while (scan.hasNextLine()) {
            String input = scan.nextLine();

            if(input.contains("xboard")) {
                xboard = new Board(); // Initializez board-ul
             }

            if (input.contains("protover 2")) {
                System.out.println("feature sigint=0 done=1");
             }

            if(input.contains("quit")) {
                return;
             }

            if(input.contains("force")) {
			    // Engine-ul nu mai gandeste si tor face miscarile date de la Xboard
			    // pentru ambele culori pana la intilnirea lui "go" sau "new"
                while(!(input = scan.nextLine()).equals("go") && !input.equals("new")) {
                	if ((input.length() >= 4) && (input.charAt(1) <= '8') && (input.charAt(2) <= 'h') ){
                   		xboard.move(input);
                    }
                    if(input.contains("black")) {
                        culoare = Colour.White;// Seteaz culoare de miscare a Xboard-ului 
                        ourColour = Colour.Black;// Setez culoare de miscare a Engine-ului
                    }
                    if(input.contains("white")) {
                        culoare = Colour.Black;// Setez culoare de miscare a Engine-ului
                        ourColour = Colour.White;// Setez culoare de miscare a Engine-ului
                    }
                }
             }

            if(input.contains("new")) {
                xboard = new Board(); // initializez board-ul cu valorile initiale
                culoare = Colour.White;	// Seteaz culoare de miscare a Xboard-ului 
                ourColour = Colour.Black; // Setez culoare de miscare a Engine-ului
             }
	   
            if(input.contains("black")) {
            	culoare = Colour.Black; // Seteaz culoare de miscare a Xboard-ului 
            	ourColour = Colour.White; // Setez culoare de miscare a Engine-ului
             }
            
            if(input.contains("white")) {
            	culoare = Colour.White; // Setez culoare de miscare a Engine-ului
            	ourColour = Colour.Black; // Setez culoare de miscare a Engine-ului
             }
            
            if(input.contains("go")) {
            	String s = null;
				// Engine-ul face o miscare cu culoarea corespunzatoare
                if((s = xboard.minimaxRoot(4, xboard.board, ourColour).second)!= null) {
                    xboard.move(s);
                    System.out.println("move " + s);
                } else System.out.println(ourColour + " resigns");
             }
	 
            if ((input.length() >= 4) && (input.charAt(1) <= '8') && (input.charAt(2) <= 'h') ) {
            	// Face miscarea primita de la Xboard
                xboard.move(input);
                String s = null;
                // Cauta o miscare valida pentru Engine
                if((s = xboard.minimaxRoot(4, xboard.board, ourColour).second) != null) {
                	xboard.move(s);
                    char[] move = s.toCharArray();
                    // if introdus pentru momentul in care are loc un enpassant facut de engine
                    if(move.length == 5 && move[4] != 'q') {
                        move = Arrays.copyOfRange(move, 0, 4);
                    }
                    s = String.valueOf(move);
                    System.out.println("move " + s);
                    
                } else System.out.println(ourColour + " resigns"); // Daca nu gaseste pierde
             }
        }
        
    }
}



