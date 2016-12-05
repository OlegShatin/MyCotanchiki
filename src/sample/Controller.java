package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.*;

public class Controller {
    @FXML
    private  ImageView target1V;
    @FXML
    private  ImageView target2V;
    @FXML
    private ImageView catV;
    @FXML
    private Pane root;
    @FXML
    private Pane gameField;
    private List<Cotank> targets = new ArrayList<>();
    private Cotank cat;


    public void keyPressed(KeyEvent event) {

        //todo: hardcode!
        cat = new Cotank(catV, gameField);
        Cotank enemy1 = new Cotank(target1V, gameField);
        Cotank enemy2 = new Cotank(target2V, gameField);

        if (event.getCode() == KeyCode.RIGHT) {
            cat.getView().setScaleX(1);
            cat.move(20,0);
            enemy1.shoot(Arrays.asList(new Cotank[]{cat}));

        }
        if (event.getCode() == KeyCode.DOWN) {
            cat.move(0,20);
            enemy2.shoot(Arrays.asList(new Cotank[]{cat}));
        }
        if (event.getCode() == KeyCode.LEFT) {
            cat.getView().setScaleX(-1);
            cat.move(-20, 0);
            enemy2.shoot(Arrays.asList(new Cotank[]{cat}));
        }
        if (event.getCode() == KeyCode.UP) {
            cat.move(0, -20);
            enemy1.shoot(Arrays.asList(new Cotank[]{cat}));
        }
        if (event.getCode() == KeyCode.SPACE) {
            targets.add(enemy1);
            targets.add(enemy2);
            cat.shoot(targets);
        }
    }
}
