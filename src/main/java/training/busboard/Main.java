package training.busboard;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Do you want to input bus stop code or postcode?");
            String read = scanner.nextLine();
            if(read.equalsIgnoreCase("bus stop code")) {
                String id = inputLoop("Input Bus Stop ID: ");
                BusClient.findFirstFiveBuses(id);
                break;
            } else if(read.equalsIgnoreCase("postcode")) {
                String postcode = inputLoop("Input Postcode: ");
                BusClient.findTwoClosestStops(postcode);
                break;
            }
        }
    }

    private static String inputLoop(String communicate) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(communicate);
        return scanner.nextLine();
    }

}
