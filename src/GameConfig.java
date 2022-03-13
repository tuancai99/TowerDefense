import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.*;
import java.util.ArrayList;

public class GameConfig extends Application {
    private Button beginBtn;
    private Button endBtn;
    private Button accessShop;
    private ArrayList<Tower> currentTowers;
    /**
     * Game screen using Javafx
     * @param stage stage
     * @throws Exception handler
     */
    @Override
    public void start(Stage stage) throws Exception {
        currentTowers = Player.getTowersOwned();
        Image image = new Image("/Images/map2.png");

        ImageView imageView = new ImageView(image);

        imageView.setX(0);
        imageView.setY(0);

        imageView.setFitHeight(1200);
        imageView.setFitWidth(1450);

        imageView.setPreserveRatio(true);

        int startingMoney = Player.getMoney();
        int startingHealth = Base.getHealth();

        String moneyStr = "MONEY: " + String.valueOf(startingMoney);
        String healthStr = "HEALTH: " + String.valueOf(startingHealth) + "hp";

        Text text = new Text();
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        text.setX(900);
        text.setY(130);
        text.setText(moneyStr);

        Text text2 = new Text();
        text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        text2.setX(300);
        text2.setY(100);
        text2.setText("Prepare for Battle");

        Text text3 = new Text();
        text3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        text3.setX(900);
        text3.setY(65);
        text3.setText(healthStr);

        Font f1 = Font.font("Comic Sans MS", FontWeight.BOLD, 20);
        beginBtn = new Button("Start Round");
        beginBtn.setFont(f1);
        beginBtn.setLayoutX(50);
        beginBtn.setLayoutY(18);
        beginBtn.setPrefWidth(150);
        beginBtn.setPrefHeight(60);
        beginBtn.setOnAction(event -> {
            try {
                pressStartRoundBtn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        endBtn = new Button("End Game");
        endBtn.setFont(f1);
        endBtn.setLayoutX(1250);
        endBtn.setLayoutY(50);
        endBtn.setPrefWidth(150);
        endBtn.setPrefHeight(60);
        endBtn.setOnAction(event -> {
            try {
                pressEndBtn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        accessShop = new Button("Shop");
        accessShop.setFont(f1);
        accessShop.setLayoutX(50);
        accessShop.setLayoutY(90);
        accessShop.setPrefWidth(150);
        accessShop.setPrefHeight(60);
        accessShop.setOnAction(new ShopHandler());

        Rectangle rectangle = new Rectangle(120, 321,  248, 38);

        Group root = new Group(imageView, text, text2, text3, beginBtn, endBtn,
                accessShop, rectangle);

        if (currentTowers != null) {
            for (int i = 0; i < currentTowers.size(); i++) {
                Tower curr = currentTowers.get(i);
                root.getChildren().add(curr.getImageView());
            }
        }

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.setX(0);
        stage.setY(0);
        stage.setFullScreen(true);
        stage.show();

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



}