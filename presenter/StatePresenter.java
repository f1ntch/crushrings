package crushrings.presenter;

import crushrings.model.CrushRings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import crushrings.model.Settings;
import crushrings.view.PauseView;
import crushrings.view.StateView;
import javafx.stage.WindowEvent;

import java.util.Optional;
//todo info W !
public class StatePresenter {
    private CrushRings model;
    private StateView view;

    public StatePresenter(CrushRings model, StateView view) {
        this.model = model;
        this.view = view;
        updateView();
        addEventHandlers();
    }

    public void updateView() {
        //view.getLblPlayer().setText(Settings.playerName);

        // Update de score
        view.getLblScore().setText(String.valueOf(Settings.gameScore));
    }

    private void addEventHandlers() {
        view.getBtnBack().setOnAction(event -> {
            Settings.playerName = "";
            Settings.gameScore = 0;

            // restart game
            model.restart();

            // switch to the parent node startscherm
            view.getScene().setRoot(view.getParentNode());
        });

        view.getBtnPause().setOnAction(event -> {
            PauseView pauseView = new PauseView();

            Scene scene = new Scene(pauseView);
            //als je op pauze drukt
            pauseView.addEventFilter(MouseEvent.MOUSE_PRESSED, e ->  {
                ((Node) (e.getSource())).getScene().getWindow().hide();
            });

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            // window buttons verdwijen
//            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        });
    }

}
