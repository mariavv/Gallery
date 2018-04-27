package com.maria.gallery.mvp.model.data;

public class ImagesPair {

    Image leftPic, rightPic;

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
