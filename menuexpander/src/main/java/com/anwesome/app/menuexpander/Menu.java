package com.anwesome.app.menuexpander;

import android.app.Activity;
import android.graphics.*;


/**
 * Created by anweshmishra on 13/02/17.
 */
public class Menu {
    private Bitmap bitmap;
    private MenuClickListener onClickListener;
    private boolean clicked = false;
    private float x,y,r,scale = 0.7f,dir =0;
    public Menu(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public void setOnClickListener(MenuClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    public void setDimensions(float x,float y,float r) {
        bitmap = Bitmap.createScaledBitmap(bitmap,(int)r,(int)r,true);
        this.x = x;
        this.y = y;
        this.r = r;
    }
    public void draw(Canvas canvas,Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        canvas.save();
        canvas.translate(x+r,y+r);
        canvas.scale(scale,scale);
        canvas.drawCircle(0,0,r,paint);
        canvas.drawBitmap(bitmap,-r/2,-r/2,paint);
        canvas.restore();

    }
    public void render() {
        scale+=dir;
        if(scale>=1) {
            dir = -0.1f;
        }
        if(scale<=0.7f) {
            dir = 0;
            scale = 0.7f;
            clicked = true;
            if(onClickListener!=null) {
                onClickListener.onClick();
            }

        }
    }
    public boolean handleTap(float x,float y) {
        boolean condition = x>=this.x && x<=this.x+2*r && y>=this.y && y<=this.y+2*r;
        if(condition) {
            dir = 0.1f;
        }
        return condition;
    }
    public int hashCode() {
        return bitmap.hashCode()+(int)x+(int)y;
    }
    public boolean isClicked() {
        return clicked;
    }
    public void resetClick() {
        this.clicked = false;
    }
    public interface MenuClickListener {
        void onClick();
    }
}
