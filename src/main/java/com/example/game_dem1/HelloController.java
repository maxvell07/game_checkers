package com.example.game_dem1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Calendar;
import java.util.Map;

public class HelloController {
    public void initBoard(Rectangle[] rect, Rect[] rec) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((j % 2 + i % 2) % 2 == 0) {
                    rect[j + i * 8].setFill(Color.WHITE);
                } else rect[j + i * 8].setFill(Color.BLACK);
            }

        }
        checknewqueens(rec);
        for (int j=0;j<rect.length;j++){
            if (rec[j].hasChip()){
            if ((rec[j].chip.circle.getFill()== Color.WHEAT || rec[j].chip.circle.getFill()== Color.CADETBLUE)){
                rec[j].chip.queen=true;
            }
        }}
    }

    public Chip selectedChip;

    public Turn turn = Turn.WHITE;
    String White = "WHITE";
    String Black = "BLACK";
    boolean canEatQueen = false;

    public void HilightPosMove(Chip cir, Rectangle[] rects, Map<Cord, Rectangle> map, Rect[] rect) {
        initBoard(rects, rect);
        canEatQueen=false;
        for (int i = 0; i < rect.length; i++) {//1
            if (rect[i].hasChip()){
                rect[i].rectangle.setFill(Color.RED);
                if (rect[i].chip.circle.getFill()==Color.CADETBLUE){
                    rect[i].chip.queen=true;
                }
            }}
        int X = cir.getX();
        int Y = cir.getY();
        int Xld = 0;
        int Yld = 0;
        int Xrd = 0;
        int Yrd = 0;
        int underXld = 0;
        int underXrd = 0;
        int underYrd = 0;
        int underYld = 0;
        boolean rd = true;
        boolean ld = true;
        boolean underRd = true;
        boolean underLd = true;
        boolean underLdEat = true;
        boolean underRdEat = true;
        boolean rdEat = true;
        boolean ldEat = true;
        if (turn == Turn.WHITE && !cir.queen && cir.circle.getFill()==Color.WHITE ) {
            for (int i = 0; i < rect.length; i++) {
                if (rect[i].hasChip() && (((X + 55) == rect[i].chip.getX() && (Y - 55) == rect[i].chip.getY()) && (cir.circle.getFill() == Color.WHITE))){
                    rd = false; //ход по правой диаг на 1 клетку для белых (просто проверка есть ли фишка)
                    //}
                    Xrd = X + 55;
                    Yrd = Y - 55;
                }
                if (rect[i].hasChip() && (((X - 55) == rect[i].chip.getX() && (Y - 55) == rect[i].chip.getY() && (cir.circle.getFill() == Color.WHITE)))) {
                    ld = false; //ход по левой диаг на 1 клетку для белых (просто проверка есть ли фишка)
                    //}
                    Xld = X - 55;
                    Yld = Y - 55;
                }
                //
                if (rect[i].hasChip() && ((X + 55) == rect[i].chip.getX() && (Y + 55) == rect[i].chip.getY() && (cir.circle.getFill() == Color.WHITE))) {
                    underRd = false;
                    underXrd = X + 55;
                    underYrd = Y + 55;
                }
                if (rect[i].hasChip() && ((X - 55) == rect[i].chip.getX() && (Y + 55) == rect[i].chip.getY() && (cir.circle.getFill() == Color.WHITE))) {
                    underLd = false;

                    underXld = X - 55;
                    underYld = Y + 55;
                }
                //
            }
            for (int i = 0; i < rect.length; i++) {
                if (rect[i].hasChip() && rect[i].chip.getX() == (Xld - 55) && rect[i].chip.getY() == (Yld - 55)) {
                    ldEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (Xrd + 55) && rect[i].chip.getY() == (Yrd - 55)) {
                    rdEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (Xld) && rect[i].chip.getY() == (Yld) && (rect[i].chip.circle.getFill() == Color.WHITE || rect[i].chip.circle.getFill() == Color.WHEAT)) {
                    ldEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (Xrd) && rect[i].chip.getY() == (Yrd) && (rect[i].chip.circle.getFill() == Color.WHITE || rect[i].chip.circle.getFill() == Color.WHEAT)) {
                    rdEat = false;
                }// проверка ходов вверх
                //
                if (rect[i].hasChip() && rect[i].chip.getX() == (underXrd + 55) && rect[i].chip.getY() == (underYrd + 55)) {
                    underRdEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (underXld - 55) && rect[i].chip.getY() == (underYld + 55)) {
                    underLdEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (underXrd) && rect[i].chip.getY() == (underYrd) && (rect[i].chip.circle.getFill() == Color.WHITE || rect[i].chip.circle.getFill() == Color.WHEAT)) {
                    underRdEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (underXld) && rect[i].chip.getY() == (underYld) && (rect[i].chip.circle.getFill() == Color.WHITE || rect[i].chip.circle.getFill() == Color.WHEAT)) {
                    underLdEat = false;
                }//проверка вниз
                //
            }

            if (X + 85 <= 435 && Y - 135 >= 50 && rdEat && !rd) {//могу укусить в право?
                map.get(new Cord(X + 85, Y - 135)).setFill(Color.GREEN);
            }
            if (X - 135 >= 50 && Y - 135 >= 50 && ldEat && !ld) {//могу укусить в лево?
                map.get(new Cord(X - 135, Y - 135)).setFill(Color.GREEN);
            }
            //
            if (X + 85 <= 435 && Y + 85 <= 435 && underRdEat && !underRd) {//могу укусить в право? вниз
                map.get(new Cord(X + 85, Y + 85)).setFill(Color.GREEN);
            }
            if (X - 135 >= 50 && Y + 85 <= 435 && underLdEat && !underLd) {//могу укусить в лево? вниз
                map.get(new Cord(X - 135, Y + 85)).setFill(Color.GREEN);
            }
            for (int i = 0; i < rect.length; i++) { //если есть возможность укусить стирает обычные ходы
                if (rect[i].rectangle.getFill() == Color.GREEN) {
                    rd = false;
                    ld = false;
                }
            }


            if (findEatMoves(rect, White) || findQueenQatMoves(rect,White)) {
                rd = false;
                ld = false;
            }
            if (X + 30 <= 435 && Y - 80 >= 50 && rd) { // обычный ход

                map.get(new Cord(X + 30, Y - 80)).setFill(Color.GREEN);
            }
            if (X - 80 >= 50 && Y - 80 >= 50 && ld) {  // обычный ход

                map.get(new Cord(X - 80, Y - 80)).setFill(Color.GREEN);
            }
        }
        if (turn == Turn.BLACK && !cir.queen &&cir.circle.getFill()==Color.GRAY) {
            for (int i = 0; i < rect.length; i++) {
                if (rect[i].hasChip() && ((X + 55) == rect[i].chip.getX() && (Y + 55) == rect[i].chip.getY() && (cir.circle.getFill() == Color.GRAY))) {
                    ld = false;
                    Xld = X + 55;
                    Yld = Y + 55;
                }
                if (rect[i].hasChip() && ((X - 55) == rect[i].chip.getX() && (Y + 55) == rect[i].chip.getY() && (cir.circle.getFill() == Color.GRAY))) {
                    rd = false;
                    Xrd = X - 55;
                    Yrd = Y + 55;
                }
                if (rect[i].hasChip() && ((X + 55) == rect[i].chip.getX() && (Y - 55) == rect[i].chip.getY() && (cir.circle.getFill() == Color.GRAY))) {
                    underLd = false;
                    underXld = X + 55;
                    underYld = Y - 55;
                }
                if (rect[i].hasChip() && ((X - 55) == rect[i].chip.getX() && (Y - 55) == rect[i].chip.getY() && (cir.circle.getFill() == Color.GRAY))) {
                    underRd = false;
                    underXrd = X - 55;
                    underYrd = Y - 55;
                }
            }

            for (int i = 0; i < rect.length; i++) {
                if (rect[i].hasChip() && rect[i].chip.getX() == (Xrd - 55) && rect[i].chip.getY() == (Yrd + 55)) {
                    rdEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (Xld + 55) && rect[i].chip.getY() == (Yld + 55)) {
                    ldEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (Xrd) && rect[i].chip.getY() == (Yrd) && (rect[i].chip.circle.getFill() == Color.GRAY || rect[i].chip.circle.getFill() == Color.CADETBLUE)) {
                    rdEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (Xld) && rect[i].chip.getY() == (Yld) && (rect[i].chip.circle.getFill() == Color.GRAY || rect[i].chip.circle.getFill() == Color.CADETBLUE)) {
                    ldEat = false;
                }
                //проверка ходов вверх
                if (rect[i].hasChip() && rect[i].chip.getX() == (underXld + 55) && rect[i].chip.getY() == (underYld - 55)) {
                    underLdEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (underXrd - 55) && rect[i].chip.getY() == (underYrd - 55)) {
                    underRdEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (underXld) && rect[i].chip.getY() == (underYld) && (rect[i].chip.circle.getFill() == Color.GRAY || rect[i].chip.circle.getFill() == Color.CADETBLUE)) {
                    underLdEat = false;
                }
                if (rect[i].hasChip() && rect[i].chip.getX() == (underXrd) && rect[i].chip.getY() == (underYrd) && (rect[i].chip.circle.getFill() == Color.GRAY || rect[i].chip.circle.getFill() == Color.CADETBLUE)) {
                    underRdEat = false;
                }//проверка вниз
            }
            if (X + 85 <= 435 && Y + 85 <= 435 && ldEat && !ld) { // могу укусить влево?
                map.get(new Cord(X + 85, Y + 85)).setFill(Color.GREEN);
            }
            if (X - 135 >= 50 && Y + 85 <= 435 && rdEat && !rd) {// могу укусить в право?
                map.get(new Cord(X - 135, Y + 85)).setFill(Color.GREEN);
            }
            if (X - 135 >= 50 && Y - 135 >= 50 && underRdEat && !underRd) {//могу укусить в право? вниз
                map.get(new Cord(X - 135, Y - 135)).setFill(Color.GREEN);
            }
            if (X + 85 <= 435 && Y - 135 >= 50 && underLdEat && !underLd) {//могу укусить в лево? вниз
                map.get(new Cord(X + 85, Y - 135)).setFill(Color.GREEN);

            }
            for (int i = 0; i < rect.length; i++) { //если есть возможность укусить стирает обычные ходы
                if (rect[i].rectangle.getFill() == Color.GREEN) {
                    rd = false;
                    ld = false;
                }
            }
            if (findEatMoves(rect, Black) || findQueenQatMoves(rect, Black)) {
                rd = false;
                ld = false;
            }
            if (X + 30 <= 435 && Y + 30 <= 435 && ld) {

                map.get(new Cord(X + 30, Y + 30)).setFill(Color.GREEN);
            }
            if (X - 80 >= 50 && Y + 30 <= 435 && rd) {

                map.get(new Cord(X - 80, Y + 30)).setFill(Color.GREEN);
            }
        }
        for (int i=0;i<rect.length;i++){
            if (rect[i].hasChip() && (rect[i].chip.circle.getFill()==Color.CADETBLUE || rect[i].chip.circle.getFill()==Color.WHEAT)){
                rect[i].chip.queen=true;
            }
        }
        if (turn==Turn.BLACK){
        if (cir!=null && cir.queen && cir.circle.getFill()==Color.CADETBLUE  && findQueenQatMoves(rect, Black) ){ // cьедения дамкой черных вниз в право
                int stepX=55;
                int stepY=55;
                int ind =-1;
                int checks=0;
                boolean not =true;
                boolean queeneat = false;
                while (cir.getX()+stepX<=460 && cir.getY()+stepY<=460 && checks==0 && not){ //
                    for (int j=0;j< rect.length;j++){
                        if ((rect[j].rectangle.getX()==(cir.getX()-25)+stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)+stepY)){
                            if (rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.CADETBLUE || rect[j].chip.circle.getFill()==Color.GRAY)){
                                queeneat =false;
                                not =false;
                                checks=0;
                                break;
                            } else if ( not && rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.WHEAT || rect[j].chip.circle.getFill()==Color.WHITE)){
                                ind =j;
                                checks++;
                                break;}}}
                    stepX+=55;
                    stepY+=55;}
                if (checks==0 && not){queeneat =false;} else queeneat =true;
                if (queeneat && not) {
                    for (int i = 0; i < rect.length; i++) {
                        if ((rect[i].rectangle.getX() == (cir.getX() - 25) + stepX) && (rect[i].rectangle.getY() == (cir.getY() - 25) + stepY) && !rect[i].hasChip()) {
                            canEatQueen = true;
                            queeneat =true;
                        } else if ((rect[i].rectangle.getX() == (cir.getX() - 25) + stepX) && (rect[i].rectangle.getY() == (cir.getY() - 25) + stepY) && rect[i].hasChip()) {
                            queeneat=false;
                        }
                    }
                }
                stepX=55;
                stepY=55;
                if (queeneat && not){
                    while (rect[ind].chip.getX()+stepX<=460 && rect[ind].chip.getY()+stepY<=460 && queeneat){
                        if ( rect[ind].chip.circle.getFill()==Color.WHEAT || rect[ind].chip.circle.getFill()==Color.WHITE) {
                            for (int j = 0; j < rect.length; j++) {
                                if (rect[j].rectangle.getX()==(rect[ind].chip.getX()+stepX-25) && rect[j].rectangle.getY()==(rect[ind].chip.getY()+stepY-25) && !rect[j].hasChip()){
                                    rect[j].rectangle.setFill(Color.GREEN);
                                } else if (rect[j].rectangle.getX()==(rect[ind].chip.getX()+stepX-25) && rect[j].rectangle.getY()==(rect[ind].chip.getY()+stepY-25) && rect[j].hasChip()){
                                    queeneat=false;
                                    break;
                                }
                            }
                        } else break;
                        stepX+=55;
                        stepY+=55;
                    }}
            }if (cir!=null && cir.queen && cir.circle.getFill()==Color.CADETBLUE && findQueenQatMoves(rect, Black)){ // cьедения дамкой черных вниз влево
                int stepX=55;
                int stepY=55;
                int ind =-1;
                int checks=0;
                boolean not =true;
                boolean queeneat = false;
                while (cir.getX()-stepX>=75 && cir.getY()+stepY<=460 && checks==0 && not){ //
                    for (int j=0;j< rect.length;j++){
                        if ((rect[j].rectangle.getX()==(cir.getX()-25)-stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)+stepY)){
                            if (rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.CADETBLUE || rect[j].chip.circle.getFill()==Color.GRAY)){
                                queeneat =false;
                                not =false;
                                checks=0;
                                break;
                            } else if ( not && rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.WHEAT || rect[j].chip.circle.getFill()==Color.WHITE)){
                                ind =j;
                                checks++;
                                break;}}}
                    stepX+=55;
                    stepY+=55;}
                if (checks==0 && not){queeneat =false;} else queeneat =true;
                if (queeneat && not) {
                    for (int i = 0; i < rect.length; i++) {
                        if ((rect[i].rectangle.getX() == (cir.getX() - 25) - stepX) && (rect[i].rectangle.getY() == (cir.getY() - 25) + stepY) && !rect[i].hasChip()) {
                            canEatQueen = true;
                            queeneat =true;
                        } else if ((rect[i].rectangle.getX() == (cir.getX() - 25) - stepX) && (rect[i].rectangle.getY() == (cir.getY() - 25) + stepY) && rect[i].hasChip()) {
                            queeneat=false;
                        }
                    }
                }
                stepX=55;
                stepY=55;
                if (queeneat && not){
                    while (rect[ind].chip.getX()-stepX>=75 && rect[ind].chip.getY()+stepY<=460 && queeneat){
                        if ( rect[ind].chip.circle.getFill()==Color.WHEAT || rect[ind].chip.circle.getFill()==Color.WHITE) {
                            for (int j = 0; j < rect.length; j++) {
                                if (rect[j].rectangle.getX()==(rect[ind].chip.getX()-stepX-25) && rect[j].rectangle.getY()==(rect[ind].chip.getY()+stepY-25) && !rect[j].hasChip()){
                                    rect[j].rectangle.setFill(Color.GREEN);
                                } else if (rect[j].rectangle.getX()==(rect[ind].chip.getX()-stepX-25) && rect[j].rectangle.getY()==(rect[ind].chip.getY()+stepY-25) && rect[j].hasChip()){
                                    queeneat=false;
                                    break;
                                }
                            }
                        } else break;
                        stepX+=55;
                        stepY+=55;
                    }}
            }
            if (cir!=null && cir.queen && cir.circle.getFill()==Color.CADETBLUE  && findQueenQatMoves(rect, Black)){ // cьедения дамкой черных вверх влево
                    int stepX=55;
                    int stepY=55;
                    int ind =-1;
                    int checks=0;
                    boolean not =true;
                    boolean queeneat = false;
                    while (cir.getX()-stepX>=75 && cir.getY()-stepY>=75 && checks==0 && not){ //
                        for (int j=0;j< rect.length;j++){
                            if ((rect[j].rectangle.getX()==(cir.getX()-25)-stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)-stepY)){
                                if (rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.CADETBLUE || rect[j].chip.circle.getFill()==Color.GRAY)){
                                    queeneat =false;
                                    not =false;
                                    checks=0;
                                    break;
                                } else if ( not && rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.WHEAT || rect[j].chip.circle.getFill()==Color.WHITE)){
                                    ind =j;
                                    checks++;
                                    break;}}}
                        stepX+=55;
                        stepY+=55;}
                    if (checks==0 && not){queeneat =false;} else queeneat =true;
                    if (queeneat && not) {
                        for (int i = 0; i < rect.length; i++) {
                            if ((rect[i].rectangle.getX() == (cir.getX() - 25) - stepX) && (rect[i].rectangle.getY() == (cir.getY() - 25) - stepY) && !rect[i].hasChip()) {
                                canEatQueen = true;
                                queeneat =true;
                            } else if ((rect[i].rectangle.getX() == (cir.getX() - 25) - stepX) && (rect[i].rectangle.getY() == (cir.getY() - 25) - stepY) && rect[i].hasChip()) {
                                queeneat=false;
                            }
                        }
                    }
                    stepX=55;
                    stepY=55;
                    if (queeneat && not){
                        while (rect[ind].chip.getX()-stepX>=75 && rect[ind].chip.getY()-stepY>=75 && queeneat){
                            if ( rect[ind].chip.circle.getFill()==Color.WHEAT || rect[ind].chip.circle.getFill()==Color.WHITE) {
                                for (int j = 0; j < rect.length; j++) {
                                    if (rect[j].rectangle.getX()==(rect[ind].chip.getX()-stepX-25) && rect[j].rectangle.getY()==(rect[ind].chip.getY()-stepY-25) && !rect[j].hasChip()){
                                        rect[j].rectangle.setFill(Color.GREEN);
                                    } else if (rect[j].rectangle.getX()==(rect[ind].chip.getX()-stepX-25) && rect[j].rectangle.getY()==(rect[ind].chip.getY()-stepY-25) && rect[j].hasChip()){
                                        queeneat=false;
                                        break;
                                    }
                                }
                            } else break;
                            stepX+=55;
                            stepY+=55;
                        }}
                }
            if (cir!=null && cir.queen && cir.circle.getFill()==Color.CADETBLUE && findQueenQatMoves(rect, Black)){ // cьедения дамкой черных вверх в право
                    int stepX=55;
                    int stepY=55;
                    int ind =-1;
                    int checks=0;
                    boolean not =true;
                    boolean queeneat = false;
                    while (cir.getX()+stepX<=460 && cir.getY()-stepY>=75 && checks==0 && not){ //
                        for (int j=0;j< rect.length;j++){
                            if ((rect[j].rectangle.getX()==(cir.getX()-25)+stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)-stepY)){
                                if (rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.CADETBLUE || rect[j].chip.circle.getFill()==Color.GRAY)){
                                    queeneat =false;
                                    not =false;
                                    checks=0;
                                    break;
                                } else if ( not && rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.WHEAT || rect[j].chip.circle.getFill()==Color.WHITE)){
                                    ind =j;
                                    checks++;
                                    break;}}}
                        stepX+=55;
                        stepY+=55;}
                    if (checks==0 && not){queeneat =false;} else queeneat =true;
                    if (queeneat && not) {
                        for (int i = 0; i < rect.length; i++) {
                            if ((rect[i].rectangle.getX() == (cir.getX() - 25) + stepX) && (rect[i].rectangle.getY() == (cir.getY() - 25) - stepY) && !rect[i].hasChip()) {
                                canEatQueen = true;
                                queeneat =true;
                            } else if ((rect[i].rectangle.getX() == (cir.getX() - 25) + stepX) && (rect[i].rectangle.getY() == (cir.getY() - 25) - stepY) && rect[i].hasChip()) {
                                queeneat=false;
                            }
                        }
                    }
                    stepX=55;
                    stepY=55;
                    if (queeneat && not){
                        while (rect[ind].chip.getX()+stepX<=460 && rect[ind].chip.getY()-stepY>=75 && queeneat){
                            if ( rect[ind].chip.circle.getFill()==Color.WHEAT || rect[ind].chip.circle.getFill()==Color.WHITE) {
                                for (int j = 0; j < rect.length; j++) {
                                    if (rect[j].rectangle.getX()==(rect[ind].chip.getX()+stepX-25) && rect[j].rectangle.getY()==(rect[ind].chip.getY()-stepY-25) && !rect[j].hasChip()){
                                        rect[j].rectangle.setFill(Color.GREEN);
                                    } else if (rect[j].rectangle.getX()==(rect[ind].chip.getX()+stepX-25) && rect[j].rectangle.getY()==(rect[ind].chip.getY()-stepY-25) && rect[j].hasChip()){
                                        queeneat=false;
                                        break;
                                    }
                                }
                            } else break;
                            stepX+=55;
                            stepY+=55;
                        }}
                }
            //обычные ходы дамки черных
        if ( cir.queen && cir.circle.getFill()==Color.CADETBLUE && !canEatQueen && !findQueenQatMoves(rect, Black)){ //для дамок черных вправо вниз
            int stepX=55;
            int stepY=55;
            boolean as=false;
                if (cir!=null && cir.queen && cir.circle.getFill()==Color.CADETBLUE&& !findEatMoves(rect,Black)){
                    while (cir.getX()+stepX<=460 && cir.getY()+stepY<=460){
                        for (int j=0;j<rect.length;j++){
                            if (rect[j].hasChip() && (rect[j].rectangle.getX()==(cir.getX()-25)+stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)+stepY)){
                                as=true;
                            }
                            if (!as && !rect[j].hasChip() && (rect[j].rectangle.getX()==(cir.getX()-25)+stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)+stepY)){
                                rect[j].rectangle.setFill(Color.GREEN);
                            }
                        }
                        stepX+=55;
                        stepY+=55;
                    }
            }
        }
        if (cir.queen && cir.circle.getFill()==Color.CADETBLUE && !canEatQueen && !findQueenQatMoves(rect, Black)){ //для дамок черных вправо вверх
            int stepX=55;
            int stepY=55;
            boolean as =false;
            if (cir!=null && cir.queen && cir.circle.getFill()==Color.CADETBLUE&& !findEatMoves(rect,Black)){
                while (cir.getX()+stepX<=460 && cir.getY()-stepY>=75){
                    for (int j=0;j<rect.length;j++){
                        if (rect[j].hasChip() && (rect[j].rectangle.getX()==(cir.getX()-25)+stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)-stepY)){
                            as=true;
                        }
                        if (!as && !rect[j].hasChip() && (rect[j].rectangle.getX()==(cir.getX()-25)+stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)-stepY)){
                            rect[j].rectangle.setFill(Color.GREEN);
                        }
                    }
                    stepX+=55;
                    stepY+=55;
                }
            }
        }
        if (turn==Turn.BLACK && cir.queen && cir.circle.getFill()==Color.CADETBLUE&& !canEatQueen && !findQueenQatMoves(rect, Black)){ //для дамок черных влево вверх
            int stepX=55;
            int stepY=55;
            boolean as =false;
            if (cir!=null && cir.queen && cir.circle.getFill()==Color.CADETBLUE&& !findEatMoves(rect,Black)){
                while (cir.getX()-stepX>=75 && cir.getY()-stepY>=75){
                    for (int j=0;j<rect.length;j++){
                        if (rect[j].hasChip() && (rect[j].rectangle.getX()==(cir.getX()-25)-stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)-stepY)){
                            as=true;
                        }
                        if (!as && !rect[j].hasChip() && (rect[j].rectangle.getX()==(cir.getX()-25)-stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)-stepY)){
                            rect[j].rectangle.setFill(Color.GREEN);
                        }
                    }
                    stepX+=55;
                    stepY+=55;
                }
            }
        }
        if (turn==Turn.BLACK && cir.queen && cir.circle.getFill()==Color.CADETBLUE&& !canEatQueen && !findQueenQatMoves(rect, Black)){ //для дамок черных влево вниз
            int stepX=55;
            int stepY=55;
            boolean as =false;
            if (cir!=null && cir.queen && cir.circle.getFill()==Color.CADETBLUE && !findEatMoves(rect,Black)){
                while (cir.getX()-stepX>=75 && cir.getY()+stepY<=460){
                    for (int j=0;j<rect.length;j++){
                        if (rect[j].hasChip() && (rect[j].rectangle.getX()==(cir.getX()-25)-stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)+stepY)){
                            as=true;
                        }
                        if (!as && !rect[j].hasChip() && (rect[j].rectangle.getX()==(cir.getX()-25)-stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)+stepY)){
                            rect[j].rectangle.setFill(Color.GREEN);
                        }
                    }
                    stepX+=55;
                    stepY+=55;
                }
            }
            }
        }
        if (turn==Turn.WHITE){
            if (cir!=null && cir.queen && cir.circle.getFill()==Color.WHEAT && findQueenQatMoves(rect, White)){ // cьедения дамкой бедых вниз в право
                int stepX=55;
                int stepY=55;
                int ind =-1;
                int checks=0;
                boolean not =true;
                boolean queeneat = false;
                while (cir.getX()+stepX<=460 && cir.getY()+stepY<=460 && checks==0 && not){ //
                    for (int j=0;j< rect.length;j++){
                        if ((rect[j].rectangle.getX()==(cir.getX()-25)+stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)+stepY)){
                            if (rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.WHEAT || rect[j].chip.circle.getFill()==Color.WHITE)){
                                queeneat =false;
                                not =false;
                                checks=0;
                                break;
                            } else if ( not && rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.CADETBLUE || rect[j].chip.circle.getFill()==Color.GRAY)){
                                ind =j;
                                checks++;
                                break;}}}
                    stepX+=55;
                    stepY+=55;}
                if (checks==0 && not){queeneat =false;} else queeneat =true;
                if (queeneat && not) {
                    for (int i = 0; i < rect.length; i++) {
                        if ((rect[i].rectangle.getX() == (cir.getX() - 25) + stepX) && (rect[i].rectangle.getY() == (cir.getY() - 25) + stepY) && !rect[i].hasChip()) {
                            canEatQueen = true;
                            queeneat =true;
                        } else if ((rect[i].rectangle.getX() == (cir.getX() - 25) + stepX) && (rect[i].rectangle.getY() == (cir.getY() - 25) + stepY) && rect[i].hasChip()) {
                            queeneat=false;
                        }
                    }
                }
                stepX=55;
                stepY=55;
                if (queeneat && not){
                    while (rect[ind].chip.getX()+stepX<=460 && rect[ind].chip.getY()+stepY<=460 && queeneat){
                        if ( rect[ind].chip.circle.getFill()==Color.CADETBLUE || rect[ind].chip.circle.getFill()==Color.GRAY) {
                            for (int j = 0; j < rect.length; j++) {
                                if (rect[j].rectangle.getX()==(rect[ind].chip.getX()+stepX-25) && rect[j].rectangle.getY()==(rect[ind].chip.getY()+stepY-25) && !rect[j].hasChip()){
                                    rect[j].rectangle.setFill(Color.GREEN);
                                } else if (rect[j].rectangle.getX()==(rect[ind].chip.getX()+stepX-25) && rect[j].rectangle.getY()==(rect[ind].chip.getY()+stepY-25) && rect[j].hasChip()){
                                    queeneat=false;
                                    break;
                                }
                            }
                        } else break;
                        stepX+=55;
                        stepY+=55;
                    }}
            }if (cir!=null && cir.queen && cir.circle.getFill()==Color.WHEAT && findQueenQatMoves(rect, White)){ // cьедения дамкой белых вниз влево
                int stepX=55;
                int stepY=55;
                int ind =-1;
                int checks=0;
                boolean not =true;
                boolean queeneat = false;
                while (cir.getX()-stepX>=75 && cir.getY()+stepY<=460 && checks==0 && not){ //
                    for (int j=0;j< rect.length;j++){
                        if ((rect[j].rectangle.getX()==(cir.getX()-25)-stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)+stepY)){
                            if (rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.WHEAT || rect[j].chip.circle.getFill()==Color.WHITE)){
                                queeneat =false;
                                not =false;
                                checks=0;
                                break;
                            } else if ( not && rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.CADETBLUE || rect[j].chip.circle.getFill()==Color.GRAY)){
                                ind =j;
                                checks++;
                                break;}}}
                    stepX+=55;
                    stepY+=55;}
                if (checks==0 && not){queeneat =false;} else queeneat =true;
                if (queeneat && not) {
                    for (int i = 0; i < rect.length; i++) {
                        if ((rect[i].rectangle.getX() == (cir.getX() - 25) - stepX) && (rect[i].rectangle.getY() == (cir.getY() - 25) + stepY) && !rect[i].hasChip()) {
                            canEatQueen = true;
                            queeneat =true;
                        } else if ((rect[i].rectangle.getX() == (cir.getX() - 25) - stepX) && (rect[i].rectangle.getY() == (cir.getY() - 25) + stepY) && rect[i].hasChip()) {
                            queeneat=false;
                        }
                    }
                }
                stepX=55;
                stepY=55;
                if (queeneat && not){
                    while (rect[ind].chip.getX()-stepX>=75 && rect[ind].chip.getY()+stepY<=460 && queeneat){
                        if ( rect[ind].chip.circle.getFill()==Color.CADETBLUE || rect[ind].chip.circle.getFill()==Color.GRAY) {
                            for (int j = 0; j < rect.length; j++) {
                                if (rect[j].rectangle.getX()==(rect[ind].chip.getX()-stepX-25) && rect[j].rectangle.getY()==(rect[ind].chip.getY()+stepY-25) && !rect[j].hasChip()){
                                    rect[j].rectangle.setFill(Color.GREEN);
                                } else if (rect[j].rectangle.getX()==(rect[ind].chip.getX()-stepX-25) && rect[j].rectangle.getY()==(rect[ind].chip.getY()+stepY-25) && rect[j].hasChip()){
                                    queeneat=false;
                                    break;
                                }
                            }
                        } else break;
                        stepX+=55;
                        stepY+=55;
                    }}
            }
            if (cir!=null && cir.queen && cir.circle.getFill()==Color.WHEAT&& findQueenQatMoves(rect, White)){ // cьедения дамкой белых вверх влево
                int stepX=55;
                int stepY=55;
                int ind =-1;
                int checks=0;
                boolean not =true;
                boolean queeneat = false;
                while (cir.getX()-stepX>=75 && cir.getY()-stepY>=75 && checks==0 && not){ //
                    for (int j=0;j< rect.length;j++){
                        if ((rect[j].rectangle.getX()==(cir.getX()-25)-stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)-stepY)){
                            if (rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.WHEAT || rect[j].chip.circle.getFill()==Color.WHITE)){
                                queeneat =false;
                                not =false;
                                checks=0;
                                break;
                            } else if ( not && rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.CADETBLUE || rect[j].chip.circle.getFill()==Color.GRAY)){
                                ind =j;
                                checks++;
                                break;}}}
                    stepX+=55;
                    stepY+=55;}
                if (checks==0 && not){queeneat =false;} else queeneat =true;
                if (queeneat && not) {
                    for (int i = 0; i < rect.length; i++) {
                        if ((rect[i].rectangle.getX() == (cir.getX() - 25) - stepX) && (rect[i].rectangle.getY() == (cir.getY() - 25) - stepY) && !rect[i].hasChip()) {
                            canEatQueen = true;
                            queeneat =true;
                        } else if ((rect[i].rectangle.getX() == (cir.getX() - 25) - stepX) && (rect[i].rectangle.getY() == (cir.getY() - 25) - stepY) && rect[i].hasChip()) {
                            queeneat=false;
                        }
                    }
                }
                stepX=55;
                stepY=55;
                if (queeneat && not){
                    while (rect[ind].chip.getX()-stepX>=75 && rect[ind].chip.getY()-stepY>=75 && queeneat){
                        if ( rect[ind].chip.circle.getFill()==Color.CADETBLUE || rect[ind].chip.circle.getFill()==Color.GRAY) {
                            for (int j = 0; j < rect.length; j++) {
                                if (rect[j].rectangle.getX()==(rect[ind].chip.getX()-stepX-25) && rect[j].rectangle.getY()==(rect[ind].chip.getY()-stepY-25) && !rect[j].hasChip()){
                                    rect[j].rectangle.setFill(Color.GREEN);
                                } else if (rect[j].rectangle.getX()==(rect[ind].chip.getX()-stepX-25) && rect[j].rectangle.getY()==(rect[ind].chip.getY()-stepY-25) && rect[j].hasChip()){
                                    queeneat=false;
                                    break;
                                }
                            }
                        } else break;
                        stepX+=55;
                        stepY+=55;
                    }}
            }
            if (cir!=null && cir.queen && cir.circle.getFill()==Color.WHEAT&& findQueenQatMoves(rect, White)){ // cьедения дамкой белых вверх в право
                int stepX=55;
                int stepY=55;
                int ind =-1;
                int checks=0;
                boolean not =true;
                boolean queeneat = false;
                while (cir.getX()+stepX<=460 && cir.getY()-stepY>=75 && checks==0 && not){ //
                    for (int j=0;j< rect.length;j++){
                        if ((rect[j].rectangle.getX()==(cir.getX()-25)+stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)-stepY)){
                            if (rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.WHEAT || rect[j].chip.circle.getFill()==Color.WHITE)){
                                queeneat =false;
                                not =false;
                                checks=0;
                                break;
                            } else if ( not && rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.CADETBLUE || rect[j].chip.circle.getFill()==Color.GRAY)){
                                ind =j;
                                checks++;
                                break;}}}
                    stepX+=55;
                    stepY+=55;}
                if (checks==0 && not){queeneat =false;} else queeneat =true;
                if (queeneat && not) {
                    for (int i = 0; i < rect.length; i++) {
                        if ((rect[i].rectangle.getX() == (cir.getX() - 25) + stepX) && (rect[i].rectangle.getY() == (cir.getY() - 25) - stepY) && !rect[i].hasChip()) {
                            canEatQueen = true;
                            queeneat =true;
                        } else if ((rect[i].rectangle.getX() == (cir.getX() - 25) + stepX) && (rect[i].rectangle.getY() == (cir.getY() - 25) - stepY) && rect[i].hasChip()) {
                            queeneat=false;
                        }
                    }
                }
                stepX=55;
                stepY=55;
                if (queeneat && not){
                    while (rect[ind].chip.getX()+stepX<=460 && rect[ind].chip.getY()-stepY>=75 && queeneat){
                        if ( rect[ind].chip.circle.getFill()==Color.CADETBLUE || rect[ind].chip.circle.getFill()==Color.GRAY) {
                            for (int j = 0; j < rect.length; j++) {
                                if (rect[j].rectangle.getX()==(rect[ind].chip.getX()+stepX-25) && rect[j].rectangle.getY()==(rect[ind].chip.getY()-stepY-25) && !rect[j].hasChip()){
                                    rect[j].rectangle.setFill(Color.GREEN);
                                } else if (rect[j].rectangle.getX()==(rect[ind].chip.getX()+stepX-25) && rect[j].rectangle.getY()==(rect[ind].chip.getY()-stepY-25) && rect[j].hasChip()){
                                    queeneat=false;
                                    break;
                                }
                            }
                        } else break;
                        stepX+=55;
                        stepY+=55;
                    }}
            }
            //обычные ходы дамки белых
            if (cir.queen && cir.circle.getFill()==Color.WHEAT&& !canEatQueen&& !findQueenQatMoves(rect, White)){ //для дамок белых вправо вниз
                int stepX=55;
                int stepY=55;
                boolean as =false;
                boolean caneatqueen=false;
                if ( cir!=null && cir.queen && cir.circle.getFill()==Color.WHEAT && !findEatMoves(rect,White)){
                    while (cir.getX()+stepX<=460 && cir.getY()+stepY<=460){
                        for (int j=0;j<rect.length;j++){
                            if (rect[j].hasChip() && (rect[j].rectangle.getX()==(cir.getX()-25)+stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)+stepY)){
                                as=true;
                            }
                            if (!as && !rect[j].hasChip() && (rect[j].rectangle.getX()==(cir.getX()-25)+stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)+stepY)){
                                rect[j].rectangle.setFill(Color.GREEN);
                            }
                        }
                        stepX+=55;
                        stepY+=55;
                    }
                }
            }
            if (cir.queen && cir.circle.getFill()==Color.WHEAT&& !canEatQueen && !findQueenQatMoves(rect, White)){ //для дамок черных вправо вверх
                int stepX=55;
                int stepY=55;
                boolean as =false;
                if ( cir!=null && cir.queen && cir.circle.getFill()==Color.WHEAT&& !findEatMoves(rect,White)){
                    while (cir.getX()+stepX<=460 && cir.getY()-stepY>=75){
                        for (int j=0;j<rect.length;j++){
                            if (rect[j].hasChip() && (rect[j].rectangle.getX()==(cir.getX()-25)+stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)-stepY)){
                                as=true;
                            }
                            if (!as && !rect[j].hasChip() && (rect[j].rectangle.getX()==(cir.getX()-25)+stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)-stepY)){
                                rect[j].rectangle.setFill(Color.GREEN);
                            }
                        }
                        stepX+=55;
                        stepY+=55;
                    }
                }
            }
            if ( cir.queen && cir.circle.getFill()==Color.WHEAT&& !canEatQueen && !findQueenQatMoves(rect, White)){ //для дамок черных влево вверх
                int stepX=55;
                int stepY=55;
                boolean as =false;
                if ( cir!=null && cir.queen && cir.circle.getFill()==Color.WHEAT&& !findEatMoves(rect,White)){
                    while (cir.getX()-stepX>=75 && cir.getY()-stepY>=75){
                        for (int j=0;j<rect.length;j++){
                            if (rect[j].hasChip() && (rect[j].rectangle.getX()==(cir.getX()-25)-stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)-stepY)){
                                as=true;
                            }
                            if (!as && !rect[j].hasChip() && (rect[j].rectangle.getX()==(cir.getX()-25)-stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)-stepY)){
                                rect[j].rectangle.setFill(Color.GREEN);
                            }
                        }
                        stepX+=55;
                        stepY+=55;
                    }
                }
            }
            if ( cir.queen && cir.circle.getFill()==Color.WHEAT&& !canEatQueen && !findQueenQatMoves(rect, White)){ //для дамок черных влево вниз
                int stepX=55;
                int stepY=55;
                boolean as =false;
                if ( cir!=null && cir.queen && cir.circle.getFill()==Color.WHEAT&& !findEatMoves(rect,White)){
                    while (cir.getX()-stepX>=75 && cir.getY()+stepY<=460){
                        for (int j=0;j<rect.length;j++){
                            if (rect[j].hasChip() && (rect[j].rectangle.getX()==(cir.getX()-25)-stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)+stepY)){
                                as=true;
                            }
                            if (!as && !rect[j].hasChip() && (rect[j].rectangle.getX()==(cir.getX()-25)-stepX)&&(rect[j].rectangle.getY()==(cir.getY()-25)+stepY)){
                                rect[j].rectangle.setFill(Color.GREEN);
                            }
                        }
                        stepX+=55;
                        stepY+=55;
                    }
                }
            }
        }
    }

    public void Move(Rect rect, Rectangle[] recs, Rect[] rec) {
        int X = selectedChip.getX();
        int Y = selectedChip.getY();
        boolean a = false;
        int index = 0;
        int step=55;
        boolean queeneat=false;
        selectedChip.moveChip((int) (rect.rectangle.getX() + 25), (int) (rect.rectangle.getY() + 25));
        for (int i=0;i<rec.length;i++){
            if (rec[i].chip==selectedChip){
                rect.chip=new Chip(selectedChip.getX(),selectedChip.getY(),selectedChip.circle,selectedChip.hasChip,selectedChip.queen);//исправил чип вроде пока все работает
                rec[i].chip=null;
                selectedChip=rect.chip;
            break;
            }
        }

        for (int i = 0; i < rec.length; i++) {
            if (rec[i].hasChip() && (selectedChip.getX() == rec[i].chip.getX() && selectedChip.getY() == rec[i].chip.getY())) {
                index = i;
            }
        }
        if (turn==Turn.WHITE && selectedChip.circle.getFill()==Color.WHEAT && selectedChip.queen && canEatQueen ){ //сьедения дамок белых
            if ((selectedChip.getX() - X > 0) && (selectedChip.getY() - Y < 0)){//сьедения дамок белых вверх вправо (робит)
                while (selectedChip.getX()-step>=X && selectedChip.getY()+step<=Y){
                    for (int i = 0; i < rec.length; i++){
                        if (rec[i].hasChip() &&  (rec[i].chip.circle.getFill()==Color.GRAY ||rec[i].chip.circle.getFill()==Color.CADETBLUE)
                                &&rec[i].chip.getX()== selectedChip.getX()-step && rec[i].chip.getY()== selectedChip.getY()+step){
                            queeneat=true;
                            rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                            rec[i].chip.circle.setCenterX(10);
                            rec[i].chip.circle.setCenterY(10);
                            rec[i].removeChip();
                            break;
                        }
                    }
                    step+=55;
                }
                step=55;
            }
            if ((selectedChip.getX() - X < 0) && (selectedChip.getY() - Y < 0)){ //сьедения влево вверх
                while (selectedChip.getX()+step<=X && selectedChip.getY()+step<=Y){
                    for (int i = 0; i < rec.length; i++){
                        if (rec[i].hasChip() &&  (rec[i].chip.circle.getFill()==Color.GRAY ||rec[i].chip.circle.getFill()==Color.CADETBLUE)
                                &&rec[i].chip.getX()== selectedChip.getX()+step && rec[i].chip.getY()== selectedChip.getY()+step){
                            queeneat=true;
                            rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                            rec[i].chip.circle.setCenterX(10);
                            rec[i].chip.circle.setCenterY(10);
                            rec[i].removeChip();
                            break;
                        }
                    }
                    step+=55;
                }
                step=55;
            }
            if ((selectedChip.getX() - X < 0) && (selectedChip.getY() - Y > 0)){ // влево вниз
                while (selectedChip.getX()+step<=X && selectedChip.getY()-step>=Y){
                    for (int i = 0; i < rec.length; i++){
                        if (rec[i].hasChip() &&  (rec[i].chip.circle.getFill()==Color.GRAY ||rec[i].chip.circle.getFill()==Color.CADETBLUE)
                                &&rec[i].chip.getX()== selectedChip.getX()+step && rec[i].chip.getY()== selectedChip.getY()-step){
                            queeneat=true;
                            rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                            rec[i].chip.circle.setCenterX(10);
                            rec[i].chip.circle.setCenterY(10);
                            rec[i].removeChip();
                            break;
                        }
                    }
                    step+=55;
                }
                step=55;
            }
            if ((selectedChip.getX() - X > 0) && (selectedChip.getY() - Y > 0)){ // вправо вниз
                while (selectedChip.getX()-step>=X && selectedChip.getY()-step>=Y){
                    for (int i = 0; i < rec.length; i++){
                        if (rec[i].hasChip() &&  (rec[i].chip.circle.getFill()==Color.GRAY ||rec[i].chip.circle.getFill()==Color.CADETBLUE)
                                &&rec[i].chip.getX()== selectedChip.getX()-step && rec[i].chip.getY()== selectedChip.getY()-step){
                            queeneat=true;
                            rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                            rec[i].chip.circle.setCenterX(10);
                            rec[i].chip.circle.setCenterY(10);
                            rec[i].removeChip();
                            break;
                        }
                    }
                    step+=55;
                }
                step=55;
            }
        }
        if (turn==Turn.BLACK && selectedChip.circle.getFill()==Color.CADETBLUE && selectedChip.queen && canEatQueen ){ //сьедения дамок черных
            if ((selectedChip.getX() - X > 0) && (selectedChip.getY() - Y < 0)){//сьедения дамок белых вверх вправо (робит)
                while (selectedChip.getX()-step>=X && selectedChip.getY()+step<=Y){
                    for (int i = 0; i < rec.length; i++){
                        if (rec[i].hasChip() &&  (rec[i].chip.circle.getFill()==Color.WHITE ||rec[i].chip.circle.getFill()==Color.WHEAT)
                                &&rec[i].chip.getX()== selectedChip.getX()-step && rec[i].chip.getY()== selectedChip.getY()+step){
                            queeneat=true;
                            rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                            rec[i].chip.circle.setCenterX(10);
                            rec[i].chip.circle.setCenterY(10);
                            rec[i].removeChip();
                            break;
                        }
                    }
                    step+=55;
                }
                step=55;
            }
            if ((selectedChip.getX() - X < 0) && (selectedChip.getY() - Y < 0)){ //сьедения влево вверх
                while (selectedChip.getX()+step<=X && selectedChip.getY()+step<=Y){
                    for (int i = 0; i < rec.length; i++){
                        if (rec[i].hasChip() &&  (rec[i].chip.circle.getFill()==Color.WHITE ||rec[i].chip.circle.getFill()==Color.WHEAT)
                                &&rec[i].chip.getX()== selectedChip.getX()+step && rec[i].chip.getY()== selectedChip.getY()+step){
                            queeneat=true;
                            rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                            rec[i].chip.circle.setCenterX(10);
                            rec[i].chip.circle.setCenterY(10);
                            rec[i].removeChip();
                            break;
                        }
                    }
                    step+=55;
                }
                step=55;
            }
            if ((selectedChip.getX() - X < 0) && (selectedChip.getY() - Y > 0)){ // влево вниз
                while (selectedChip.getX()+step<=X && selectedChip.getY()-step>=Y){
                    for (int i = 0; i < rec.length; i++){
                        if (rec[i].hasChip() &&  (rec[i].chip.circle.getFill()==Color.WHITE ||rec[i].chip.circle.getFill()==Color.WHEAT)
                                &&rec[i].chip.getX()== selectedChip.getX()+step && rec[i].chip.getY()== selectedChip.getY()-step){
                            queeneat=true;
                            rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                            rec[i].chip.circle.setCenterX(10);
                            rec[i].chip.circle.setCenterY(10);
                            rec[i].removeChip();
                            break;
                        }
                    }
                    step+=55;
                }
                step=55;
            }
            if ((selectedChip.getX() - X > 0) && (selectedChip.getY() - Y > 0)){ // вправо вниз
                while (selectedChip.getX()-step>=X && selectedChip.getY()-step>=Y){
                    for (int i = 0; i < rec.length; i++){
                        if (rec[i].hasChip() &&  (rec[i].chip.circle.getFill()==Color.WHITE ||rec[i].chip.circle.getFill()==Color.WHEAT)
                                &&rec[i].chip.getX()== selectedChip.getX()-step && rec[i].chip.getY()== selectedChip.getY()-step){
                            queeneat=true;
                            rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                            rec[i].chip.circle.setCenterX(10);
                            rec[i].chip.circle.setCenterY(10);
                            rec[i].removeChip();
                            break;
                        }
                    }
                    step+=55;
                }
                step=55;
            }
        }
        if (turn == Turn.WHITE && selectedChip.circle.getFill() == Color.WHITE && !selectedChip.queen) {
            for (int i = 0; i < rec.length; i++) {
                if (rec[i].hasChip() && (selectedChip.getX() - X > 0) && (selectedChip.getY() - Y < 0) && (rec[i].chip.getX() == X + 55
                        && rec[i].chip.getY() == Y - 55) && (rec[i].chip.circle.getFill() == Color.GRAY || rec[i].chip.circle.getFill() == Color.CADETBLUE)) {
                    rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                    rec[i].chip.circle.setCenterX(10);
                    rec[i].chip.circle.setCenterY(10);
                    rec[i].removeChip();// не использовать chips[] пока хз надо удалить фишку
                    a = true;
                    break;
                }
                if (rec[i].hasChip() && (selectedChip.getX() - X < 0) && (selectedChip.getY() - Y < 0) && (rec[i].chip.getX() == X - 55
                        && rec[i].chip.getY() == Y - 55) && (rec[i].chip.circle.getFill() == Color.GRAY || rec[i].chip.circle.getFill() == Color.CADETBLUE)) {
                    rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                    rec[i].chip.circle.setCenterX(10);
                    rec[i].chip.circle.setCenterY(10);
                    rec[i].removeChip();// не использовать chips[] пока хз надо удалить фишку
                    a = true;
                    break;
                }
                if (rec[i].hasChip() && (selectedChip.getX() - X > 0) && (selectedChip.getY() - Y > 0) && (rec[i].chip.getX() == X + 55
                        && rec[i].chip.getY() == Y + 55) && (rec[i].chip.circle.getFill() == Color.GRAY || rec[i].chip.circle.getFill() == Color.CADETBLUE)) {
                    rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                    rec[i].chip.circle.setCenterX(10);
                    rec[i].chip.circle.setCenterY(10);
                    rec[i].removeChip();// не использовать chips[] пока хз надо удалить фишку
                    a = true;
                    break;
                }
                if (rec[i].hasChip() && (selectedChip.getX() - X < 0) && (selectedChip.getY() - Y > 0) && (rec[i].chip.getX() == X - 55
                        && rec[i].chip.getY() == Y + 55) && (rec[i].chip.circle.getFill() == Color.GRAY || rec[i].chip.circle.getFill() == Color.CADETBLUE)) {
                    rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                    rec[i].chip.circle.setCenterX(10);
                    rec[i].chip.circle.setCenterY(10);
                    rec[i].removeChip();// не использовать chips[] пока хз надо удалить фишку
                    a = true;
                    break;
                }
            }
        }
        if (turn == Turn.BLACK && selectedChip.circle.getFill() == Color.GRAY) {
            for (int i = 0; i < rec.length; i++) {
                if (rec[i].hasChip() && (selectedChip.getX() - X > 0) && (selectedChip.getY() - Y > 0) && (rec[i].chip.getX() == X + 55
                        && rec[i].chip.getY() == Y + 55) && (rec[i].chip.circle.getFill() == Color.WHITE || rec[i].chip.circle.getFill() == Color.WHEAT)) {
                    rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                    rec[i].chip.circle.setCenterX(10);
                    rec[i].chip.circle.setCenterY(10);
                    rec[i].removeChip();// не использовать chips[] пока хз надо удалить фишку
                    a = true;
                    break;
                }
                if (rec[i].hasChip() && (selectedChip.getX() - X < 0) && (selectedChip.getY() - Y > 0) && (rec[i].chip.getX() == X - 55
                        && rec[i].chip.getY() == Y + 55) && (rec[i].chip.circle.getFill() == Color.WHITE || rec[i].chip.circle.getFill() == Color.WHEAT)) {
                    rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                    rec[i].chip.circle.setCenterX(10);
                    rec[i].chip.circle.setCenterY(10);
                    rec[i].removeChip();// не использовать chips[] пока хз надо удалить фишку
                    a = true;
                    break;
                }
                if (rec[i].hasChip() && (selectedChip.getX() - X > 0) && (selectedChip.getY() - Y < 0) && (rec[i].chip.getX() == X + 55
                        && rec[i].chip.getY() == Y - 55) && (rec[i].chip.circle.getFill() == Color.WHITE || rec[i].chip.circle.getFill() == Color.WHEAT)) {
                    rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                    rec[i].chip.circle.setCenterX(10);
                    rec[i].chip.circle.setCenterY(10);
                    rec[i].removeChip();// не использовать chips[] пока хз надо удалить фишку
                    a = true;
                    break;
                }
                if (rec[i].hasChip() && (selectedChip.getX() - X < 0) && (selectedChip.getY() - Y < 0) && (rec[i].chip.getX() == X - 55
                        && rec[i].chip.getY() == Y - 55) && (rec[i].chip.circle.getFill() == Color.WHITE || rec[i].chip.circle.getFill() == Color.WHEAT)) {
                    rec[i].chip.circle.setFill(Color.DARKGOLDENROD);
                    rec[i].chip.circle.setCenterX(10);
                    rec[i].chip.circle.setCenterY(10);
                    rec[i].removeChip();// не использовать chips[] пока хз надо удалить фишку
                    a = true;
                    break;
                }
            }
        }
        boolean check =false;
        if (turn == Turn.WHITE && !rec[index].chip.queen) {
            if (a && canCheckerEat(rec, rec[index])) {
            } else {
                turn = Turn.BLACK;
            }
        }else
        if (turn==Turn.WHITE && rec[index].chip.queen){
            if (queeneat && canQueeneat(rec, rec[index]) && rec[index].chip.queen) {
            }else {
                turn=Turn.BLACK;
            }
        }else if (turn == Turn.BLACK && !rec[index].chip.queen) {
            if (a && canCheckerEat(rec, rec[index])) {
            } else {
                turn = Turn.WHITE;
            }
        }else
        if (turn==Turn.BLACK && rec[index].chip.queen){
            if (queeneat && canQueeneat(rec, rec[index]) && rec[index].chip.queen) {
            }else {
                turn=Turn.WHITE;
            }
        }
        initBoard(recs,rec);

        if (turn ==Turn.WHITE){
            checkend(rec, White);
        }else checkend(rec,Black);
    }

    public boolean findEatMoves(Rect[] rec, String color) {//все ходы для съедения для нужного цвета
        boolean eat = false;
        if (color.equals("WHITE")) {
            for (int i = 0; i < rec.length; i++) {//1
                if (rec[i].hasChip()){
                    rec[i].rectangle.setFill(Color.RED);
                }
                if (rec[i].hasChip() && rec[i].chip.circle.getFill() == Color.WHITE) {
                    if ((rec[i].chip.getX() + 110 <= 460) && (rec[i].chip.getY() + 110 <= 460)) {//проверка съедения вниз вправо для белых
                        for (int j = 0; j < rec.length; j++) {//2
                            if (rec[j].hasChip() && (rec[j].chip.circle.getFill() == Color.GRAY || rec[j].chip.circle.getFill() == Color.CADETBLUE)
                                    && (rec[j].chip.getX() == rec[i].chip.getX() + 55 && rec[j].chip.getY() == rec[i].chip.getY() + 55)) {
                                eat = true;
                            }
                        }
                        for (int j = 0; j < rec.length; j++) {
                            if (rec[j].hasChip() && (rec[j].chip.getX() == rec[i].chip.getX() + 110 && rec[j].chip.getY() == rec[i].chip.getY() + 110)) {
                                eat = false;
                            }
                        }
                    }
                    if (eat) return true;//если можем съесть выводим true

                    if ((rec[i].chip.getX() - 110 >= 75) && (rec[i].chip.getY() + 110 <= 460)) {
                        for (int j = 0; j < rec.length; j++) {// проверка съедения вниз влево для белых
                            if (rec[j].hasChip() && (rec[j].chip.circle.getFill() == Color.GRAY || rec[j].chip.circle.getFill() == Color.CADETBLUE)
                                    && (rec[j].chip.getX() == rec[i].chip.getX() - 55 && rec[j].chip.getY() == rec[i].chip.getY() + 55)) {
                                eat = true;
                            }
                        }
                        for (int j = 0; j < rec.length; j++) {
                            if (rec[j].hasChip() && (rec[j].chip.getX() == rec[i].chip.getX() - 110 && rec[j].chip.getY() == rec[i].chip.getY() + 110)) {
                                eat = false;
                            }
                        }
                    }
                    if (eat) return true; //если можем съесть выводим true

                    if ((rec[i].chip.getX() - 110 >= 75) && (rec[i].chip.getY() - 110 >= 75)) {
                        for (int j = 0; j < rec.length; j++) {// проверка съедения вверх влево для белых
                            if (rec[j].hasChip() && (rec[j].chip.circle.getFill() == Color.GRAY || rec[j].chip.circle.getFill() == Color.CADETBLUE)
                                    && (rec[j].chip.getX() == rec[i].chip.getX() - 55 && rec[j].chip.getY() == rec[i].chip.getY() - 55)) {
                                eat = true;
                            }
                        }
                        for (int j = 0; j < rec.length; j++) {
                            if (rec[j].hasChip() && (rec[j].chip.getX() == rec[i].chip.getX() - 110 && rec[j].chip.getY() == rec[i].chip.getY() - 110)) {
                                eat = false;
                            }
                        }
                    }
                    if (eat) return true;//если можем съесть выводим true

                    if ((rec[i].chip.getX() + 110 <= 460) && (rec[i].chip.getY() - 110 >= 75)) {
                        for (int j = 0; j < rec.length; j++) {// проверка съедения вниз влево для белых
                            if (rec[j].hasChip() && (rec[j].chip.circle.getFill() == Color.GRAY || rec[j].chip.circle.getFill() == Color.CADETBLUE)
                                    && (rec[j].chip.getX() == rec[i].chip.getX() + 55 && rec[j].chip.getY() == rec[i].chip.getY() - 55)) {
                                eat = true;
                            }
                        }
                        for (int j = 0; j < rec.length; j++) {
                            if (rec[j].hasChip() && (rec[j].chip.getX() == rec[i].chip.getX() + 110 && rec[j].chip.getY() == rec[i].chip.getY() - 110)) {
                                eat = false;
                            }
                        }
                    }
                    if (eat) return true;//если можем съесть выводим true
                }
            }
        } else {//для черных BLACK
            for (int i = 0; i < rec.length; i++) {
                if (rec[i].hasChip()){
                    rec[i].rectangle.setFill(Color.RED);
                }//1
                if (rec[i].hasChip() && rec[i].chip.circle.getFill() == Color.GRAY) {
                    if ((rec[i].chip.getX() + 110 <= 460) && (rec[i].chip.getY() + 110 <= 460)) {
                        for (int j = 0; j < rec.length; j++) {//2  //проверка съедения вниз вправо для черных

                            if (rec[j].hasChip() && (rec[j].chip.circle.getFill() == Color.WHITE || rec[j].chip.circle.getFill() == Color.WHEAT)
                                    && ((rec[j].chip.getX() == rec[i].chip.getX() + 55) && (rec[j].chip.getY() == rec[i].chip.getY() + 55))) {
                                eat = true;
                            }
                        }
                        for (int j = 0; j < rec.length; j++) {
                            if (rec[j].hasChip() && (rec[j].chip.getX() == rec[i].chip.getX() + 110 && rec[j].chip.getY() == rec[i].chip.getY() + 110)) {
                                eat = false;
                            }
                        }
                    }
                    if (eat) return true;//если можем съесть выводим true

                    if ((rec[i].chip.getX() - 110 >= 75) && (rec[i].chip.getY() + 110 <= 460)) {
                        for (int j = 0; j < rec.length; j++) {// проверка съедения вниз влево для черных
                            if (rec[j].hasChip() && (rec[j].chip.circle.getFill() == Color.WHITE || rec[j].chip.circle.getFill() == Color.WHEAT)
                                    && (rec[j].chip.getX() == rec[i].chip.getX() - 55 && rec[j].chip.getY() == rec[i].chip.getY() + 55)) {
                                eat = true;
                            }
                        }
                        for (int j = 0; j < rec.length; j++) {
                            if (rec[j].hasChip() && (rec[j].chip.getX() == rec[i].chip.getX() - 110 && rec[j].chip.getY() == rec[i].chip.getY() + 110)) {
                                eat = false;
                            }
                        }
                    }
                    if (eat) return true;//если можем съесть выводим true

                    if ((rec[i].chip.getX() - 110 >= 75) && (rec[i].chip.getY() - 110 >= 75)) {
                        for (int j = 0; j < rec.length; j++) {// проверка съедения вверх влево для белых
                            if (rec[j].hasChip() && (rec[j].chip.circle.getFill() == Color.WHITE || rec[j].chip.circle.getFill() == Color.WHEAT)
                                    && (rec[j].chip.getX() == rec[i].chip.getX() - 55 && rec[j].chip.getY() == rec[i].chip.getY() - 55)) {
                                eat = true;
                            }
                        }
                        for (int j = 0; j < rec.length; j++) {
                            if (rec[j].hasChip() && (rec[j].chip.getX() == rec[i].chip.getX() - 110 && rec[j].chip.getY() == rec[i].chip.getY() - 110)) {
                                eat = false;
                            }
                        }
                    }
                    if (eat) return true;//если можем съесть выводим true


                    if ((rec[i].chip.getX() + 110 <= 460) && (rec[i].chip.getY() - 110 >= 75)) {
                        for (int j = 0; j < rec.length; j++) {// проверка съедения вниз влево для белых
                            if (rec[j].hasChip() && (rec[j].chip.circle.getFill() == Color.WHITE || rec[j].chip.circle.getFill() == Color.WHEAT)
                                    && (rec[j].chip.getX() == rec[i].chip.getX() + 55 && rec[j].chip.getY() == rec[i].chip.getY() - 55)) {
                                eat = true;
                            }
                        }
                        for (int j = 0; j < rec.length; j++) {
                            if (rec[j].hasChip() && (rec[j].chip.getX() == rec[i].chip.getX() + 110 && rec[j].chip.getY() == rec[i].chip.getY() - 110)) {
                                eat = false;
                            }
                        }
                    }
                    if (eat) return true;//если можем съесть выводим true
                }
            }
        }
        return false;
    }

    public boolean findMoves(Rect[] rec, String color) {//поиск обычных ходов ~
        boolean ceat = false;
        if (findEatMoves(rec, color) || findQueenQatMoves(rec, color)){
            System.out.println("kjk");
            return false;
        }
        if (color.equals(White)) {
            for (int i = 0; i < rec.length; i++) {//1
                if (rec[i].hasChip()){
                if (rec[i].hasChip() && rec[i].chip.circle.getFill() == Color.WHITE) {
                    if ((rec[i].chip.getX() - 55 >= 75) && (rec[i].chip.getY() - 55 >= 75)) {
                        for (int j = 0; j < rec.length; j++) {// проверка съедения вверх влево для белых
                            if (!rec[j].hasChip() && (rec[j].getX()+25 == rec[i].chip.getX() - 55 && rec[j].getY()+25 == rec[i].chip.getY() - 55)) {
                                ceat = true;
                            }
                        }
                    }
                    if (ceat) return true;//если можем ходить выводим true

                    if ((rec[i].chip.getX() + 55 <= 460) && (rec[i].chip.getY() - 55 >= 75)) {
                        for (int j = 0; j < rec.length; j++) {// проверка съедения вниз влево для белых
                            if (!rec[j].hasChip() && (rec[j].getX()+25 == rec[i].chip.getX() + 55 && rec[j].getY()+25 == rec[i].chip.getY() - 55)) {
                                ceat = true;
                            }
                        }
                    }
                    if (ceat) return true;//если можем съесть выводим true
                }}
            }
        } else {//для черных BLACK
            for (int i = 0; i < rec.length; i++) { //1
                if (rec[i].hasChip()){
                if (rec[i].hasChip() && rec[i].chip.circle.getFill() == Color.GRAY) {
                    if ((rec[i].chip.getX() + 55 <= 460) && (rec[i].chip.getY() + 55 <= 460)) {
                        for (int j = 0; j < rec.length; j++) {// 2 // проверка можно ходить вниз вправо для черных

                            if (!rec[j].hasChip() && ((rec[j].getX()+25 == rec[i].chip.getX() + 55) && (rec[j].getY()+25 == rec[i].chip.getY() + 55))) {
                                ceat = true;
                            }
                        }
                    }
                    if (ceat) return true;//если можем ходить выводим true

                    if ((rec[i].chip.getX() - 110 >= 75) && (rec[i].chip.getY() + 110 <= 460)) {
                        for (int j = 0; j < rec.length; j++) {// проверка съедения вниз влево для черных
                            if (!rec[j].hasChip() && (rec[j].getX()+25 == rec[i].chip.getX() - 55 && rec[j].getY()+25 == rec[i].chip.getY() + 55)) {
                                ceat = true;
                            }
                        }
                    }
                    if (ceat) return true;//если можем ходить выводим true
                }}
            }
        }
        return false;
    }
    public boolean findQueenQatMoves (Rect[] rect, String color){
        if (color.equals(White)){
            for (int i=0;i<rect.length;i++){
            if (canQueeneat(rect,rect[i])){
                return true;
            }}
        } else {
            if (color.equals(Black)){
                for (int i=0;i<rect.length;i++){
                    if (canQueeneat(rect,rect[i])){
                        return true;
                    }}
            }
        }
        return false;
    }

    public void checkend(Rect[] rects, String color) {
        boolean hasowerchip=false;
        if (findEatMoves(rects, color) || findQueenQatMoves(rects,color)) {
            System.out.println("find eatmoves" + findQueenQatMoves(rects,color));
        }else if (findMoves(rects, color)) {
            System.out.println("findsimplemoves");
        } else{
            if (turn==Turn.WHITE){
                for (int i=0;i< rects.length;i++){
                if (rects[i].hasChip() && rects[i].chip.queen && rects[i].chip.circle.getFill()==Color.WHEAT){
                    hasowerchip=true;
                }}
            } else if (turn==Turn.BLACK){
                for (int i=0;i< rects.length;i++){
                    if (rects[i].hasChip() && rects[i].chip.queen && rects[i].chip.circle.getFill()==Color.CADETBLUE){
                        hasowerchip=true;
                    }
                }
            }
            if (!hasowerchip){
            System.out.println("end"+ findMoves(rects, color));
            InfoAlert.showAlertWin();
        System.exit(1);
        }}
    }

    public boolean canCheckerEat(Rect[] rec, Rect rect) {
        boolean caneat = false;
        if (rect.chip.circle.getFill() == Color.WHITE) {
            if ((rect.chip.getX() + 110 <= 460) && (rect.chip.getY() + 110 <= 460)) {//проверка съедения вниз вправо для белых
                for (int j = 0; j < rec.length; j++) {//2
                    if (rec[j].hasChip() && (rec[j].chip.circle.getFill() == Color.GRAY || rec[j].chip.circle.getFill() == Color.CADETBLUE)
                            && (rec[j].chip.getX() == rect.chip.getX() + 55 && rec[j].chip.getY() == rect.chip.getY() + 55)) {
                        caneat = true;
                    }
                }
                for (int j = 0; j < rec.length; j++) {
                    if (rec[j].hasChip() && (rec[j].chip.getX() == rect.chip.getX() + 110 && rec[j].chip.getY() == rect.chip.getY() + 110)) {
                        caneat = false;
                    }
                }
            }
            if (caneat) return true;//если можем съесть выводим true

            if ((rect.chip.getX() - 110 >= 75) && (rect.chip.getY() + 110 <= 460)) {
                for (int j = 0; j < rec.length; j++) {// проверка съедения вниз влево для белых
                    if (rec[j].hasChip() && (rec[j].chip.circle.getFill() == Color.GRAY || rec[j].chip.circle.getFill() == Color.CADETBLUE)
                            && (rec[j].chip.getX() == rect.chip.getX() - 55 && rec[j].chip.getY() == rect.chip.getY() + 55)) {
                        caneat = true;
                    }
                }
                for (int j = 0; j < rec.length; j++) {
                    if (rec[j].hasChip() && (rec[j].chip.getX() == rect.chip.getX() - 110 && rec[j].chip.getY() == rect.chip.getY() + 110)) {
                        caneat = false;
                    }
                }
            }
            if (caneat) return true;//если можем съесть выводим true

            if ((rect.chip.getX() - 110 >= 75) && (rect.chip.getY() - 110 >= 75)) {
                for (int j = 0; j < rec.length; j++) {// проверка съедения вверх влево для белых
                    if (rec[j].hasChip() && (rec[j].chip.circle.getFill() == Color.GRAY || rec[j].chip.circle.getFill() == Color.CADETBLUE)
                            && (rec[j].chip.getX() == rect.chip.getX() - 55 && rec[j].chip.getY() == rect.chip.getY() - 55)) {
                        caneat = true;
                    }
                }
                for (int j = 0; j < rec.length; j++) {
                    if (rec[j].hasChip() && (rec[j].chip.getX() == rect.chip.getX() - 110 && rec[j].chip.getY() == rect.chip.getY() - 110)) {
                        caneat = false;
                    }
                }
            }
            if (caneat) return true;//если можем съесть выводим true

            if ((rect.chip.getX() + 110 <= 460) && (rect.chip.getY() - 110 >= 75)) {
                for (int j = 0; j < rec.length; j++) {// проверка съедения вниз влево для белых
                    if (rec[j].hasChip() && (rec[j].chip.circle.getFill() == Color.GRAY || rec[j].chip.circle.getFill() == Color.CADETBLUE)
                            && (rec[j].chip.getX() == rect.chip.getX() + 55 && rec[j].chip.getY() == rect.chip.getY() - 55)) {
                        caneat = true;
                    }
                }
                for (int j = 0; j < rec.length; j++) {
                    if (rec[j].hasChip() && (rec[j].chip.getX() == rect.chip.getX() + 110 && rec[j].chip.getY() == rect.chip.getY() - 110)) {
                        caneat = false;
                    }
                }
            }
            if (caneat) return true;//если можем съесть выводим true
        } else {//для черных BLACK
            if (rect.chip.circle.getFill() == Color.GRAY) {
                if ((rect.chip.getX() + 110 <= 460) && (rect.chip.getY() + 110 <= 460)) {
                    for (int j = 0; j < rec.length; j++) {//2  //проверка съедения вниз вправо для черных

                        if (rec[j].hasChip() && (rec[j].chip.circle.getFill() == Color.WHITE || rec[j].chip.circle.getFill() == Color.WHEAT)
                                && ((rec[j].chip.getX() == rect.chip.getX() + 55) && (rec[j].chip.getY() == rect.chip.getY() + 55))) {
                            caneat = true;
                        }
                    }
                    for (int j = 0; j < rec.length; j++) {
                        if (rec[j].hasChip() && (rec[j].chip.getX() == rect.chip.getX() + 110 && rec[j].chip.getY() == rect.chip.getY() + 110)) {
                            caneat = false;
                        }
                    }
                }
                if (caneat) return true;//если можем съесть выводим true

                if ((rect.chip.getX() - 110 >= 75) && (rect.chip.getY() + 110 <= 460)) {
                    for (int j = 0; j < rec.length; j++) {// проверка съедения вниз влево для черных
                        if (rec[j].hasChip() && (rec[j].chip.circle.getFill() == Color.WHITE || rec[j].chip.circle.getFill() == Color.WHEAT)
                                && (rec[j].chip.getX() == rect.chip.getX() - 55 && rec[j].chip.getY() == rect.chip.getY() + 55)) {
                            caneat = true;
                        }
                    }
                    for (int j = 0; j < rec.length; j++) {
                        if (rec[j].hasChip() && (rec[j].chip.getX() == rect.chip.getX() - 110 && rec[j].chip.getY() == rect.chip.getY() + 110)) {
                            caneat = false;
                        }
                    }
                }
                if (caneat) return true;//если можем съесть выводим true

                if ((rect.chip.getX() - 110 >= 75) && (rect.chip.getY() - 110 >= 75)) {
                    for (int j = 0; j < rec.length; j++) {// проверка съедения вверх влево для белых
                        if (rec[j].hasChip() && (rec[j].chip.circle.getFill() == Color.WHITE || rec[j].chip.circle.getFill() == Color.WHEAT)
                                && (rec[j].chip.getX() == rect.chip.getX() - 55 && rec[j].chip.getY() == rect.chip.getY() - 55)) {
                            caneat = true;
                        }
                    }
                    for (int j = 0; j < rec.length; j++) {
                        if (rec[j].hasChip() && (rec[j].chip.getX() == rect.chip.getX() - 110 && rec[j].chip.getY() == rect.chip.getY() - 110)) {
                            caneat = false;
                        }
                    }
                }
                if (caneat) return true;//если можем съесть выводим true


                if ((rect.chip.getX() + 110 <= 460) && (rect.chip.getY() - 110 >= 75)) {
                    for (int j = 0; j < rec.length; j++) {// проверка съедения вниз влево для белых
                        if (rec[j].hasChip() && (rec[j].chip.circle.getFill() == Color.WHITE || rec[j].chip.circle.getFill() == Color.WHEAT)
                                && (rec[j].chip.getX() == rect.chip.getX() + 55 && rec[j].chip.getY() == rect.chip.getY() - 55)) {
                            caneat = true;
                        }
                    }
                    for (int j = 0; j < rec.length; j++) {
                        if (rec[j].hasChip() && (rec[j].chip.getX() == rect.chip.getX() + 110 && rec[j].chip.getY() == rect.chip.getY() - 110)) {
                            caneat = false;
                        }
                    }
                }
                if (caneat) return true;//если можем съесть выводим true
            }
        }
        return false;
    }
    // написать метод для поиска ходов сьедения выбранной дамки
    public boolean canQueeneat(Rect[] rect, Rect rec){ //////////////////////////////////////////////////////////////////////////////////////////////////////////////1
        boolean res =false;
        if (turn==Turn.WHITE){
            if (rec.hasChip() && rec.chip.queen && rec.chip.circle.getFill()==Color.WHEAT){ // cьедения дамкой черных вниз в лево
                int stepX=55; //сюда попадаю
                int stepY=55;
                int ind =-1;
                int checks=0;
                boolean not =true;
                boolean queeneat = false;
                while (rec.chip.getX()-stepX>=75 && rec.chip.getY()+stepY<=460 && checks==0 && not){ //
                    for (int j=0;j< rect.length;j++){
                        if ((rect[j].rectangle.getX()==(rec.chip.getX()-25)-stepX)&&(rect[j].rectangle.getY()==(rec.chip.getY()-25)+stepY)){
                            if (rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.WHEAT || rect[j].chip.circle.getFill()==Color.WHITE)){
                                queeneat =false;
                                not =false;
                                checks=0;
                                break;
                            } else if ( not && rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.CADETBLUE || rect[j].chip.circle.getFill()==Color.GRAY)){
                                ind =j;
                                checks++;
                                break;}}}
                    stepX+=55;
                    stepY+=55;
                }
                if (checks==0 && not){queeneat =false;} else queeneat =true;

                if (queeneat && not) {
                    for (int i = 0; i < rect.length; i++) {
                        if ((rect[i].rectangle.getX() == rec.chip.getX() - 25 - stepX) && (rect[i].rectangle.getY() == rec.chip.getY() - 25 + stepY) && !rect[i].hasChip()) {
                            res = true;
                            return true;
                        } else if ((rect[i].rectangle.getX() == (rec.chip.getX() - 25) - stepX) && (rect[i].rectangle.getY() == (rec.chip.getY() - 25) + stepY) && rect[i].hasChip()) {

                        }
                    }
                }
            }//////////////////////////////////////////////////////////////////////////////////////////////////////////////////2
            if (rec.hasChip() && rec.chip.queen && rec.chip.circle.getFill()==Color.WHEAT ){ // дамкой черных вниз в право
                int stepX=55; //сюда попадаю
                int stepY=55;
                int ind =-1;
                int checks=0;
                boolean not =true;
                boolean queeneat = false;
                while (rec.chip.getX()+stepX<=460 && rec.chip.getY()+stepY<=460 && checks==0 && not){ //
                    for (int j=0;j< rect.length;j++){
                        if ((rect[j].rectangle.getX()==(rec.chip.getX()-25)+stepX)&&(rect[j].rectangle.getY()==(rec.chip.getY()-25)+stepY)){
                            if (rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.WHEAT || rect[j].chip.circle.getFill()==Color.WHITE)){
                                queeneat =false;
                                not =false;
                                checks=0;
                                break;
                            } else if ( not && rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.CADETBLUE || rect[j].chip.circle.getFill()==Color.GRAY)){
                                ind =j;
                                checks++;
                                break;}}}

                    stepX+=55;
                    stepY+=55;
                }
                if (checks==0 && not){queeneat =false;} else queeneat =true;
                if (queeneat && not) {
                    for (int i = 0; i < rect.length; i++) {//ошибка тут
                        if ((rect[i].rectangle.getX() == rec.chip.getX() - 25 + stepX) && (rect[i].rectangle.getY() == rec.chip.getY() - 25 + stepY) && !rect[i].hasChip()) {
                            res = true;
                        } else if ((rect[i].rectangle.getX() == (rec.chip.getX() - 25) + stepX) && (rect[i].rectangle.getY() == (rec.chip.getY() - 25) + stepY) && rect[i].hasChip()) {
                            res = false;
                        }
                    }
                }
            } // след проверка черных дамок////////////////////////////////////////////////////////////////////////////////////////////////////////////////3
            if (rec.hasChip() && rec.chip.queen && rec.chip.circle.getFill()==Color.WHEAT ){ // cьедения дамкой черных вверх в право
                int stepX=55; //сюда попадаю
                int stepY=55;
                int ind =-1;
                int checks=0;
                boolean not =true;
                boolean queeneat = false;
                while (rec.chip.getX()+stepX<=460 && rec.chip.getY()-stepY>=75 && checks==0 && not){ //
                    for (int j=0;j< rect.length;j++){
                        if ((rect[j].rectangle.getX()==(rec.chip.getX()-25)+stepX)&&(rect[j].rectangle.getY()==(rec.chip.getY()-25)-stepY)){
                            if (rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.WHEAT || rect[j].chip.circle.getFill()==Color.WHITE)){
                                queeneat =false;
                                not =false;
                                checks=0;
                                break;
                            } else if ( not && rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.CADETBLUE || rect[j].chip.circle.getFill()==Color.GRAY)){
                                ind =j;
                                checks++;
                                break;}}}

                    stepX+=55;
                    stepY+=55;
                }
                if (checks==0 && not){queeneat =false;} else queeneat =true;
                if (queeneat && not) {
                    for (int i = 0; i < rect.length; i++) {//ошибка тут
                        if ((rect[i].rectangle.getX() == rec.chip.getX() - 25 + stepX) && (rect[i].rectangle.getY() == rec.chip.getY() - 25 - stepY) && !rect[i].hasChip()) {
                            res = true;
                            return true;
                        } else if ((rect[i].rectangle.getX() == (rec.chip.getX() - 25) + stepX) && (rect[i].rectangle.getY() == (rec.chip.getY() - 25) - stepY) && rect[i].hasChip()) {
                        }
                    }
                }
            }////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////4
            if (rec.hasChip() && rec.chip.queen && rec.chip.circle.getFill()==Color.WHEAT ){ // cьедения дамкой черных вверх в право
                int stepX=55; //сюда попадаю
                int stepY=55;
                int ind =-1;
                int checks=0;
                boolean not =true;
                boolean queeneat = false;
                while (rec.chip.getX()-stepX>=75 && rec.chip.getY()-stepY>=75 && checks==0 && not){ //
                    for (int j=0;j< rect.length;j++){
                        if ((rect[j].rectangle.getX()==(rec.chip.getX()-25)-stepX)&&(rect[j].rectangle.getY()==(rec.chip.getY()-25)-stepY)){
                            if (rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.WHEAT || rect[j].chip.circle.getFill()==Color.WHITE)){
                                queeneat =false;
                                not =false;
                                checks=0;
                                break;
                            } else if ( not && rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.CADETBLUE || rect[j].chip.circle.getFill()==Color.GRAY)){
                                ind =j;
                                checks++;
                                break;}}}

                    stepX+=55;
                    stepY+=55;
                }
                if (checks==0 && not){queeneat =false;} else queeneat =true;
                if (queeneat && not) {
                    for (int i = 0; i < rect.length; i++) {//ошибка тут
                        if ((rect[i].rectangle.getX() == rec.chip.getX() - 25 - stepX) && (rect[i].rectangle.getY() == rec.chip.getY() - 25 - stepY) && !rect[i].hasChip()) {
                            res = true;
                            return true;
                        } else if ((rect[i].rectangle.getX() == (rec.chip.getX() - 25) - stepX) && (rect[i].rectangle.getY() == (rec.chip.getY() - 25) - stepY) && rect[i].hasChip()) {
                        }
                    }
                }
            }

            return res;
        }
        else if (turn==Turn.BLACK){
            if (rec.hasChip() && rec.chip.queen && rec.chip.circle.getFill()==Color.CADETBLUE){ // cьедения дамкой черных вниз в лево
                int stepX=55; //сюда попадаю
                int stepY=55;
                int ind =-1;
                int checks=0;
                boolean not =true;
                boolean queeneat = false;
                while (rec.chip.getX()-stepX>=75 && rec.chip.getY()+stepY<=460 && checks==0 && not){ //
                    for (int j=0;j< rect.length;j++){
                        if ((rect[j].rectangle.getX()==(rec.chip.getX()-25)-stepX)&&(rect[j].rectangle.getY()==(rec.chip.getY()-25)+stepY)){
                            if (rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.CADETBLUE || rect[j].chip.circle.getFill()==Color.GRAY)){
                                queeneat =false;
                                not =false;
                                checks=0;
                                break;
                            } else if ( not && rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.WHEAT || rect[j].chip.circle.getFill()==Color.WHITE)){
                                ind =j;
                                checks++;
                                break;}}}
                    stepX+=55;
                    stepY+=55;
                }
                if (checks==0 && not){queeneat =false;} else queeneat =true;
                if (queeneat && not) {
                    for (int i = 0; i < rect.length; i++) {
                        if ((rect[i].rectangle.getX() == rec.chip.getX() - 25 - stepX) && (rect[i].rectangle.getY() == rec.chip.getY() - 25 + stepY) && !rect[i].hasChip()) {
                            res = true;
                            return true;
                        } else if ((rect[i].rectangle.getX() == (rec.chip.getX() - 25) - stepX) && (rect[i].rectangle.getY() == (rec.chip.getY() - 25) + stepY) && rect[i].hasChip()) {
                        }
                    }
                }
            }//////////////////////////////////////////////////////////////////////////////////////////////////////////////////2
            if (rec.hasChip() && rec.chip.queen && rec.chip.circle.getFill()==Color.CADETBLUE ){ // дамкой черных вниз в право
                int stepX=55; //сюда попадаю
                int stepY=55;
                int ind =-1;
                int checks=0;
                boolean not =true;
                boolean queeneat = false;
                while (rec.chip.getX()+stepX<=460 && rec.chip.getY()+stepY<=460 && checks==0 && not){ //
                    for (int j=0;j< rect.length;j++){
                        if ((rect[j].rectangle.getX()==(rec.chip.getX()-25)+stepX)&&(rect[j].rectangle.getY()==(rec.chip.getY()-25)+stepY)){
                            if (rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.CADETBLUE || rect[j].chip.circle.getFill()==Color.GRAY)){
                                queeneat =false;
                                not =false;
                                checks=0;
                                break;
                            } else if ( not && rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.WHEAT || rect[j].chip.circle.getFill()==Color.WHITE)){
                                ind =j;
                                checks++;
                                break;}}}

                    stepX+=55;
                    stepY+=55;
                }
                if (checks==0 && not){queeneat =false;} else queeneat =true;
                if (queeneat && not) {
                    for (int i = 0; i < rect.length; i++) {//ошибка тут
                        if ((rect[i].rectangle.getX() == rec.chip.getX() - 25 + stepX) && (rect[i].rectangle.getY() == rec.chip.getY() - 25 + stepY) && !rect[i].hasChip()) {
                            res = true;
                        } else if ((rect[i].rectangle.getX() == (rec.chip.getX() - 25) + stepX) && (rect[i].rectangle.getY() == (rec.chip.getY() - 25) + stepY) && rect[i].hasChip()) {
                            res = false;
                        }
                    }
                }
            } // след проверка черных дамок////////////////////////////////////////////////////////////////////////////////////////////////////////////////3
            if (rec.hasChip() && rec.chip.queen && rec.chip.circle.getFill()==Color.CADETBLUE ){ // cьедения дамкой черных вверх в право
                int stepX=55; //сюда попадаю
                int stepY=55;
                int ind =-1;
                int checks=0;
                boolean not =true;
                boolean queeneat = false;
                while (rec.chip.getX()+stepX<=460 && rec.chip.getY()-stepY>=75 && checks==0 && not){ //
                    for (int j=0;j< rect.length;j++){
                        if ((rect[j].rectangle.getX()==(rec.chip.getX()-25)+stepX)&&(rect[j].rectangle.getY()==(rec.chip.getY()-25)-stepY)){
                            if (rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.CADETBLUE || rect[j].chip.circle.getFill()==Color.GRAY)){
                                queeneat =false;
                                not =false;
                                checks=0;
                                break;
                            } else if ( not && rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.WHEAT || rect[j].chip.circle.getFill()==Color.WHITE)){
                                ind =j;
                                checks++;
                                break;}}}

                    stepX+=55;
                    stepY+=55;
                }
                if (checks==0 && not){queeneat =false;} else queeneat =true;
                if (queeneat && not) {
                    for (int i = 0; i < rect.length; i++) {//ошибка тут
                        if ((rect[i].rectangle.getX() == rec.chip.getX() - 25 + stepX) && (rect[i].rectangle.getY() == rec.chip.getY() - 25 - stepY) && !rect[i].hasChip()) {
                            res = true;
                            return true;
                        } else if ((rect[i].rectangle.getX() == (rec.chip.getX() - 25) + stepX) && (rect[i].rectangle.getY() == (rec.chip.getY() - 25) - stepY) && rect[i].hasChip()) {
                        }
                    }
                }
            }////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////4
            if (rec.hasChip() && rec.chip.queen && rec.chip.circle.getFill()==Color.CADETBLUE ){ // cьедения дамкой черных вверх в право
                int stepX=55; //сюда попадаю
                int stepY=55;
                int ind =-1;
                int checks=0;
                boolean not =true;
                boolean queeneat = false;
                while (rec.chip.getX()-stepX>=75 && rec.chip.getY()-stepY>=75 && checks==0 && not){ //
                    for (int j=0;j< rect.length;j++){
                        if ((rect[j].rectangle.getX()==(rec.chip.getX()-25)-stepX)&&(rect[j].rectangle.getY()==(rec.chip.getY()-25)-stepY)){
                            if (rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.CADETBLUE || rect[j].chip.circle.getFill()==Color.GRAY)){
                                queeneat =false;
                                not =false;
                                checks=0;
                                break;
                            } else if ( not && rect[j].hasChip() && ( rect[j].chip.circle.getFill()==Color.WHEAT || rect[j].chip.circle.getFill()==Color.WHITE)){
                                ind =j;
                                checks++;
                                break;}}}

                    stepX+=55;
                    stepY+=55;
                }
                if (checks==0 && not){queeneat =false;} else queeneat =true;
                if (queeneat && not) {
                    for (int i = 0; i < rect.length; i++) {//ошибка тут
                        if ((rect[i].rectangle.getX() == rec.chip.getX() - 25 - stepX) && (rect[i].rectangle.getY() == rec.chip.getY() - 25 - stepY) && !rect[i].hasChip()) {
                            res = true;
                            return true;
                        } else if ((rect[i].rectangle.getX() == (rec.chip.getX() - 25) - stepX) && (rect[i].rectangle.getY() == (rec.chip.getY() - 25) - stepY) && rect[i].hasChip()) {
                        }
                    }
                }
            }

            return res;
        }
    return res;
    }
    public void checknewqueens(Rect[] rec){
        for (int i=0;i<rec.length;i++){
            if (rec[i].hasChip() && rec[i].chip.circle.getFill()==Color.WHITE &&rec[i].chip.getY()==75){
                rec[i].chip.queen=true;
                rec[i].chip.circle.setFill(Color.WHEAT);
            }
            if (rec[i].hasChip() && rec[i].chip.circle.getFill()==Color.GRAY &&rec[i].chip.getY()==460){
                rec[i].chip.queen=true;
                rec[i].chip.circle.setFill(Color.CADETBLUE);
            }
        }
    }
}


