package crushrings.presenter;

import crushrings.Log;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import javafx.util.Pair;
import crushrings.model.*;
import crushrings.view.BoardView;
import javafx.scene.image.ImageView;
import crushrings.view.StateView;
import crushrings.view.Utils;

import java.util.ArrayList;


public class BoardPresenter {
    private CrushRings model;
    private BoardView view;
    private StateView stateView;
    private int nCombo = 0;


    private final int[][] triples = {
            {0, 1, 2},  // horizonale lijn
            {3, 4, 5},  // horizonale lijn
            {6, 7, 8},  // horizonale lijn
            {0, 3, 6},  // verticale lijn
            {1, 4, 7},  // verticale lijn
            {2, 5, 8},  // verticale lijn
            {0, 4, 8},  // diagonale lijn
            {2, 4, 6}   // diagonale lijn
    };

    private final int COMBO_SCORE = 3;

    public BoardPresenter(CrushRings model, BoardView view, StateView stateView) {
        this.model = model;
        this.view = view;
        this.stateView = stateView;

        updateView();
        addEventHandlers();
    }

    public void updateView() {
        Log.debug("BoardPresenter > updateView() is called");


        //todo W
        // updateView-functie krijgt gegevens van model (bord) en geeft gegevens weer via weergave (BoardView).
        // imageView is een individuele weergave van Ring-model
        // BoardView bevat 9 imageView
        // SpawnView bevat 3 imageView
        if (model.getBoard() != null) {
            for (int i = 0; i < model.getBoard().BOARD_SIZE; i++) {
                Ring ring = model.getBoard().getRing(i);
                ImageView imageView = view.getImageViews()[i];
                //Haal uit utills the afbeelingen ?
                Utils.displayRing(ring, imageView, true);
            }
        }
    }

    //(SpawnView | DragDetected) => (BoardView | DragOver) => (BoardView | DragDropped) => (SpawnView | DragDone)
    private void addEventHandlers() {
        for (int i = 0; i < view.getImageViews().length; i++) {
            ImageView imageView = view.getImageViews()[i];
            imageView.setUserData(i);

            //DragOver wordt geactiveerd wanneer de gebruiker  een ring in BoardView sleept.
            imageView.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent e) {
                    Dragboard dragboard = e.getDragboard();

                    if (dragboard.hasImage()) {
                        //De handler voor DragOver controleert eenvoudig weg of het clipbaord een imageView bevat.
                        e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        //Als true staat het de Transfere toe
                    }
                    e.consume();
                }
            });

            //Als de gebruiker een ring dropt in board view wordt het dragdrop event getriggerd
            imageView.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent e) {
                    Dragboard dragboard = e.getDragboard();

                    if (dragboard.hasImage()) {
                        // update het bord volgens het draggen

                        // globale variable draggedRing geeft de ring door die in spawnPresenter er werdt ingestoken (draggedRing)
                        Ring ring = Settings.draggedRing;
                        int index = (int) imageView.getUserData();
                        boolean mixed = model.getBoard().getRing(index).mixValue(ring);

                        //  mixed checkt of de gedropte ring (Settings.DraggedRing) kan worden gecombineerd op het bord (veld) met de huidige ringen ****
                        if (mixed) {
                            e.setDropCompleted(true);
                            // update om het de aanpassing van de gesleepte ring weer te geven
                            updateView();

                            // nAddedRings = het aantal gedropte ringen dit kan 1 of 2 zijn
                            int nAddedRings = ring.getNumberOfSubRings();

                            // checkGameRule verwijder lijn combianties van de zelfde kleur
                            checkGameRule(nAddedRings);

                            PauseTransition delay = new PauseTransition(Duration.millis(500));
                            delay.setOnFinished(event1 -> updateView());
                            delay.play();
                        }
                    } else {
                        //als de gedraggde ring niet kan worden gecombineerd op het bord (veld) met de huidige ringen ****

                        e.setDropCompleted(false);
                    }
                    e.consume();
                }
            });
        }
    }

    // nAddedSubRings : newly added sub rings count
    private void checkGameRule(int nAddedSubRings) {
        int count = 0;

        // Verhoogte de score van de speler
        Settings.gameScore += nAddedSubRings;

        // controleerd  of er op 1 veld 3 ringen van de zelfde kleur staan
        ArrayList<Integer> sameSpotList = new ArrayList<>();
        for (int i = 0; i < model.getBoard().BOARD_SIZE; i++) {
            if (isSameSpotTriple(i)) {
                // teller voor combo
                count++;
                sameSpotList.add(i);
            }
        }

        ArrayList<Pair<int[], RingColor>> sameLineList = new ArrayList<>();
        // controleerd de lijnen op ringen van de zelfde kleur
        for (int i = 0; i < triples.length; i++) {
            for (RingColor ringColor : RingColor.values()) {
                if (isSameLineTriple(triples[i], ringColor)) {
                    // teller voor combo
                    count++;
                    sameLineList.add(new Pair(triples[i], ringColor));
                }
            }
        }


        // verwijder same spot list
        for (int i : sameSpotList) {
            removeSameSpotTriple(i);
        }

        // verwijderd zelde lijn list
        for (Pair<int[], RingColor> pair : sameLineList) {
            removeSameLineTriple(pair.getKey(), pair.getValue());
        }

        // berekend combo
        if (count > 0) {
            nCombo++;
        } else {
            nCombo = 0;
        }

        Settings.gameScore += nCombo * COMBO_SCORE;

        if (count > 1) {
            Settings.gameScore += COMBO_SCORE;
        }
        stateView.getLblScore().setText(makeScoreLabel(Settings.gameScore, nCombo));
    }


    // ringIndex is de index van
    private boolean isSameSpotTriple(int ringIndex) {
        Ring ring = model.getBoard().getRing(ringIndex);

        //als ringcombinatie op een veld niet bestaat uit 3 ringen = fase
        if (ring.getNumberOfSubRings() != model.getBoard().RING_SPOT_MAX_SIZE) {
            return false;
        }

        // controleerd de indexen van de ringen op zelfde kleuren
        if (ring.getRingColor(0) != ring.getRingColor(1) ||  ring.getRingColor(0) != ring.getRingColor(2)) {
            return false;
        }

        return true;
    }

    private void removeSameSpotTriple(int ringIndex) {
        Ring ring = model.getBoard().getRing(ringIndex);

        for (int i = 0; i < model.getBoard().RING_SPOT_MAX_SIZE; i++) {
            ring.removeSubRing(0);
        }
        lineSound();
    }

    //controleer de ringen op lijnen
    private boolean isSameLineTriple(int[] triple, RingColor ringColor) {
        boolean isSame = false;

        for (int i : triple) {
            isSame = false;
            Ring ring = model.getBoard().getRing(i);
            for (int j = 0; j < ring.getNumberOfSubRings(); j++) {
                if (ring.getRingColor(j) == ringColor) {
                    isSame = true;
                    break;
                }
            }

            if (!isSame) {
                break;
            }
        }

        return isSame;
    }

    private void removeSameLineTriple(int[] triple, RingColor ringColor) {
        for (int i : triple) {
            Ring ring = model.getBoard().getRing(i);
            for (int j = 0; j < ring.getNumberOfSubRings(); j++) {
                if (ring.getRingColor(j) == ringColor) {
                    ring.removeSubRing(j);
                    j--;
                }
            }
        }
        lineSound();
    }

    private String makeScoreLabel(int score, int combo) {
        String strScore;

        if (combo == 0) {
            strScore = String.valueOf(score);
        } else {
            strScore = String.valueOf(score) + "COMBO(" + String.valueOf(combo) + ")";
        }

        return strScore;
    }

    // play click sound
    public void lineSound() {

        AudioClip note = new
                AudioClip(this.getClass().getResource("xaline_sound.mp3").toString());
        note.play();
    }


}
