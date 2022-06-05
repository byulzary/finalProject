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

import org.xguzm.pathfinding.grid.GridCell;

class Cell extends GridCell {

    private Type type;

    public Cell(Type type) {
        this.type = type;
    }

    @Override
    public boolean isWalkable() {
        return this.type != Type.Barrier;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    enum Type {
        None,
        Path,
        Item,
        Barrier;

        public int getColor () {
        switch (this) {
            case Path:
                return Color.RED;
            case Barrier:
                return Color.BLACK;
            case Item:
                return Color.GREEN;
            default:
                return Color.WHITE;
        }
    }

        public static Type fromSymbol (Character symbol){
        switch (Character.toUpperCase(symbol)) {
            case 'B':
                return Barrier;
            case 'P':
                return Path;
            case 'I':
                return Item;
            default:
                return None;
        }
    }
    }
}