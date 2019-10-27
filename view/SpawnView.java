package crushrings.view;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 13-02-2019
 */

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

// via een for loop wordt het de spawn aan gemaakt met de afbeelding van een vak default.png
public class SpawnView extends GridPane {
    public static final Image DEFAULT_IMAGE = new Image("crushrings/view/images/default.png");
    public static final int DEFAULT_SIZE = 3;
    private ImageView[]     imageViews;
    public SpawnView() {
        layoutNodes();
        imageViews = new ImageView[DEFAULT_SIZE];
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            imageViews[i] = new ImageView(DEFAULT_IMAGE);
            add(imageViews[i], i, 0);
        }
    }

    public ImageView[] getImageViews() {
        return imageViews;
    }

    private void layoutNodes() {
        setAlignment(Pos.CENTER);
        getColumnConstraints().add(new ColumnConstraints(100, 100, 100, Priority.SOMETIMES, HPos.CENTER, false));
        getColumnConstraints().add(new ColumnConstraints(100, 100, 100, Priority.SOMETIMES, HPos.CENTER, false));
        getColumnConstraints().add(new ColumnConstraints(100, 100, 100, Priority.SOMETIMES, HPos.CENTER, false));
        getRowConstraints().add(new RowConstraints(100,100,100,Priority.SOMETIMES, VPos.CENTER, false));
    }
}
