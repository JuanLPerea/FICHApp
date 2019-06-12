package edu.cftic.fichapp;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import edu.cftic.fichapp.beans.Empleado;
import edu.cftic.fichapp.beans.Empresa;
import edu.cftic.fichapp.beans.Fichaje;
import edu.cftic.fichapp.controlador.DB;

public class RegistroEntradaSalida extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private LinearLayout linearBase;
    private TextView horaEntrada, horaSalida, textoCronoFichaje, tituloTV;
    private EditText mensajeET;
    private Button botonEntrada, botonSalida;
    private Date objDate;
    private CountDownTimer cronoFichaje;
    private CheckBox mensajeCheck;
    private Spinner spinner;
    private boolean haFichado;
    private int tipoFichaje;
    private DB bdd;
    private Fichaje ultimoFichaje;


    private final int tiempoFichar = 60000;
    private final int tiempoActualizaCrono = 1000;

    private Fichaje nuevoFichaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_entrada_salida);

        String[] mensajes_tipo = getResources().getStringArray(R.array.mensajes_array);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mensajes_tipo);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(this);


        // Ocultar teclado Virtual
        hideKeyboard(this);


        horaEntrada = findViewById(R.id.horaEntrada);
        horaSalida = findViewById(R.id.horaSalida);

        botonEntrada = findViewById(R.id.ficharentradaBTN);
        botonSalida = findViewById(R.id.ficharsalidaBTN);

        textoCronoFichaje = findViewById(R.id.cronoFichaje);
        mensajeCheck = findViewById(R.id.checkmensaje);
        mensajeET = findViewById(R.id.mensajeET);

        tituloTV = findViewById(R.id.tituloTV);

        tituloTV.requestFocus();


        // Utilizamos un boolean para saber si ha fichado o no. Cuando entra en esta Actividad, de primeras es false hasta que pulse el botón de fichar
        // También desactivamos el EditText del mensaje y el check para incluir un mensaje hasta que hayamos fichado
        haFichado = false;
        mensajeET.setEnabled(false);
        mensajeCheck.setEnabled(false);


        // Instanciamos la BB.DD.
        bdd = new DB(this);

        cargarDatosPrueba();


        // Cronometro, tiene un tiempo máximo para fichar (1 min.) si finaliza, vuelve a la Actividad Anterior
        cronoFichaje = new CountDownTimer(tiempoFichar, tiempoActualizaCrono) {
            @Override
            public void onTick(long millisUntilFinished) {
                textoCronoFichaje.setText(millisUntilFinished / 1000 + "");
                //    Log.d("MIAPP", "Ha pasado un segundo " + millisUntilFinished / 1000 + textoCronoFichaje.toString());
            }

            @Override
            public void onFinish() {
                // Si acaba el cronometro y no ha fichado, volver a la pantalla de login
                textoCronoFichaje.setText("Ha terminado el tiempo. ");

               // cronoFichaje.cancel();
                salir(null);


            }
        };
        cronoFichaje.start();


        Log.d("MIAPP", "Entrada: " + ultimoFichaje.getFechainicio());
        Log.d("MIAPP", "Salida: " + ultimoFichaje.getFechafin());

        // Si no existe ultimo fichaje (La primera vez que ficha un nuevo empleado
        // Tenemos que crear un ultimo fichaje vacío
        if (ultimoFichaje == null) {

        }


        // Establecemos el tipo de fichaje si es de salida o de entrada
        if (!ultimoFichaje.getFechafin().equals(new Timestamp(0))) {
            // Tipo fichaje 2 es fichaje de entrada (Fichaje Nuevo)
            tipoFichaje = 1;
        } else {
            // Tipo fichaje 1 es fichaje de salida (Modificar fichaje)
            tipoFichaje = 2;
        }

        // Actualizamos la vista dependiendo si el último fichaje fue de entrada o de salida
        actualizarVista(tipoFichaje);


    }

    @Override
    protected void onResume() {
        super.onResume();
        hideKeyboard(this);
    }

    public void ficharEntrada(View view) {

        // MOSTRAR HORA ACTUAL EN LA VISTA CUANDO PULSE EL BOTON DE FICHAR

        Log.d("MIAPP", horaFichaje());

        horaEntrada.setVisibility(View.VISIBLE);
        horaEntrada.setText(horaFichaje());
        mensajeET.setEnabled(true);

        haFichado = true;


    }


    public void ficharSalida(View view) {

        // MOSTRAR HORA ACTUAL EN LA VISTA CUANDO PULSE EL BOTON DE FICHAR

        Log.d("MIAPP", horaFichaje());

        horaSalida.setVisibility(View.VISIBLE);
        horaSalida.setText(horaFichaje());
        mensajeET.setEnabled(true);
        haFichado = true;


    }


    private String horaFichaje() {
        objDate = new Date(); // Sistema actual La fecha y la hora se asignan a objDate

        Log.d("MIAPP", objDate.toString());

        String strDateFormat = "HH:mm dd-MMMM-YYYY"; // El formato de fecha está especificado
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // La cadena de formato de fecha se pasa como un argumento al objeto

        return objSDF.format(objDate);
    }

    private void actualizarVista(int tipoFichaje) {

        switch (tipoFichaje) {
            case 1:

                // El último fichaje fue de salida
                // Fichaje nuevo

                horaEntrada.setVisibility(View.VISIBLE);
                botonSalida.setEnabled(false);
                botonEntrada.setEnabled(true);

                break;
            case 2:

                // El último fichaje fue Entrada
                // Mostramos en pantalla la hora del fichaje de entrada y
                // Habilitamos la salida (Botón)


                String strDateFormat = "HH:mm dd-MMMM-YYYY"; // El formato de fecha está especificado
                SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // La cadena de formato de fecha se pasa como un argumento al objeto
                String ultimoFichajeFormateado = objSDF.format(ultimoFichaje.getFechainicio());


                horaEntrada.setText(ultimoFichajeFormateado);
                botonSalida.setEnabled(true);
                botonEntrada.setEnabled(false);
                break;


        }
    }

    private void guardarBBDD() {

        objDate = new Date(); // Sistema actual La fecha y la hora se asignan a objDate
        Timestamp fichajeTimestamp = new Timestamp(new Date().getTime());

        switch (tipoFichaje) {
            case 1:
                // Actualizamos el nuevo fichaje y lo guardamos en la BB.DD
                Empleado empleado_ultimo = ultimoFichaje.getEmpleado();
                nuevoFichaje = new Fichaje(empleado_ultimo, fichajeTimestamp, new Timestamp(0), "");
                if (mensajeCheck.isChecked()) {
                    // Guardar mensaje
                    nuevoFichaje.setMensaje(mensajeET.getText().toString());
                }

                DB.fichar.nuevo(nuevoFichaje);
                break;
            case 2:
                // Actualizar el fichaje que existía, añadiendo el campo de salida y lo guardamos en la BB.DD
                ultimoFichaje.setFechafin(fichajeTimestamp);
                // Si hemos incluido mensaje, ponerlo
                // Guardar en Base de Datos
                if (mensajeCheck.isChecked()) {
                    // Guardar mensaje
                    ultimoFichaje.setMensaje(mensajeET.getText().toString());
                }

                DB.fichar.actualizar(ultimoFichaje);
                break;

        }

    }


    // Seleccionar texto en el Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position != 0) {
            Log.d("MIAPP", "SELECCIONADO SPINNER");
            mensajeET.setText(getResources().getTextArray(R.array.mensajes_array)[position]);
            mensajeCheck.setChecked(true);
        } else {
            hideKeyboard(this);
            view.clearFocus();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public void salir(View view) {

        cronoFichaje.cancel();

        if (haFichado) {
            guardarBBDD();
        }

        // Volvemos a la actividad de login
        Intent intentSalida = new Intent(this, MainActivity.class);
        startActivity(intentSalida);

    }

    private void cargarDatosPrueba() {
        Empresa em = new Empresa("B123456", "XYZYZ SA", "T T", "xyz@xyz.com");
        //boolean v = DB.empresaDao.nuevo(em);
        //Empleado nu = DB.empleados.getEmpleadoUsuarioClave("","");
//        ArrayList<Empresa> ae = (ArrayList<Empresa>) DB.empresas.getEmpresas();
        //       em = DB.empresas.ultimo();

        Log.i("APPK", "u: " + em);

        Empleado tr = new Empleado("JUAN YONG 2", "JYON3", "12345", "B", false, em);
        boolean t = DB.empleados.nuevo(tr);
        ArrayList<Empleado> at = (ArrayList<Empleado>) DB.empleados.getEmpleados();

        tr = DB.empleados.ultimo();
        Log.i("APPK", "E: " + tr);
        for (Empleado es : at) {
            Log.i("APPK", "= " + es);
        }
        at = (ArrayList<Empleado>) DB.empleados.getEmpleados();
/*
        Timestamp de = new Timestamp(new Date().getTime());
        Timestamp hasta = new Timestamp(new Date().getTime());

        Fichaje fe = new Fichaje(tr, de, hasta, "Mensaje");
        Log.i("APPK", "F: " + fe);
        boolean d = DB.fichar.nuevo(fe);
        ArrayList<Fichaje> af = (ArrayList<Fichaje>) DB.fichar.getFicheje(tr.getId_empleado());

        for (Fichaje es : af) {
            Log.i("APPK", "= " + es);
        }

        ArrayList<String> rol = (ArrayList<String>) DB.empleados.getRoles();
        for (String es : rol) {
            Log.i("APPK", "R:: " + es);
        }
*/

        // RECUPERAR DATOS DEL USUARIO (A TRAVES DEL INTENT
        Intent intent = getIntent();
        String idEmpleado = intent.getStringExtra("IDEMPLEADO");


        // Recuperar el último fichaje
        Fichaje ul = DB.fichar.getFichajeUltimo(tr.getId_empleado());
        ultimoFichaje = ul;
        Log.i("APPK", "" + ul.toString());
    }
}
