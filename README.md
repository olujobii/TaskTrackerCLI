Find project on  [Roadmap.sh](https://roadmap.sh/projects/task-tracker)

# Task-Tracker CLI
A CLI-based task tracker where users can manage their tasks and it ensures data persistence.

## Features
- Add a task with description.
- Delete a task.
- Update status of your task (todo,done,in-progress).
- Update task description.
- List all tasks or filter tasks by status (todo,done,in-progress).

## Installation
1. Clone the repository
```bash
git clone https://github.com/olujobii/TaskTrackerCLI
```

2. Navigate to src folder of the cloned repository in your terminal
```bash
cd src
```

3. Compile the code
```bash
javac Main.java
```

4. Run the program
```bash
java Main <command> [argument]
```

## Usage
```bash
# Adding a new task
java Main add "Buy groceries"
# Output: Task added successfully (ID: 1)

# Updating and deleting tasks
java Main update 1 "Buy groceries and cook dinner"
java Main delete 1

# Marking a task as in progress or done
java Main mark-in-progress 1
java Main mark-done 1

# Listing all tasks
java Main list

# Listing tasks by status
java Main list done
java Main list todo
java Main list in-progress
```
