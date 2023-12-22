## Team Members
- [x] Arnav Gupta
- [x] Kartikeya Chhikara

## Project Description
Stick Hero is a game where the user has to extend a stick to a certain length to cross a gap between two platforms. The user has to press the spacebar to extend the stick and release it to stop the stick. If the stick is too short or too long, the hero will fall and the game will end. If the stick is of the correct length, the hero will cross the gap and the user will get a point. The user can also collect cherries to get extra points. The game ends when the hero falls or the user presses the escape key.

### Run the App.java class to play the game

## Controls
1) Use spaceabar to extend the stick.
2) Up and down keys to move the stickman up/down.
3) CheatCode- Press AK simultaneously to get revived via 2 cherries instead of 5 after dying.

## Assumptions
1) Cherry is collected irrespective whether the person is dying or has a correct landing
2) Game state is saved only if the user has a current score greater than 0


## Design Patterns
1) Singleton Pattern used in Stick
2) Flyweight used in Hero Class
3) Observer Pattern: This pattern is used to implement event handling in the Gameplay class. The setOnKeyPressed and setOnKeyReleased methods are examples of this pattern.
4) Serialization Pattern: This pattern is used to save and load the game state. The saveState, loadState, PauseSaveState, and PauseLoadState methods in the Gameplay class are examples of this pattern.


## JUnit Test Cases
Test cases have a separate package and a runner class testing 3-4 different test cases.They basically check collision functions and the the save/load game serialised functions


##   Bonus
1) Multithreading in implementing the sound effects
2) Implemented CheatCode- When the user presses the keys AK simultaneously after dying, they will be able to revive with 2 cherries instead of 5.
3) Sound effects implemented while stick increases and the hero falls.
4) Serialised the game state to save the game and load it later.
