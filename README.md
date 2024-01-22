Vehicle Directory
============================================================

Directory for organizing sales of automotive equipment

### Technology stack used:
* Java
* Maven
* Spring Boot
* Lombok
* PostgreSQL
* JUnit
* Swagger
* Thymeleaf

### Project key logic:

* Required fields for transport equipment: make, model, category, state number, type, year, trailer.
* Mandatory program functions: search for a list of vehicles using available data (for example, by category and model).
* The graphical interface consists of the main window and windows for adding/editing vehicles.
* The main window displays a table with a list of vehicles. Below the table are input fields that allow you to filter the contents of the table.
  Input fields are as follows: brand, model, category, state number, year of manufacture.
* The window for adding/editing a vehicle has the following input/edit fields: make, model, category, state number, year of manufacture and the “vehicle type” drop-down list.
* The “Save” button allows you to save the data and close the window for adding/editing a vehicle. The Close button closes the window without saving changes.

### API documentation:
### [REST API documentation](http://localhost:8080/swagger-ui.html)  