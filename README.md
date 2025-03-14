# Tourist Group Management System

## Overview

This project is a **backend system for managing tourist groups and museums**. It allows users to create and manage museums, register tourist groups, and handle event notifications for tour guides. The system follows **object-oriented principles** and utilizes **several design patterns** to ensure maintainability and scalability.

## Features

- **Museum Management**: Add and organize museums, each with detailed attributes like location, manager, and contact information.
- **Tourist Group Handling**: Create and manage tourist groups, including assigning guides, adding/removing members, and handling reservations.
- **Event Notification System**: Museums can notify assigned tour guides about upcoming events or schedule changes.
- **Command-based Processing**: Uses a structured approach to handle commands from input files, ensuring flexibility and easy expansion.

## Design Patterns Used

I implemented **four key design patterns** to improve the architecture of this system:

1. **Singleton (Database Management)**  
   - Ensures that only one instance of the database exists throughout the application, preventing conflicts and ensuring consistent data access.

2. **Builder (Museum & Location Initialization)**  
   - Simplifies object creation for entities with multiple optional attributes, leading to cleaner and more readable code.

3. **Command (Input Processing)**  
   - Each command (e.g., adding a museum, registering a group) is encapsulated in a separate class. This allows easy extension of functionalities without modifying existing code.

4. **Observer (Event Notifications for Guides)**  
   - Museums act as subjects, while tour guides are observers. When a museum announces an event, all subscribed guides automatically receive notifications.
