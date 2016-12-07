package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.*;

public class Controller {
    @FXML
    private ImageView target1V;
    @FXML
    private ImageView target2V;
    @FXML
    private ImageView catV;
    @FXML
    private Pane root;
    @FXML
    private Pane gameField;
    private List<Cotank> targets = new ArrayList<>();
    private Cotank cat;
    private Cotank enemy1;
    private Cotank enemy2;

    public void initialize() {
        cat = new Cotank(catV, gameField);
        enemy1 = new Cotank(target1V, gameField);
        enemy2 = new Cotank(target2V, gameField);
        targets.add(enemy1);
        targets.add(enemy2);
    }

    public void keyPressed(KeyEvent event) {

        if (event.getCode() == KeyCode.RIGHT) {
            cat.getView().setScaleX(1);
            cat.move(20, 0);
            if (targets.contains(enemy1)) {
                enemy1.shoot(Arrays.asList(new Cotank[]{cat}));
            }

        }
        if (event.getCode() == KeyCode.DOWN) {
            cat.move(0, 20);
            if (targets.contains(enemy2)) {
                enemy2.shoot(Arrays.asList(new Cotank[]{cat}));
            }
        }
        if (event.getCode() == KeyCode.LEFT) {
            cat.getView().setScaleX(-1);
            cat.move(-20, 0);
            if (targets.contains(enemy2)) {
                enemy2.shoot(Arrays.asList(new Cotank[]{cat}));
            }
        }
        if (event.getCode() == KeyCode.UP) {
            cat.move(0, -20);
            if (targets.contains(enemy1)) {
                enemy1.shoot(Arrays.asList(new Cotank[]{cat}));
            }
        }
        if (event.getCode() == KeyCode.SPACE) {
            cat.shoot(targets);
        }
    }

    public void mouseMoved(MouseEvent mouseEvent) {
        cat.move(mouseEvent.getX(),mouseEvent.getY());
    }
}
