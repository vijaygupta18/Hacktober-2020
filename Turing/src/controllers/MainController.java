package controllers;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.swing.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
/*
    Author: @RockLee444
 */
public class MainController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField inputField;
    @FXML
    private Button startButton;
    @FXML
    private Button resetButton;

    private Polygon head;
    private ArrayList<StackPane> stackPaneList;
    private ArrayList<String> animationValue;
    private ArrayList<Integer> animationOrder;
    private String message;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stackPaneList = new ArrayList<>();
        animationOrder = new ArrayList<>();
        animationValue = new ArrayList<>();
        message = "";
        startButton.setDisable(false);
        resetButton.setDisable(true);
    }

    @FXML
    public void verifyInput(ActionEvent event){
        startButton.setDisable(true);
        resetButton.setDisable(false);
        String inputText = inputField.getText() + "B";
        String output = "";
        String[] inputArray = inputText.split("");
        String[] outputArray = inputArray.clone();
        int counter = 0, animationCounter = 0, currentState = 0, finalState = 5, hasSpace = 0;
        boolean finished = false;
        animationOrder.add(0);
        while(!finished) {
            String data = "";
            if (counter < outputArray.length) {
                animationCounter = counter;
                data = outputArray[counter];
            }
            if (!data.equals(" ")) {
                switch (currentState) {
                    case 0:
                        if (data.equals("0")) {
                            currentState = 1;
                            outputArray[counter] = "" + 0;
                            counter++;
                        } else if (data.equals("1")) {
                            currentState = 4;
                            outputArray[counter] = "" + 1;
                            counter++;
                        } else if (data.equals("B")) {
                            currentState = 5;
                            outputArray[counter] = "B";
                            counter++;
                        } else {
                            currentState = -1;
                        }
                        break;

                    case 1:
                        if (data.equals("0")) {
                            currentState = 2;
                            outputArray[counter] = "" + 1;
                            counter--;
                            if(hasSpace>0){
                                for(int i=0;i<hasSpace;i++){
                                    counter--;
                                }
                                hasSpace = 0;
                            }
                        } else if (data.equals("1")) {
                            currentState = 4;
                            outputArray[counter] = "" + 1;
                            counter++;
                        } else if (data.equals("B")) {
                            currentState = 5;
                            outputArray[counter] = "B";
                            counter++;
                        } else {
                            currentState = -1;
                        }
                        break;

                    case 2:
                        if (data.equals("0")) {
                            currentState = 3;
                            outputArray[counter] = "" + 1;
                            counter++;
                        } else if (data.equals("1")) {
                            currentState = 3;
                            outputArray[counter] = "" + 0;
                            counter++;
                        } else {
                            currentState = -1;
                        }
                        break;

                    case 3:
                        if (data.equals("0")) {
                            currentState = 0;
                            outputArray[counter] = "" + 0;
                            counter++;
                        } else if (data.equals("1")) {
                            currentState = 0;
                            outputArray[counter] = "" + 1;
                            counter++;
                        } else {
                            currentState = -1;
                        }
                        break;

                    case 4:
                        if (data.equals("0")) {
                            currentState = 1;
                            outputArray[counter] = "" + 0;
                            counter++;
                        } else if (data.equals("1")) {
                            currentState = 2;
                            outputArray[counter] = "" + 0;
                            counter--;
                            if(hasSpace > 0){
                                for(int i=0;i<hasSpace;i++){
                                    counter--;
                                }
                                hasSpace = 0;
                            }
                        } else if (data.equals("B")) {
                            currentState = 5;
                            outputArray[counter] = "B";
                            counter++;
                        } else {
                            currentState = -1;
                        }
                        break;

                    case 5:
                        finished = true;
                        break;

                    default:
                        currentState = -1;
                        finished = true;
                        break;
                }
                if (currentState >= 0) {
                    if (!finished) {
                        animationOrder.add(animationCounter);
                        animationValue.add(outputArray[animationCounter]);
                    }
                }
                hasSpace = 0;
            } else {
                counter++;
                hasSpace++;
            }
        }

        for(int i=0;i<outputArray.length;i++){
            output += outputArray[i];
        }
        if(currentState>=0){
            String inputTextNoEmpty = inputText.split("B")[0];
            String outputNoEmpty = output.split("B")[0];
            message = "The input '" + inputTextNoEmpty + "' is correct.\n" +
                    "The output is: '" + outputNoEmpty + "'\n" +
                    "The result has been copied to the clipboard.";
            generateVisualNodes(inputText);
            moveHead(animationOrder,animationValue);
            copyToClipboard(outputNoEmpty);
        } else {
            String inputTextNoEmpty = inputText.split("B")[0];
            message = "The input '" + inputTextNoEmpty + "' contains non-valid symbols.";
            showAlert(1,"ERROR", message);
            restartProgram();
        }
    }

    public void generateVisualNodes(String input){
        int layoutX = -29, layoutY = 254, counter = 0;
        double polygonPointLeft[] = {1, 310};
        double polygonPointRight[] = {25, 310};
        double polygonPointTop[] = {12.5, 290};
        StackPane stackPane;
        Text text;
        Rectangle rectangle;
        Rectangle baseRectangle = null;
        head = new Polygon( polygonPointLeft[0],polygonPointLeft[1],
                                    polygonPointRight[0],polygonPointRight[1],
                                    polygonPointTop[0],polygonPointTop[1]);
        head.setFill(Color.BLACK);
        head.setStroke(Color.WHITE);
        head.setStrokeWidth(2);
        head.setStrokeType(StrokeType.INSIDE);
        anchorPane.getChildren().add(head);

        String inputArray[] = input.split("");

        for(int i=0;i<input.length();i++){
            stackPane = new StackPane();

            text = new Text(inputArray[i]);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Arial"));

            rectangle = new Rectangle(30,30, Color.BLACK);
            rectangle.setStroke(Color.WHITE);
            rectangle.setStrokeType(StrokeType.INSIDE);
            rectangle.setStrokeWidth(2);

            stackPane.setLayoutX(layoutX + 29);
            stackPane.setLayoutY(layoutY);
            layoutX += 29;

            stackPane.getChildren().addAll(rectangle,text);
            stackPaneList.add(stackPane);

            anchorPane.getChildren().add(stackPane);
        }
    }

    public void moveHead(ArrayList<Integer> order, ArrayList<String> valuesArray){
        SequentialTransition sequentialTransition = new SequentialTransition();
        int previousPosition,newPosition, counter= 0;
        double fromX,toX;
        for(int i=0;i<order.size();i++){
            if( (i+1) < order.size()){
                previousPosition = order.get(i);
                newPosition = order.get(i + 1);
                fromX = stackPaneList.get(previousPosition).getLayoutX();
                toX = stackPaneList.get(newPosition).getLayoutX();


                TranslateTransition translateTransition = new TranslateTransition();
                translateTransition.setFromX(fromX);
                translateTransition.setToX(toX);
                translateTransition.setNode(head);
                translateTransition.setDuration(Duration.seconds(1.5));
                translateTransition.setAutoReverse(false);

                FillTransition fillTransition = new FillTransition();
                fillTransition.setFromValue(Color.BLACK);
                fillTransition.setToValue(Color.RED);
                fillTransition.setShape( ( (Rectangle) stackPaneList.get(newPosition).getChildren().get(0)) );
                fillTransition.setDuration(Duration.seconds(1));
                fillTransition.setAutoReverse(false);

                FillTransition fillTransition2 = new FillTransition();
                fillTransition2.setFromValue(Color.RED);
                fillTransition2.setToValue(Color.BLACK);
                fillTransition2.setShape( ( (Rectangle) stackPaneList.get(newPosition).getChildren().get(0)) );
                fillTransition2.setDuration(Duration.seconds(1));
                fillTransition2.setAutoReverse(false);

                int finalNewPosition = newPosition;
                int finalI = i;
                if(counter == 0){
                    fillTransition2.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Text text =(Text) stackPaneList.get(finalNewPosition).getChildren().get(1);
                            text.setText(valuesArray.get(finalI));
                        }
                    });
                } else {
                    counter = 1;
                }
                sequentialTransition.getChildren().addAll(translateTransition,fillTransition,fillTransition2);

            }
        }
        sequentialTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showAlert(0,"HECHO", message);
            }
        });
        sequentialTransition.play();
    }

    private void showAlert(int alertType,String title, String contentText){
        Alert alert = null;
        if(alertType == 0) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
        }
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText(null);
        alert.show();

    }

    @FXML
    public void restartProgram(){
        anchorPane.getChildren().removeAll(stackPaneList);
        anchorPane.getChildren().remove(head);
        inputField.setText("");
        stackPaneList = new ArrayList<>();
        animationOrder = new ArrayList<>();
        animationValue = new ArrayList<>();
        startButton.setDisable(false);
        resetButton.setDisable(true);
    }

    public void copyToClipboard(String result){
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.put(DataFormat.PLAIN_TEXT, result);;
        clipboard.setContent(clipboardContent);
    }

}
