package com.example.myfirstapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etNombre, etApellido;
    private TextView tvNacim;
    private CheckBox ckbCsharp, ckbC, ckbGo, ckbJS, ckbJava, ckbPython;
    private Spinner spnGenero;
    private RadioGroup radioGroup;
    private RadioButton rdBtn;
    private final ArrayList<String> lenguajes = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.txtNombre);
        etApellido = findViewById(R.id.txtApellido);
        tvNacim = findViewById(R.id.txtNacim);

        spnGenero = findViewById(R.id.spnGenero);
        radioGroup = findViewById(R.id.radioGroup);
        rdBtn = findViewById(R.id.rdBtnSi);
        rdBtn.setChecked(true);

        ckbCsharp = findViewById(R.id.ckbCsharp);
        ckbC = findViewById(R.id.ckbC);
        ckbGo = findViewById(R.id.ckbGo);
        ckbJS = findViewById(R.id.ckbJS);
        ckbJava = findViewById(R.id.ckbJava);
        ckbPython = findViewById(R.id.ckbPython);

        tvNacim = findViewById(R.id.txtNacim);
        tvNacim.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            DatePickerDialog picker = new DatePickerDialog(MainActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> tvNacim.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1), year, month, day);
            picker.show();
        });

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            estadoCkb();
        });
    }


    public void validarDatos(View view){
        if(etNombre.getText().toString().equals("") && etApellido.getText().toString().equals("")){
            etNombre.setError("Este campo es obligatorio");
            etApellido.setError("Este campo es obligatorio");
        }else if(etApellido.getText().toString().equals("")){
            etApellido.setError("Este campo es obligatorio");
        }else if(etNombre.getText().toString().equals("")){
            etNombre.setError("Este campo es obligatorio");
        }else{
            if(rdBtn.isChecked()) {
                if (ckbCsharp.isChecked() || ckbC.isChecked() || ckbGo.isChecked() || ckbJS.isChecked()
                        || ckbJava.isChecked() || ckbPython.isChecked()) {
                    enviarDatos();
                }else{
                    Toast.makeText(MainActivity.this, "Debe de seleccionar al menos un lenguaje", Toast.LENGTH_LONG).show();
                }
            }else{
                enviarDatos();
            }
        }
    }

    public void enviarDatos(){
        RadioButton rdBtn = findViewById(radioGroup.getCheckedRadioButtonId());

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra("nombre", etNombre.getText().toString());
        intent.putExtra("apellido", etApellido.getText().toString());
        intent.putExtra("nacim", tvNacim.getText().toString());
        intent.putExtra("genero", spnGenero.getSelectedItem().toString());
        intent.putExtra("prog", rdBtn.getText().toString());

        if(ckbCsharp.isChecked()){
            lenguajes.add(ckbCsharp.getText().toString());
        }

        if(ckbC.isChecked()){
            lenguajes.add(ckbC.getText().toString());
        }

        if(ckbGo.isChecked()){
            lenguajes.add(ckbGo.getText().toString());
        }

        if(ckbJS.isChecked()){
            lenguajes.add(ckbJS.getText().toString());
        }

        if(ckbJava.isChecked()){
            lenguajes.add(ckbJava.getText().toString());
        }

        if(ckbPython.isChecked()){
            lenguajes.add(ckbPython.getText().toString());
        }

        intent.putExtra("lenguajes", lenguajes);

        startActivity(intent);
    }

    public void limpiarDatos(View view){
        etNombre.getText().clear();
        etApellido.getText().clear();
        spnGenero.setSelection(0);
        tvNacim.setText(null);
        ((RadioButton)radioGroup.getChildAt(0)).setChecked(true);
        uncheckCkb();
   }

   public void estadoCkb(){
       boolean valor = ((RadioButton)radioGroup.getChildAt(0)).isChecked();;

       if(!valor){
           uncheckCkb();
       }

       ckbCsharp.setEnabled(valor);
       ckbC.setEnabled(valor);
       ckbGo.setEnabled(valor);
       ckbJS.setEnabled(valor);
       ckbJava.setEnabled(valor);
       ckbPython.setEnabled(valor);

   }

   public void uncheckCkb(){
       ckbCsharp.setChecked(false);
       ckbC.setChecked(false);
       ckbGo.setChecked(false);
       ckbJS.setChecked(false);
       ckbJava.setChecked(false);
       ckbPython.setChecked(false);
   }
}