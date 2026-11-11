import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Scanner;

public class AlarmControl {

    private static final DateTimeFormatter ALARM_TIME_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm").withResolverStyle(ResolverStyle.STRICT);

    public ArrayList<String> getUserAlarms(Scanner sc, ArrayList<String> fullAlarmData) {

        if (fullAlarmData.isEmpty()) {
            System.out.println("\nNo alarms have been set.");
        }
        else {
            checkAlarms(fullAlarmData);
        }

        int alarmCountAtStart = fullAlarmData.size();

        while (true) {

            System.out.print("\nDo you want to enter an alarm? (yes/no or 1/0): ");
            String response = sc.nextLine().trim().toLowerCase();

            if (response.equals("no") || response.equals("0")) {

                if (fullAlarmData.size() > alarmCountAtStart) {
                    checkAlarms(fullAlarmData);
                }
                System.out.println("\nReturning to the Main Menu...");
                return fullAlarmData;
            }
            else if (!response.equals("yes") && !response.equals("1")) {
                System.out.println("Invalid response. Please enter yes/no or 1/0.");
                continue;
            }

            String name;
            while (true) {

                System.out.print("\nPlease enter a name for the new alarm: ");
                name = sc.nextLine().trim();

                if (name.isEmpty()) {
                    System.out.println("Alarm name cannot be empty.");
                    continue;
                }

                if (alarmNameExists(name, fullAlarmData)) {
                    System.out.println("An alarm with this name already exists.");
                    continue;
                }
                break;
            }

            while (true) {

                System.out.print("Please enter your preferred time for '" + name + "' (24-hour HH:mm format, 00:00 to 23:59 range): ");
                String input = sc.nextLine().trim();

                try {
                    LocalTime.parse(input, ALARM_TIME_FORMAT);
                    fullAlarmData.add(name);
                    fullAlarmData.add(input);
                    System.out.println("'" + name + "' alarm set to: " + input + ".");
                    break;
                }
                catch (DateTimeParseException e) {
                    System.out.println("Invalid input. Please use 24-hour HH:mm format in 00:00 to 23:59 range.");
                }
            }
        }
    }

    private boolean alarmNameExists(String name, ArrayList<String> completeData) {

        for (int i = 0; i < completeData.size(); i += 2) {
            if (completeData.get(i).equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void checkAlarms(ArrayList<String> completeAlarmData) {

        for (int i = 0; i < completeAlarmData.size() - 1; i += 2) {
            for (int j = 0; j < completeAlarmData.size() - 2 - i; j += 2) {

                LocalTime timeA = LocalTime.parse(completeAlarmData.get(j + 1));
                LocalTime timeB = LocalTime.parse(completeAlarmData.get(j + 3));

                if (timeA.isAfter(timeB)) {
                    String tempName = completeAlarmData.get(j);
                    completeAlarmData.set(j, completeAlarmData.get(j + 2));
                    completeAlarmData.set(j + 2, tempName);

                    String tempTime = completeAlarmData.get(j + 1);
                    completeAlarmData.set(j + 1, completeAlarmData.get(j + 3));
                    completeAlarmData.set(j + 3, tempTime);
                }
            }
        }

        LocalTime now = LocalTime.now().withSecond(0).withNano(0);

        System.out.println("\n*******************************************************************************************");
        System.out.println("\nGreat! The Robot Assistant is checking the current local time ...");
        System.out.println("The current local time is: " + now + ".");
        System.out.println();
        System.out.println("List of Alarms Set:");

        for (int i = 0; i < completeAlarmData.size(); i += 2) {

            String name = completeAlarmData.get(i);
            String timeStr = completeAlarmData.get(i + 1);
            LocalTime alarmTime = LocalTime.parse(timeStr);

            if (now.equals(alarmTime)) {
                System.out.println("\nMessage for Robot Assistant: Please trigger alarm '" + name + "' - It is " + alarmTime + ".");
            }
            else {
                Duration remaining = timeDiffChecker(now, alarmTime);
                printTimeRemaining(name, remaining);
                System.out.println("Message for Robot Assistant: Please trigger alarm '" + name + "' at " + alarmTime + ".");
            }
        }
        System.out.println("\n*******************************************************************************************");
    }

    private Duration timeDiffChecker(LocalTime now, LocalTime target) {

        Duration duration = Duration.between(now, target);

        if (duration.isNegative()) {
            duration = duration.plusHours(24);
        }
        return duration;
    }

    private void printTimeRemaining(String name, Duration duration) {

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        String hourWord = (hours == 1) ? " hour" : " hours";
        String minuteWord = (minutes == 1) ? " minute" : " minutes";

        System.out.println("\nAlarm '" + name + "' - Time remaining: " + hours + hourWord + " and " + minutes + minuteWord + ".");
    }
}
