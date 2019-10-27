package crushrings.presenter;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 22-02-2019
 */


import crushrings.view.GameOverView;
import javafx.scene.media.AudioClip;

public class GameOverPresenter {
    private GameOverView view;

    public GameOverPresenter(GameOverView view) {
        this.view = view;
        updateView();
        addEventHandlers();
        addWindowEventHandlers();
    }

    private void updateView() {
    }

    private void addEventHandlers() {
        view.getBtnOK().setOnAction(e -> {
            scoreSound();
            view.getScene().getWindow().hide();


        });
    }

    public void addWindowEventHandlers() {

    }



    public void scoreSound() {

        AudioClip note = new
                AudioClip(this.getClass().getResource("xclick.wav").toString());
        note.play();
    }
}
