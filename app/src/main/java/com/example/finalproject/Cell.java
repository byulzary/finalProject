package com.example.finalproject;

//public class Cell {
//
//    int startX;
//    int startY;
//    int endY;
//    int endX;
//    private boolean isBarrier;
//
//    public int getStartX() {
//        return startX;
//    }
//
//    public void setStartX(int startX) {
//        this.startX = startX;
//    }
//
//    public int getStartY() {
//        return startY;
//    }
//
//    public void setStartY(int startY) {
//        this.startY = startY;
//    }
//
//    public int getEndY() {
//        return endY;
//    }
//
//    public void setEndY(int endY) {
//        this.endY = endY;
//    }
//
//    public int getEndX() {
//        return endX;
//    }
//
//    public void setEndX(int endX) {
//        this.endX = endX;
//    }
//
//    public boolean isBarrier() {
//        return isBarrier;
//    }
//
//    public void setIsBarrier(boolean isBarrier) {
//        this.isBarrier = isBarrier;
//    }
//
//    public Cell() {
//
//    }
//}


import android.graphics.Color;

enum Cell {
    None,
    Path,
    Item,
    Barrier;

    public int getColor() {
        switch (this) {
            case Path:
                return Color.RED;
            case Barrier:
                return Color.BLACK;
            default:
                return Color.WHITE;
        }
    }

    public static Cell fromSymbol(String symbol) {
        switch (symbol.toUpperCase()) {
            case "B":
                return Barrier;
            case "P":
                return Path;
            case "I":
                return Item;
            default:
                return None;
        }
    }
}