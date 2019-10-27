package crushrings.view;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 13-02-2019
 */

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

// view class for the screen to enter the name before te game starts
public class InputView extends BorderPane {

    private Label       lblEnter;
    private TextField   txtPlayer;
    private Button      btnOK;
    private Button      btnCancel;

    public InputView() {
        initializeNodes();
        layoutNodes();
    }

    public Label getLblEnter() {
        return lblEnter;
    }

    public TextField getTxtPlayer() {
        return txtPlayer;
    }

    public Button getBtnOK() {
        return btnOK;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    //initialisation for all the labels en buttons and for the textfield to enter name
    private void initializeNodes() {
        lblEnter = new Label("Vul je naam in: ");
        lblEnter.setStyle("-fx-text-fill: white"); // ?
        txtPlayer = new TextField();
        btnOK = new Button("OK");
        btnCancel = new Button("Annuleren");
    }

    //the layout for the attributes
    private void layoutNodes() {
        // inplements style.css
        setId("input");


        // Gridpane nesten in borderpain
        GridPane panCenter = new GridPane();
        setTop(panCenter);
        BorderPane.setAlignment(panCenter, Pos.CENTER);
        panCenter.getColumnConstraints().add(new ColumnConstraints());
        panCenter.getColumnConstraints().add(new ColumnConstraints());
        panCenter.getRowConstraints().add(new RowConstraints());
        // inplements style.css
        lblEnter.setId("lblEnter");
        panCenter.add(lblEnter, 0, 0);
        // inplements style.css
        txtPlayer.setId("txtPlayer");
        panCenter.add(txtPlayer, 1, 0);

        BorderPane.setMargin(panCenter, new Insets(24));

        // Gridpane nesten in border pain  bottom
        GridPane panBottom = new GridPane();
        setBottom(panBottom);
        //p
        BorderPane.setAlignment(panBottom, Pos.CENTER);
        panBottom.getColumnConstraints().add(new ColumnConstraints(10, 100, Double.POSITIVE_INFINITY, Priority.SOMETIMES, HPos.CENTER, false));
        panBottom.getColumnConstraints().add(new ColumnConstraints(10, 100, Double.POSITIVE_INFINITY, Priority.SOMETIMES, HPos.CENTER, false));
        panBottom.getRowConstraints().add(new RowConstraints(10, 30, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, false));

        // inplements style.css
        btnOK.setId("btnInput");
        btnOK.setMinWidth(90);
        btnOK.setMnemonicParsing(false);
        GridPane.setHalignment(btnOK, HPos.CENTER);
        GridPane.setValignment(btnOK, VPos.CENTER);
        panBottom.add(btnOK, 0, 0);
        // inplements style.css
        btnCancel.setId("btnInput");
        btnCancel.setMinWidth(90);
        btnCancel.setMnemonicParsing(false);
        GridPane.setHalignment(btnCancel, HPos.CENTER);
        GridPane.setValignment(btnCancel, VPos.CENTER);
        panBottom.add(btnCancel, 1, 0);

        BorderPane.setMargin(panBottom, new Insets(0, 24, 24, 24));

        //haalt style uit css
        getStylesheets().addAll(this.getClass().getResource("css/style.css").toExternalForm());
    }
}
