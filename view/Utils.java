package crushrings.view;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 13-02-2019
 */

import crushrings.model.Ring;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import crushrings.model.RingColor;
import crushrings.model.RingSize;



/* Deze klasse koppeled de afbeelingen aan de ringen volgens het model */

public class Utils {
    static public void displayRing(Ring ring, ImageView imageView, boolean showBackground) {
        int numberOfSubRings = ring.getNumberOfSubRings();

        Image backgroundImage;
        if (showBackground) {
            backgroundImage = new Image("crushrings/view/images/default.png");
        } else {
            backgroundImage = new Image("crushrings/view/images/OPAQUE.png");
        }

        Group blend = new Group(new ImageView(backgroundImage));

        for (int j = 0; j < numberOfSubRings; j++) {
            RingSize ringSize = ring.getRingSize(j);
            RingColor ringColor = ring.getRingColor(j);

            String ringImageUrl = "crushrings/view/images/RING_" + ringSize.toString()
                    + "_" + ringColor.toString() + ".png";
            ImageView tmpView = new ImageView(new Image(ringImageUrl));
            blend.getChildren().add(tmpView);
        }


        //Zorgt voor samenvoegen van twee ring afbeeldingen
        //En voeg de 2 afbeeldingen samen volgens het model

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        WritableImage writableImage = blend.snapshot(params, null);
        ImageView finalImageView = new ImageView(writableImage);

        // display to the view
        imageView.setImage(finalImageView.getImage());
    }
}


//new MediaPlayer(sound).play();