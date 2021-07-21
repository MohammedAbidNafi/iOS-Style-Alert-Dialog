package com.margsapp.iosdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.lang.ref.WeakReference;

public class iOSDialog {

    private String titletxt,messagetxt,positivetxt,negativetxt;

    @Visibility
    private int visibility;
    private iOSDialogListener pListener,nListener;
    @ColorInt
    private int pTxtColor, nTxtColor;


    private boolean cancel;
    private Dialog dialog;

    private iOSDialog(Builder builder){

        this.titletxt = builder.titletxt;
        this.positivetxt = builder.positivetxt;
        this.negativetxt = builder.negativetxt;
        this.messagetxt = builder.messagetxt;
        this.visibility = builder.visibility;
        this.pListener = builder.pListener;
        this.nListener = builder.nListener;
        this.cancel = builder.cancel;
        this.dialog = builder.dialog;
        this.pTxtColor = builder.pTxtColor;
        this.nTxtColor = builder.nTxtColor;
    }

    public iOSDialog show(){
        dialog.show();
        return this;
    }

    public iOSDialog dismiss(){
        dialog.dismiss();
        return this;
    }


    public static class Builder {

        private String titletxt,messagetxt, positivetxt, negativetxt;
        @Visibility
        private int visibility;
        private iOSDialogListener pListener, nListener;

        @ColorInt int pTxtColor;
        @ColorInt int nTxtColor;

        private boolean cancel;

        private WeakReference<Context> context;
        private Dialog dialog;


        private Builder(Context context) {
            // use #with
            this.context = new WeakReference<>(context);
        }

        public static Builder with(@NonNull Context context) {
            return new Builder(context);
        }

        public Builder setTitle(String titletxt) {
            this.titletxt = titletxt;
            return this;
        }

        public Builder setMessage(String messagetxt){
            this.messagetxt = messagetxt;
            return this;
        }

        public Builder setPostiveTextColor(@ColorInt int pTxtColor){
            this.pTxtColor = pTxtColor;
            return this;
        }

        public Builder setNegativeTextColor(@ColorInt int nTxtColor){
            this.nTxtColor = nTxtColor;
            return this;
        }

        public Builder setPositiveText(String positiveText) {
            this.positivetxt = positiveText;
            return this;
        }

        public Builder setNegativeText(String negativeText) {
            this.negativetxt = negativeText;
            return this;
        }


        public Builder onPositiveClicked(@Nullable iOSDialogListener pListener) {
            this.pListener = pListener;
            return this;
        }

        public Builder onNegativeClicked(@Nullable iOSDialogListener nListener) {
            this.nListener = nListener;
            return this;
        }


        public Builder isCancellable(boolean cancel) {
            this.cancel = cancel;
            return this;
        }


        public iOSDialog build() {


            CardView negativecard,titlecard,positivecard;
            TextView positivetxtview,negativetxtview,messageview,titleview;

            // View view;


            dialog.getWindow().setGravity(Gravity.BOTTOM);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(cancel);
            dialog.setContentView(R.layout.main_dialog);

            titlecard = dialog.findViewById(R.id.titlecard);

            positivecard = dialog.findViewById(R.id.positivecard);
            negativecard = dialog.findViewById(R.id.negativecard);

            positivetxtview = dialog.findViewById(R.id.positivetxt);
            negativetxtview = dialog.findViewById(R.id.negativetxt);
            messageview = dialog.findViewById(R.id.messageview);
            titleview = dialog.findViewById(R.id.titleview);


            titleview.setText(titletxt);

            if (pTxtColor != 0) {
                positivetxtview.setTextColor(pTxtColor);
            }
            if (nTxtColor != 0) {
               negativetxtview.setTextColor(nTxtColor);
            }

            if (positivetxt != null) {
                positivetxtview.setText(positivetxt);
            }

            if(messagetxt != null){
                messageview.setText(messagetxt);
            }


            if (negativetxt != null) {
                negativetxtview.setText(negativetxt);
            }

            if (pListener != null) {
                positivecard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pListener.onClick(dialog);
                        dialog.dismiss();
                    }
                });
            }
            if (nListener != null) {
                negativecard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nListener.onClick(dialog);
                        dialog.dismiss();
                    }
                });
            }


            return new iOSDialog(this);
        }


    }


}
