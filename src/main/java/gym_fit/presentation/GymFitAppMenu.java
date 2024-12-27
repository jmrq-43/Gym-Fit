package gym_fit.presentation;

import gym_fit.data.ClientDAO;

import java.util.Scanner;

public class GymFitAppMenu {
    public static void menu() {
        var leave = false;
        var console = new Scanner(System.in);
        var clientDAO = new ClientDAO();
        while (!leave) {
            try {
                printMenu();
                leave = executeOptions(console, clientDAO);

            } catch (Exception e) {
                System.out.println("Error to execute menu: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static boolean executeOptions(Scanner console, ClientDAO clientDAO) {

        return false;
    }

    private static void printMenu() {
        System.out.print("""
                **** Welcome to GymFit ****
                1. List clients
                2. Search client by id
                3. Add new client
                4. Delete client
                5. Modified client
                6. Exit
                Choose an option:\s""");
    }
}
