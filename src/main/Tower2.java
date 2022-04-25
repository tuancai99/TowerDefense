package main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Tower2 extends Tower {
    private Image sprite = new Image("/Images/yellowTower.png");
    private Line l;
    private double gainedHealth = .03;
    public Tower2() {
        classification = "Yellow";
        price = 35 * playerLevel;
        dps = ((4 - (0.3 * (playerLevel - 1)))) / 2;
        upgrade = new Upgrade();
    }

    public void setGainedHealth(double h) {
        gainedHealth = h;
    }
    public double getGainedHealth() {
        return gainedHealth;
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

    public Line createAttackObject(Enemy e) {
        l = new Line(xVal + (75.0 / 2), yVal + (75.0 / 2),
                e.getXVal() + (35.0 / 2), e.getYVal() + (35.0 / 2));
        l.setStroke(Color.BLUE);
        l.setStrokeWidth(10);
        return l;
    }

    public boolean attackEnemy(Enemy e) {
        if (e.getImageView().intersects(l.getBoundsInLocal())) {
            e.setHealth(e.getHealth() - dps);
            Base.setHealth(Base.getHealth() + gainedHealth);
            return true;
        }
        return false;
    }

    public void upgradeAttack() {
        proximity += 3;
        dps += .2;
        gainedHealth += .01;
        upgrade.upgrade();
    }
}