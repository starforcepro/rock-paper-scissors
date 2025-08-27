# Rock-Paper-Scissors (Console)

A simple, test-covered console implementation of the Rock–Paper–Scissors game.

You play against the computer over a configurable number of rounds. 
The game guides you with prompts and validates your input,
if input is invalid, the game will prompt you again, number of rounds does not decrease. 
You can exit at any moment by typing `exit`.

## Prerequisites
- Java Development Kit (JDK) 24
  - To verify your environment:
    - Windows/macOS/Linux
        - `java -version`
        - `mvn -version`
- Apache Maven 3.9+
- The project does not use any external libraries except for the testing engine JUnit 5.

## Build
- Windows PowerShell:
  - `mvn.cmd clean package`
- macOS/Linux:
  - `mvn clean package`

This will produce a runnable JAR at `target/rock-paper-scissors-1.0.jar`.

## Run
After building, run the application:

- Windows PowerShell:
  - `java -jar .\target\rock-paper-scissors-1.0.jar`
- macOS/Linux:
  - `java -jar ./target/rock-paper-scissors-1.0.jar`

## How to play (controls)
- Number of rounds: enter a positive integer from 0 to 1000 (inclusive).
- Moves:
  - Rock: `r` or `rock`
  - Paper: `p` or `paper`
  - Scissors: `s` or `scissors`
- Exit: type `exit` at any prompt to quit immediately.

## Run tests
- Windows PowerShell:
  - `mvn.cmd test`
- macOS/Linux:
  - `mvn test`
