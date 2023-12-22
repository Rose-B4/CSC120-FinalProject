# CSC120-FinalProject

- What was your overall approach to tackling this project?
Start small with just taking input and moving between rooms, then add features in order of how important they are to the game functioning and being fun overall
- What new thing(s) did you learn / figure out in completing this project?
How to check and manipulate lists. I knew how to do both individually, but I hadn't done them both together before which ended up raising a lot of problems. I eventually realized that almost all of the lists could be handled the same way and wrote one function to take care of all of them, but it was more than I was originally expecting.
- Is there anything that you wish you had implemented differently?
I go into more detail in a later question, but the input system. I wish it was more robust so the player could input more different combinations of words and get the same result.
- If you had unlimited time, what additional features would you implement?
An easier user interface and a map graphic. So many people wanted to write "climb table" instead of "climb up" which is how I programmed it, so I wish I had time to implement a way for the game to better understand user input. I also wish I had time to make the map I had planned. I wanted to make a metriodvainia style map where each time you entered a new room, a picture of it would be added to the map slowly unlocking the whole house over the course of the game.
- What was the most helpful piece of feedback you received while working on your project? Who gave it to you?
Add some form of help menu to the set of actions the player can make. I forget who this was in response to, but it was some advice I overheard Jordan giving to another student during the peer to peer code review.
- If you could go back in time and give your past self some advice about this project, what hints would you give?
Have a better understanding of the layout and general structure of the game before starting work
- If you worked with a team: please comment on how your team dynamics influenced your experience working on this project.
N/A
- At least one (reasonable) **alternative design** that could have been used, and the reasons why you decided against this alternative.
I ended up using a giant switch statement in the script that handles the user's inputs. Almost all of the cases are just calling a single method that is named the same as the player's input, so the other way it could have been done is if I used "invoke" on the inputted word. This would have reduced the length of the script significantly, but it would have raised some other issues. For one, readability, if someone else (or more likely myself a few weeks later) wants to continue working on this, using invoke is a lot less self explanatory and would make it harder to know exactly what is going on. Also, if I later want to add support for different ways of saying the same thing (for example inputting "climb table" or "jump up") this could become a nightmare keeping track of everything.