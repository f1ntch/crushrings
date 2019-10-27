package crushrings.view;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 13-02-2019
 */

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

// deze klasse geeft de score weer en de naviatie knoppen
public class StateView extends GridPane {

    private Label lblPlayer;
    private Label lblScore;
    private Button btnPause;
    private Button btnBack;

    private Parent parentNode;
  //The base class for all nodes that have children in the scene graph.
    public StateView(Parent parentNode) {

        this.parentNode = parentNode;

        initializeNodes();
        layoutNodes();
    }

    public Parent getParentNode() {
        return parentNode;
    }

    public Label getLblPlayer() {
        return lblPlayer;
    }

    public Label getLblScore() {
        return lblScore;
    }

    public Button getBtnPause() {
        return btnPause;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    //initialize for label and Button for the game screen
    private void initializeNodes() {
        lblPlayer = new Label();
        lblScore = new Label("0");
        btnPause = new Button();
        btnBack = new Button();
    }

    //layout for the label and Button for the game screen
    private void layoutNodes() {
        setAlignment(Pos.CENTER);
        getColumnConstraints().add(new ColumnConstraints(90, 90, 90, Priority.SOMETIMES, HPos.CENTER, false));
        getColumnConstraints().add(new ColumnConstraints(120, 120, 120, Priority.SOMETIMES, HPos.CENTER, false));
        getColumnConstraints().add(new ColumnConstraints(90, 90, 90, Priority.SOMETIMES, HPos.CENTER, false));
        getRowConstraints().add(new RowConstraints(74, 50, 50, Priority.SOMETIMES, VPos.CENTER, false));
        getRowConstraints().add(new RowConstraints(50, 50, 50, Priority.SOMETIMES, VPos.CENTER, false));


//        lblPlayer.setId("lblScore");
//        lblPlayer.setStyle("-fx-text-fill: white");
//        lblPlayer.setAlignment(Pos.CENTER);
//        lblPlayer.setMnemonicParsing(false);
//        lblPlayer.setMinWidth(200);
//        GridPane.setHalignment(lblPlayer, HPos.CENTER);
//        GridPane.setValignment(lblPlayer, VPos.TOP);
//        add(lblPlayer, 0, 1, 3, 1);

        // inplements style.css
        btnBack.setId("btnBack");
        btnBack.setAlignment(Pos.CENTER);
        btnBack.setMnemonicParsing(false);
        btnBack.setMinWidth(30);
        btnBack.setMaxHeight(10);
        GridPane.setHalignment(btnBack, HPos.CENTER);
        GridPane.setValignment(btnBack, VPos.CENTER);
        add(btnBack, 0, 2);
        // inplements style.css
        lblScore.setId("lblScore");
        lblScore.setStyle("-fx-text-fill: white");
        lblScore.setAlignment(Pos.CENTER);
        lblScore.setMnemonicParsing(false);
        lblScore.setMinWidth(90);
        GridPane.setHalignment(lblScore, HPos.CENTER);
        GridPane.setValignment(lblScore, VPos.CENTER);
        add(lblScore, 1, 2);
        // inplements style.css
        btnPause.setId("btnPause");
        btnPause.setAlignment(Pos.CENTER);
        btnPause.setMnemonicParsing(false);
        btnBack.setMinWidth(30);
        btnBack.setMaxHeight(10);
        GridPane.setHalignment(btnPause, HPos.CENTER);
        GridPane.setValignment(btnPause, VPos.CENTER);
        add(btnPause, 2, 2);

        getStylesheets().addAll(this.getClass().getResource("css/style.css").toExternalForm());
    }
}
