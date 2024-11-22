package com.example.pr1belova;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private EditText edit_weight, edit_value;
    private Button btn, btnSave, btnLoad;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        result = findViewById(R.id.result);
        edit_weight = findViewById(R.id.edit_weight);
        edit_value = findViewById(R.id.edit_value);
        btn = findViewById(R.id.btn);

        edit_weight.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        edit_value.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        loadText();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float num1 = Float.parseFloat(edit_weight.getText().toString());
                float num2 = Float.parseFloat(edit_value.getText().toString());
                float res = num1*num2;
                result.setText(String.valueOf(res));
                saveText(String.valueOf(res));
            }
        });
    }

    void saveText(String new_text) {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("ROUBLES", new_text);
        ed.commit();
    }
    void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString("ROUBLES", "");
        result.setText(savedText);
    }

}