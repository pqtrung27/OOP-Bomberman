package uet.oop.bomberman.display;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.entities.Option;

public class Instruction extends DisplayScene {
    private Page[] pages;
    private int currentPage = 0;
    public Instruction() {
        pages = new Page[3];
        pages[0] = new Page("CONTROLS");
        pages[1] = new Page("CHARACTERS");
        pages[2] = new Page("POWER-UPS");

        setControlsBody();
        setCharactersBody();
        setPowerUpsBody();
    }

    private void setControlsBody() {
        pages[0].addBody(createInstruction(
                null,
            createLabel(" ←↑→↓"),
            createDescription("use up, down, left, right keys\nto direct bomberman player")
        ));
        pages[0].addBody(createInstruction(
                null,
            createLabel("SPACE"),
            createDescription("use space to lay a bomb\nin player's current position")
        ));
        pages[0].addBody(createInstruction(
                null,
                createLabel("ESCAPE"),
                createDescription("use escape to pause game")
        ));
    }

    private void setCharactersBody() {

    }

    private void setPowerUpsBody() {

    }

    private HBox createInstruction(Image img, VBox label, VBox description) {
        HBox ins = new HBox();
        if (img != null) {
            ins.getChildren().add(new ImageView(img));
        }
        ins.getChildren().add(label);
        ins.getChildren().add(description);
        ins.setSpacing(50);
        // ins.setAlignment(Pos.CENTER);
        ins.setVisible(true);
        return ins;
    }

    private VBox createLabel(String mes) {
        Text label = new Text(mes);
        label.setFont(Main.FONT);
        label.setFill(Color.YELLOW);
        label.setStyle("-fx-font-size: 25");
        VBox labelBox = new VBox(label);
        labelBox.setAlignment(Pos.CENTER_RIGHT);
        labelBox.setMinWidth(250);
        labelBox.setMaxWidth(250);
        // labelBox.setStyle("-fx-border-color: WHITE; -fx-border-width: 2");
        return labelBox;
    }

    private VBox createDescription(String mes) {
        Text description = new Text(mes);
        description.setFont(Main.FONT);
        description.setFill(Color.WHITE);
        description.setStyle("-fx-font-size: 25");
        VBox descriptionBox = new VBox(description);
        descriptionBox.setAlignment(Pos.CENTER_LEFT);
        return descriptionBox;
    }

    @Override
    public Scene getScene() {
        return pages[currentPage].getScene();
    }

    @Override
    public void update() {
        pages[currentPage].update();
    }

    @Override
    public void reset() {
        currentPage = 0;
        pages[currentPage].reset();
    }

    private void nextPage() {
        if (currentPage < pages.length - 1) {
            pages[++currentPage].reset();
        }
    }

    private void prevPage() {
        if (currentPage > 0) {
            pages[--currentPage].reset();
        }
    }

    private class Page extends Menu {
        private VBox body = new VBox();
        public Page(String name) {
            text.setText(name);

            body.setSpacing(15);

            menuItems = new Option[3];

            menuItems[0] = new Option("NEXT");
            menuItems[0].setOnActivate(Instruction.this::nextPage);

            menuItems[1] = new Option("BACK");
            menuItems[1].setOnActivate(Instruction.this::prevPage);

            menuItems[2] = new Option("RETURN");
            menuItems[2].setOnActivate(() -> Main.setPlayingStatus(0, "back"));

            menuItems[currentItem].setActive(true);
            root.getChildren().add(text);
            root.getChildren().add(new Text());
            root.getChildren().add(body);
            root.getChildren().add(new Text());
            root.getChildren().addAll(menuItems);
        }

        public void addBody(HBox ins) {
            body.getChildren().add(ins);
        }
    }
}
