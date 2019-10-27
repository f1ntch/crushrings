package crushrings.presenter;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 13-02-2019
 */

import crushrings.model.Settings;
import crushrings.view.InputView;
import javafx.scene.media.AudioClip;

public class InputPresenter {
    private InputView   view;
    private boolean     bOK;

    public InputPresenter(InputView view) {
        this.view = view;
        updateView();
        addEventHandlers();
    }

    //todo vragen
    public boolean isOK() {
        return bOK;
    }

    private void updateView() {

    }

    private void addEventHandlers() {
        view.getBtnOK().setOnAction(event -> {
            clickSound();
            bOK = true;
            Settings.playerName = view.getTxtPlayer().getText();

            view.getScene().getWindow().hide();
        });

        view.getBtnCancel().setOnAction(event -> {
            clickSound();
            bOK = false;
            view.getScene().getWindow().hide();
        });
    }

    public void addWindowEventHandlers() {

    }


    // play click sound
    public void clickSound() {

        AudioClip note = new
                AudioClip(this.getClass().getResource("xclick.wav").toString());
        note.play();
    }


}
