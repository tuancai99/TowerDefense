package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class GameConfig extends Application {
    private static Stage currStage;
    private static Tower updateTower;
    private Button beginBtn;
    private Button endBtn;
    private Button accessShop;
    private ArrayList<Tower> currentTowers;
    private int round;
    /**
     * Game screen using Javafx
     * @param stage stage
     * @throws Exception handler
     */
    @Override
    public void start(Stage stage) throws Exception {
        currStage = stage;
        currentTowers = Map.getTowersPlaced();
        round = Map.getRound();

        Image image = new Image("/Images/map2.png");
        ImageView imageView = new ImageView(image);

        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitHeight(900);
        imageView.setFitWidth(1200);
        imageView.setPreserveRatio(true);

        int startingMoney = Player.getMoney();
        double startingHealth = Base.getHealth();

        NumberFormat nf = new DecimalFormat("#####.##");
        String moneyStr = "MONEY: " + startingMoney;
        String healthStr = "HEALTH: " + nf.format(startingHealth) + "hp";

        Text text = new Text();
        text.setFont(Font.font("Sinhala MN", FontWeight.BOLD, FontPosture.REGULAR, 25));
        text.setX(730);
        text.setY(105);
        text.setText(moneyStr);

        Text text2 = new Text();
        text2.setFont(Font.font("Sinhala MN", FontWeight.BOLD, FontPosture.REGULAR, 25));
        text2.setX(730);
        text2.setY(65);
        text2.setText(healthStr);

        Text text3 = new Text();
        text3.setFont(Font.font("Sinhala MN", FontWeight.BOLD, FontPosture.REGULAR, 40));
        text3.setX(250);
        text3.setY(70);
        text3.setStrokeWidth(.5);
        text3.setText("Prepare for Battle");

        Text text4 = new Text();
        text4.setFont(Font.font("Sinhala MN", FontWeight.BOLD, FontPosture.REGULAR, 25));
        text4.setX(375);
        text4.setY(115);
        text4.setStrokeWidth(.5);
        if (round == 4) {
            text4.setX(360);
            text4.setText("Final Round");
        } else {
            text4.setText("Round " + round);
        }

        Font f1 = Font.font("Sinhala MN", FontWeight.BOLD, 18);
        beginBtn = new Button("Start Round");
        crBt(beginBtn, 50, 10, 150, 60, f1);
        beginBtn.setOnAction(event -> {
            try {
                pressStartRoundBtn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        endBtn = new Button("End Game");
        crBt(endBtn, 1000, 45, 150, 60, f1);
        endBtn.setOnAction(event -> {
            try {
                pressEndBtn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        accessShop = new Button("Shop");
        crBt(accessShop, 50, 80, 150, 60, f1);
        accessShop.setOnAction(new ShopHandler());

        Group root = new Group(imageView, text, text2, text3, text4, beginBtn, endBtn,
                accessShop);

        Font f2 = Font.font("Sinhala MN", FontWeight.BOLD, 8);
        if (currentTowers != null) {
            for (int i = 0; i < currentTowers.size(); i++) {
                Tower curr = currentTowers.get(i);
                root.getChildren().add(curr.getImageView());
                Upgrade upCurr = currentTowers.get(i).getUpgrade();
                Text upgrade = new Text(curr.getXVal() + 30, curr.getYVal() - 5, "+" + upCurr.getUpgradeLevel());
                upgrade.setFill(Color.WHITE);
                root.getChildren().add(upgrade);
                Button upgradeButton = new Button("Upgrade");
                crBt(upgradeButton, (int)curr.getXVal() + 12, (int)curr.getYVal() + 45, 50, 20, f2);
                root.getChildren().add(upgradeButton);
                upgradeButton.setOnAction(event -> {
                    try {
                        pressUpgradeBtn(curr);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setX(150);
        stage.setY(0);
        stage.show();

    }

    public void crBt(Button b, int x, int y, int w, int h, Font f1) {
        b.setFont(f1);
        b.setLayoutX(x);
        b.setLayoutY(y);
        b.setPrefWidth(w);
        b.setPrefHeight(h);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void pressStartRoundBtn() throws Exception {
        Stage stage;

        stage = (Stage) beginBtn.getScene().getWindow();
        GameStart gameRoundStart = new GameStart();
        gameRoundStart.start(stage);

    }

    public void pressEndBtn() throws Exception {
        Stage stage;

        stage = (Stage) endBtn.getScene().getWindow();
        stage.close();

    }

    public void pressUpgradeBtn(Tower curr) throws Exception {
        updateTower = curr;
        UpgradeController upgradeController = new UpgradeController();
        Stage subStage = new Stage();
        subStage.setTitle("Upgrade");
        upgradeController.start(subStage);
    }

    public class ShopHandler implements EventHandler<javafx.event.ActionEvent> {
        public void handle(ActionEvent action) {
            Stage myStage;
            myStage = (Stage) accessShop.getScene().getWindow();
            Shop shop = new Shop();
            try {
                shop.start(myStage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static Tower getUpdateTower() {
        return updateTower;
    }
    public static Stage getCurrStage() {
        return currStage;
    }

}
