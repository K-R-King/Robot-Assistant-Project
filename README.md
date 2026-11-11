# Robot-Assistant-Project

A command-line Robot Assistant written in Java. It was originally built as a project in **2024**.

The assistant has a simple menu with two tasks:

1. **Alarm Control**: set named alarms in 24-hour `HH:mm` time. The assistant lists them in time order and shows how long is left until each one goes off.
2. **Temperature Control**: enter your lowest and highest preferred room temperatures (44–90 °F). The assistant compares them with the room temperature and decides whether to turn on the heater, turn on the cooler, or keep both off.

## Files

| File | What it does |
| --- | --- |
| `RobotAssistantTasks.java` | Main menu and program entry point |
| `AlarmControl.java` | Adds, checks, sorts and displays alarms |
| `TemperatureControl.java` | Reads temperature preferences and decides heater/cooler action |

## How to run

You need Java 8 or newer. It works the same way on Windows, macOS and Linux.

```bash
javac *.java
java RobotAssistantTasks
```

## Example

```
********** Robot Assistant Tasks **********

1. Alarm Control
2. Temperature Control
3. Exit

Choose an option (1-3): 2

Please enter the Lowest preferred room temperature in the 44-90 degrees Fahrenheit range: 70
Please enter the Highest preferred room temperature in the 44-90 degrees Fahrenheit range: 75

*******************************************************************************************

Great! The Robot Assistant is checking the current room temperature ...
The current temperature is: 77.0 degrees Fahrenheit

Your lowest preferred temperature is: 70.0 degrees Fahrenheit
Your highest preferred temperature is: 75.0 degrees Fahrenheit

Message for Robot Assistant: Please switch on the cooler.

*******************************************************************************************
```

## Notes

- The room temperature sensor is simulated with a fixed reading of 77 °F.
- Alarms are kept only while the program is running.
- Every input is checked: invalid menu choices, empty names, duplicate alarm names, badly formatted times and out-of-range or non-numeric temperatures are all rejected with a message, and the question is asked again.
