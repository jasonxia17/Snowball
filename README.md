# Snowball
A turn-based snowball fight

Utilizes the external library stdlib.jar, which can be downloaded from here: https://introcs.cs.princeton.edu/java/stdlib/

How the game works:

On each turn, a player can move one space on the grid, throw a snowball, make a snowball (this takes two turns), or heal.
Obviously, not all of these moves are valid on any given turn.

The probability of getting hit by a snowball is a function of the Euclidean distance between the thrower and the target.
Currently, this function is e^(-0.2 * distance).

The current version of the game is a 2 versus 2 snowball fight. Players 1+4 are on a team, and Players 2+3 are on a team.
A player is eliminated when his/her health is no longer positive.
The game ends when all players on a team have been eliminated.
