package com.company.view;

import com.company.controller.EventController;
import java.util.Scanner;
import com.company.model.Event;
import java.util.List;


public class EventView {

    private Scanner scanner = new Scanner(System.in);
    private EventController eventController = new EventController();

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
        int eventId = Integer.parseInt(scanner.nextLine()); // Assuma a entrada válida ou adicione tratamento de erro

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
}
