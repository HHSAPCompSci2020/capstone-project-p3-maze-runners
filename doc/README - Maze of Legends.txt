
[Maze of Legends]
Authors: Christopher Lew, Lakshya Shrivastava, Joseph Huang
Revision: 5/15/2021

Introduction: 
[In a few paragraphs totaling about ½ page, introduce the high-level concept of your program. What this looks like depends a lot on what type of thing you are making. An introduction for an application will look different than one for a game. In general, your introduction should address questions like these:
What does your program do?
What problem does it solve? Why did you write it?
What is the story?
What are the rules? What is the goal?
Who would want to use your program?
What are the primary features of your program?]

Our program is a maze/dungeon game that spawns the player in a complicated maze. The player controls their character from a platformer game’s perspective. They are able to use the arrow or WASD keys on the keyboard to move around the maze. The player is a hero trying to find the exit of the maze. Along the way there are monsters and power ups. The player can pick up powerups that sometimes help them. The player has a certain amount of lives such as 3 and for each unsuccessful fight or encounter with a monster, the player loses a life which means the player will die and the game ends when they run out of lives. Our program is designed for students and players who like to play games and want to try a puzzle-like action game. 

Instructions:
[Explain how to use the program. This needs to be specific: 
Which keyboard keys will do what? 
Where will you need to click? 
Will you have menus that need to be navigated? What will they look like? 
Do actions need to be taken in a certain order?]

Enter on a screen that has the title and a button that says start game, click on this
Use the arrow keys or WASD to move the player around the maze.
Use the spacebar to use your ability (if you’ve picked one up)
Try to reach the ‘X’ which represents the exit






Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:
[These are features that we agree you will definitely have by the project due date. A good final project would have all of these completed. At least 5 are required. Each feature should be fully described (at least a few full sentences for each)]
 There will be a GUI: Images represent the parts of the maze and all its components, as well as the characters and enemies represented on screen by images. 
 User input (keyboard): The user can use the arrow keys or wasd to control the character as they move around the maze and to interact with elements like monsters or powerups
There are power ups that the player can collect by touching them. There’ll be several power ups that change how the user can progress through the game like increasing movement, changing how much maze is visible.  Powerups: grant temporary more movespeed, vision, etc
Monsters are also in the maze and can hurt/kill the player. The player can avoid the monsters or attack them. Player has a limited amount of health/lives and will be killed after a certain amount of bad encounters with monsters. Different monsters will have a different AI and have different behavior.
The character can freely walk on the screen, not bound to a grid, but cannot walk out of bounds or through walls. 

Want-to-have Features:
[These are features that you would like to have by the project due date, but you’re unsure whether you’ll hit all of them. A good final project would have perhaps half of these completed. At least 5 are required. Again, fully describe each.]
 Random level generation (good: spend less time making levels, bad: spend time making winnable random levels) 
 Pick up weapons to fight the monsters that are encountered 
 Pick up advanced tools for skills like going through a wall or going invisible etc
 Make different types of monsters and give them ability to move in a random computer generated way in a certain part of the maze
 Option for 2-player but local (not great for this remote learning tho)

Stretch Features:
[These are features that we agree a fully complete version of this program would have, but that you probably will not have time to implement. A good final project does not necessarily need to have any of these completed at all. At least 3 are required. Again, fully describe each.]
 First Person, make the maze in first person perspective and render the maze and everything 3D to make it more difficult
 Maze has levels that randomly generate, and becomes more and more difficult
 Monetary system to buy gear/characterskins/permanent powerups etc
Leaderboard feature over firebase of fastest completion time


Class List:
[This section lists the Java classes that make up the program and very briefly describes what each represents. It’s totally fine to put this section in list format and not to use full sentences.]
package Maze;
	Main
		Has the main() method and creates different Panels (JPanel, OptionPanel).
	Maze
		has an ArrayList<Shape> obstacles representing the walls of the maze
		has an ArrayList<Creatures> that hold all the Enemies that spawn on that particular maze
		Can access every Creature in the current maze to access that Creature’s act(), draw(), and other methods
		When calling a Creature’s act() method, you can pass in the current Maze’s obstacles
	DrawingSurface
		Draws everything on the screen and has the maze and entities on the screen
	MovingImage
		Represent a moving image and allows us to use a image as the player
	OptionPanel
		Creates a button for player to access options and menu
package Entities
	Entity
		Superclass of moving entities in the game, including enemy and player, includes methods for collisions of other entities and walls.
	Player
		Represents the character that the player controls. Is a Subclass of Creature
	Exit
		Represents the exit of a maze. WHen a player reaches the exit current maze is finished
	Fence
		Used for denoting safe areas in the maze, player can pass through, enemies cannot
package entities.enemies
	Enemy
		Represents the superclass of enemies that have a move speed, health. Subclass of Creature
	TimingTrap (T)
		Enemy that attacks with spikes every couple seconds appearing in a set location on the floor. Subclass of enemy
	Monster(M)
		Enemy that moves randomly in the maze and can damage the player by running into them. Subclass of enemy
	HomingMonster(H)
		Enemy that moves toward the player, and can damage the player by running into them. Subclass of enemy
	Spike(S)
		Spikes that the player cannot walk on 
package entities.abilities
	Ability/tool 
		Represents the superclass of items that can be picked up in the maze to boost the player or give players the skill to do something. Subclasses would include the specific ability to power up
	Heal (h)
		Adds a certain amount of of health to the player
	Star(i)
		Grants player temporary invincibility and allows player to kill enemies
	Stealth(s)
		Grants temporary immunity to the player from enemies
	InvincibilityPrank(I)
		Our little joke


Credits:
[Gives credit for project components. This includes both internal credit (your group members) and external credit (other people, websites, libraries). To do this:
Contributions:
Joseph Huang 
	started ability class and interaction with player, spawning Abilities on the screen, update UML,
Christopher Lew 
	Changed Player behavior and controlling, loading Enemies and Walls from a Maze, coded logic to initialize a Maze with Shapes and Enemies from a text file, Made collision logic that Enemies hurt and push back the Player, made touch Heal power ups properly heal you, made timing traps properly hurt player, Monster moves randomly, HomingMonster moves towards Player
Lakshya Shrivastava 
	maze creation, 3D Maze workshop(Scrapped Idea, not seen in the final repo), added timing trap to maze, started Monster(Chris fixed the bugs with movement), drawing the maze in 2D, created main menu, created some levels of the game, adjusted difficulty of existing levels, cleaning/formatting the repo, updating all documentaion for final release


Give credit to all outside resources used. This includes downloaded images or sounds, external java libraries, parent/tutor/student coding help, etc.]
Resources:
Luigi image 
	https://static.wikia.nocookie.net/papermario/images/6/6d/Luigi0.png/revision/latest?cb=20130525184424 
Spike image 
	https://www.pikpng.com/transpng/iixmiRb/ 
Goomba “Monster” 
	http://pixelartmaker-data-78746291193.nyc3.digitaloceanspaces.com/image/6dda6cf7ea58634.png 
Red Goomba “HomingMonster” is the same as Monster but edited by Christopher
Ghost used for invisibility powerup 
	http://pixelartmaker.com/art/185d788be1b9687
Star 
	http://pixelartmaker.com/art/61025f7ec76bce5
	 
Shelby’s “Processing - Game and Physics Demo - AP Version” starter demo (for its graphics and collisions), 

Shelby’s Labyrinth.readFile() from the Recursion in 2D lab

Heart code https://www.processing.org/discourse/beta/num_1246205739.html 

Main Menu Background: https://wallpapercave.com/wp/wp1898752.png

