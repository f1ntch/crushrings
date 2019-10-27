package crushrings.presenter;

import com.sun.org.apache.xpath.internal.operations.And;
import crushrings.model.CrushRings;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import crushrings.view.*;

public class CrushRingsPresenter {

    //root klasse van model deze wordt door gegeven aan de constructor van CrushRingsPresenter
    //lijn 29
    private CrushRings model;

    //root klasse van view deze wordt door gegeven aan de constructor van CrushRingsPresenter
    //lijn 29
    private CrushRingsView view;

    // de verschillende views die in de presenter worden weer gegevens
    private PlayView playView;
    private ScoreView scoreView;
    private HelpView helpView;

    // de verschillende pesenters die in de presenter worden weer gegevens
    private PlayPresenter playPresenter;
    private ScorePresenter scorePresenter;
    private HelpPresenter helpPresenter;

    public CrushRingsPresenter(CrushRings model, CrushRingsView view) {
        this.model = model;
        this.view = view;

        playView = new PlayView(view);
        scoreView = new ScoreView(view);
        helpView = new HelpView(view);

        playPresenter = new PlayPresenter(model, playView, scoreView);
        scorePresenter = new ScorePresenter(model, scoreView);
        helpPresenter = new HelpPresenter(helpView);

        updateView();
        addEventHandlers();
    }

    public void updateView() {

    }

    // verbind de view nodes aan handles
    private void addEventHandlers() {
        // Als je op spelen knop durkt wordt er een nieuwe stage gemaakt
        // Hierin kan de speler zijn naam ingeven

        view.getBtnStart().setOnAction(event -> {
            // show the InputView on top of PlayView
            InputView inputView = new InputView();
            InputPresenter inputPresenter = new InputPresenter(inputView);

            // Nieuwe stage vraagt de naam van despeler

            Stage inputStage = new Stage();
            inputStage.initOwner(view.getScene().getWindow());
            inputStage.initModality(Modality.APPLICATION_MODAL);
            inputStage.setTitle("Naam speler");
            Scene scene = new Scene(inputView);
            inputStage.setScene(scene);
            inputStage.showAndWait();

            // schakel over naar HelpView
            if (inputPresenter.isOK()) {
                // model called restart() welke een leeg board instantieerd
                model.restart();

                //al de views worden geupdate ook de childeren
                playPresenter.updateView();
                // het spel scherm playView wordt getoont
                view.getScene().setRoot(playView);
                //  setRoot() will change it from RingsGameView to PlayView.
            }
        });

        view.getBtnScore().setOnAction(event -> {
            // schakel over naar ScoreView
            scorePresenter.updateView();
            view.getScene().setRoot(scoreView);
        });

        view.getBtnHelp().setOnAction(event -> {
            // schakel over naar HelpView
            view.getScene().setRoot(helpView);
        });

        view.getBtnClose().setOnAction(event -> {
            // close
            view.getScene().getWindow().hide();
        });
    }

    public void addWindowEventHandlers() {


    }
}
