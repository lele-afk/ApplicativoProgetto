package com.example.view;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class main extends Application {
   /* private Home home = new Home();
    @Override
    public void start(Stage stage) throws IOException {
        home.start(home);

    }

    public static void main(String[] args) {
        launch(args);
    }*/
   public static void main(String[] args) {
       launch(args);
   }

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {




        Home startApp  = new Home();
        startApp.start(primaryStage);
        //primaryStage.setScene(new Scene(root));/primaryStage.show();
    }
}