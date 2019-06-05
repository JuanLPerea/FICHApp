package edu.cftic.fichapp;

import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class RegistroEntradaSalida extends AppCompatActivity {

    private TextView horaEntrada, horaSalida;
    private Button botonEntrada, botonSalida;
    private Date objDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_entrada_salida);

        horaEntrada = findViewById(R.id.horaEntrada);
        horaSalida = findViewById(R.id.horaSalida);

        botonEntrada = findViewById(R.id.ficharentradaBTN);
        botonSalida = findViewById(R.id.ficharsalidaBTN);


        // TODO RECUPERAR DATOS DEL USUARIO (A TRAVES DEL INTENT O A TRAVES DE LA BB.DD

        // TODO GUARDAR EN LA BASE DE DATOS TODOS LOS FICHAJES DIARIOS




    }

    public void ficharEntrada(View view) {

        // MOSTRAR HORA ACTUAL EN LA VISTA CUANDO PULSE EL BOTON DE FICHAR

        Log.d("MIAPP", horaFichaje());


        // Mostramos en pantalla la hora del fichaje de entrada y
        // Habilitamos la salida (Botón)
        horaEntrada.setText(horaFichaje());
        horaEntrada.setVisibility(View.VISIBLE);
        botonSalida.setEnabled(true);
        botonEntrada.setEnabled(false);

        // GUARDAR EN BB.DD.

    }


    public void ficharSalida(View view) {

        // MOSTRAR HORA ACTUAL EN LA VISTA CUANDO PULSE EL BOTON DE FICHAR

        Log.d("MIAPP", horaFichaje());


        // Mostramos en pantalla la hora del fichaje de entrada y
        // Habilitamos la salida (Botón)
        horaSalida.setText(horaFichaje());
        horaSalida.setVisibility(View.VISIBLE);
        botonSalida.setEnabled(false);
        botonEntrada.setEnabled(true);


        // GUARDAR EN BB.DD.

    }


    private String horaFichaje () {
         objDate = new Date(); // Sistema actual La fecha y la hora se asignan a objDate


        Log.d("MIAPP", objDate.toString());

        String strDateFormat = "HH:mm dd-MMMM-YYYY"; // El formato de fecha está especificado
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // La cadena de formato de fecha se pasa como un argumento al objeto

        return  objSDF.format(objDate);
    }
}
