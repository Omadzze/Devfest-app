package com.example.devfest.Model;

public class OnBoardingItem {

    private int title, description, screenImage, bgColor, colorText;

    public int getTitle() {
        return title;
    }

    public int getBgColor() {
        return bgColor;
    }

    public int getColorText() {
        return colorText;
    }

    public void setColorText(int colorText) {
        this.colorText = colorText;
    }

    public int getDescription() {
        return description;
    }

    public int getScreenImage() {
        return screenImage;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public void setScreenImage(int screenImage) {
        this.screenImage = screenImage;
    }
}
