# Bug Hunter — Virtual Errors Dictionary

A Java Swing desktop application that acts as a small virtual dictionary for common programming bugs and errors.

## Features

- Select common bugs from a button panel.
- Diagnose a selected bug and view its explanation/fix.
- Edit and save a bug diagnosis.
- Report and add a new bug.
- View the current bug database and total bug count.
- Remove/collapse database views and clear the current selection.
- Show developer information.

## Project Structure

```text
src/
└── bugHunter/
    ├── Bug.java
    ├── BugManager.java
    ├── BugHunterGUI.java
    └── Main.java
```

## Run Locally

Requires Java 17+.

```bash
javac -d bin src/bugHunter/*.java
java -cp bin bugHunter.Main
```

## Browser Demo

The `docs/` directory contains the GitHub Pages wrapper for running the compiled Java application in a browser with CheerpJ.

Live demo (after enabling GitHub Pages):

`https://mohammadjabr.github.io/Bug-Hunter-Virtual-Errors-Dictionary/`

## Author

Mohammed Jabr — Computer Engineering
