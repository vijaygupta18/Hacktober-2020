package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.StageStyle;
import models.Hilo;
import models.Leaderboard;

import java.net.URL;
import java.sql.*;
import java.util.*;

/*
    Author: @RockLee444
 */
public class MainController implements Initializable, Observer {
    @FXML
    private Circle s1RedCircle;

    @FXML
    private Circle s1YellowCircle;

    @FXML
    private Circle s1GreenCircle;

    @FXML
    private Circle s2RedCircle;

    @FXML
    private Circle s2YellowCircle;

    @FXML
    private Circle s2GreenCircle;

    @FXML
    private TableView<Leaderboard> leaderboardTable;

    @FXML
    private Label leaderboardLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private Button buttonStart;

    private int score;

    private Connection conn;
    private Hilo hilo1;
    private Hilo hilo2;

    private Thread running1;
    private Thread running2;

    private ObservableList<Leaderboard> leaderboardList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        score = 0;
        this.scoreLabel.setText("Score: " + score);
        fillLeaderboard();
    }

    public void fillLeaderboard(){
        leaderboardList.add(new Leaderboard("RockLee444",30));
        leaderboardList.add(new Leaderboard("RockLee333",20));
        leaderboardList.add(new Leaderboard("RockLee222",1));
        this.leaderboardTable.getItems().addAll(leaderboardList);
    }


    @FXML
    void howToPlay(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("How to play?");
        alert.setContentText("Welcome!\n" +
                "Use the keys 'Z' & 'M' to control the left and right stoplights!.\n" +
                "Make sure to hit the right key when any of the stoplights gets the green light!\n" +
                "Last as long as you can and try to achieve the best score!");
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText(null);
        alert.showAndWait();

    }

    @FXML
    void startGame(ActionEvent event) {
        score = 0;
        this.scoreLabel.setText("Score: " + score);
        buttonStart.setDisable(true);

        Hilo leftStoplight = new Hilo(true,"LEFT");
        Hilo rightStoplight = new Hilo(false,"RIGHT");

        hilo1 = leftStoplight;
        hilo2 = rightStoplight;

        leftStoplight.addObserver(this);
        rightStoplight.addObserver(this);

        Thread leftThread = new Thread(leftStoplight);
        Thread rightThread = new Thread(rightStoplight);

        leftThread.setDaemon(false);
        rightThread.setDaemon(false);

        running1 = leftThread;
        running2 = rightThread;

        leftThread.start();
        rightThread.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        int turn;
        turn = Integer.parseInt(arg.toString());
        Hilo changed = (Hilo) o;
        if(turn == -1){
            gameLost();
        } else {
            if (changed.toString().equals("LEFT")) {
                switch (turn) {
                    case 1:
                        s1RedCircle.setFill(Color.RED);
                        s1YellowCircle.setFill(Color.BLACK);
                        s1GreenCircle.setFill(Color.BLACK);
                        break;

                    case 2:
                        s1RedCircle.setFill(Color.BLACK);
                        s1YellowCircle.setFill(Color.YELLOW);
                        s1GreenCircle.setFill(Color.BLACK);
                        break;

                    case 3:
                        s1RedCircle.setFill(Color.BLACK);
                        s1YellowCircle.setFill(Color.BLACK);
                        s1GreenCircle.setFill(Color.GREEN);
                        break;
                }
            } else {
                switch (turn) {
                    case 1:
                        s2RedCircle.setFill(Color.RED);
                        s2YellowCircle.setFill(Color.BLACK);
                        s2GreenCircle.setFill(Color.BLACK);
                        break;

                    case 2:
                        s2RedCircle.setFill(Color.BLACK);
                        s2YellowCircle.setFill(Color.YELLOW);
                        s2GreenCircle.setFill(Color.BLACK);
                        break;

                    case 3:
                        s2RedCircle.setFill(Color.BLACK);
                        s2YellowCircle.setFill(Color.BLACK);
                        s2GreenCircle.setFill(Color.GREEN);
                        break;
                }
            }
        }

    }

    @FXML
    public void keyPressed(KeyEvent event){
        boolean failed = false;
        String code = event.getCode().toString();
        switch(code){
            case "Z":
                if(hilo1.updateScore()){
                    score++;
                } else {
                    failed = true;
                }
            break;

            case "M":
                if(hilo2.updateScore()){
                    score++;
                } else {
                    failed = true;
                }
            break;

            default:
            break;
        }
        if(failed){
            gameLost();
        } else {
            this.scoreLabel.setText("Score: " + score);
        }

    }

    public void gameLost(){
        hilo1.gameLost();
        hilo2.gameLost();

        hilo1 = null;
        hilo2 = null;

        running1 = null;
        running2 = null;
        buttonStart.setDisable(false);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                TextInputDialog dialog = new TextInputDialog("Your name");
                dialog.setTitle("GAME FINISHED");
                dialog.setHeaderText("Your score was: " + score);
                dialog.setContentText("Type your name to save it:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    saveScore(result.get(),score);
                }

                scoreLabel.setText("Score: 0");
                s1RedCircle.setFill(Color.WHITE);
                s1YellowCircle.setFill(Color.WHITE);
                s1GreenCircle.setFill(Color.WHITE);
                s2RedCircle.setFill(Color.WHITE);
                s2YellowCircle.setFill(Color.WHITE);
                s2GreenCircle.setFill(Color.WHITE);

            }
        });

    }

    public void saveScore(String name,int score){
        this.leaderboardList.add(new Leaderboard(name,score));
        leaderboardList.sort(new Comparator<Leaderboard>() {
            @Override
            public int compare(Leaderboard o1, Leaderboard o2) {
                if(o1.getScore() < o2.getScore()){
                    return  1;
                } else if(o1.getScore() > o2.getScore()){
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        this.leaderboardTable.getItems().clear();
        this.leaderboardTable.getItems().addAll(leaderboardList);
    }
}
