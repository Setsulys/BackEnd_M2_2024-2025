package fr.uge.jee.servlet.rectanglesession;

public class Rectangle {
    private int width;
    private int height;

    public int area(){return width*height;}

    // getters and setters


    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}