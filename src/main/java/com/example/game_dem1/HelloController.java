package com.example.game_dem1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
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
    public boolean canEat = false;
    public void HilightPosMove(Chip cir, Chip[] chips, Rectangle[] rects, Map<Cord, Rectangle> map,Rect[] rect) {
        initBoard(rects);
        int X = cir.getX();
        int Y = cir.getY();
        int Xld=0;
        int Yld=0;
        int Xrd=0;
        int Yrd=0;
        int underXld=0;
        int underXrd=0;
        int underYrd=0;
        int underYld=0;
        boolean rd = true;
        boolean ld = true;
        boolean underRd = true;
        boolean underLd = true;
        boolean underLdEat = true;
        boolean underRdEat = true;
        boolean rdEat=true;
        boolean ldEat=true;
        boolean eats = false;
        int indexremovechip;
        if (turn == Turn.WHITE ) {
            for (int i = 0; i < rect.length; i++) {
                if (rect[i].hasChip() && (((X + 55) == rect[i].chip.getX() && (Y - 55) == rect[i].chip.getY())&&(cir.circle.getFill()==Color.WHITE))) {
                    rd = false; //ход по правой диаг на 1 клетку для белых (просто проверка есть ли фишка)
                    //if (){
                            //(X+110)!=chips[i].getX() && (Y - 110) != chips[i].getY()&&chips[i].circle.getFill()==Color.GRAY)
                      //  rdEat=true;
                        //rd =true;
                        indexremovechip=i;
                    //}
                    Xrd=X+55;
                    Yrd=Y-55;
                }
                if (rect[i].hasChip() && (((X - 55) == rect[i].chip.getX() && (Y - 55) == rect[i].chip.getY()&&(cir.circle.getFill()==Color.WHITE)))) {
                    ld = false; //ход по левой диаг на 1 клетку для белых (просто проверка есть ли фишка )
                    //if (((X-110)!=chips[i].getX() && (Y + 110) != chips[i].getY())&&chips[i].circle.getFill()==Color.GRAY){
                      //  ldEat=true;
                        //ld =true;
                        indexremovechip=i;
                    //}
                    Xld=X-55;
                    Yld=Y-55;
                }
               //
                if (rect[i].hasChip() &&( (X + 55) == rect[i].chip.getX() && (Y + 55) == rect[i].chip.getY()&&(cir.circle.getFill()==Color.WHITE))) {
                    underRd = false;
                    indexremovechip=i;
                    underXrd=X+55;
                    underYrd=Y+55;
                }
                if (rect[i].hasChip() && ((X - 55) == rect[i].chip.getX() && (Y + 55) == rect[i].chip.getY()&&(cir.circle.getFill()==Color.WHITE))) {
                    underLd = false;
                    indexremovechip=i;
                    underXld=X-55;
                    underYld=Y+55;
                }
            //
            }
            for (int i=0;i<rect.length;i++) {
                if (rect[i].hasChip() && rect[i].chip.getX() == (Xld - 55) && rect[i].chip.getY() == (Yld - 55)) {
                    ldEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (Xrd + 55) && rect[i].chip.getY() == (Yrd - 55)) {
                    rdEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (Xld) && rect[i].chip.getY() == (Yld) && rect[i].chip.circle.getFill() == Color.WHITE) {
                    ldEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (Xrd) && rect[i].chip.getY() == (Yrd) && rect[i].chip.circle.getFill() == Color.WHITE) {
                    rdEat = false;
                }// проверка ходов вверх
                //
                if (rect[i].hasChip() && rect[i].chip.getX() == (underXrd + 55) && rect[i].chip.getY() == (underYrd + 55)) {
                    underRdEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (underXld - 55) && rect[i].chip.getY() == (underYld + 55)) {
                    underLdEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (underXrd) && rect[i].chip.getY() == (underYrd) && rect[i].chip.circle.getFill() == Color.WHITE) {
                    underRdEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (underXld) && rect[i].chip.getY() == (underYld) && rect[i].chip.circle.getFill() == Color.WHITE) {
                    underLdEat = false;
                }//проверка вниз
                //
            }


            if (X + 85 <= 435 && Y - 135 >= 50 && rdEat && !rd ) {//могу укусить в право?
                System.out.println(rdEat);
                map.get(new Cord(X + 85, Y - 135)).setFill(Color.GREEN);
                canEat=true;
            }
            if (X - 135 >= 50 && Y - 135 >= 50 && ldEat && !ld) {//могу укусить в лево?
                System.out.println(ldEat);
                map.get(new Cord(X - 135, Y - 135)).setFill(Color.GREEN);
                canEat = true;
            }
        //
            if (X + 85 <= 435 && Y + 85 <= 435 && underRdEat && !underRd) {//могу укусить в право? вниз
                System.out.println(rdEat);
                map.get(new Cord(X + 85, Y + 85)).setFill(Color.GREEN);
                canEat=true;
            }
            if (X - 135 >= 50 && Y + 85 <= 435 && underLdEat && !underLd) {//могу укусить в лево? вниз
                System.out.println(ldEat);
                map.get(new Cord(X - 135, Y + 85)).setFill(Color.GREEN);
               canEat = true;
           }
            for (int i=0;i<rect.length;i++){ //если есть возможность укусить стирает обычные ходы
             if (rect[i].rectangle.getFill()==Color.GREEN){
                 rd =false;
                 ld =false;
             }
            }
            if (X + 30 <= 435 && Y - 80 >= 50 && rd  ) { // обычный ход
                System.out.println(rd);
                map.get(new Cord(X + 30, Y - 80)).setFill(Color.GREEN);
            }
            if (X - 80 >= 50 && Y - 80 >= 50 && ld ) {  // обычный ход
                System.out.println(ld);
                map.get(new Cord(X - 80, Y - 80)).setFill(Color.GREEN);
            }
        }
        if (turn == Turn.BLACK) {
            for (int i = 0; i < rect.length; i++) {
                if (rect[i].hasChip() &&( (X + 55) == rect[i].chip.getX() && (Y + 55) == rect[i].chip.getY()&&(cir.circle.getFill()==Color.GRAY))) {
                    ld = false;
                    indexremovechip=i;
                    Xld=X+55;
                    Yld=Y+55;
                }
                if (rect[i].hasChip() && ((X - 55) == rect[i].chip.getX() && (Y + 55) == rect[i].chip.getY()&&(cir.circle.getFill()==Color.GRAY))) {
                    rd = false;
                    indexremovechip=i;
                    Xrd=X-55;
                    Yrd=Y+55;
                }
                if (rect[i].hasChip() &&( (X + 55) == rect[i].chip.getX() && (Y - 55) == rect[i].chip.getY()&&(cir.circle.getFill()==Color.GRAY))) {
                    underLd = false;
                    indexremovechip=i;
                    underXld=X+55;
                    underYld=Y-55;
                }
                if (rect[i].hasChip() && ((X - 55) == rect[i].chip.getX() && (Y - 55) == rect[i].chip.getY()&&(cir.circle.getFill()==Color.GRAY))) {
                    underRd = false;
                    indexremovechip=i;
                    underXrd=X-55;
                    underYrd=Y-55;
                }
            }

            for (int i=0;i<rect.length;i++){
                if (rect[i].hasChip() && rect[i].chip.getX()==(Xrd-55) && rect[i].chip.getY()==(Yrd+55)){
                    rdEat=false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX()==(Xld+55) && rect[i].chip.getY()==(Yld+55)){
                    ldEat=false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX()==(Xrd) && rect[i].chip.getY()==(Yrd) && rect[i].chip.circle.getFill()==Color.GRAY){
                    rdEat=false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX()==(Xld) && rect[i].chip.getY()==(Yld) && rect[i].chip.circle.getFill()==Color.GRAY){
                    ldEat=false;
                }
                //проверка ходов вверх
                if (rect[i].hasChip() && rect[i].chip.getX() == (underXld + 55) && rect[i].chip.getY() == (underYld - 55)) {
                    underLdEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (underXrd - 55) && rect[i].chip.getY() == (underYrd - 55)) {
                    underRdEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (underXld) && rect[i].chip.getY() == (underYld) && rect[i].chip.circle.getFill() == Color.GRAY) {
                    underLdEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (underXrd) && rect[i].chip.getY() == (underYrd) && rect[i].chip.circle.getFill() == Color.GRAY) {
                    underRdEat = false;
                }//проверка вниз
            }
            if (X + 85 <= 435 && Y +85 <= 435 && ldEat && !ld) { // могу укусить влево?
                System.out.println(ldEat);
                map.get(new Cord(X + 85, Y +85)).setFill(Color.GREEN);
            }
            if (X - 135 >= 50 && Y +85 <= 435 && rdEat && !rd) {// могу укусить в право?
                System.out.println(rdEat);
                map.get(new Cord(X - 135, Y +85)).setFill(Color.GREEN);
            }
            if (X -135 >= 50 && Y - 135 >= 50 && underRdEat && !underRd) {//могу укусить в право? вниз
                System.out.println(rdEat);
                map.get(new Cord(X - 135, Y - 135)).setFill(Color.GREEN);
                canEat=true;
            }
            if (X +85 <= 435 && Y - 135 >= 50 && underLdEat && !underLd) {//могу укусить в лево? вниз
                System.out.println(ldEat);
                map.get(new Cord(X + 85, Y - 135)).setFill(Color.GREEN);
                canEat = true;
            }
            for (int i=0;i<rect.length;i++){ //если есть возможность укусить стирает обычные ходы
                if (rect[i].rectangle.getFill()==Color.GREEN){
                    rd =false;
                    ld =false;
                }}
                for (int i=0;i<rect.length;i++){ //если есть возможность укусить стирает обычные ходы
                    if (rect[i].rectangle.getFill()==Color.GREEN){
                        rd =false;
                        ld =false;
                    }
                }
                if (X + 30 <= 435 && Y + 30 <= 435 && ld) {
                    System.out.println(rd);
                    map.get(new Cord(X + 30, Y + 30)).setFill(Color.GREEN);
                }
                if (X - 80 >= 50 && Y + 30 <= 435 && rd) {
                    System.out.println(ld);
                    map.get(new Cord(X - 80, Y + 30)).setFill(Color.GREEN);
                }
        }
    }

    public void Move(Rect rect, Rectangle[] recs, Chip[] chips, Rect[] rec) {
        int X = selectedChip.getX();
        int Y = selectedChip.getY();
        selectedChip.moveChip((int) (rect.rectangle.getX() + 25), (int) (rect.rectangle.getY() + 25));
        if (rect.rectangle.getFill() == Color.GREEN) {
            System.out.println("step");
        }

        if (turn == Turn.WHITE && selectedChip.circle.getFill()==Color.WHITE){
            for (int i =0; i< rec.length;i++){
                if (rec[i].hasChip() &&(selectedChip.getX()-X >0)&&(selectedChip.getY()-Y <0)&&(rec[i].chip.getX()==X+55
                        && rec[i].chip.getY()==Y-55) && rec[i].chip.circle.getFill()==Color.GRAY){
                    rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                    rec[i].chip.circle.setCenterX(10);
                    rec[i].chip.circle.setCenterY(10);
                    rec[i].removeChip();// не использовать chips[] пока хз надо удалить фишку
                    break;
                }
                if (rec[i].hasChip() &&(selectedChip.getX()-X <0)&&(selectedChip.getY()-Y <0)&&(rec[i].chip.getX()==X-55
                        && rec[i].chip.getY()==Y-55) && rec[i].chip.circle.getFill()==Color.GRAY){
                    rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                    rec[i].chip.circle.setCenterX(10);
                    rec[i].chip.circle.setCenterY(10);
                    rec[i].removeChip();// не использовать chips[] пока хз надо удалить фишку
                    break;
                }
                if (rec[i].hasChip() &&(selectedChip.getX()-X >0)&&(selectedChip.getY()-Y >0)&&(rec[i].chip.getX()==X+55
                        && rec[i].chip.getY()==Y+55) && rec[i].chip.circle.getFill()==Color.GRAY){
                    rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                    rec[i].chip.circle.setCenterX(10);
                    rec[i].chip.circle.setCenterY(10);
                    rec[i].removeChip();// не использовать chips[] пока хз надо удалить фишку
                    break;
                }
                if (rec[i].hasChip() &&(selectedChip.getX()-X <0)&&(selectedChip.getY()-Y >0) &&(rec[i].chip.getX()==X-55
                        && rec[i].chip.getY()==Y+55) && rec[i].chip.circle.getFill()==Color.GRAY){
                    rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                    rec[i].chip.circle.setCenterX(10);
                    rec[i].chip.circle.setCenterY(10);
                    rec[i].removeChip();// не использовать chips[] пока хз надо удалить фишку
                    break;
                }
            }
        }
        if (turn == Turn.BLACK && selectedChip.circle.getFill()==Color.GRAY){
            for (int i =0; i< rec.length;i++){
                if (rec[i].hasChip() &&(selectedChip.getX()-X >0)&&(rec[i].chip.getX()==X+55
                        && rec[i].chip.getY()==Y+55) && rec[i].chip.circle.getFill()==Color.WHITE){
                    rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                    rec[i].chip.circle.setCenterX(10);
                    rec[i].chip.circle.setCenterY(10);
                    rec[i].removeChip();// не использовать chips[] пока хз надо удалить фишку
                    break;
                }
                if (rec[i].hasChip() &&(selectedChip.getX()-X <0)&&(rec[i].chip.getX()==X-55
                        && rec[i].chip.getY()==Y+55) && rec[i].chip.circle.getFill()==Color.WHITE){
                    rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                    rec[i].chip.circle.setCenterX(10);
                    rec[i].chip.circle.setCenterY(10);
                    rec[i].removeChip();// не использовать chips[] пока хз надо удалить фишку
                    break;
                }
                if (rec[i].hasChip() &&(selectedChip.getX()-X >0)&&(selectedChip.getY()-Y <0)&&(rec[i].chip.getX()==X+55
                        && rec[i].chip.getY()==Y-55) && rec[i].chip.circle.getFill()==Color.WHITE){
                    rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                    rec[i].chip.circle.setCenterX(10);
                    rec[i].chip.circle.setCenterY(10);
                    rec[i].removeChip();// не использовать chips[] пока хз надо удалить фишку
                    break;
                }
                if (rec[i].hasChip() &&(selectedChip.getX()-X <0)&&(selectedChip.getY()-Y <0) &&(rec[i].chip.getX()==X-55
                        && rec[i].chip.getY()==Y-55) && rec[i].chip.circle.getFill()==Color.WHITE){
                    rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                    rec[i].chip.circle.setCenterX(10);
                    rec[i].chip.circle.setCenterY(10);
                    rec[i].removeChip();// не использовать chips[] пока хз надо удалить фишку
                    break;
                }
            }
        if (selectedChip.circle.getFill()==Color.WHITE && (selectedChip.getX()+55 <=460 || selectedChip.getX()-55>=75)
                && (selectedChip.getY()+55 <=460)){
            for (int i=0;i<rec.length;i++){
                if (rec[i].hasChip() && rec[i].chip.circle.getFill()==Color.GRAY
                        && (rec[i].chip.getX()== selectedChip.getX()+55 && rec[i].chip.getY() == selectedChip.getY()+55)){
                    canEat=true;
                }
            }
        }
        }
        if (turn == Turn.WHITE) turn = Turn.BLACK;
        else turn = Turn.WHITE;
        initBoard(recs);
    }
}

