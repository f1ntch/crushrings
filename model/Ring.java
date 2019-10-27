package crushrings.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ring {
    static final Random random = new Random();

    private int numberOfSubRings; // bevat RingSize en RingColor

    // mogelijke grootes
    private List<RingSize> ringSizeList;
    // mogelijke kleuren
    private List<RingColor> ringColorList;

    public Ring() {
        this.numberOfSubRings = 0;
        ringSizeList = new ArrayList<>();
        ringColorList = new ArrayList<>();
    }


    // subring = combiantie
    // ring //


    //checkt of huidige ring gecombineerd kan worden met een andere ring als combinatie
    // en geeft true terug als er 2 ringen met elkaar kunenn gemixed worden dus als ze niet de zelfde grooten hebben
    public boolean canMix(Ring other) {
        for (int i = 0; i < other.numberOfSubRings; i++) {
            if (this.ringSizeList.contains(other.ringSizeList.get(i))) {
                return false;
            }
        }

        return true;
    }


        // mixValue controleert en veranderde de huidige ring in mixed ring.
        // current ring is changed to mixed ring
    public boolean mixValue(Ring other) {

        for (int i = 0; i < other.numberOfSubRings; i++) {
            if (this.ringSizeList.contains(other.ringSizeList.get(i))) {
                return false;
            }
        }

        for (int i = 0; i < other.numberOfSubRings; i++) {
            ringSizeList.add(other.ringSizeList.get(i));
            ringColorList.add(other.ringColorList.get(i));
        }
        this.numberOfSubRings += other.numberOfSubRings;

        return true;
    }

    public int getNumberOfSubRings() {
        return numberOfSubRings;
    }

    public RingSize getRingSize(int i) {
        checkIndex(i);
        return ringSizeList.get(i);
    }

    public RingColor getRingColor(int i) {
        checkIndex(i);
        return ringColorList.get(i);
    }

    public void removeSubRing(int i) {
        checkIndex(i);

        ringSizeList.remove(i);
        ringColorList.remove(i);
        numberOfSubRings--;
    }

    public void removeAllSubRings() {
        ringSizeList.clear();
        ringColorList.clear();
        numberOfSubRings = 0;
    }


    // controleert de index van subrings
    // Dus het controleert de index groter is dan max. (3)
    // Als de index out of range is wordt er een excetpion getrowed
    private void checkIndex(int i) {
        if (i < 0) throw new RuntimeException("Index can't be negative!");
        if (i >= numberOfSubRings) throw new RuntimeException("Max index is " + numberOfSubRings);
    }



    // maakt een ring aan met random kleur en random size


    public void randomise() {

        // Maakt een ringcombinatie van max 1 of 2 ringen
        this.numberOfSubRings = random.nextInt(RingSize.values().length - 1) + 1;

        for (int i = 0; i < numberOfSubRings; i++) {

            RingSize newRingSize;
            RingColor newRingColor;

            do {
                //todo ?
                newRingSize = RingSize.values()[random.nextInt(RingSize.values().length)];
            } while (ringSizeList.contains(newRingSize));

            newRingColor = RingColor.values()[random.nextInt(3)];
            for (int j = Settings.levels.length - 1; j >= 0; j--) {
                // check level
                if (Settings.gameScore >= Settings.levels[j]) {
                    newRingColor = RingColor.values()[random.nextInt(3 + j)];
                    break;
                }
            }

            ringSizeList.add(newRingSize);
            ringColorList.add(newRingColor);
        }
    }
}
