package crushrings;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 13-02-2019
 */

import crushrings.model.CrushRings;
import crushrings.presenter.CrushRingsPresenter;
import crushrings.view.CrushRingsView;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        /*
        MVP
        Als het model wijzigd, roept het Model de View niet rechtstreeks.

        Model zal de updateView-functie van Presenter aan aanroepen en Presenter zal View updaten.

        Als de View interactie heeft met de gebruiker, wordt de EventHandler van Presenter aangeroepen.
        */

        // model initialisatie van CrushRings
        CrushRings model = new CrushRings();

        // view initialisatie van RinggamesView (CrushRingsView)
        CrushRingsView view = new CrushRingsView();


        // presenter initialisatie van CrushRingsPresenter(CrushRingsPresenter)
        CrushRingsPresenter presenter = new CrushRingsPresenter(model, view);


        // Maakt een scene aan van view -> CrushRingsView
        Scene scene = new Scene(view);
        scene.setCursor(Cursor.HAND);

        // Zet de scene op de stage
        primaryStage.setScene(scene);

        primaryStage.setTitle("Crushrings");
        primaryStage.show();
        primaryStage.setMaxWidth(550);
        primaryStage.setMinHeight(700);
        primaryStage.setMaxHeight(700);
        primaryStage.setResizable(false);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
