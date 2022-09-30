# Project Duke

This is a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.
1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/command.Duke.java` file, right-click it, and choose `Run command.Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

```
Hello! I'm Duke
   
Let me load the existing data for you (if any)
   
Please confirm if this current directory is correct.
./data/
   
If it is, please confirm by typing 'Yes'
If you want to select a different directory, please confirm by typing 'No'
```
## Datafile Setup

1. Selecting directory path

This step allows you to select `./data/` as your directory used to store the data file if you select `yes`.

You may also change the directory by selecting `no`, and thereafter keying in your preferred directory.<br>
`Please type the name of the directory. E.g. './data/'`

2. Selecting datafile name

Once directory is specified, the program then asks you to confirm if the datafile name should be `duke.txt`.<br><br>
`Please confirm if this current file is correct.`<br>
`duke.txt`

`If it is, please confirm by typing 'Yes'`<br>
`If you want to select a different file, please confirm by typing 'No'`

If you wish to rename the default datafile name, select `no` and key in the preferred datafile name.

3. Loading any existing data

Once the directory has been specified and the datafile name has been confirmed, the program checks if there is an existing data file.<br><br>
If yes, it informs you of the datafile found and loads the content into the program. Else, it displays the following:<br><br>
`Thank you for your response`<br>
`No existing data is found`

## UI Display

Following this, the program displays the main UI page as the menu below:

`What would you like to do ?`<br>
`List of valid entries include the following:`

`"Bye\n"     +`<br>
`"List\n"    +`<br>
`"Mark         'X'\n" +`<br>
`"Unmark       'X'\n" +`<br>
`"Delete       'X'\n" +`<br>
`"Todo         'Y'\n" +`<br>
`"Event        'Y' /at 'Z'\n" +`<br>
`"Deadline     'Y' /by 'Z'\n" +`<br>
`"Where 'X' refers to the task number, 'Y' refers to the task description and 'Z' refers to the date using the format YYYY-MM-DD.\n");`<br>

1. **Bye**<br>
   This option helps to exit from the Task Manager.<br><br>

1. **List**<br>
   This option lists all the items from the TaskList.<br><br>
1. **Mark**<br>
   This option helps to mark a task as completed, which changes its status icon from `[ ]` to `[X]`. User must specify task number to mark. <br><br>
1. **Unmark**<br>
   This option helps to mark a task as completed, which changes its status icon from `[X]` to `[ ]`. User must specify task number to unmark. <br><br>
1. **Delete**<br>
   This option removes a task from the TaskList. User must specify task number to delete.<br><br>
1. **Todo**<br>
   This option adds a `Todo` task into the TaskList. User must specify task description. One example is `Todo read book`.<br><br>
1. **Event**<br>
   This option adds an `Event` task into the TaskList. User must specify task description & date. One example is `Event concert /at 2022-12-30`.<br><br>
1. **Deadline**<br>
   This option adds an `Deadline` task into the TaskList. User must specify task description & date. One example is `Deadline homework /by 2022-09-26`.<br><br>