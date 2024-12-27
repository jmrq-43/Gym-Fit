package gym_fit.presentation;

import gym_fit.data.ClientDAO;
import gym_fit.data.IClientDAO;
import gym_fit.domain.Client;

import java.util.Scanner;

public class GymFitAppMenu {
    static Scanner console = new Scanner(System.in);

    public static void menu() {
        var leave = false;
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
        switch (option) {
            case 1 -> listClients(clientDAO);
            case 2 -> searchClient(clientDAO);
            case 3 -> addClient(clientDAO);
        }
        return false;
    }

    private static void addClient(IClientDAO clientDAO) {
        System.out.println("--- add new client ---");
        System.out.print("Enter name: ");
        var name = String.valueOf(console.nextLine());

        System.out.print("Enter last name: ");
        var lastName = String.valueOf(console.nextLine());

        System.out.print("Enter membership number: ");
        var membership = Integer.parseInt(console.nextLine());

        var client = new Client(name, lastName, membership);
        var search = clientDAO.addClient(client);

        if (search) {
            System.out.println("Client added successfully");
        } else
            System.out.println("Error added client");
    }

    private static void searchClient(IClientDAO clientDAO) {
        System.out.print("Enter the client id: ");
        var idClient = Integer.parseInt(console.nextLine());
        var client = new Client(idClient);
        var found = clientDAO.searchClientById(client);
        if (found) {
            System.out.println("Client found: " + client);
        } else
            System.out.println("Error, client not found " + client);
    }

    private static void listClients(IClientDAO clientDAO) {
        System.out.println("---- list of clients ----");
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
