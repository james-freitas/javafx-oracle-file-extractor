Database File Extractor 1.0
------------------------------------------------------------------------
- **Description**: Java MVC Desktop Application using JavaFX
- **Author**: James Freitas
- **Technologies**: Java, JavaFX, JDBC
- **Summary**: Java MVC Desktop Application using JavaFX


What is it?
-----------

This project demonstrates an JavaFX tool that extracts files recorded in Oracle Long Raw data fields.


Structure
------------------------------------------------------------------------
- _Model_: contains the domain classes that hold information about the connection and queries made by the end user
- _Controller_: this layer communicates with the view (fxml)and binds methods and properties with it
- _View_: this layer contains the view file (fxml) made in Scene Builder and the fx styles
- _Logic_: this layer contains the logic of the file extraction 
- _DAO_: contains the query operations used in the file extraction
- _Infra_: Isolates the class responsible for open a new Connection



Technologies used
--------------------
- Eclipse
- Scene Builder
- Java
- Java FX
- JDBC


System requirements
-------------------
- Java 1.7 or higher