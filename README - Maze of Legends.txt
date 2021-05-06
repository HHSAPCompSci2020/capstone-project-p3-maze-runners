AP Computer Science Final Project - README Template


Instructions:
The first step in creating an excellent APCS final project is to write up a README. At this stage, this README file acts as your project proposal. Once you’ve filled in all components, Shelby will read through it and suggest edits. Ultimately, you need a document that adequately describes your project idea and we must agree on this plan.


Have one member of your group make a copy of this Google Doc. Then, they should share it with all other members and with Mr. Shelby so that every group member has edit permissions, and Shelby can add comments on your ideas.


There’s a lot of parts of this document that you might not have full answers for yet. Because you haven’t written the program yet, it’s difficult to think about the instructions or which group members will do which parts. Even though this is hard to think about, you must have something in these sections that acts as your current plan. However, during the course of the project, you’ll continuously update this document. This means that you will not be held to exactly what you put here - components of this document can change (and it’s pretty common!).


There is one exception: the Features List section. Once Shelby OKs your README, the Features List section cannot be modified. For this reason, it is most important that you get a solid idea of what you want to make and the primary features it will have now.


Talk with your group. Consider drawing some pictures of what you think your project might look like. Be precise. When you’re ready, fill this out together. Each component in brackets below ( [these things] ) should be replaced with your ideas. Note that there are several sample READMEs posted on this assignment for you to use as guidance.


-------------------When README is finalized, remove everything above this line--------------------


[Maze of Legends]
Authors: Christopher Lew, Lakshya Shrivastava, Joseph Huang
Revision: 5/5/2021


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


The arrow or wasd keys will be the main keys that the player is using in the game which allow the player to move their character in the maze.
There should not be a need to click as the player is spawned in a spot automatically. There might be an opening menu that welcomes players to the game and instructions and a play button to enter the game for players to click. 


Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:
[These are features that we agree you will definitely have by the project due date. A good final project would have all of these completed. At least 5 are required. Each feature should be fully described (at least a few full sentences for each)]
*  There will be a GUI: Images represent the parts of the maze and all its components, as well as the characters and enemies represented on screen by images. 
*  User input (keyboard): The user can use the arrow keys or wasd to control the character as they move around the maze and to interact with elements like monsters or powerups
* There are power ups that the player can collect by touching them. There’ll be several powerups that change how the user can progress through the game like increasing movement, changing how much maze is visible.  Powerups: grant temporary more movespeed, vision, etc
* Monsters are also on the maze and can hurt/kill the player. The player can avoid the monsters or attack them. Player has a limited amount of health/lives and will be killed after a certain amount of bad encounters with monsters. Different monsters will have a different AI and have different behavior.[b][c]
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
*  Maze has [d]levels that randomly generate, and becomes more and more difficult
*  Monetary system to buy gear/characterskins/permanent powerups etc
* Leaderboard feature over firebase of fastest completion time




Class List:
[This section lists the Java classes that make up the program and very briefly describes what each represents. It’s totally fine to put this section in list format and not to use full sentences.]
* Maze
   * represents the maze and has the walls, player, and enemies on screen
* Player
   * Represents the character that the player controls
* Enemy
   * Represents the superclass of enemies that have a move speed, health, 
* Hitbox
   * Represents the physical space an entity takes up. Methods in hitbox will be used for collisions. 
* Ability/tool 
   * Represents the superclass of items that can be picked up in the maze to boost the player or give players the skill to do something. Subclasses would include the specific ability to powerup




Credits:
[Gives credit for project components. This includes both internal credit (your group members) and external credit (other people, websites, libraries). To do this:
* List the group members and describe how each member contributed to the completion of the final program. This could be classes written, art assets created, leadership/organizational skills exercises, or other tasks. Initially, this is how you plan on splitting the work.
* * Joseph Huang
* Christopher Lew
* Lakshya Shrivastava
* * * Give credit to all outside resources used. This includes downloaded images or sounds, external java libraries, parent/tutor/student coding help, etc.]
* * Shelby’s “Processing - Game and Physics Demo - AP Version” starter demo (for its graphics and collisions)


[a]In my opinion, this seems a little bit similar to the Labyrinth lab. You should make sure that you have features that make the game unique. Overall, it seems like it would be a fun game.
[b]Can these monsters move?
[c]How many will you have?
[d]Isn't this the same as your first Want-to-have Feature?