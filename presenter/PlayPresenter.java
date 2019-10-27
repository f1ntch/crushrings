package crushrings.presenter;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 13-02-2019
 */

import crushrings.model.CrushRings;
import crushrings.view.PlayView;
import crushrings.view.ScoreView;

public class PlayPresenter {
    private CrushRings model;
    private PlayView view;
    private ScoreView scoreView;

    private StatePresenter statePresenter;
    private BoardPresenter boardPresenter;
    private SpawnPresenter spawnPresenter;

    public PlayPresenter(CrushRings model, PlayView view, ScoreView scoreView) {
        this.model = model;
        this.view = view;
        this.scoreView = scoreView;
        //todo ?
        statePresenter = new StatePresenter(model, view.getStateView());
        boardPresenter = new BoardPresenter(model, view.getBoardView(), view.getStateView());
        spawnPresenter = new SpawnPresenter(model, view.getSpawnView(), scoreView);

        updateView();
        addEventHandlers();
    }

    public void updateView() {
        // Al deze cilderen wordt geupdate als updateView wil doen !
        statePresenter.updateView();
        boardPresenter.updateView();
        spawnPresenter.updateView();
    }

    private void addEventHandlers() {

    }

}
