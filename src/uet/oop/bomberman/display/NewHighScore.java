package uet.oop.bomberman.display;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.entities.Option;

public class NewHighScore extends Menu {
    public NewHighScore() {
        text.setText("NEW HIGH SCORE");

        Text mes = new Text("Congratulations!\nYou made it to leaderboard!\nPlease enter your name");
        mes.setFont(Main.FONT);
        mes.setFill(Color.WHITE);
        mes.setTextAlignment(TextAlignment.CENTER);
        mes.setLineSpacing(5);

        TextField textField = new TextField();
        textField.setMaxWidth(500);
        textField.setMinHeight(50);
        textField.setFont(Main.FONT);
        textField.setAlignment(Pos.CENTER);
        textField.setBackground(null);
        textField.setStyle("-fx-border-color: YELLOW; -fx-border-width: 3; -fx-text-fill: YELLOW; -fx-font-size: 20");

        menuItems = new Option[1];
        menuItems[0] = new Option("ENTER");
        menuItems[0].setOnActivate(() -> {
            String name = textField.getText();
            if (name.length() > 0) {
                LeaderBoard.setHighScore(name, BombermanGame.getScore());
                Main.setPlayingStatus(4, "game over");
            }
        });
        menuItems[0].setActive(true);

        root.setSpacing(20);
        root.getChildren().add(text);
        root.getChildren().add(mes);
        root.getChildren().add(textField);
        root.getChildren().addAll(menuItems);
    }
}
