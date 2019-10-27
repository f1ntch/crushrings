package crushrings.presenter;


/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 13-02-2019
 */

import crushrings.model.CrushRings;
import crushrings.model.ScoreRecord;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import crushrings.view.ScoreView;
import javafx.scene.media.AudioClip;

public class ScorePresenter {
    private CrushRings model;
    private ScoreView view;

    public class ScoreTableViewRecord {
        private final SimpleStringProperty player; // theatre title
        private final SimpleIntegerProperty score; // seat count

        public ScoreTableViewRecord(String player, int score) {
            this.player = new SimpleStringProperty(player);
            this.score = new SimpleIntegerProperty(score);
        }

        public String getPlayer() {
            return player.get();
        }

        public SimpleStringProperty playerProperty() {
            return player;
        }

        public void setPlayer(String player) {
            this.player.set(player);
        }

        public int getScore() {
            return score.get();
        }

        public SimpleIntegerProperty scoreProperty() {
            return score;
        }

        public void setScore(int score) {
            this.score.set(score);
        }
    }


    public ScorePresenter(CrushRings model, ScoreView view) {
        this.model = model;
        this.view = view;
        updateView();
        addEventHandlers();
    }

    public void updateView() {
        ObservableList<ScoreTableViewRecord> list = FXCollections.observableArrayList();

        for (ScoreRecord item : model.getScore().getScoreList()) {
            list.add(new ScoreTableViewRecord(item.getPlayer(), item.getScore()));
        }

        view.getTblScore().setItems(list);
    }

    private void addEventHandlers() {
        view.getBtnOK().setOnAction(event -> {
            clickSound();
            // switch to the parent node ga terug naar het startmenu
            view.getScene().setRoot(view.getParentNode());
        });

        // deze handler wordt gecalled als scene wordt vervangen
        // voor het switchen tussen scenes
        view.sceneProperty().addListener((obs, oldScene, newScene) -> {
            updateView();
        });


        view.getPlayer().setCellValueFactory(new PropertyValueFactory<ScoreTableViewRecord, String>("player"));
        view.getScore().setCellValueFactory(new PropertyValueFactory<ScoreTableViewRecord, Integer>("score"));
    }

    // play click sound
    public void clickSound() {

        AudioClip note = new
                AudioClip(this.getClass().getResource("xclick.wav").toString());
        note.play();
    }
}

