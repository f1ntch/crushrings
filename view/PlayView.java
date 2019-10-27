package crushrings.view;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 13-02-2019
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

//view for when you play the game
public class PlayView extends BorderPane {

    private Parent parentNode;

    // childeren van playview
    private StateView       stateView;
    private BoardView       boardView;
    private SpawnView       spawnView;


    //parrent view
    public PlayView(Parent parentNode) {
        this.parentNode = parentNode;

        stateView = new StateView(parentNode);
        //child verwijst ander child
        boardView = new BoardView();
        spawnView = new SpawnView();
        layoutNodes();
    }

    public StateView getStateView() {
        return stateView;
    }
    public BoardView getBoardView() {
        return boardView;
    }
    public SpawnView getSpawnView() {
        return spawnView;
    }

    private void layoutNodes() {
        // indeling van speel scherm
        // top
        setTop(stateView);
        BorderPane.setAlignment(stateView, Pos.CENTER);
        setMargin(stateView, new Insets(0, 0, 24, 0));

        // center
        setCenter(boardView);
        BorderPane.setAlignment(boardView, Pos.CENTER);

        // bottom
        setBottom(spawnView);
        BorderPane.setAlignment(spawnView, Pos.CENTER);
        BorderPane.setMargin(spawnView, new Insets(24, 0, 0, 0));

        // achergrond van het spel
        setId("game");
        this.setPadding(new Insets(24));
        getStylesheets().addAll(this.getClass().getResource("css/style.css").toExternalForm());
    }
}
