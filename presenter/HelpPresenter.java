package crushrings.presenter;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 13-02-2019
 */

import crushrings.view.HelpView;
import javafx.scene.media.AudioClip;

public class HelpPresenter {
    private HelpView view;

    public HelpPresenter(HelpView view) {
        this.view = view;
        updateView();
        addEventHandlers();
    }

    private void updateView() {

    }

    private void addEventHandlers() {
        view.getBtnOK().setOnAction(event -> {
            clickSound();
            // switch to the parent node
            view.getScene().setRoot(view.getParentNode());
        });
    }

    // play click sound
    public void clickSound() {

        AudioClip note = new
                AudioClip(this.getClass().getResource("xclick.wav").toString());
        note.play();
    }

}
