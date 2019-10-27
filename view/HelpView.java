package crushrings.view;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 13-02-2019
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

// this view is for the help screen
public class HelpView extends BorderPane {

    private Text txtHelp;
    private Button btnOK;

    private Parent parentNode;

    public HelpView(Parent parentNode) {
        this.parentNode = parentNode;

        initializeNodes();
        layoutNodes();
    }

    public Parent getParentNode() {
        return parentNode;
    }

    public Button getBtnOK() {
        return btnOK;
    }

    private void initializeNodes() {
        txtHelp = new Text();
        txtHelp = new Text();
        btnOK = new Button("OK");
    }

    //layout for the Text and Button for the help screen
    private void layoutNodes() {
        // center
        setCenter(txtHelp);
        txtHelp.setId("txtHelp");
        txtHelp.setStrokeType(StrokeType.OUTSIDE);
        txtHelp.setStrokeWidth(0.0);
        txtHelp.setFill(Color.WHITE);
        txtHelp.setText("HOE TE SPELEN\n\n" +
                   "Plaats de gekleurde ringen op het spelbord\n\n"
                + "Ringen verdwijnen zodra een verticale, horizontale of diagonale lijn gevormd wordt\n van ringen in dezelfde kleur\n  " +
                "\n"
                + "Het spel is voorbij als er geen ruimte \n meer is om ringen te plaatsen\n  \n"
                + "Geen tijdslimiet  \n"

        );
        txtHelp.setWrappingWidth(550);
        txtHelp.setFont(new Font(18.0));
        BorderPane.setAlignment(txtHelp, Pos.TOP_CENTER);
        setMargin(txtHelp, new Insets(24, 24, 24, 24));

        // bottom
        setBottom(btnOK);
        btnOK.setId("btnOK");
        btnOK.setMinWidth(200.0);
        btnOK.setMnemonicParsing(false);
        BorderPane.setAlignment(btnOK, Pos.CENTER);
        setMargin(btnOK, new Insets(24, 24, 24, 24));

        // entire margin
        setId("help");
        setPrefSize(800, 600);
        getStylesheets().addAll(this.getClass().getResource("css/style.css").toExternalForm());
    }
}
