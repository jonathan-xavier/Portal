package com.example.portalamais.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.example.portalamais.model.Instituicao;
import com.example.portalamais.model.Responsavel;

import com.example.portalamais.R;
import com.example.portalamais.helper.ConfigFirebase;
import com.example.portalamais.model.Responsavel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroUsuarioActivity extends AppCompatActivity {


    private Button botaoCadastrar;
    private EditText campoSenha;
    private EditText campoEmail;
    private FirebaseAuth autenticacao;
    private Responsavel responsavel;
    private Switch tipoAcesso;
    private Instituicao instituicao;
    private TextView voltarLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro_usuario);
        inicializarComponentes();


        voltarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginAutenticacaoActivity.class));
            }
        });

        autenticacao = ConfigFirebase.getFirebaseAutenticacao();


            botaoCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String email  = campoEmail.getText().toString();
                    final String senha = campoSenha.getText().toString();


                    if(!email.isEmpty()){
                        if(!senha.isEmpty()) {


                            //---------------inicio do se switch------------------------
                            if (tipoAcesso.isChecked()) {
                                //cadastro instituição

                                autenticacao.createUserWithEmailAndPassword(
                                        email, senha
                                ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                            //atribuindo email e senha ao objeto Instituição.

                                            instituicao.setEmailAdministracao(email);
                                            instituicao.setSenhaAdministracao(senha);




                                            //enviar usuario para tela principal
                                            Toast.makeText(CadastroUsuarioActivity.this, "Cadastro realizado com sucesso!",
                                                    Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), HomeInstituicaoActivity.class));
                                        } else {
                                            String erroExcecao = "";

                                            try {
                                                throw task.getException();

                                            } catch (FirebaseAuthWeakPasswordException e) {
                                                erroExcecao = "Digite uma senha forte!";
                                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                                erroExcecao = "Favor, Digite um email válido";

                                            } catch (FirebaseAuthUserCollisionException e) {
                                                erroExcecao = "Está conta ja foi cadastrada";

                                            } catch (Exception e) {
                                                erroExcecao = "ao cadastrar usuario:" + e.getMessage();
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                });


                            } else {

                                //cadastro responsavel

                            //se estiver preenchido fazer autenticacão.
                            autenticacao.createUserWithEmailAndPassword(
                                    email, senha
                            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        //atribuindo email e senha ao objeto Responsável.
                                        responsavel.setEmailResponsavel(email);
                                        responsavel.setSenhaResponsavel(senha);


                                        //enviar usuario para tela principal
                                        Toast.makeText(CadastroUsuarioActivity.this, "Cadastro realizado com sucesso!",
                                                Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), HomeUsuarioActivity.class));
                                    } else {
                                        String erroExcecao = "";

                                        try {
                                            throw task.getException();

                                        } catch (FirebaseAuthWeakPasswordException e) {
                                            erroExcecao = "Digite uma senha forte!";
                                        } catch (FirebaseAuthInvalidCredentialsException e) {
                                            erroExcecao = "Favor, Digite um email válido";

                                        } catch (FirebaseAuthUserCollisionException e) {
                                            erroExcecao = "Está conta ja foi cadastrada";

                                        } catch (Exception e) {
                                            erroExcecao = "ao cadastrar usuario:" + e.getMessage();
                                            e.printStackTrace();
                                        }
                                    }
                                }

                            });

                        }//---------------fim do se do switch----------------------

                        }else{
                            Toast.makeText(CadastroUsuarioActivity.this,"Insira a Senha",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(CadastroUsuarioActivity.this,"Insira o Email",Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    //voltar login

    //autenticar os dados
    private void inicializarComponentes(){
        botaoCadastrar = findViewById(R.id.buttonCadastroCadastrar);
        campoSenha = findViewById(R.id.editLoginSenha);
        campoEmail = findViewById(R.id.editLoginEmail);
        tipoAcesso = findViewById(R.id.switchCadastro);

        voltarLogin = findViewById(R.id.textViewVoltarParaLogin);
        responsavel = new Responsavel();
        instituicao  = new Instituicao();

    }
}
