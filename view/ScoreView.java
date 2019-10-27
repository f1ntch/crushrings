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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;


// view for the score screen
public class ScoreView extends BorderPane {

    private Button btnOK;
    private TableView tblScore;
    private TableColumn player;
    private TableColumn score;

    private Parent parentNode;

    public ScoreView(Parent parentNode) {
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

    public TableView getTblScore() {
        return tblScore;
    }

    public TableColumn getPlayer() {
        return player;
    }

    public TableColumn getScore() {
        return score;
    }

    private void initializeNodes() {
        btnOK = new Button("OK");
        tblScore = new TableView();
        player = new TableColumn();
        score = new TableColumn();
    }

    private void layoutNodes() {
        setCenter(tblScore);
        // inplements style.css
        tblScore.setId("tblScore");
        tblScore.setPrefSize(450.0, 400.0);
        BorderPane.setAlignment(tblScore, Pos.CENTER);
        player.setPrefWidth(310);
        player.setText("Player");
        player.setStyle("-fx-text-fill: white");
        tblScore.getColumns().add(player);

        score.setPrefWidth(170);
        score.setText("Score");
        score.setStyle("-fx-text-fill: white");
        tblScore.getColumns().add(score);
        BorderPane.setMargin(tblScore, new Insets(24));
        // inplements style.css
        setBottom(btnOK);
        btnOK.setId("btnOK");
        btnOK.setMinWidth(200.0);

        btnOK.setMnemonicParsing(false);
        BorderPane.setAlignment(btnOK, Pos.CENTER);
        BorderPane.setMargin(btnOK, new Insets(0, 24, 24, 24));
        // inplements style.css
        setId("score");
        setPrefSize(800, 600);
        getStylesheets().addAll(this.getClass().getResource("css/style.css").toExternalForm());
    }
}
