package crushrings.view;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 13-02-2019
 */

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class CrushRingsView extends GridPane {

    // start scherm als je het spel opstart
    private Button btnStart;
    private Button btnScore;
    private Button btnHelp;
    private Button btnClose;


    public CrushRingsView() {
        initializeNodes();
        layoutNodes();
    }



    public Button getBtnStart() {
        return btnStart;
    }

    public Button getBtnScore() {
        return btnScore;
    }

    public Button getBtnHelp() {
        return btnHelp;
    }

    public Button getBtnClose() {
        return btnClose;
    }

    private void initializeNodes() {
        btnStart = new Button("Spelen");
        btnScore = new Button("Score");
        btnHelp = new Button("Hulp");
        btnClose = new Button("Afsluiten");
    }

    private void layoutNodes() {
        // start button
        add(btnStart, 0, 1);
        setValignment(btnStart, VPos.CENTER);
        setHalignment(btnStart, HPos.CENTER);
        btnStart.setId("menuButton");
        btnStart.setAlignment(Pos.CENTER);
        btnStart.setMinWidth(400.0);
        btnStart.setMinHeight(60.0);

        btnStart.setMnemonicParsing(false);

        // score button
        add(btnScore, 0, 2);
        setValignment(btnScore, VPos.CENTER);
        setHalignment(btnScore, HPos.CENTER);
        btnScore.setId("menuButton");
        btnScore.setAlignment(Pos.CENTER);
        btnScore.setMinWidth(400.0);
        btnScore.setMinHeight(60);
        btnScore.setMnemonicParsing(false);

        // start button
        add(btnHelp, 0, 3);
        setValignment(btnHelp, VPos.CENTER);
        setHalignment(btnHelp, HPos.CENTER);
        btnHelp.setId("menuButton");
        btnHelp.setAlignment(Pos.CENTER);
        btnHelp.setMinWidth(400.0);
        btnHelp.setMinHeight(60);
        btnHelp.setMnemonicParsing(false);

        // start button
        add(btnClose, 0, 4);
        setValignment(btnClose, VPos.CENTER);
        setHalignment(btnClose, HPos.CENTER);
        btnClose.setId("menuButton");
        btnClose.setAlignment(Pos.CENTER);
        btnClose.setMinWidth(400.0);
        btnClose.setMinHeight(60);
        btnClose.setMnemonicParsing(false);

        // colum en row contraints
        setPrefSize(800, 600);
        getColumnConstraints().add(new ColumnConstraints(10.0, 100.0, Double.POSITIVE_INFINITY, Priority.SOMETIMES, HPos.CENTER, false));
        getRowConstraints().add(new RowConstraints(10.0, 30.0, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, false));
        getRowConstraints().add(new RowConstraints(10.0, 30.0, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, false));
        getRowConstraints().add(new RowConstraints(10.0, 30.0, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, false));
        getRowConstraints().add(new RowConstraints(10.0, 30.0, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, false));
        getRowConstraints().add(new RowConstraints(10.0, 30.0, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, false));
        getRowConstraints().add(new RowConstraints(10.0, 30.0, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, false));

        setId("menu");
        // achtergrond
        getStylesheets().addAll(this.getClass().getResource("css/style.css").toExternalForm());
    }
}
