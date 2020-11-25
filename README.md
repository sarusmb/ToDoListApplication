# CPSC 210 Personal Project

## A simple To-Do list

For my CPSC 210 personal project, I decided to build a to-do list application. This project is of interest to me 
because I always use some form of digital reminders and to-do lists to organize my day. I want to explore the process 
behind building such an application and putting the concepts I learned in class to use!

The purpose of this application is to allow a user to keep track of all of the tasks they need to complete in an easy 
and simple manner. The application allows a user to add a task with an associated due date, and cross out these tasks
as they are completed. The application also allows the user to see all of the entered tasks in their to-do list. 
This application is targeted at any users who want to organize their tasks using one central application. 

## User Stories

*As a user, I want to be able to:*

- add a task to my to-do list
- delete a task from my to-do list
- set a task as completed
- view all my pending tasks in my to-do list
- save my to-do list to a file
- load my to-do list from a file

*User story ideas are based on the examples given in CPSC 210 edX page.*

**Phase 4: Task 2**
- Bi-directional association between AddTaskListener & ApplicationGui 

**Phase 4: Task 3**

*Reflection on UML diagram*

- All of the button listener classes (except AddTaskListener) have a unidirectional
  association with Application Gui.
- ApplicationGui has a unidirectional association with ToDoList, allowing interaction
    between ui and model classes
    
If I had more time to spend refactoring my code, I would...

- Improve code duplication in listener classes by making all listener classes extend
a superclass that implements playsound() method

- Make Listener classes nested within ApplicationGui to reduce coupling and dependency
between ApplicationGui and listener classes(as seen in ListDemo example). 

- actionPerformed() methods in AddTaskListener, CompleteTaskListener and RemoveTaskListener
are long/do multiple things at once. Split this using smaller helper methods



