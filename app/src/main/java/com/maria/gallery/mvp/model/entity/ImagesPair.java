package com.maria.gallery.mvp.model.entity;

/*
Структура для элемента RecyclerView
 */

public class ImagesPair {

    private Image leftPic, rightPic;

    public ImagesPair(Image leftPic, Image rightPic) {
        this.leftPic = leftPic;
        this.rightPic = rightPic;
    }

    public Image getLeftPic() {
        return leftPic;
    }

    public Image getRightPic() {
        return rightPic;
    }
}
