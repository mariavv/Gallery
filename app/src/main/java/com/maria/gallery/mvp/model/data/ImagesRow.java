package com.maria.gallery.mvp.model.data;

public class ImagesRow {

    File leftPic, rightPic;

    public ImagesRow(File leftPic, File rightPic) {
        this.leftPic = leftPic;
        this.rightPic = rightPic;
    }

    public File getLeftPic() {
        return leftPic;
    }

    public File getRightPic() {
        return rightPic;
    }
}
