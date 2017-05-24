package com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ReadActivity extends AppCompatActivity {

    private TextView txtContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        txtContent=(TextView) findViewById(R.id.txtContent);
        txtContent.setMovementMethod(new ScrollingMovementMethod());
        txtContent.setText("jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj" +
                "jdfjkdjfjdjskfjdjkfjkjdkfjkdjfj");
    }
}
