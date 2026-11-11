import java.util.ArrayList;
import java.util.Scanner;

public class TemperatureControl {

    public void checkTemperatureRequirements(double sensorInpTemp, Scanner sc) {

        ArrayList<Double> temperaturePreference;

        while (true) {

            temperaturePreference = temperatureRequirements(sc);

            if (temperaturePreference.get(0) > temperaturePreference.get(1)) {
                System.out.println("Lowest preferred temperature must be less than or equal to Highest preferred temperature.");
            }
            else {
                break;
            }
        }
        System.out.println("\n*******************************************************************************************");
        System.out.println("\nGreat! The Robot Assistant is checking the current room temperature ...");

        temperatureMaintain(sensorInpTemp, temperaturePreference);
    }

    public ArrayList<Double> temperatureRequirements(Scanner sc) {

        double lowestPreferred, highestPreferred;

        System.out.print("\nPlease enter the Lowest preferred room temperature in the 44-90 degrees Fahrenheit range: ");
        lowestPreferred = getValidTemperatureInput(sc);

        System.out.print("Please enter the Highest preferred room temperature in the 44-90 degrees Fahrenheit range: ");
        highestPreferred = getValidTemperatureInput(sc);

        ArrayList<Double> temperaturePreference = new ArrayList<>();
        temperaturePreference.add(lowestPreferred);
        temperaturePreference.add(highestPreferred);

        return temperaturePreference;
    }

        public double getValidTemperatureInput(Scanner sc) {

            double temperature;

            while (true) {

                String temp = sc.nextLine().trim();

                if (temp.isEmpty()) {
                    System.out.print("Input is empty. Please enter a valid temperature in the 44-90 degrees Fahrenheit range: ");
                }
                else {
                    try {
                        if (!temp.matches("[+-]?(\\d+(\\.\\d*)?|\\.\\d+)")) {
                            throw new NumberFormatException();
                        }
                        temperature = Double.parseDouble(temp);
                        if (Double.isNaN(temperature) || temperature < 44 || temperature > 90) {
                            System.out.print("This does not fall in the 44-90 degrees Fahrenheit range. Please enter a valid temperature: ");
                        }
                        else {
                            break;
                        }
                    }
                    catch (NumberFormatException e) {
                        System.out.print("This is not a valid number. Please enter a valid temperature in the 44-90 degrees Fahrenheit range: ");
                    }
                }
            }
            return temperature;
        }

    public void temperatureMaintain(double sensorInpTemp, ArrayList<Double> tempPref) {

        double low = tempPref.get(0);
        double high = tempPref.get(1);

        System.out.println("The current temperature is: " + sensorInpTemp + " degrees Fahrenheit");
        System.out.println();
        System.out.println("Your lowest preferred temperature is: " + low + " degrees Fahrenheit");
        System.out.println("Your highest preferred temperature is: " + high + " degrees Fahrenheit");
        System.out.println();

        if (low == high && sensorInpTemp == low) {
            System.out.println("The highest and lowest preferred temperature are both: " + low + " degrees Fahrenheit");
            System.out.println();
            System.out.println("Message for Robot Assistant: Please keep the cooler and the heater off.");
        }
        else if (sensorInpTemp < low) {
            System.out.println("Message for Robot Assistant: Please switch on the heater.");
        }
        else if (sensorInpTemp > high) {
            System.out.println("Message for Robot Assistant: Please switch on the cooler.");
        }
        else {
            System.out.println("The current temperature is within your preferred temperature range.");
            System.out.println();
            System.out.println("Message for Robot Assistant: Please keep the cooler and the heater off.");
        }
        System.out.println("\n*******************************************************************************************");
    }
}
