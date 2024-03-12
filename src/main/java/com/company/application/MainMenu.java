package com.company.application;

import com.company.view.UserView;
import com.company.controller.UserController;
import com.company.controller.UserEventController;
import com.company.view.EventView;
import com.company.view.UserEventView;
import java.util.Scanner;

public class MainMenu {
	private Scanner scanner;
    private int loggedUserId = -1;
    private UserEventController userEventController;
    private UserEventView userEventView;
    

    public MainMenu() {
        this.scanner = new Scanner(System.in);
        this.userEventController = new UserEventController();
        this.userEventView = new UserEventView(userEventController);
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
                    this.loggedUserId = userController.loginUser();
                    if(this.loggedUserId != -1) {
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
    	EventView eventView = new EventView(userEventController);
        
        boolean continueInEventMenu = true;
        while (continueInEventMenu) {
        	System.out.println("\nMenu de Eventos:");
            System.out.println("1 - Cadastrar novo evento");
            System.out.println("2 - Listar todos os eventos");
            System.out.println("3 - Editar evento");
            System.out.println("4 - Adicionar Usuário ao Evento");
            System.out.println("5 - Listar meus eventos registrados");
            System.out.println("6 - Remover da lista de eventos");
            System.out.println("7 - Voltar");
             
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
                	if(this.loggedUserId != -1) {
                        eventView.registerUserToEvent(this.loggedUserId);
                    } else {
                        System.out.println("Nenhum usuário logado.");
                    }
                    break;
                case "5": 
                    if(this.loggedUserId != -1) {
                        eventView.displayRegisteredEvents(this.loggedUserId);
                    } else {
                        System.out.println("Por favor, faça login primeiro.");
                    }
                    break;
                case "6":
                    if (this.loggedUserId != -1) {
                        System.out.print("Digite o ID do evento do qual deseja remover o usuário: ");
                        int eventIdToRemove = scanner.nextInt();
                        scanner.nextLine(); 
                        userEventView.removeUserFromEvent(this.loggedUserId, eventIdToRemove);
                    } else {
                        System.out.println("Nenhum usuário logado.");
                    }
                    break;
                case "7":
                    continueInEventMenu = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
