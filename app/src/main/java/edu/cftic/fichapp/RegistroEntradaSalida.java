package edu.cftic.fichapp;

import android.icu.text.SimpleDateFormat;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Date;

public class RegistroEntradaSalida extends AppCompatActivity {

    private TextView horaEntrada, horaSalida, textoCronoFichaje;
    private Button botonEntrada, botonSalida;
    private Date objDate;
    private CountDownTimer cronoFichaje;
    private CheckBox mensajeCheck;

    private final int tiempoFichar = 30000;
    private final int tiempoActualizaCrono = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_entrada_salida);

        horaEntrada = findViewById(R.id.horaEntrada);
        horaSalida = findViewById(R.id.horaSalida);

        botonEntrada = findViewById(R.id.ficharentradaBTN);
        botonSalida = findViewById(R.id.ficharsalidaBTN);

        textoCronoFichaje = findViewById(R.id.cronoFichaje);
        mensajeCheck = findViewById(R.id.checkmensaje);


        cronoFichaje = new CountDownTimer(tiempoFichar, tiempoActualizaCrono) {
            @Override
            public void onTick(long millisUntilFinished) {
                textoCronoFichaje.setText(millisUntilFinished / 1000 + "");
                Log.d("MIAPP", "Ha pasado un segundo " + millisUntilFinished / 1000 + textoCronoFichaje.toString());
            }

            @Override
            public void onFinish() {
                // Si acaba el cronometro y no ha fichado, volver a la pantalla de login
                textoCronoFichaje.setText("Ha terminado el tiempo. ");
            }
        };
        cronoFichaje.start();


        // TODO HABILITAR EL EDITTEXT DEL MENSAJE PARA QUE SOLO PUEDA ESCRIBIR CUANDO HAYA FICHADO


        // TODO Listener para el checkbox del mensaje PARA QUE ESCRIBA EL MENSAJE EN BBDD CUANDO LE DEMOS AL CHECK


        // TODO RECUPERAR DATOS DEL USUARIO (A TRAVES DEL INTENT O A TRAVES DE LA BB.DD)
        // TODO RECUPERAR EL ULTIMO FICHAJE PARA SABER SI FUE DE ENTRADA O SALIDA

       // Fichaje fichaje = consultarBBDD.getUltimoFichaje(idUsuario);

        //    actualizarVista(fichaje.getTipoUltimoFichaje);


        actualizarVista(1);

        // TODO GUARDAR EN LA BASE DE DATOS TODOS LOS FICHAJES DIARIOS




    }

    public void ficharEntrada(View view) {

        // MOSTRAR HORA ACTUAL EN LA VISTA CUANDO PULSE EL BOTON DE FICHAR

        Log.d("MIAPP", horaFichaje());

        horaEntrada.setVisibility(View.VISIBLE);
        horaEntrada.setText(horaFichaje());

        // GUARDAR EN BB.DD.

    }


    public void ficharSalida(View view) {

        // MOSTRAR HORA ACTUAL EN LA VISTA CUANDO PULSE EL BOTON DE FICHAR

        Log.d("MIAPP", horaFichaje());

        horaSalida.setVisibility(View.VISIBLE);
        horaSalida.setText(horaFichaje());



        // GUARDAR EN BB.DD.

    }


    private String horaFichaje () {
        objDate = new Date(); // Sistema actual La fecha y la hora se asignan a objDate

        Log.d("MIAPP", objDate.toString());

        String strDateFormat = "HH:mm dd-MMMM-YYYY"; // El formato de fecha está especificado
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // La cadena de formato de fecha se pasa como un argumento al objeto

        return  objSDF.format(objDate);
    }

    private void actualizarVista (int tipoFichaje) {

        switch (tipoFichaje) {
            case 1:
                // El último fichaje fue Entrada
                // Mostramos en pantalla la hora del fichaje de entrada y
                // Habilitamos la salida (Botón)

                botonSalida.setEnabled(false);
                botonEntrada.setEnabled(true);

                break;
            case 2:
                // El último fichaje fue de salida
                // Mostramos en pantalla la hora del fichaje de entrada y
                // Habilitamos la salida (Botón)


                botonSalida.setEnabled(true);
                botonEntrada.setEnabled(false);
                break;


        }
    }

    private void guardarBBDD () {

        if (mensajeCheck.isChecked()) {
            // Guardar mensaje
        }


        // Guardar en Base de Datos

    }
}
