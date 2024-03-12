package com.company.view;

import java.util.List;
import java.util.Scanner;
import com.company.controller.UserEventController;
import com.company.model.UserEvent;
import com.company.model.Event;

public class UserEventView {
    private final UserEventController userEventController;

    public UserEventView(UserEventController userEventController) {
        this.userEventController = userEventController;
    }

    public void displayEventsAndRegisterUser(int userId) {
        List<Event> events = userEventController.listAvailableEvents();
        if (events.isEmpty()) {
            System.out.println("Não há eventos disponíveis no momento.");
            return;
        }

        System.out.println("Eventos Disponíveis:");
        for (int i = 0; i < events.size(); i++) {
            System.out.println(i + 1 + ". " + events.get(i).getTitle() + " - " + events.get(i).getDescription()); // Alterado getName() para getTitle()
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número do evento que deseja participar:");
        int eventChoice = scanner.nextInt();

        if (eventChoice < 1 || eventChoice > events.size()) {
            System.out.println("Escolha inválida.");
        } else {
            userEventController.registerUserToEvent(userId, events.get(eventChoice - 1).getEntityId());
        }
    }
    
    public void removeUserFromEvent(int userId, int eventId) {
        boolean removed = userEventController.removeUserFromEvent(userId, eventId);
        if (removed) {
            System.out.println("Usuário removido do evento com sucesso!");
        } else {
            System.out.println("Falha ao remover o usuário do evento.");
        }
    }
}