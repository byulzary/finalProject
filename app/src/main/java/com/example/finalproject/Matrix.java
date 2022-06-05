package com.example.finalproject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matrix {

    private Cell[][] elements;
    private int rows;

    public Matrix(int rows) {
        this.rows = rows;
        this.elements = new Cell[rows][rows];

    }

    public Matrix(int r, List<Cell> elements) {
        this(r);
        setFromList(elements);
    }

    public Cell get(int i, int j) {
        return  elements[i][j];
    }

    public Cell set(int i, int j, Cell e) {
        Cell o = elements[i][j];
        elements[i][j] = e;
        return o;
    }

    public void setFromList(List<Cell> items) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                set(i, j, items.get(i * rows + j));
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return rows;
    }

    public List<Cell> asList() {
        return Arrays.stream(elements)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    public Cell[][] asMatrix() {
        return elements;
    }
}
