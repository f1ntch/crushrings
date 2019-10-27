package crushrings.view;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 13-02-2019
 */

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class BoardView extends GridPane {
    public static final Image DEFAULT_IMAGE = new Image("crushrings/view/images/default.png");
    public static final int DEFAULT_SIZE = 9;
    private ImageView[]     imageViews;

    // via een for loop wordt het bord aan gemaakt met de afbeelding van een vak default.png
    public BoardView() {
        layoutNodes();
        imageViews = new ImageView[DEFAULT_SIZE];
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            int row = i / 3;
            int col = i % 3;
            imageViews[i] = new ImageView(DEFAULT_IMAGE);
            add(imageViews[i], col, row);
        }
    }

    public ImageView[] getImageViews() {
        return imageViews;
    }


    // colum en row contraints
    private void layoutNodes() {
        setAlignment(Pos.CENTER);

        getColumnConstraints().add(new ColumnConstraints(100, 100, 100, Priority.SOMETIMES, HPos.CENTER, false));
        getColumnConstraints().add(new ColumnConstraints(100, 100, 100, Priority.SOMETIMES, HPos.CENTER, false));
        getColumnConstraints().add(new ColumnConstraints(100, 100, 100, Priority.SOMETIMES, HPos.CENTER, false));
        getRowConstraints().add(new RowConstraints(100,100,100,Priority.SOMETIMES, VPos.CENTER, false));
        getRowConstraints().add(new RowConstraints(100,100,100,Priority.SOMETIMES, VPos.CENTER, false));
        getRowConstraints().add(new RowConstraints(100,100,100,Priority.SOMETIMES, VPos.CENTER, false));
    }
}
