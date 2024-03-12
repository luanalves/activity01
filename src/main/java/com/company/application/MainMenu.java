package com.company.application;

import com.company.view.UserView;
import com.company.controller.UserController;
import com.company.view.EventView;
import java.util.Scanner;

public class MainMenu {
    private Scanner scanner;

    public MainMenu() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        UserView userView = new UserView();
        UserController userController = new UserController(userView);

        while (true) {
            System.out.println("Selecione uma opção:");
            System.out.println("0 - Criar usuário");
            System.out.println("1 - Login");
            System.out.println("2 - Sair");

            String input = scanner.nextLine();

            switch (input) {
                case "0":
                    userController.registerUser();
                    break;
                case "1":
                    login();
                    if(userController.loginUser()) {
                        displayEventMenu();
                    }
                    break;
                case "2":
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void login() {
        System.out.println("Realizando login...");
    }
    
    private void displayEventMenu() {
        EventView eventView = new EventView();
        
        boolean continueInEventMenu = true;
        while (continueInEventMenu) {
        	System.out.println("\nMenu de Eventos:");
            System.out.println("1 - Cadastrar novo evento");
            System.out.println("2 - Listar eventos");
            System.out.println("3 - Editar evento");
            System.out.println("4 - Voltar");
             
         	System.out.print("Escolha uma opção: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    eventView.displayAddEventForm();
                    break;
                case "2":
                	eventView.displayEventsList();  
                    break;
                case "3":
                	eventView.displayEditEventForm();
                    break;
                case "4":
                    continueInEventMenu = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
