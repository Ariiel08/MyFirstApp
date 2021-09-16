package com.example.myfirstapp;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Objects;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();

        String nombre = intent.getStringExtra("nombre");
        String apellido = intent.getStringExtra("apellido");
        String genero = intent.getStringExtra("genero");
        String nacim = intent.getStringExtra("nacim");
        String prog = intent.getStringExtra("prog");

        String msg1 = "Hola!, mi nombre es: "+nombre+" "+apellido;
        String msg2 = "Soy "+genero+", y nací en la fecha: "+nacim;

        TextView textView = findViewById(R.id.mostrarMsj);
        textView.setText(msg1);

        TextView textView2 = findViewById(R.id.mostrarMsj2);
        textView2.setText(msg2);

        TextView textView3 = findViewById(R.id.mostrarMsj3);

        if(prog.equals("Si")){

            String msg3 = "Me gusta programar. Mis lenguajes favoritos son: ";
            ArrayList<String> lenguajes = intent.getStringArrayListExtra("lenguajes");

            for (int i = 0; i < lenguajes.size(); i++) {
                if(i == lenguajes.size()-1){
                    msg3 = msg3.concat(lenguajes.get(i)+".");
                }else{
                    msg3 = msg3.concat(lenguajes.get(i)+", ");
                }
            }

            textView3.setText(msg3);
        }else{
            String msg3 = "No me gusta programar.";
            textView3.setText(msg3);
        }

    }
}