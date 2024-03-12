package com.company.view;

import com.company.controller.EventCategoryController;
import java.util.Scanner;

public class EventCategoryView {

    private EventCategoryController eventCategoryController = new EventCategoryController();
    private Scanner scanner = new Scanner(System.in);

    public void addEventCategoryForm() {
        System.out.println("Enter event category title:");
        String title = scanner.nextLine();
        System.out.println("Enter event category description:");
        String description = scanner.nextLine();

        boolean isSaved = eventCategoryController.addEventCategory(title, description);
        if (isSaved) {
            System.out.println("Event category saved successfully.");
        } else {
            System.out.println("Failed to save the event category.");
        }
    }
}