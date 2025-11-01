Find more projects on  [Roadmap.sh](www.roadmap.sh)

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
java Main.java <command> [argument]
```

## Usage
```bash
# Adding a new task
task-cli add "Buy groceries"
# Output: Task added successfully (ID: 1)

# Updating and deleting tasks
task-cli update 1 "Buy groceries and cook dinner"
task-cli delete 1

# Marking a task as in progress or done
task-cli mark-in-progress 1
task-cli mark-done 1

# Listing all tasks
task-cli list

# Listing tasks by status
task-cli list done
task-cli list todo
task-cli list in-progress
```
