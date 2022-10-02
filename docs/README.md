# User Guide

## How it Works?
1. Run the command, `java -jar ip.jar`, in the same folder as the jar file.
2. Specify a filename to begin. If the file exists at *C:\Users\computer name\Duke\filename.txt*, it will be used as the working file. Otherwise, a new file will be automatically created at the same directory. 
3. You can exit the application by typing `bye` and hitting the `enter` key.

## Features 

### Add Task

Below are the commands to adding `todo`, `deadline` or `event` tasks. These tasks will also be added to the working file.

```
todo <define task description>
deadline <define task description> /by <specify task due>
event <define task description> /at <specify task due>
```
For example:

```
todo borrow book
deadline return book /by Sunday
event project meeting /at Mon 2-4pm
```

### List Task

To list task, type `list` and hit the `enter` key.

### Mark Task

To mark task, type `mark <specify the task nunmber>` and hit the `enter` key.  
For example: `mark 2`

### Unmark Task

To unmark task, type `unmark <specify the task nunmber>` and hit the `enter` key.  
For example: `unmark 4`

### Delete Task

To delete task, type `delete <specify the task nunmber>` and hit the `enter` key.  
For example: `delete 3`

### Find Task

To find task, type `find <specify the keyword>` and hit the `enter` key.  
For example: `find book`
