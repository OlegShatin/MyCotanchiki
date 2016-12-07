package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.Collection;

/**
 * @author Oleg Shatin
 *         11-501
 */
public class Cotank {
    private boolean isMoving;
    private AnimationTimer currentMoveTimer;

    public ImageView getView() {
        return view;
    }

    private ImageView view;
    private Pane gameField;

    public Cotank(ImageView view, Pane gameField) {
        this.view = view;
        this.gameField = gameField;
    }

    public void move(double newX, double newY) {
        if (gameField.getChildren().contains(view)) {
            double deltaX = newX - view.getX()- view.getLayoutX();
            double deltaY = newY - view.getY() - view.getLayoutY();
            final double DISTANCE = 20;
            double rateX = deltaX / DISTANCE;
            double rateY = deltaY / DISTANCE;
            if (currentMoveTimer != null) {
                currentMoveTimer.stop();
            }
            currentMoveTimer = new AnimationTimer() {
                        int i = 0;
                        @Override
                        public void handle(long now) {
                            if (i < DISTANCE) {
                                view.setX(view.getX() + rateX);
                                view.setY(view.getY() + rateY);
                                i++;
                            } else {
                                this.stop();
                            }
                        }
                    };
            currentMoveTimer.start();

        }
    }

    public void shoot(Collection<Cotank> targets) {
        if (gameField.getChildren().contains(view)) {
            Circle c = new Circle(view.getScaleX() > 0 ? view.getX() + view.getLayoutX() : view.getX() + view.getLayoutX() + view.getFitWidth(),
                    view.getLayoutY() + view.getY(), 10);
            gameField.getChildren().add(c);
            new AnimationTimer() {
                long was = 0;
                int d = (int) view.getScaleX();

                @Override
                public void handle(long now) {
                    if (now - was > 1) {
                        c.setCenterX(c.getCenterX() + d * 10);
                        was = now;
                        for (Cotank target : targets) {
                            if (gameField.getChildren().contains(target.getView()) && target.getView().getBoundsInParent().contains(c.getBoundsInParent())) {
                                gameField.getChildren().remove(target.getView());
                                gameField.getChildren().remove(c);
                                this.stop();
                            }
                        }
                    }
                    if (c.getCenterX() < gameField.getLayoutX() || c.getCenterX() > gameField.getLayoutX() + gameField.getWidth()) {
                        this.stop();
                        gameField.getChildren().remove(c);
                    }
                }

            }.start();
        }
    }
}
