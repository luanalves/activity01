package com.company.view;
import java.util.Scanner;
import com.company.model.User;

public class UserView {

	private Scanner scanner = new Scanner(System.in);

    public User getUserInput() {
        System.out.println("Digite o nome completo:");
        String fullName = scanner.nextLine();

        System.out.println("Digite o nome de usuário:");
        String username = scanner.nextLine();

        System.out.println("Digite a senha:");
        String password = scanner.nextLine();

        return new User(fullName, username, password);
    }

    public void displayUserRegistered() {
        System.out.println("Usuário cadastrado com sucesso!");
    }
    
    public User getUserLogin() {
        System.out.println("### Login ###");
        System.out.print("Digite o nome de usuário: ");
        String username = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String password = scanner.nextLine();

        return new User("", username, password); // Note que o fullName não é necessário para login
    }

    public void displayLoginSuccess() {
        System.out.println("Login realizado com sucesso!");
    }

    public void displayLoginFailure() {
        System.out.println("Falha no login. Usuário ou senha incorretos.");
    }
}
