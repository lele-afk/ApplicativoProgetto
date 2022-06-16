package com.example.view;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class main extends Application {

   public static void main(String[] args) {
       launch(args);
   }

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {




        Login startApp  = new Login();
        startApp.start(primaryStage);
        //primaryStage.setScene(new Scene(root));/primaryStage.show();
    }
}