# TempConverterUI

`TempConverterUI` is a Java Swing desktop application that converts temperature values between Celsius, Fahrenheit, and Kelvin through a custom-designed graphical interface.

## Features

- Convert temperature from Celsius, Fahrenheit, or Kelvin
- Show results for all three units at the same time
- Built with Java Swing
- Displays a warning for invalid input
- Includes a reset button to clear the form

## Technologies Used

- Java
- Java Swing
- AWT Graphics for custom UI styling

## Project File

- `TempConverterUI.java` - main source code file

## How to Run

1. Make sure Java is installed on your system.
2. Open a terminal in the project folder.
3. Compile the program:

```bash
javac TempConverterUI.java
```

4. Run the program:

```bash
java TempConverterUI
```

## How to Use

1. Enter a temperature value.
2. Choose the input unit: Celsius, Fahrenheit, or Kelvin.
3. Click `Convert Temperature`.
4. Read the converted values shown in all units.
5. Click `Reset` to clear the input and results.

## Conversion Formulas

- Celsius to Fahrenheit: `(C * 9 / 5) + 32`
- Celsius to Kelvin: `C + 273.15`
- Fahrenheit to Celsius: `(F - 32) * 5 / 9`
- Kelvin to Celsius: `K - 273.15`

## Example

If the input is `25` Celsius, the output will be:

- `25.00 deg C`
- `77.00 deg F`
- `298.15 K`

## Learning Outcome

This project helps practice:

- Java Swing GUI development
- Event handling in Java
- Temperature conversion logic
- Custom component styling

## Author

Created as a Java mini project for practice and learning.
