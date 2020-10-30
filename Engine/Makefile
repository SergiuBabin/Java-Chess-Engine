JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
	
CLASSES = \
	ROOK.java\
	BISHOP.java\
	KNIGHT.java\
	KING.java\
	QUEEN.java\
        PAWN.java \
        Piece.java \
        Board.java \
	Move.java\
        Main.java 

default: classes

classes: $(CLASSES:.java=.class)

run:
	java Main

clean:
	$(RM) *.class
