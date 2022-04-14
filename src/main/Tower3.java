package main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tower3 extends Tower {
    private Image sprite = new Image("/Images/pinkTower.png");
    private Rectangle r;

    public Tower3() {
        price = 40 * playerLevel;
        dps = ((6 - (0.4 * (playerLevel - 1))))/2;
    }

    public ImageView draw() {
        imageView = new ImageView();
        imageView.setImage(sprite);
        imageView.setFitHeight(75);
        imageView.setFitWidth(75);
        imageView.setX(xVal);
        imageView.setY(yVal);
        return imageView;
    }

    public Rectangle createAttackObject(Enemy e) {
        r = new Rectangle(xVal + (75.0 / 6), yVal + (75.0 / 2), 75.0 / 3,
                ((e.getYVal() + (35.0 / 2)) - (yVal + (75.0 / 2))));
        r.setArcHeight(5);
        r.setArcWidth(5);
        r.setFill(Color.GREEN);
        return r;
    }

    public boolean attackEnemy(Enemy e) {
        if (e.getImageView().intersects(r.getBoundsInLocal())) {
            e.setHealth(e.getHealth() - dps);
            Player.setMoney(Player.getMoney() + (int) dps);
            return true;
        }
        return false;
    }

}