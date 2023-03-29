package com.example.game_dem1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Map;
public class HelloController {
    public void initBoard(Rectangle[] rect) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((j % 2 + i % 2) % 2 == 0) {
                    rect[j + i * 8].setFill(Color.WHITE);
                } else rect[j + i * 8].setFill(Color.BLACK);
            }
        }
    }

    public Chip selectedChip;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public Turn turn = Turn.WHITE;

    public void HilightPosMove(Chip cir, Chip[] chips, Rectangle[] rects, Map<Cord, Rectangle> map,Rect[] rect) {
        initBoard(rects);
        int X = cir.getX();
        int Y = cir.getY();
        int Xld=0;
        int Yld=0;
        int Xrd=0;
        int Yrd=0;
        boolean rd = true;
        boolean ld = true;
        boolean rdEat=true;
        boolean ldEat=true;
        int indexremovechip;
        if (turn == Turn.WHITE ) {
            for (int i = 0; i < chips.length; i++) {
                if (((X + 55) == chips[i].getX() && (Y - 55) == chips[i].getY())&&(cir.circle.getFill()==Color.WHITE)) {
                    rd = false;
                    //if (){
                            //(X+110)!=chips[i].getX() && (Y - 110) != chips[i].getY()&&chips[i].circle.getFill()==Color.GRAY)
                      //  rdEat=true;
                        //rd =true;
                        indexremovechip=i;
                    //}
                    Xrd=X+55;
                    Yrd=Y-55;
                }
                if (((X - 55) == chips[i].getX() && (Y - 55) == chips[i].getY()&&(cir.circle.getFill()==Color.WHITE))) {
                    ld = false;
                    //if (((X-110)!=chips[i].getX() && (Y + 110) != chips[i].getY())&&chips[i].circle.getFill()==Color.GRAY){
                      //  ldEat=true;
                        //ld =true;
                        indexremovechip=i;
                    //}
                    Xld=X-55;
                    Yld=Y-55;
                }
            }
            //for (var cord : map.entrySet()) {
            //}
            for (int i=0;i<chips.length;i++){
                if (chips[i].getX()==(Xld-55) && chips[i].getY()==(Yld-55)){
                    ldEat=false;
                }
                if (chips[i].getX()==(Xrd+55) && chips[i].getY()==(Yrd-55)){
                    rdEat=false;
                }
                if (chips[i].getX()==(Xld) && chips[i].getY()==(Yld) && chips[i].circle.getFill()==Color.WHITE){
                    ldEat=false;
                }
                if (chips[i].getX()==(Xrd) && chips[i].getY()==(Yrd) && chips[i].circle.getFill()==Color.WHITE){
                    rdEat=false;
                }
            }

            if (X + 30 <= 435 && Y - 80 >= 50 && rd) {
                System.out.println(rd);
                map.get(new Cord(X + 30, Y - 80)).setFill(Color.GREEN);
            }
            if (X - 80 >= 50 && Y - 80 >= 50 && ld) {
                System.out.println(ld);
                map.get(new Cord(X - 80, Y - 80)).setFill(Color.GREEN);
            }
            if (X + 85 <= 435 && Y - 135 >= 50 && rdEat && !rd) {//могу укусить в право?
                System.out.println(rdEat);
                map.get(new Cord(X + 85, Y - 135)).setFill(Color.GREEN);
            }
            if (X - 135 >= 50 && Y - 135 >= 50 && ldEat && !ld) {//могу укусить в лево?
                System.out.println(ldEat);
                map.get(new Cord(X - 135, Y - 135)).setFill(Color.GREEN);
            }
        }
        if (turn == Turn.BLACK) {
            for (int i = 0; i < chips.length; i++) {
                if ((X + 55) == chips[i].getX() && (Y + 55) == chips[i].getY()&&(cir.circle.getFill()==Color.GRAY)) {
                    rd = false;
                    indexremovechip=i;
                    Xrd=X+55;
                    Yrd=Y+55;
                }
                if ((X - 55) == chips[i].getX() && (Y + 55) == chips[i].getY()&&(cir.circle.getFill()==Color.GRAY)) {
                    ld = false;
                    indexremovechip=i;
                    Xld=X-55;
                    Yld=Y+55;
                }
            }

            for (int i=0;i<chips.length;i++){
                if (chips[i].getX()==(Xld-55) && chips[i].getY()==(Yld+55)){
                    ldEat=false;
                }
                if (chips[i].getX()==(Xrd+55) && chips[i].getY()==(Yrd+55)){
                    rdEat=false;
                }
                if (chips[i].getX()==(Xld) && chips[i].getY()==(Yld) && chips[i].circle.getFill()==Color.GRAY){
                    ldEat=false;
                }
                if (chips[i].getX()==(Xrd) && chips[i].getY()==(Yrd) && chips[i].circle.getFill()==Color.GRAY){
                    rdEat=false;
                }
            }

            if (X + 30 <= 435 && Y + 30 <= 435 && rd) {
                System.out.println(rd);
                map.get(new Cord(X + 30, Y + 30)).setFill(Color.GREEN);
            }
            if (X - 80 >= 50 && Y + 30 <= 435 && ld) {
                System.out.println(ld);
                map.get(new Cord(X - 80, Y + 30)).setFill(Color.GREEN);
            }
            if (X + 85 <= 435 && Y +85 <= 435 && rdEat && !rd) { // могу укусить влево?
                System.out.println(rdEat);
                map.get(new Cord(X + 85, Y +85)).setFill(Color.GREEN);
            }
            if (X - 135 >= 50 && Y +85 <= 435 && ldEat && !ld) {// могу укусить в право?
                System.out.println(ldEat);
                map.get(new Cord(X - 135, Y +85)).setFill(Color.GREEN);
            }
        }

    }

    public void Move(Rect rect, Rectangle[] recs) {
        selectedChip.moveChip((int) (rect.rectangle.getX() + 25), (int) (rect.rectangle.getY() + 25));
        if (rect.rectangle.getFill() == Color.GREEN) {
            System.out.println("step");
        }
        if (turn == Turn.WHITE) turn = Turn.BLACK;
        else turn = Turn.WHITE;
        initBoard(recs);
    }
}

