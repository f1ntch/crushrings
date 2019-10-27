package crushrings.view;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 13-02-2019
 */

import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

//view class for the special pause button when you press the pause button in game
public class PauseView extends Label {
    public PauseView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
    }

    private void layoutNodes() {
        setId("lblPause");
        setText("    PAUZE");
        setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#c20e9b 0%, #432e64 20%, #191d22 100%),\n" +
                "        linear-gradient(#c20e9b, #4c2a64),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(194,14,155,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 24px;\n" +
                "    -fx-padding: 10 20 10 20;" +
                "    -fx-font-weight: bold;"

        );
        setTextAlignment(TextAlignment.LEFT);
        setMinSize(180, 40);
        setPrefSize(180, 50);
        setMaxSize(180, 50);
    }
}
