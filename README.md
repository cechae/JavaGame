# JavaGame

This is a java game made with Swing Library. 
  - This game accepts 5 keyboard inputs(Up key, Down key, Left key, Right key, and Space bar).
  - The objective of this game is to press the appropriate key to one of 5 different obstacles.
  - With the help of Swing timers, I repaint the game every 0.3 sec to update changes to the state of the game.
  - I have another timer called difficultyTimer, which functions to increment the speed of obstacles as time goes.
     (I wanted to make the game start slowly at first for the user to get used to it. And then gradually increase the     difficulty by speeding up the obstacles)

  - Each of the 5 obstacles(Tree, Bomb, Cloud, Mount, Log) in this game inherit from the Obstacles class because they share       position properties.
