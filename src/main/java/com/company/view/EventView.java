package com.company.view;

import com.company.controller.EventController;
import com.company.controller.UserEventController;
import java.util.Scanner;
import com.company.model.Event;
import java.util.List;


public class EventView {

    private Scanner scanner = new Scanner(System.in);
    private EventController eventController = new EventController();
    private UserEventController userEventController;
    
    public EventView(UserEventController userEventController) {
        this.scanner = new Scanner(System.in);
        this.userEventController = userEventController;
    }

    public void displayAddEventForm() {
        System.out.println("### Adicionar Novo Evento ###");
        System.out.print("Digite o título do evento: ");
        String title = scanner.nextLine();

        System.out.print("Digite a descrição do evento: ");
        String description = scanner.nextLine();

        boolean isEventAdded = eventController.addEvent(title, description);
        if (isEventAdded) {
            System.out.println("Evento adicionado com sucesso!");
        } else {
            System.out.println("Falha ao adicionar o evento.");
        }
    }
    
    public void displayEventsList() {
        List<Event> events = eventController.listEvents();
        if (events.isEmpty()) {
            System.out.println("Não há eventos cadastrados.");
        } else {
            System.out.println("### Lista de Eventos ###");
            for (Event event : events) {
                System.out.println("ID: " + event.getEntityId() + ", Título: " + event.getTitle() + ", Descrição: " + event.getDescription()
                + ", Created: " + event.getCreatedAt() + ", Updated: " + event.getUpdatedAt()
                		);
            }
        }
    }
    
    
    public void displayEditEventForm() {
        System.out.println("Digite o ID do evento que deseja editar:");
        int eventId = Integer.parseInt(scanner.nextLine());

        Event event = eventController.getEventById(eventId);
        if (event == null) {
            System.out.println("Evento não encontrado.");
            return;
        }
        
        System.out.println("Digite o novo título do evento (deixe em branco para manter o atual):");
        String newTitle = scanner.nextLine();

        System.out.println("Digite a nova descrição do evento (deixe em branco para manter a atual):");
        String newDescription = scanner.nextLine();
        
        if (!newTitle.isEmpty()) {
            event.setTitle(newTitle);
        }
        if (!newDescription.isEmpty()) {
            event.setDescription(newDescription);
        }
        
        System.out.println("Editando evento: " + event.getTitle());

        
        boolean success = eventController.editEvent(event);
        if (success) {
            System.out.println("Evento atualizado com sucesso.");
        } else {
            System.out.println("Falha ao atualizar o evento.");
        }
    }
    
    public void registerUserToEvent(int userId) {
        List<Event> events = userEventController.listAvailableEvents();
        if (events.isEmpty()) {
            System.out.println("Não há eventos disponíveis.");
            return;
        }

        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + " - " + events.get(i).getTitle());
        }

        System.out.print("Escolha um evento para se inscrever: ");
        int eventChoice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (eventChoice >= 0 && eventChoice < events.size()) {
            Event selectedEvent = events.get(eventChoice);
            userEventController.registerUserToEvent(userId, selectedEvent.getEntityId());
        } else {
            System.out.println("Escolha inválida.");
        }
    }
}
