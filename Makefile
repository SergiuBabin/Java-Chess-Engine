JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
	
CLASSES = \
	Engine/ROOK.java\
	Engine/BISHOP.java\
	Engine/KNIGHT.java\
	Engine/KING.java\
	Engine/QUEEN.java\
        Engine/PAWN.java \
        Engine/Piece.java \
        Engine/Board.java \
	Engine/Move.java\
        Engine/Main.java 

default: classes

classes: $(CLASSES:.java=.class)

run:
	java Main

clean:
	$(RM) *.class
