# Siri project 

This is a chatbot called Siri that helps you to mangage your tasks.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/siri.siri.java` file, right-click it, and choose `Run siri.siri.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
     _____  _      _ 
    / ____|(_)    (_)
    | (___  _ _ __ _ 
    \\___\\| | '__| |
     ____) | | |  | |
    |_____/|_|_|  |_|
                   

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

# User Guide

## Introduction
Siri is a simple **task management chatbot**. It lets you add, list, mark, unmark, delete, search, and undo tasks through text commands.

---

## Commands

| Command | Format | Example |
|---------|--------|---------|
| **List tasks** | `list` | `list` |
| **Add Todo** | `todo <description>` | `todo read book` |
| **Add Deadline** | `deadline <description> /by <date>` | `deadline return book /by 2025-10-10` |
| **Add Event** | `event <description> /from <start> /to <end>` | `event meeting /from 14:00 /to 16:00` |
| **Mark done** | `mark <task number>` | `mark 2` |
| **Unmark** | `unmark <task number>` | `unmark 2` |
| **Delete** | `delete <task number>` | `delete 1` |
| **Find** | `find <keyword>` | `find book` |
| **Undo** | `undo` or `undo <task number>` | `undo` |
| **Exit** | `bye` | `bye` |



