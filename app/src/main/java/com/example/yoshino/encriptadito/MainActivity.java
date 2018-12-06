package com.example.yoshino.encriptadito;

import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final String PREFERENCIAS_COMPARTIDAD = "preferenciaCompartida";
    static final String lista_alfabeto_1 = "list01";
    static final String lista_alfabeto_2 = "list02";
    ArrayList<String> lista01 = new ArrayList<String>();
    ArrayList<String> lista02 = new ArrayList<String>();
    EditText alfabeto01;
    EditText alfabeto02;
    EditText txtClaveTrama;
    EditText txtIDSaltos;
    EditText txtClaveSaltos;
    EditText txtClaveTranspo;
    EditText txtDato;
    EditText txtLetra;
    TextView anchoalf01;
    TextView anchoalf02;
    TextView alfGenerado;
    TextView txtTitulo;
    TextView txtMsCrip;
    Button btnGuardarAlf;
    Button btnCrito1;
    Button btnLlano1;

    Metodos metodos = new Metodos();
    LinearLayout linearSeating;
    LinearLayout linearSaltos;
    LinearLayout linearTranspocision;
    LinearLayout linearTrama;
    LinearLayout idEntorno;
    RelativeLayout btcopi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         btcopi = findViewById(R.id.btncopy);
        linearSeating = findViewById(R.id.linearSeating);
        alfabeto01 = findViewById(R.id.alfPrimario);
        txtIDSaltos = findViewById(R.id.txtIDSaltos);
        alfabeto02 = findViewById(R.id.alfSecundario);
        anchoalf01 = findViewById(R.id.alfAncho01);
        txtClaveSaltos = findViewById(R.id.txtClaveSaltos);
        anchoalf02 = findViewById(R.id.alfAncho02);
        txtClaveTranspo = findViewById(R.id.txtClaveTranspo);
        alfGenerado = findViewById(R.id.alfbetoGenerado);
        txtLetra = findViewById(R.id.txtLetra);
        txtClaveTrama = findViewById(R.id.txtClaveTrama);
        linearSaltos = findViewById(R.id.linearSaltos);
        txtMsCrip = findViewById(R.id.txtMsCrip);
        txtDato = findViewById(R.id.txtDato);
        linearTranspocision = findViewById(R.id.linearTranspocision);
        linearTrama = findViewById(R.id.linearTrama);
        btnGuardarAlf = findViewById(R.id.btnGuardarAlf);
        btnLlano1 = findViewById(R.id.btnLlano);
        btnCrito1 = findViewById(R.id.btnCrito);
        txtTitulo = findViewById(R.id.txtTitulo);
        idEntorno = findViewById(R.id.idEntorno);
        loadlista01();
        loadlista02();
        btcopi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(), "Texto Copiado", Toast.LENGTH_SHORT).show();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                clipboard.setText(txtMsCrip.getText().toString());
            }
        });
    }

    public void RevelarConfig(View view) {
        linearSeating.setVisibility(View.VISIBLE);

    }

    public void OcultarConfig(View view) {
        linearSeating.setVisibility(View.GONE);
    }

    public String ObtenerAlfabeto01() {
        return alfabeto01.getText().toString();
    }

    public String ObtenerClaveSaltos() {
        return txtClaveSaltos.getText().toString();
    }

    public String ObtenerIDSaltos() {
        return txtIDSaltos.getText().toString();
    }

    public String ObtenerAlfabeto02() {
        return alfabeto02.getText().toString();
    }

    public String ObtenertxtClaveTrama() {
        return txtClaveTrama.getText().toString();
    }

    public String ObtenertxtLetra() {
        return txtLetra.getText().toString();
    }

    public String ObtenertxtClaveTranspo() {
        return txtClaveTranspo.getText().toString();
    }
    public String ObtenertxtDato() {
        return txtDato.getText().toString();
    }


    public void saveArraySharedPreference(ArrayList<String> lista, String name) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCIAS_COMPARTIDAD, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(lista);
        editor.putString(name, json);
        editor.apply();
    }

    public void loadlista01() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCIAS_COMPARTIDAD, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(lista_alfabeto_1, null);

        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        lista01 = gson.fromJson(json, type);

        if (lista01 == null) {
            lista01 = new ArrayList<>();
        } else {
            alfabeto01.setText(metodos.parsearString(lista01));
        }
    }


    public void loadlista02() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCIAS_COMPARTIDAD, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(lista_alfabeto_2, null);

        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        lista02 = gson.fromJson(json, type);

        if (lista02 == null) {
            lista02 = new ArrayList<>();
        } else {
            alfabeto02.setText(metodos.parsearString(lista02));
        }
    }
    String mensajeEncritado;
    public void EjecutarCripDescrip(View view) {
        if (ObtenerAlfabeto01().length() == ObtenerAlfabeto02().length()) {
            if (metodos.contar(ObtenerAlfabeto01()) == 1 && metodos.contar(ObtenerAlfabeto02()) == 1) {
                updateAnchoNum();
                Toast.makeText(this, "Validado y Guardado", Toast.LENGTH_SHORT).show();
                mensajeEncritado ="";
                String msj = ObtenertxtDato();
                ArrayList<String> alfCrito = metodos.convertStringToArraylist(ObtenerAlfabeto01());
                ArrayList<String> alfLlano = metodos.convertStringToArraylist(ObtenerAlfabeto02());
                if (isDececriptado){
                    for (int i = 0; i < msj.length(); i++) {
                        String letra = String.valueOf(msj.charAt(i));

                        String letraEncriptada = metodos.descriptaLetra(letra,alfLlano , alfCrito, i);
                        mensajeEncritado = mensajeEncritado + letraEncriptada;
                    }
                    txtMsCrip.setText(mensajeEncritado);
                    mensajeEncritado = "";
                }else {
                    for (int i = 0; i < msj.length(); i++) {
                        String letra = String.valueOf(msj.charAt(i));

                        String letraEncriptada = metodos.cripLetra(letra, alfCrito, alfLlano, i);
                        mensajeEncritado = mensajeEncritado + letraEncriptada;
                    }
                    txtMsCrip.setText(mensajeEncritado);
                    mensajeEncritado = "";

                }
            } else {
                Toast.makeText(this, "No repitas letras en ninguno de los alfabetos", Toast.LENGTH_SHORT).show();
            }
        } else {
            updateAnchoNum();
            Toast.makeText(this, "Ambos alfabetos deben tener las mismas dimensiones", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateAnchoNum() {
        anchoalf01.setText("N# Letras: " + ObtenerAlfabeto01().length());
        anchoalf02.setText("N# Letras: " + ObtenerAlfabeto02().length());
    }


    //    Crypto.generateAlphabet(Alphabet.EN, true, 'P')
    public void onCheckboxClicked(View view) {
        if (alphabet1 != null) {
            boolean checked = ((CheckBox) view).isChecked();

            // Check which checkbox was clicked
            switch (view.getId()) {
                case R.id.checkbox_saltos:
                    if (checked) {
                        linearSaltos.setVisibility(View.VISIBLE);
                        Toast.makeText(this, "con saltos", Toast.LENGTH_SHORT).show();
                    } else {
                        linearSaltos.setVisibility(View.GONE);
                        Toast.makeText(this, "sin saltos", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.checkbox_tanspo:
                    if (checked) {
                        linearTranspocision.setVisibility(View.VISIBLE);
                        Toast.makeText(this, "con transpocision", Toast.LENGTH_SHORT).show();
                    } else {
                        linearTranspocision.setVisibility(View.GONE);
                        Toast.makeText(this, "sin transpocision", Toast.LENGTH_SHORT).show();

                    }
                    break;

                case R.id.checkbox_trama:
                    if (checked) {
                        linearTrama.setVisibility(View.VISIBLE);
                        Toast.makeText(this, "con trama", Toast.LENGTH_SHORT).show();
                    } else {
                        linearTrama.setVisibility(View.GONE);
                        Toast.makeText(this, "sin trama", Toast.LENGTH_SHORT).show();
                    }
                    break;

            }
        } else {
            Toast.makeText(this, "Aun no ha generado del abecedario", Toast.LENGTH_SHORT).show();
        }

    }

    boolean isInverso;

    public void onCheckboxInverso(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkbox_inverso:
                if (checked) {
                    Toast.makeText(this, "Inverso", Toast.LENGTH_SHORT).show();
                    isInverso = true;
                } else {
                    Toast.makeText(this, "no inverso", Toast.LENGTH_SHORT).show();
                    isInverso = false;
                }
                break;
        }
    }



    public void GenerarAvc(View view) {
        if (ObtenertxtLetra().length() == 1) {
            alphabet1 = Crypto.generateAlphabet(Alphabet.EN, isInverso, ObtenertxtLetra().charAt(0));
            String newAvc = metodos.getStringListCharacters(alphabet1);
            alfGenerado.setText(newAvc);
            btnGuardarAlf.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "Ingrese letra de partida", Toast.LENGTH_SHORT).show();
        }

    }

    boolean isInicia;

    public void onCheckboxSaltos(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkbox_Inicia:
                if (checked) {
                    Toast.makeText(this, "Inicia", Toast.LENGTH_SHORT).show();
                    isInicia = true;
                } else {
                    Toast.makeText(this, "Parte", Toast.LENGTH_SHORT).show();
                    isInicia = false;
                }
                break;
        }
    }

    public void EjecutarSaltos(View view) {
        if (ObtenerIDSaltos().length() == 3 && ObtenerClaveSaltos().length() == 3) {

            alphabet1 = Crypto.successiveJumps(alphabet1, ObtenerClaveSaltos() + ObtenerIDSaltos(), false);
            String newAvc = metodos.getStringListCharacters(alphabet1);
            alfGenerado.setText(newAvc);
        } else {
            Toast.makeText(this, "llene los datos correctamente para continuar", Toast.LENGTH_SHORT).show();
        }
    }
    List<Character> alphabet1;
    public void EjecutarTranspocision(View view) {
        if (ObtenertxtClaveTranspo().length() > 0) {
            String newAvc = metodos.getStringListCharacters(alphabet1);
            ArrayList<String> lista = metodos.convertStringToArraylist(newAvc);
            lista = metodos.parsearList(metodos.doTransposicion(lista, ObtenertxtClaveTranspo() + "", isDescendenteT));

            newAvc = metodos.parsearString(lista);
            alphabet1.clear();
            //alphabet1 = metodos.asList(newAvc);

            // newAvc =   metodos.getStringListCharacters(alphabet1);
            alfGenerado.setText(newAvc);

            List<Character> charList = new ArrayList<Character>();
            for (char c :  newAvc.toCharArray()) {
                charList.add(c);
            }
            alphabet1=charList;

        } else {
            Toast.makeText(this, "Ingrese la clave numerica", Toast.LENGTH_SHORT).show();
        }

    }

    boolean isDescendenteT = true;

    public void onCheckboxDescendente(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkbox_descendente:
                if (checked) {
                    Toast.makeText(this, "Descendente", Toast.LENGTH_SHORT).show();
                    isDescendenteT = false;
                } else {
                    Toast.makeText(this, "Ascendente", Toast.LENGTH_SHORT).show();
                    isDescendenteT = true;
                }
                break;
        }
    }

    boolean isDescendenteTrama = true;

    public void onCheckboxDescendenteTrama(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkbox_descendenteTrama:
                if (checked) {
                    Toast.makeText(this, "Descendente", Toast.LENGTH_SHORT).show();
                    isDescendenteTrama = false;
                } else {
                    Toast.makeText(this, "Ascendente", Toast.LENGTH_SHORT).show();
                    isDescendenteTrama = true;
                }
                break;
        }
    }

    public void EjecutarTrama(View view) {
        if (ObtenertxtClaveTrama().length() > 0) {
            String newAvc = metodos.getStringListCharacters(alphabet1);
            ArrayList<String> lista = metodos.convertStringToArraylist(newAvc);
            lista = metodos.parsearList(metodos.doTrama(lista, ObtenertxtClaveTrama() + "", isDescendenteTrama));
            newAvc = metodos.parsearString(lista);

            alphabet1.clear();
            //alphabet1 = metodos.asList(newAvc);

            // newAvc =   metodos.getStringListCharacters(alphabet1);
            alfGenerado.setText(newAvc);

            List<Character> charList = new ArrayList<Character>();
            for (char c :  newAvc.toCharArray()) {
                charList.add(c);
            }
            alphabet1=charList;


        } else {
            Toast.makeText(this, "Ingrese la clave numerica", Toast.LENGTH_SHORT).show();
        }

    }

    boolean isAlfCripto;

    public void Guardar(View view) {
        String newAvc = metodos.getStringListCharacters(alphabet1);
        ArrayList<String> lista = metodos.convertStringToArraylist(newAvc);
        if (isAlfCripto) {
            saveArraySharedPreference(lista, lista_alfabeto_1);
            alfabeto01.setText(newAvc);
        }
        if (isAlfLLanno) {
            saveArraySharedPreference(lista, lista_alfabeto_2);
            alfabeto02.setText(newAvc);
        }
        refresh();
    }

    public void refresh() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);


    }

    boolean isAlfLLanno;

    public void btnCripto(View view) {
        txtTitulo.setText("Generador del alfabeto para el Cripto");
        idEntorno.setVisibility(View.VISIBLE);
        isAlfCripto = true;
        isAlfLLanno =false;
        btnCrito1.setEnabled(false);
        btnLlano1.setEnabled(true);
    }

    public void btnLLanoC(View view) {

        txtTitulo.setText("Generador del alfabeto para el LLano");
        idEntorno.setVisibility(View.VISIBLE);
        isAlfCripto = false;
        isAlfLLanno =true;
        btnLlano1.setEnabled(false);
        btnCrito1.setEnabled(true);
    }

    public void EjecutarClean(View view) {
        txtDato.setText("");
        txtMsCrip.setText("");
    }


    boolean isDececriptado = false;
    public void onCheckboxDescriptado(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkbox_desCrip:
                if (checked) {
                    Toast.makeText(this, "Decencriptado", Toast.LENGTH_SHORT).show();
                    isDececriptado = true;
                } else {
                    Toast.makeText(this, "Encriptado", Toast.LENGTH_SHORT).show();
                    isDececriptado = false;
                }
                break;
        }
    }
}
