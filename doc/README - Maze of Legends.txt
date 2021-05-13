[Maze of Legends]
Authors: Christopher Lew, Lakshya Shrivastava, Joseph Huang
Revision: 5/6/2021


Introduction: 
[In a few paragraphs totaling about ½ page, introduce the high-level concept of your program. What this looks like depends a lot on what type of thing you are making. An introduction for an application will look different than one for a game. In general, your introduction should address questions like these:
What does your program do?
What problem does it solve? Why did you write it?
What is the story?
What are the rules? What is the goal?
Who would want to use your program?
What are the primary features of your program?]
Our program is a maze/dungeon game that spawns the player in a complicated maze. The player controls their character from a third person perspective and they have limited vision of the map of the maze. They are able to use the arrow or wasd keys on the keyboard to move around the maze. The player is a hero trying to find the exit of the maze. Along the way there are monsters and treasures. The player can pick up powerups and weapons and fight monsters. The player has a certain amount of lives such as 3 and for each unsuccessful fight or encounter with a monster, the player loses a life which means the player will die and the game ends when they run out of lives. Our program is designed for students and players who like to play games and want to try a puzzle-like action game. [a]


Instructions:
[Explain how to use the program. This needs to be specific: 
Which keyboard keys will do what? 
Where will you need to click? 
Will you have menus that need to be navigated? What will they look like? 
Do actions need to be taken in a certain order?]


* Use the arrow keys or WASD to move the player around the maze
* Press m button to change the maze
* There might be an opening menu that welcomes players to the game and instructions and a play button to enter the game for players to click. 




Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:
[These are features that we agree you will definitely have by the project due date. A good final project would have all of these completed. At least 5 are required. Each feature should be fully described (at least a few full sentences for each)]
*  There will be a GUI: Images represent the parts of the maze and all its components, as well as the characters and enemies represented on screen by images. 
*  User input (keyboard): The user can use the arrow keys or wasd to control the character as they move around the maze and to interact with elements like monsters or powerups
* There are power ups that the player can collect by touching them. There’ll be several powerups that change how the user can progress through the game like increasing movement, changing how much maze is visible.  Powerups: grant temporary more movespeed, vision, etc
* Monsters are also on the maze and can hurt/kill the player. The player can avoid the monsters or attack them. Player has a limited amount of health/lives and will be killed after a certain amount of bad encounters with monsters. Different monsters will have a different AI and have different behavior.
* The character can freely walk on the screen, not bound to a grid, but cannot walk out of bounds or through walls. 


Want-to-have Features:
[These are features that you would like to have by the project due date, but you’re unsure whether you’ll hit all of them. A good final project would have perhaps half of these completed. At least 5 are required. Again, fully describe each.]
*  Random level generation (good: spend less time making levels, bad: spend time making winnable random levels) 
*  Pick up weapons to fight the monsters that are encountered 
*  Pick up advanced tools for skills like going through a wall or going invisible etc
*  Make different types of monsters and give them ability to move in a random computer generated way in a certain part of the maze
*  Option for 2-player but local (not great for this remote learning tho)


Stretch Features:
[These are features that we agree a fully complete version of this program would have, but that you probably will not have time to implement. A good final project does not necessarily need to have any of these completed at all. At least 3 are required. Again, fully describe each.]
*  First Person, make the maze in first person perspective and render the maze and everything 3D to make it more difficult
*  Maze has [b]levels that randomly generate, and becomes more and more difficult
*  Monetary system to buy gear/characterskins/permanent powerups etc
* Leaderboard feature over firebase of fastest completion time




Class List:
[This section lists the Java classes that make up the program and very briefly describes what each represents. It’s totally fine to put this section in list format and not to use full sentences.]
* Main
   * Has the main() method and creates different Panels (JPanel, OptionPanel).
* Maze
   * represents the maze and has the walls, creatures(enemies), and player. Add things(players, enemies, etc) to the maze, and call draw for each one.
* Player
   * Represents the character that the player controls
* Enemy
   * Represents the superclass of enemies that have a move speed, health, 
* Hitbox
   * Represents the physical space an entity takes up. Methods in hitbox will be used for collisions. 
* Ability/tool 
   * Represents the superclass of items that can be picked up in the maze to boost the player or give players the skill to do something. Subclasses would include the specific ability to power up
* DrawingSurface
   * Draws everything on the screen and has the maze and entities on the screen
* MovingImage
   * Represent a moving image and allows us to use a image as the player
* OptionPanel
   * Creates a button for player to access options and menu




Credits:
[Gives credit for project components. This includes both internal credit (your group members) and external credit (other people, websites, libraries). To do this:
* List the group members and describe how each member contributed to the completion of the final program. This could be classes written, art assets created, leadership/organizational skills exercises, or other tasks. Initially, this is how you plan on splitting the work.
* Joseph Huang - started ability class and interaction with player, 
* Christopher Lew - Changed Player behavior and controlling, Made it so you can create and load new Mazes in the code, 
* Lakshya Shrivastava - maze creation, 3D Maze worshop, added timing trap




* Give credit to all outside resources used. This includes downloaded images or sounds, external java libraries, parent/tutor/student coding help, etc.]
* Luigi image https://static.wikia.nocookie.net/papermario/images/6/6d/Luigi0.png/revision/latest?cb=20130525184424 
* Shelby’s “Processing - Game and Physics Demo - AP Version” starter demo (for its graphics and collisions)


[a]In my opinion, this seems a little bit similar to the Labyrinth lab. You should make sure that you have features that make the game unique. Overall, it seems like it would be a fun game.
[b]Isn't this the same as your first Want-to-have Feature?