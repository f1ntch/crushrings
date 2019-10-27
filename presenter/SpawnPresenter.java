package crushrings.presenter;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Modality;
import javafx.stage.Stage;
import crushrings.Log;
import crushrings.model.*;
import crushrings.view.*;
import javafx.scene.image.ImageView;

import static crushrings.model.Spawn.SPAWN_SIZE;

public class SpawnPresenter {
    private CrushRings   model;
    private SpawnView   view;

    private ScoreView scoreView;

    public SpawnPresenter(SpawnView view) {
        this(null, view, null);
    }

    public SpawnPresenter(CrushRings model, SpawnView view, ScoreView scoreView) {
        this.model = model;
        this.view = view;
        this.scoreView = scoreView;
        updateView();
        addEventHandlers();
    }

    public void updateView() {
        checkRegenerate();
        Log.debug("SpwanPresenter > updateView() is called");

        if (model != null) {
            for (int i = 0; i < SPAWN_SIZE; i++) {
                Ring ring = model.getSpawn().getRing(i);
                ImageView imageView = view.getImageViews()[i];

                Log.debug("Ring(s): " + ring.getNumberOfSubRings());

                imageView.setImage(new Image("crushrings/view/images/RING_LARGE_BLUE.png"));

                Utils.displayRing(ring, imageView, false);
            }
        }
    }

    private void addEventHandlers() {
        for (int i = 0; i < view.getImageViews().length; i++) {
            ImageView imageView = view.getImageViews()[i];

            imageView.setUserData(i);//*********SET
            //We bewaren de index van elke Ring (imageView) met behulp van de setUserData-functie
            imageView.setPickOnBounds(true);
            // Wanneer de speler een drag start wordt de evenhandler Mouse event gecalled
            imageView.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Image image = imageView.getImage();
                    if (image == null) {
                        event.consume();
                        return;
                    }

                    // put model data

                    int index = (int)imageView.getUserData(); //*********GET
                    System.out.println("INDEX DRAG=" + index);//INDEX SPAWN 0 1 2
                    // we bewaren de index van elke ring door gebruik te maken van de getUserData functie
                    // om  onderscheiden welke Ring (imageview) wordt gesleept in eventhandler.
                    Ring ring = model.getSpawn().getRing(index);
                    // We slagen de huidige imageview van de ring die wordt gedragged op in een globale variabele draggedRing(settings),
                    //omdat we deze moeten ophalen in de in BoardPresenter moeten gebruiken voor Dragover .
                    Settings.draggedRing = ring;
                    //Als de speler dragged volgt de ring de cursor van de muis.
                    Dragboard dragboard = imageView.startDragAndDrop(TransferMode.COPY_OR_MOVE);
                    ClipboardContent content = new ClipboardContent();

                    // put image
                    content.putImage(image);
                    dragboard.setContent(content);
                    event.consume();
                    // zorgt er voor dat als de er een ring wordt gedragged dat de huidige positie van de ring verdwijnd
                    imageView.setVisible(true);
                }
            });

            imageView.setOnDragDone(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    TransferMode mode = event.getTransferMode();
                    if (mode == TransferMode.MOVE) {
                        // success the dragging

                        int index = (int)imageView.getUserData();
                        Ring ring = model.getSpawn().getRing(index);
                        ring.removeAllSubRings();

                    }
                    else{
                        wrongMoveSound();
                    }

                    imageView.setVisible(true);

                    // clear the dragged ring
                    //Settings.draggedRing = null;
                    // genereerd 3 nieuwe ringen
                    checkRegenerate();
                    updateView();
                    checkGameOver();
                    event.consume();
                }
            });
        }
    }

    private void checkRegenerate() {
        // controleerd op spawn leeg gespeeld is en genereerd 3 nieuw combinaties

        if (model != null) {
            int numberOfEmpty = 0;
            do {
                // tel het het aantal aantal legen spawn velden
                numberOfEmpty = 0;
                for (int i = 0; i < SPAWN_SIZE; i++) {
                    Ring ring = model.getSpawn().getRing(i);
                    if (ring.getNumberOfSubRings() == 0) {
                        numberOfEmpty++;
                    }
                }


                if (numberOfEmpty == SPAWN_SIZE) {
                    // pas als numberOfEmpty = 3 spawn 3 nieuwe ringen
                    Log.debug("SpwanPresenter > Regenerated");
                    //SPAWN_SIZE genereerd altijd 3 ring combianties
                    for (int i = 0; i < SPAWN_SIZE; i++) {
                                        // verander = maar 1 generatie of 2 generatie
                        model.getSpawn().getRing(i).randomise();
                    }
                }
            } while (numberOfEmpty == SPAWN_SIZE);
        }
    }

    private void checkGameOver() {
        boolean bGameOver = true;
            //loop over alle velden om te kijken of er nog een geldige zit is
        for (int i = 0; i < Board.BOARD_SIZE ; i++) {
            Ring boardRing = model.getBoard().getRing(i);
            // check of  er nog een ring kan worden geplaats
            if (boardRing.getNumberOfSubRings() == 0) {
                bGameOver = false;
                break;
            }

            for (int j = 0; j < SPAWN_SIZE; j++) {
                Ring spawnRing = model.getSpawn().getRing(j);
                if (spawnRing.getNumberOfSubRings() == 0) {
                    continue;
                }
                //
                if (spawnRing.canMix(boardRing)) {
                    bGameOver = false;
                    break;
                }
            }

            if (!bGameOver) {
                break;
            }
        }

        if (bGameOver) {
            //save de score
            model.getScore().save();

            GameOverView gameOverView = new GameOverView();
            gameOverView.getLblGameOver().setText("Geen zetten meer ! \n    Uw score is " + Settings.gameScore );
            clickSound();
            new GameOverPresenter(gameOverView);
            Stage gameOverStage = new Stage();
            gameOverStage.initOwner(view.getScene().getWindow());
            gameOverStage.initModality(Modality.APPLICATION_MODAL);
            gameOverStage.setTitle("Topscore");
            Scene scene = new Scene(gameOverView);
            gameOverStage.setScene(scene);
            gameOverStage.showAndWait();
            view.getScene().setRoot(scoreView);
        }
    }

    // play click sound
    public void clickSound() {

        AudioClip note = new
                AudioClip(this.getClass().getResource("xagame_over_sound.mp3").toString());
        note.play();
    }

    public void wrongMoveSound() {

        AudioClip note = new
                AudioClip(this.getClass().getResource("xanot_possible_sound.mp3").toString());
        note.play();
    }
}

