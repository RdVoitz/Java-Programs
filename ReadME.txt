First create 2 files: myHouse.txt and Register.txt ( i have alrready created the myHouse.txt and filled it with some informations in the format that was requested)
Fill the myHouse with informations for the simulator respecting the way they are written in the coursework spec. You can add as many houses as you want
The way it work is very clear :
	A simulator reads the source file ( myHouse.txt) and creates different objects and then calls house.go
	By calling house.go, you call timePasses() 96 times simulating a day and write the results
	The timePasses in the house first calls the timePasses() for every Person in the House then for every Appliance
	By calling timePasses for every Person, it checks if any Person has any task to accomplish
	If the answer is yes, then it calls the doTask method for every Task that must be done
	By calling doTask, you call use() or useeco() method on the appliances that can perform the task
	The principle of use method is to get the appliance ready for the timePasses() Method to be called on it by turning it on (or off for the specific Turn ON/Off Appliance)
	At the end, by calling time Passes on every appliance wich basically does nothing if the appliance is off, or increases (or generates) resources values
	
It works simple because Appliance, Person, and Task are all tied to the same House, sharing the same meters.
This way of setting things made it easy to connect Task to the appliances in the house ( or verify if there is no appliance that can perform a task)
It is simple to associate the tasks to the right persons based on input using the Person list in the House.

I have accomplished some extensions too:
	first of all, you can add as many houses as you want
	then, the temperature in the house is changing during the day and the boiler is turning on when it is bellow 20 degrees. (the temerature in the house is 20 by default)
	also a person can turn off the heating ( Task name: TurnOffHeating);
	you can call eco mode for the dishWasher and Washing Machine: DoWashingEcoMode and DoDishWashingEcoMode wich will make less electricity and water consumption
	i attemted to record the data in another file called Register.txt but i still don't know why it records just for the first house give. 
	still i consider it a good atempt  for an  extension

	