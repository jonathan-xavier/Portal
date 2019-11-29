package com.example.portala.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portala.R;
import com.example.portala.helper.ConfiguracaoFirebase;
import com.example.portala.model.Instituicao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private EditText nomeInst, emailInst, senhaInst;
    private Button cadastrar;
    private TextView voltarLogin;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        inicializarComponentes();

    }

    private void inicializarComponentes(){
        nomeInst = findViewById(R.id.editCadastroNomeInstituicao);
        emailInst = findViewById(R.id.editCadastroEmail);
        senhaInst = findViewById(R.id.editCadastroSenha);
        cadastrar = findViewById(R.id.buttonCadastroLogar);
        voltarLogin = findViewById(R.id.textViewVoltarParaLogin);
    }

    public void cadastrarInstituicao(Instituicao instituicao){
        auth = ConfiguracaoFirebase.getFirebaseAuth();
        auth.createUserWithEmailAndPassword(
                instituicao.getEmaiInstituicao(), instituicao.getSenhaInstituicao()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this,
                            "Cadastrado com sucesso!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    String excecao = "";

                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte!";
                    }catch (FirebaseAuthInvalidUserException e){
                        excecao = "Por favor, digite um email válido!";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Esta conta já foi cadastrada!";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroActivity.this,
                            excecao,
                            Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    public void validarCadastroInstituicao(View view){
        String textoNome = nomeInst.getText().toString();
        String textoEmail = emailInst.getText().toString();
        String textoSenha = senhaInst.getText().toString();

        if (!textoNome.isEmpty()){//verifica nome
            if (!textoEmail.isEmpty()){//verifica e-mail
                if (!textoSenha.isEmpty()){//verifica senha

                    Instituicao instituicao = new Instituicao();

                    instituicao.setNomeInstituicao(textoNome);
                    instituicao.setEmaiInstituicao(textoEmail);
                    instituicao.setSenhaInstituicao(textoSenha);

                    cadastrarInstituicao(instituicao);

                }else {
                    Toast.makeText(CadastroActivity.this,
                            "Preencha a senha de acesso!",
                            Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(CadastroActivity.this,
                        "Preencha o e-mail de acesso!",
                        Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(CadastroActivity.this,
                    "Preencha o nome!",
                    Toast.LENGTH_SHORT).show();
        }

    }

}
