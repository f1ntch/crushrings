package crushrings.model;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 9-02-2019
 */


import java.io.*;
import java.util.ArrayList;

public class Score {


    //Score contains ArrayList of ScoreRecord.

    private ArrayList<ScoreRecord> scoreList;


    // laad de score in het spel
    public Score() {
        scoreList = new ArrayList<>();

        //todo
        try {

            BufferedReader br = new BufferedReader(new FileReader("src/crushrings/model/data/score.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                String player = tokens[0];
                int score = Integer.parseInt(tokens[1]);
                scoreList.add(new ScoreRecord(player, score));
            }
            //scoreList.sort((a, b) -> b.getScore() - a.getScore());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean save() {

        scoreList.add(0, new ScoreRecord(Settings.playerName, Settings.gameScore));

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/crushrings/model/data/score.txt", false));

            for (ScoreRecord scoreRecord : scoreList) {
                bw.write(scoreRecord.getPlayer() + "," + scoreRecord.getScore() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public ArrayList<ScoreRecord> getScoreList() {
        return scoreList;
    }
}

