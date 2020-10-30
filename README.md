# Java-Chess-Engine
<h3 align="center">Java Chess Engine work with XBoard

<p align="center">
  <img src="https://github.com/SergiuBabin/Java-Chess-Engine/blob/main/SS_Parties_Fairy_Max5.0/chess.gif" alt="animated" />
</p>

### Description

Java chess engine that accept commands from XBoard and can communicate with it. You can play with Engine on Xboard GUI or watch the game between Engine(my) and Fairy_Max5.0("Xboard Engine").
<h4>The project was realized within a faculty project at Algorithm Programming.



### How to play
For run this engine you need download Xboard 4.8 or newer
#### Drag and Drop
* Drag the piece you want to move.
* Drop the dragged piece on the target and if the move is legal piece will move.
#### Click edition
* Click on the piece you want to move.
* Click on the destination square and if the move is legal the piece will stack below the target.
#### Download:
Linux
   
    sudo apt-get install xboard
If you wanna play with my Engine, you have to write in the terminal.  
    
    xboard -fcp "make run"
If you wanna watch the game between Engine(my) and Fairy_Max5.0("Xboard Engine"), you have to write in the terminal.

    xboard -fcp "make run" -scp "fairymax" -secondInitString "new\nrandom\nsd 2\n" -tc 5 -inc 2 -autoCallFlag true -mg 10 -sgf partide.txt -reuseFirst false
### About the project.
I worked on this project for less than week

  The board is like an 8 * 8 matrix, each piece is a separate object. 
  In start of the game all pieces are each in their place like in Xboard, when Engine receives a command from Xboard it procesing and return the answer.
  The algorithm that I used is ``Minimax Alpha Beta`` and ``Simplified Evaluation Function`` that calculate the maximum favorable move for Engine.
