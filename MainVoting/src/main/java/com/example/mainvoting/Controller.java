package com.example.mainvoting;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class Controller {
    @FXML
    private AnchorPane panel;



    public void Electionbtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("ElectionTable.fxml"));
        panel.getChildren().removeAll();
        panel.getChildren().setAll(fxmlLoader);
        panel.getChildren().stream().forEach((child) -> {
            panel.setBottomAnchor(child,0.0);
            panel.setTopAnchor(child,0.0);
            panel.setLeftAnchor(child,0.0);
            panel.setRightAnchor(child,0.0);
        });
    }

    public void voterbtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("voter.fxml"));
        panel.getChildren().removeAll();
        panel.getChildren().setAll(fxmlLoader);
        panel.getChildren().stream().forEach((child) -> {
            panel.setBottomAnchor(child,0.0);
            panel.setTopAnchor(child,0.0);
            panel.setLeftAnchor(child,0.0);
            panel.setRightAnchor(child,0.0);
        });
    }

    public void candidatebtn(javafx.event.ActionEvent actionEvent)  throws IOException{
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("candidate.fxml"));
        panel.getChildren().removeAll();
        panel.getChildren().setAll(fxmlLoader);
        panel.getChildren().stream().forEach((child) -> {
            panel.setBottomAnchor(child,0.0);
            panel.setTopAnchor(child,0.0);
            panel.setLeftAnchor(child,0.0);
            panel.setRightAnchor(child,0.0);
        });
    }

    public void Logoutbtn(javafx.event.ActionEvent actionEvent) {
    }

    public void votepage(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("VotePage.fxml"));
        panel.getChildren().removeAll();
        panel.getChildren().setAll(fxmlLoader);
        panel.getChildren().stream().forEach((child) -> {
            panel.setBottomAnchor(child,0.0);
            panel.setTopAnchor(child,0.0);
            panel.setLeftAnchor(child,0.0);
            panel.setRightAnchor(child,0.0);
        });
    }
}
