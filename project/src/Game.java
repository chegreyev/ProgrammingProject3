import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;

public class Game extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        // WELCOME PAGE AND START THE GAME
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
            try {
                Map map = new Map("/Users/ChD/IdeaProjects/project/src/map1.txt");
                Player player = new MyPlayer(map);
                Food food = new Food(map, player);
                map.setPadding(new Insets(50, 50, 50, 50));


                // MOVING THE BALL
                map.setOnKeyPressed(key -> {
                    try {

                        if(food.getNumOfCircles() == 0){
                            Scene ending_scene = new Scene( bp ,410 , 410);
                            primaryStage.setScene(ending_scene);
                            primaryStage.setTitle("ENDGAME");
                        }


                        // LEFT
                        if (key.getCode().equals(KeyCode.LEFT)) {
                            if (map.getMap()[player.getPosition().getX() - 1][player.getPosition().getY()] == 1
                                    || player.getPosition().getX() > map.getSize()
                                    || player.getPosition().getY() > map.getSize())
                                System.out.println("You shall not pass!!!");
                            else
                                player.moveLeft();
                        }
                        // RIGHT
                        else if (key.getCode().equals(KeyCode.RIGHT)) {
                            if (map.getMap()[player.getPosition().getX() + 1][player.getPosition().getY()] == 1
                                    || player.getPosition().getX() > map.getSize()
                                    || player.getPosition().getY() > map.getSize())
                                System.out.println("You shall not pass!!!");
                            else
                                player.moveRight();
                        }
                        // UP
                        else if (key.getCode().equals(KeyCode.UP)) { // If Up key pressed we check whether the wall on the next cell
                            if (map.getMap()[player.getPosition().getX()][player.getPosition().getY() - 1] == 1
                                    || player.getPosition().getX() > map.getSize()
                                    || player.getPosition().getY() > map.getSize())
                                System.out.println("You shall not pass!!!");
                            else
                                player.moveUp();
                        }
                        // DOWN
                        else if (key.getCode().equals(KeyCode.DOWN)) { // If Down key pressed we check whether the wall on the next cell
                            if (map.getMap()[player.getPosition().getX()][player.getPosition().getY() + 1] == 1
                                    || player.getPosition().getX() > map.getSize()
                                    || player.getPosition().getY() > map.getSize())
                                System.out.println("You shall not pass!!!");
                            else
                                player.moveDown();
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println("You shall not pass!!!");
                    }
                });

                Scene scene = new Scene(map, 410, 410);
                primaryStage.setScene(scene);
                map.requestFocus();



            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        Scene sc = new Scene(borderPane , 410 , 410);
        primaryStage.setScene(sc);
        primaryStage.setTitle("PACMAN");
        primaryStage.show();
        System.out.println("The game has started , Good game ... ");



    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
