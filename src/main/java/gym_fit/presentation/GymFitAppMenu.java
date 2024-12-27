package gym_fit.presentation;

import gym_fit.data.ClientDAO;
import gym_fit.data.IClientDAO;

import java.util.Scanner;

public class GymFitAppMenu {
    public static void menu() {
        var leave = false;
        var console = new Scanner(System.in);
        IClientDAO clientDAO = new ClientDAO();
        while (!leave) {
            try {
                var option = printMenu(console);
                leave = executeOptions(console, clientDAO, option);

            } catch (Exception e) {
                System.out.println("Error to execute menu: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static boolean executeOptions(Scanner console, IClientDAO clientDAO, int option) {
        var leave = false;
        switch (option){
            case 1 -> listClients(clientDAO);
        }
        return false;
    }

    private static void listClients(IClientDAO clientDAO) {
        var clients = clientDAO.clientList();
        clients.forEach(System.out::println);
    }

    private static int printMenu(Scanner console) {
        System.out.print("""
                **** Welcome to GymFit ****
                1. List clients
                2. Search client by id
                3. Add new client
                4. Delete client
                5. Modified client
                6. Exit
                Choose an option:\s""");
        return Integer.parseInt(console.nextLine());
    }
}
