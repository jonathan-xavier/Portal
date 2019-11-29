package com.example.portala.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.portala.R;
import com.example.portala.helper.ConfiguracaoFirebase;
import com.example.portala.model.Instituicao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail, loginSenha;
    private Switch tipoAcesso;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializarComponentes();

        auth = ConfiguracaoFirebase.getFirebaseAuth();

    }

    private void inicializarComponentes(){
        loginEmail = findViewById(R.id.editLoginEmail);
        loginSenha = findViewById(R.id.editLoginSenha);
        tipoAcesso = findViewById(R.id.switchLogar);
    }

    public void logarUsuario(Instituicao instituicao){

        auth.signInWithEmailAndPassword(
                instituicao.getEmaiInstituicao(),
                instituicao.getSenhaInstituicao()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    if (tipoAcesso.isChecked()){
                        startActivity(new Intent(LoginActivity.this, HomeInstituicaoActivity.class));
                        finish();
                    }else {
                        startActivity(new Intent(LoginActivity.this, HomeResponsavelActivity.class));
                        finish();
                    }
                }else {

                    String excecao = "";

                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        excecao = "Usuário não está cadastrado.";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "E-mail e senha não correspondem a um usuário cadastrado.";
                    }catch (Exception e){
                        excecao = "Erro ao validar acesso: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this,
                            excecao,
                            Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    public void validarAutenticacaoUsuario(View view){

        //Recuperar textos dos campos
        String textoEmail = loginEmail.getText().toString();
        String textoSenha = loginSenha.getText().toString();

        //Validar se e-mail e senha foram digitados
        if (!textoEmail.isEmpty()){//verifica e-mail
            if (!textoSenha.isEmpty()){//verifica senha

                Instituicao instituicao = new Instituicao();

                instituicao.setEmaiInstituicao(textoEmail);
                instituicao.setSenhaInstituicao(textoSenha);

                logarUsuario(instituicao);

            }else {
                Toast.makeText(LoginActivity.this,
                        "Preencha a senha!",
                        Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(LoginActivity.this,
                    "Preencha o e-mail de acesso!",
                    Toast.LENGTH_SHORT).show();
        }

    }

}
