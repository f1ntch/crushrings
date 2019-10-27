package crushrings.view;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 13-02-2019
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

// dit schrem zal zich zelf toonen wanneer game over

public class GameOverView extends BorderPane {

    private Label lblGameOver;
    private Button btnOK;

    public GameOverView() {
        initializeNodes();
        layoutNodes();
    }

    public Label getLblGameOver() {
        return lblGameOver;
    }
    public Button getBtnOK() {
        return btnOK;
    }



    private void initializeNodes() {
        lblGameOver = new Label();
        btnOK = new Button("OK");
    }



    private void layoutNodes() {
        lblGameOver.setId("lblGameOver");
        lblGameOver.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(lblGameOver, Pos.CENTER);
        setTop(lblGameOver);

        btnOK.setId("btnGameOver");
        btnOK.setMinWidth(90);
        btnOK.setMnemonicParsing(false);
        btnOK.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(btnOK, Pos.CENTER);
        setBottom(btnOK);

        setId("gameOver");
        setPrefWidth(400.0);
        setPrefHeight(150.0);
        setPadding(new Insets(24));

        //haalt style uit css
        getStylesheets().addAll(this.getClass().getResource("css/style.css").toExternalForm());
    }
}
