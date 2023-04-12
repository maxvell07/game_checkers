package com.example.game_dem1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloApplication extends Application {
    HelloController controller = new HelloController();

    @Override
    public void start(Stage stage) throws IOException {
        Map<Cord, Rectangle> map = new HashMap<>();
        Map<Cord, Circle> map2 = new HashMap<>();
        Cord[][] cords = new Cord[8][8];
        int stepX = 50;
        int stepY = 50;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cords[i][j] = new Cord(stepX, 0);
                stepX += 55;
                cords[i][j].setY(stepY);
            }
            stepX = 50;
            stepY += 55;
        }


        //черные
        Circle circ1 = new Circle(130, 75, 25, Color.GRAY);
        Circle circ2 = new Circle(240, 75, 25, Color.GRAY);
        Circle circ3 = new Circle(350, 75, 25, Color.GRAY);
        Circle circ4 = new Circle(460, 75, 25, Color.GRAY);

        Circle circ5 = new Circle(75, 130, 25, Color.GRAY);
        Circle circ6 = new Circle(185, 130, 25, Color.GRAY);
        Circle circ7 = new Circle(295, 130, 25, Color.GRAY);
        Circle circ8 = new Circle(405, 130, 25, Color.GRAY);

        Circle circ9 = new Circle(130, 185, 25, Color.GRAY);
        Circle circ10 = new Circle(240, 185, 25, Color.GRAY);
        Circle circ11 = new Circle(350, 185, 25, Color.GRAY);
        Circle circ12 = new Circle(460, 185, 25, Color.GRAY);
//белые
        Circle circ13 = new Circle(75, 350, 25, Color.WHITE);
        Circle circ14 = new Circle(185, 350, 25, Color.WHITE);
        Circle circ15 = new Circle(295, 350, 25, Color.WHITE);
        Circle circ16 = new Circle(405, 350, 25, Color.WHITE);

        Circle circ17 = new Circle(130, 405, 25, Color.WHITE);
        Circle circ18 = new Circle(240, 405, 25, Color.WHITE);
        Circle circ19 = new Circle(350, 405, 25, Color.WHITE);
        Circle circ20 = new Circle(460, 405, 25, Color.WHITE);

        Circle circ21 = new Circle(75, 460, 25, Color.WHITE);
        Circle circ22 = new Circle(185, 460, 25, Color.WHITE);
        Circle circ23 = new Circle(295, 460, 25, Color.WHITE);
        Circle circ24 = new Circle(405, 460, 25, Color.WHITE);

        Rectangle rect1 = new Rectangle(50, 50, 50, 50);
        Rectangle rect2 = new Rectangle(105, 50, 50, 50);
        Rectangle rect3 = new Rectangle(160, 50, 50, 50);
        Rectangle rect4 = new Rectangle(215, 50, 50, 50);
        Rectangle rect5 = new Rectangle(270, 50, 50, 50);
        Rectangle rect6 = new Rectangle(325, 50, 50, 50);
        Rectangle rect7 = new Rectangle(380, 50, 50, 50);
        Rectangle rect8 = new Rectangle(435, 50, 50, 50); //1 строка полей сверху

        Rectangle rect9 = new Rectangle(50, 105, 50, 50);
        Rectangle rect10 = new Rectangle(105, 105, 50, 50);
        Rectangle rect11 = new Rectangle(160, 105, 50, 50);
        Rectangle rect12 = new Rectangle(215, 105, 50, 50);
        Rectangle rect13 = new Rectangle(270, 105, 50, 50);
        Rectangle rect14 = new Rectangle(325, 105, 50, 50);
        Rectangle rect15 = new Rectangle(380, 105, 50, 50);
        Rectangle rect16 = new Rectangle(435, 105, 50, 50);//2 строка полей сверху

        Rectangle rect17 = new Rectangle(50, 160, 50, 50);
        Rectangle rect18 = new Rectangle(105, 160, 50, 50);
        Rectangle rect19 = new Rectangle(160, 160, 50, 50);
        Rectangle rect20 = new Rectangle(215, 160, 50, 50);
        Rectangle rect21 = new Rectangle(270, 160, 50, 50);
        Rectangle rect22 = new Rectangle(325, 160, 50, 50);
        Rectangle rect23 = new Rectangle(380, 160, 50, 50);
        Rectangle rect24 = new Rectangle(435, 160, 50, 50);//3 строка полей сверху

        Rectangle rect25 = new Rectangle(50, 215, 50, 50);
        Rectangle rect26 = new Rectangle(105, 215, 50, 50);
        Rectangle rect27 = new Rectangle(160, 215, 50, 50);
        Rectangle rect28 = new Rectangle(215, 215, 50, 50);
        Rectangle rect29 = new Rectangle(270, 215, 50, 50);
        Rectangle rect30 = new Rectangle(325, 215, 50, 50);
        Rectangle rect31 = new Rectangle(380, 215, 50, 50);
        Rectangle rect32 = new Rectangle(435, 215, 50, 50);//4 строка полей сверху

        Rectangle rect33 = new Rectangle(50, 270, 50, 50);
        Rectangle rect34 = new Rectangle(105, 270, 50, 50);
        Rectangle rect35 = new Rectangle(160, 270, 50, 50);
        Rectangle rect36 = new Rectangle(215, 270, 50, 50);
        Rectangle rect37 = new Rectangle(270, 270, 50, 50);
        Rectangle rect38 = new Rectangle(325, 270, 50, 50);
        Rectangle rect39 = new Rectangle(380, 270, 50, 50);
        Rectangle rect40 = new Rectangle(435, 270, 50, 50);//5 строка полей сверху

        Rectangle rect41 = new Rectangle(50, 325, 50, 50);
        Rectangle rect42 = new Rectangle(105, 325, 50, 50);
        Rectangle rect43 = new Rectangle(160, 325, 50, 50);
        Rectangle rect44 = new Rectangle(215, 325, 50, 50);
        Rectangle rect45 = new Rectangle(270, 325, 50, 50);
        Rectangle rect46 = new Rectangle(325, 325, 50, 50);
        Rectangle rect47 = new Rectangle(380, 325, 50, 50);
        Rectangle rect48 = new Rectangle(435, 325, 50, 50);//6 строка полей сверху

        Rectangle rect49 = new Rectangle(50, 380, 50, 50);
        Rectangle rect50 = new Rectangle(105, 380, 50, 50);
        Rectangle rect51 = new Rectangle(160, 380, 50, 50);
        Rectangle rect52 = new Rectangle(215, 380, 50, 50);
        Rectangle rect53 = new Rectangle(270, 380, 50, 50);
        Rectangle rect54 = new Rectangle(325, 380, 50, 50);
        Rectangle rect55 = new Rectangle(380, 380, 50, 50);
        Rectangle rect56 = new Rectangle(435, 380, 50, 50);//7 строка полей сверху

        Rectangle rect57 = new Rectangle(50, 435, 50, 50);
        Rectangle rect58 = new Rectangle(105, 435, 50, 50);
        Rectangle rect59 = new Rectangle(160, 435, 50, 50);
        Rectangle rect60 = new Rectangle(215, 435, 50, 50);
        Rectangle rect61 = new Rectangle(270, 435, 50, 50);
        Rectangle rect62 = new Rectangle(325, 435, 50, 50);
        Rectangle rect63 = new Rectangle(380, 435, 50, 50);
        Rectangle rect64 = new Rectangle(435, 435, 50, 50);//8 строка полей сверху


        rect1.setFill(Color.WHITE);
        rect3.setFill(Color.WHITE);
        rect5.setFill(Color.WHITE);
        rect7.setFill(Color.WHITE);//1 строка

        rect10.setFill(Color.WHITE);
        rect12.setFill(Color.WHITE);
        rect14.setFill(Color.WHITE);
        rect16.setFill(Color.WHITE);//2 строка

        rect17.setFill(Color.WHITE);
        rect19.setFill(Color.WHITE);
        rect21.setFill(Color.WHITE);
        rect23.setFill(Color.WHITE);//3 строка

        rect26.setFill(Color.WHITE);
        rect28.setFill(Color.WHITE);
        rect30.setFill(Color.WHITE);
        rect32.setFill(Color.WHITE);//4 строка

        rect33.setFill(Color.WHITE);
        rect35.setFill(Color.WHITE);
        rect37.setFill(Color.WHITE);
        rect39.setFill(Color.WHITE);//5 строка

        rect42.setFill(Color.WHITE);
        rect44.setFill(Color.WHITE);
        rect46.setFill(Color.WHITE);
        rect48.setFill(Color.WHITE);//6 строка

        rect49.setFill(Color.WHITE);
        rect51.setFill(Color.WHITE);
        rect53.setFill(Color.WHITE);
        rect55.setFill(Color.WHITE);//7 строка

        rect58.setFill(Color.WHITE);
        rect60.setFill(Color.WHITE);
        rect62.setFill(Color.WHITE);
        rect64.setFill(Color.WHITE);//8 строка

        Circle CirArr[] = {circ1, circ2, circ3, circ4, circ5, circ6, circ7, circ8, circ9, circ10,
                circ11, circ12, circ13, circ14, circ15, circ16, circ17, circ18, circ19, circ20, circ21, circ22, circ23, circ24};

        Rectangle arr[] = {rect1, rect2, rect3, rect4, rect5, rect6, rect7, rect8, rect9, rect10, rect11, rect12, rect13,
                rect14, rect15, rect16, rect17, rect18, rect19, rect20, rect21, rect22, rect23, rect24, rect25, rect26, rect27, rect28,
                rect29, rect30, rect31, rect32, rect33, rect34, rect35, rect36, rect37, rect38, rect39, rect40, rect41, rect42, rect43,
                rect44, rect45, rect46, rect47, rect48, rect49, rect50, rect51, rect52, rect53, rect54, rect55, rect56, rect57, rect58,
                rect59, rect60, rect61, rect62, rect63, rect64};

        Group root = new Group(rect1, rect2, rect3, rect4, rect5, rect6, rect7, rect8, rect9, rect10, rect11, rect12, rect13,
                rect14, rect15, rect16, rect17, rect18, rect19, rect20, rect21, rect22, rect23, rect24, rect25, rect26, rect27, rect28,
                rect29, rect30, rect31, rect32, rect33, rect34, rect35, rect36, rect37, rect38, rect39, rect40, rect41, rect42, rect43,
                rect44, rect45, rect46, rect47, rect48, rect49, rect50, rect51, rect52, rect53, rect54, rect55, rect56, rect57, rect58,
                rect59, rect60, rect61, rect62, rect63, rect64, circ1, circ2, circ3, circ4, circ5, circ6, circ7, circ8, circ9, circ10,
                circ11, circ12, circ13, circ14, circ15, circ16, circ17, circ18, circ19, circ20, circ21, circ22, circ23, circ24);
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                map.put(cords[i][j], arr[j + i * 8]);
            }
        }

        Chip[] chips = new Chip[24];

        for (int i = 0; i < 24; i++) { //создал объекты фишки
            chips[i] = new Chip((int) CirArr[i].getCenterX(), (int) CirArr[i].getCenterY(), CirArr[i], false);
        }
        Rect rec[] = new Rect[64];
        for (int i = 0; i < arr.length; i++) {
            rec[i] = new Rect(null, arr[i]);
        }
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (((j % 2 + i % 2) % 2 == 0) && ((i <= 2) || (i > 4))) {
                    rec[j + i * 8].chip = chips[count];
                    count += 1;
                }
            }
        }

        Scene scene = new Scene(root, 500, 500, Color.DARKGOLDENROD);
        stage.setTitle("Chip");
        stage.setScene(scene);
        stage.show();
        //stage.setAlwaysOnTop(true);

        for (int i = 0; i < 64; i++) { // обработчик нажатия на клетку
            int finalI = i;
            arr[i].setOnMouseClicked((MouseEvent event) -> {
                System.out.println("mouseClicked");
                if (arr[finalI].getFill() == Color.GREEN) controller.Move(rec[finalI], arr, chips, rec);
            });
            if (rec[i].hasChip()) {
                rec[i].chip.circle.setOnMouseClicked((MouseEvent event) -> {
                    System.out.println("mouseClicked");
                    if ((controller.turn == Turn.WHITE && rec[finalI].chip.circle.getFill() == Color.WHITE) ||
                    (controller.turn == Turn.BLACK && rec[finalI].chip.circle.getFill() == Color.GRAY)){
                        controller.HilightPosMove(rec[finalI].chip, chips, arr, map,rec);
                        controller.selectedChip = rec[finalI].chip;
                    }
                });
            }

            //   for (int i = 0; i < 24; i++) { // обработчик нажатия на фишку
            //     int finalI = i;
            //   CirArr[i].setOnMouseClicked((MouseEvent event) -> {
            //     System.out.println("mouseClicked");
            //   controller.HilightPosMove(chips[finalI], chips, arr, map);
            //});
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getFill() == Color.GREEN) {
                arr[i].setOnMouseClicked((MouseEvent event) -> {
                    System.out.println("step");
                });
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}