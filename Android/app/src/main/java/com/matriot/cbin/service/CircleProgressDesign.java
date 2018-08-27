package com.matriot.cbin.service;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.github.lzyzsd.circleprogress.Utils;

/**
 * Created by NandhiniGovindasamy on 3/13/18.
 */

public class CircleProgressDesign extends View{
        private Paint textPaint;
        private RectF rectF;
        private float textSize;
        private int textColor;
        private float progress;
        private int max;
        private int finishedColor;
        private int unfinishedColor;
        private String prefixText;
        private String suffixText;
        private final int default_finished_color;
        private final int default_unfinished_color;
        private final int default_text_color;
        private final int default_max;
        private final float default_text_size;
        private final int min_size;
        private static final String INSTANCE_STATE = "saved_instance";
        private static final String INSTANCE_TEXT_COLOR = "text_color";
        private static final String INSTANCE_TEXT_SIZE = "text_size";
        private static final String INSTANCE_FINISHED_STROKE_COLOR = "finished_stroke_color";
        private static final String INSTANCE_UNFINISHED_STROKE_COLOR = "unfinished_stroke_color";
        private static final String INSTANCE_MAX = "max";
        private static final String INSTANCE_PROGRESS = "progress";
        private static final String INSTANCE_SUFFIX = "suffix";
        private static final String INSTANCE_PREFIX = "prefix";
        private Paint paint;

        public CircleProgressDesign(Context context) {
            this(context, (AttributeSet)null);
        }

        public CircleProgressDesign(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public CircleProgressDesign(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            this.rectF = new RectF();
            this.progress = 0;
            this.prefixText = "";
            this.suffixText = "%";
            this.default_finished_color = Color.rgb(66, 145, 241);
            this.default_unfinished_color = Color.rgb(204, 204, 204);
            this.default_text_color = -1;
            this.default_max = 100;
            this.paint = new Paint();
            this.default_text_size = Utils.sp2px(this.getResources(), 18.0F);
            this.min_size = (int)Utils.dp2px(this.getResources(), 100.0F);
            TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, com.github.lzyzsd.circleprogress.R.styleable.CircleProgress, defStyleAttr, 0);
            this.initByAttributes(attributes);
            attributes.recycle();
            this.initPainters();
        }

        protected void initByAttributes(TypedArray attributes) {
            this.finishedColor = attributes.getColor(com.github.lzyzsd.circleprogress.R.styleable.CircleProgress_circle_finished_color, this.default_finished_color);
            this.unfinishedColor = attributes.getColor(com.github.lzyzsd.circleprogress.R.styleable.CircleProgress_circle_unfinished_color, this.default_unfinished_color);
            this.textColor = attributes.getColor(com.github.lzyzsd.circleprogress.R.styleable.CircleProgress_circle_text_color, -1);
            this.textSize = attributes.getDimension(com.github.lzyzsd.circleprogress.R.styleable.CircleProgress_circle_text_size, this.default_text_size);
            this.setMax(attributes.getInt(com.github.lzyzsd.circleprogress.R.styleable.CircleProgress_circle_max, 100));
            this.setProgress(attributes.getFloat(com.github.lzyzsd.circleprogress.R.styleable.CircleProgress_circle_progress, 0));
            if(attributes.getString(com.github.lzyzsd.circleprogress.R.styleable.CircleProgress_circle_prefix_text) != null) {
                this.setPrefixText(attributes.getString(com.github.lzyzsd.circleprogress.R.styleable.CircleProgress_circle_prefix_text));
            }

            if(attributes.getString(com.github.lzyzsd.circleprogress.R.styleable.CircleProgress_circle_suffix_text) != null) {
                this.setSuffixText(attributes.getString(com.github.lzyzsd.circleprogress.R.styleable.CircleProgress_circle_suffix_text));
            }

        }

        protected void initPainters() {
            this.textPaint = new TextPaint();
            this.textPaint.setColor(this.textColor);
            this.textPaint.setTextSize(this.textSize);
            this.textPaint.setAntiAlias(true);
            this.paint.setAntiAlias(true);
        }

        public void invalidate() {
            this.initPainters();
            super.invalidate();
        }

        public float getProgress() {
            return this.progress;
        }

        public void setProgress(float progress) {
            this.progress = progress;
            if(this.progress > this.getMax()) {
                this.progress %= this.getMax();
            }

            this.invalidate();
        }

        public int getMax() {
            return this.max;
        }

        public void setMax(int max) {
            if(max > 0) {
                this.max = max;
                this.invalidate();
            }

        }

        public float getTextSize() {
            return this.textSize;
        }

        public void setTextSize(float textSize) {
            this.textSize = textSize;
            this.invalidate();
        }

        public int getTextColor() {
            return this.textColor;
        }

        public void setTextColor(int textColor) {
            this.textColor = textColor;
            this.invalidate();
        }

        public int getFinishedColor() {
            return this.finishedColor;
        }

        public void setFinishedColor(int finishedColor) {
            this.finishedColor = finishedColor;
            this.invalidate();
        }

        public int getUnfinishedColor() {
            return this.unfinishedColor;
        }

        public void setUnfinishedColor(int unfinishedColor) {
            this.unfinishedColor = unfinishedColor;
            this.invalidate();
        }

        public String getPrefixText() {
            return this.prefixText;
        }

        public void setPrefixText(String prefixText) {
            this.prefixText = prefixText;
            this.invalidate();
        }

        public String getSuffixText() {
            return this.suffixText;
        }

        public void setSuffixText(String suffixText) {
            this.suffixText = suffixText;
            this.invalidate();
        }

        public String getDrawText() {
            return this.getPrefixText() + this.getProgress() + this.getSuffixText();
        }

        protected int getSuggestedMinimumHeight() {
            return this.min_size;
        }

        protected int getSuggestedMinimumWidth() {
            return this.min_size;
        }

        public float getProgressPercentage() {
            return (float)this.getProgress() / (float)this.getMax();
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            this.rectF.set(0.0F, 0.0F, (float)MeasureSpec.getSize(widthMeasureSpec), (float)MeasureSpec.getSize(heightMeasureSpec));
            this.setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        }

        protected void onDraw(Canvas canvas) {
            float yHeight = (float)this.getProgress() / (float)this.getMax() * (float)this.getHeight();
            float radius = (float)this.getWidth() / 2.0F;
            float angle = (float)(Math.acos((double)((radius - yHeight) / radius)) * 180.0D / 3.141592653589793D);
            float startAngle = 90.0F + angle;
            float sweepAngle = 360.0F - angle * 2.0F;
            this.paint.setColor(this.getUnfinishedColor());
            canvas.drawArc(this.rectF, startAngle, sweepAngle, false, this.paint);
            canvas.save();
            canvas.rotate(180.0F, (float)(this.getWidth() / 2), (float)(this.getHeight() / 2));
            this.paint.setColor(this.getFinishedColor());
            canvas.drawArc(this.rectF, 270.0F - angle, angle * 2.0F, false, this.paint);
            canvas.restore();
            String text = this.getDrawText();
            if(!TextUtils.isEmpty(text)) {
                float textHeight = this.textPaint.descent() + this.textPaint.ascent();
                canvas.drawText(text, ((float)this.getWidth() - this.textPaint.measureText(text)) / 2.0F, ((float)this.getWidth() - textHeight) / 2.0F, this.textPaint);
            }

        }

        protected Parcelable onSaveInstanceState() {
            Bundle bundle = new Bundle();
            bundle.putParcelable("saved_instance", super.onSaveInstanceState());
            bundle.putInt("text_color", this.getTextColor());
            bundle.putFloat("text_size", this.getTextSize());
            bundle.putInt("finished_stroke_color", this.getFinishedColor());
            bundle.putInt("unfinished_stroke_color", this.getUnfinishedColor());
            bundle.putInt("max", this.getMax());
            bundle.putFloat("progress", this.getProgress());
            bundle.putString("suffix", this.getSuffixText());
            bundle.putString("prefix", this.getPrefixText());
            return bundle;
        }

        protected void onRestoreInstanceState(Parcelable state) {
            if(state instanceof Bundle) {
                Bundle bundle = (Bundle)state;
                this.textColor = bundle.getInt("text_color");
                this.textSize = bundle.getFloat("text_size");
                this.finishedColor = bundle.getInt("finished_stroke_color");
                this.unfinishedColor = bundle.getInt("unfinished_stroke_color");
                this.initPainters();
                this.setMax(bundle.getInt("max"));
                this.setProgress(bundle.getFloat("progress"));
                this.prefixText = bundle.getString("prefix");
                this.suffixText = bundle.getString("suffix");
                super.onRestoreInstanceState(bundle.getParcelable("saved_instance"));
            } else {
                super.onRestoreInstanceState(state);
            }
        }


}
