package com.example.diego.staticcapstone;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginModal extends BottomSheetDialogFragment {
    private LoginListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login, container, false);


        Button buttonConfirm = v.findViewById(R.id.btnConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onButtonClicked("confirm was clicked. implements the login flow later");
                dismiss();
            }
        });
        return v;
    }


    public interface LoginListener {
        void onButtonClicked(String text);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {

            mListener = (LoginListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement loginmodal");
        }
    }
}
