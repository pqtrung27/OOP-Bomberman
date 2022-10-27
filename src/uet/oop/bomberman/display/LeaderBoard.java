package uet.oop.bomberman.display;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.entities.Option;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LeaderBoard extends Menu {
    private VBox names;
    private VBox scores;
    public LeaderBoard() {
        bgmPlayer = new MediaPlayer(new Media(getClass().getResource("/audio/TitleScreen.mp3").toString()));

        text.setText("LEADERBOARD");

        names = new VBox();
        names.setAlignment(Pos.CENTER_LEFT);
        names.setSpacing(10);

        scores = new VBox();
        scores.setAlignment(Pos.CENTER_RIGHT);
        scores.setSpacing(10);

        loadLeaderboard();

        HBox scoreboard = new HBox(names, scores);
        scoreboard.setAlignment(Pos.CENTER);
        scoreboard.setSpacing(500);

        menuItems = new Option[1];
        menuItems[0] = new Option("BACK");
        menuItems[0].setOnActivate(() -> Main.setPlayingStatus(0, "back"));
        menuItems[0].setActive(true);

        root.setSpacing(20);
        root.getChildren().add(text);
        root.getChildren().add(scoreboard);
        root.getChildren().addAll(menuItems);
    }

    private void loadLeaderboard() {
        try {
            names.getChildren().clear();
            scores.getChildren().clear();
            Scanner scanner = new Scanner(new FileInputStream("res/leaderboard.txt"));
            while (scanner.hasNext()) {
                names.getChildren().add(createText(scanner.next()));
                scores.getChildren().add(createText(scanner.next()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Leaderboard File Not Found!!!");
        }
    }

    @Override
    public void reset() {
        super.reset();
        loadLeaderboard();
    }

    private Text createText(String mes) {
        Text textMes = new Text(mes);
        textMes.setFont(Main.FONT);
        textMes.setFill(Color.WHITE);
        return textMes;
    }

    public static boolean checkHighScore(int newScore) {
        try {
            Scanner scanner = new Scanner(new FileInputStream("res/leaderboard.txt"));
            while (scanner.hasNext()) {
                scanner.next();
                int highScore = Integer.parseInt(scanner.next());
                if (newScore > highScore) return true;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Leaderboard File Not Found!!!");
        }
        return false;
    }

    public static void setHighScore(String newName, int newScore) {
        try {
            List<data> players = new ArrayList<>();

            Scanner scanner = new Scanner(Files.newInputStream(Paths.get("res/leaderboard.txt")));
            while (scanner.hasNext()) {
                players.add(new data(scanner.next(), Integer.parseInt(scanner.next())));
            }
            players.add(new data(newName, newScore));
            Collections.sort(players);
            scanner.close();

            FileWriter file = new FileWriter("res/leaderboard.txt");
            for (int i = 0; i < 5; ++i) {
                data thisPlayer = players.get(i);
                file.write(thisPlayer.name + " " + thisPlayer.score);
                file.write("\n");
            }
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Can't write leaderboard.txt!");
        }
    }

    private static class data implements Comparable<data> {
        private final String name;
        private final int score;
        public data(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public int compareTo(data other) {
            return other.score - this.score;
        }
    }
}
