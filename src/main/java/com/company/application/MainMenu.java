package com.company.application;

import com.company.view.UserView;
import com.company.controller.UserController;
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
                    userController.loginUser();
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
        // Implemente a lógica de login aqui
        System.out.println("Realizando login...");
    }
    
    // Outros métodos, se necessário
}
