import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class RobotAssistantTasks {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AlarmControl alarmControl = new AlarmControl();
        TemperatureControl tempControl = new TemperatureControl();
        ArrayList<String> fullAlarmData = new ArrayList<>();
        boolean assistance = true;

        try {
            while (assistance) {

                System.out.println("\n********** Robot Assistant Tasks **********");
                System.out.println("\n1. Alarm Control");
                System.out.println("2. Temperature Control");
                System.out.println("3. Exit\n");
                System.out.print("Choose an option (1-3): ");
                String choice = sc.nextLine().trim();

                if (choice.equals("1")) {
                    fullAlarmData = alarmControl.getUserAlarms(sc, fullAlarmData);
                }
                else if (choice.equals("2")) {
                    double sensorInputTemp = 77;
                    tempControl.checkTemperatureRequirements(sensorInputTemp,sc);
                }
                else if (choice.equals("3")) {
                    System.out.println("Goodbye! Robot Assistant is shutting down...");
                    System.out.println("\n*******************************************************************************************");
                    assistance = false;
                }
                else {
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
                }
            }
        }
        catch (NoSuchElementException e) {
            System.out.println("\n\nNo more input received.");
            System.out.println("Goodbye! Robot Assistant is shutting down...");
            System.out.println("\n*******************************************************************************************");
        }
        sc.close();
    }
}
