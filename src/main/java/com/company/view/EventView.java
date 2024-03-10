package com.company.view;

import com.company.controller.EventController;
import java.util.Scanner;

public class EventView {

    private EventController eventController = new EventController();
    private Scanner scanner = new Scanner(System.in);

    public void addEventForm() {
        System.out.println("Enter event title:");
        String title = scanner.nextLine();
        System.out.println("Enter event description:");
        String description = scanner.nextLine();

        boolean isSaved = eventController.addEvent(title, description);
        if (isSaved) {
            System.out.println("Event saved successfully.");
        } else {
            System.out.println("Failed to save the event.");
        }
    }

    // Métodos adicionais para outras interações
}
