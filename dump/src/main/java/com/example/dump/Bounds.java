package com.example.dump;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ishant Rana on 17/06/16.
 */
public class Bounds {

    private int top;

    private int left;

    private int width;

    private int height;

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] createList(){
        int[] list = new int[]{this.getTop(), this.getLeft(), this.getHeight(), this.getWidth()};
        return list;
    }
}
