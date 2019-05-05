import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class GameBOT extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(50 , 0 , 50 , 0));

        Text text = new Text(100 , 100 , "PACMAN");
        text.setFont(Font.font("Forte" , FontPosture.REGULAR , 70));
        text.setFill(Color.WHITE);

        HBox hBox = new HBox();
        hBox.getChildren().add(text);

        String style =
                "-fx-background-color: rgba(0, 0, 0);"+
                        "    -fx-font-family: \"Forte\";\n" +
                        "    -fx-font-size: 28px;\n" +
                        "    -fx-text-fill: rgba(255, 255, 255);\n";

        String button_= "-fx-background-color: rgba(169, 169, 169);"+
                "    -fx-font-family: \"Forte\";\n" +
                "    -fx-font-size: 28px;\n" +
                "    -fx-text-fill: rgba(255, 255, 255);\n";

        Button startGame = new Button("START GAME");
        startGame.setStyle(button_);

        borderPane.setTop(hBox);
        hBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(startGame);
        startGame.setAlignment(Pos.CENTER);

        // END OF WELCOME PAGE

        // BACKGROUND
        borderPane.setBackground(Background.EMPTY);
        borderPane.setStyle("-fx-background-color: rgba(0, 0, 0);");
        //END OF BACKGROUND

        // ENDING PAGE

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(50 , 0 , 50 , 0));

        Text ending_text = new Text(100 , 100 , "Thanks!");
        ending_text.setFont(Font.font("Forte" , FontPosture.REGULAR , 70));
        ending_text.setFill(Color.WHITE);

        HBox hBox_ending = new HBox();
        hBox_ending.getChildren().add(ending_text);

        Button exit = new Button("Exit");
        exit.setStyle(button_);

        bp.setTop(hBox_ending);
        hBox_ending.setAlignment(Pos.CENTER);
        bp.setCenter(exit);
        exit.setAlignment(Pos.CENTER);

        bp.setBackground(Background.EMPTY);
        bp.setStyle("-fx-background-color: rgba(0, 0, 0);");
        //END OF ENDING PAGE
        exit.setOnMouseClicked(event -> {
            primaryStage.close();
        });

        startGame.setOnMouseClicked(event -> {
            try{
                Map map = new Map("/Users/ChD/IdeaProjects/project/src/mapBOT0.txt");
                BotPlayer player = new MyBotPlayer(map);
                Food food = new Food(map , player);
                player.feed(food);
                map.setPadding(new Insets(50 , 50 , 50 ,50 ));

                map.setOnKeyPressed(key -> {
                    if (food.getNumOfCircles() == 0){
                        Scene ending_scene = new Scene(bp , 410 , 410);
                        primaryStage.setScene(ending_scene);
                        primaryStage.setTitle("ENDGAME");
                    }

                    if (key.getCode().equals(KeyCode.E)){
                        System.out.println("E key pressed... ");
                        try {
                            player.eat();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

                Scene scene = new Scene(map , 410 , 410);
                primaryStage.setScene(scene);
                map.requestFocus();

            } catch (FileNotFoundException ex){
                ex.printStackTrace();
            }
        });

        Scene sc = new Scene (borderPane , 410 , 410);
        primaryStage.setScene(sc);
        primaryStage.setTitle("PACMAN");
        primaryStage.show();
        System.out.println("The game has started , Good game ... ");

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
