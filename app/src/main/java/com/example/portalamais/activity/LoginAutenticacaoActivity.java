package com.example.portalamais.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.portalamais.R;
import com.example.portalamais.helper.ConfigFirebase;
import com.example.portalamais.helper.UsuarioFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginAutenticacaoActivity extends AppCompatActivity {

    private Button botaoLogar;
    private EditText campoEmail,campoSenha;
    private TextView criarConta;

    private FirebaseAuth autenticacao;
    private Switch tipoAcesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);

        getSupportActionBar().hide();

        inicializarComponentes();
        autenticacao = ConfigFirebase.getFirebaseAutenticacao();
        //autenticacao.signOut();


        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = campoEmail.getText().toString();
                String senha  = campoSenha.getText().toString();

                if(!email.isEmpty() ){
                    if(!senha.isEmpty()){


                        //---------------inicio do se switch---------------------
                            if(tipoAcesso.isChecked()){
                                //login de Instituicao

                            }else {
                                //login de usuario

                                //se correto enviar para home
                                autenticacao.signInWithEmailAndPassword(
                                        email, senha
                                ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()) {
                                            //se logado com sucesso
                                            Toast.makeText(LoginAutenticacaoActivity.this,
                                                    "Logado com sucesso!", Toast.LENGTH_SHORT).show();
                                            String tipoUsuario = getTipoUsuario();
                                            UsuarioFirebase.atualizarTipoUsuario(tipoUsuario);
                                            abrirTelaPrincipal(tipoUsuario);
                                        } else {
                                            //se não passar
                                            Toast.makeText(LoginAutenticacaoActivity.this,
                                                    "Email ou senha incorretos! : " + task.getException(),
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }//----------------------fim do se switch-------------------------------

                }else{
                    Toast.makeText(LoginAutenticacaoActivity.this,"Senha não preenchida",Toast.LENGTH_SHORT).show();
                }

                }else{
                    Toast.makeText(LoginAutenticacaoActivity.this,"Email não preenchido",Toast.LENGTH_SHORT).show();
                }


            }
        });

        criarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CadastroUsuarioActivity.class));
            }
        });

    }
    private String getTipoUsuario(){
        return tipoAcesso.isChecked()? "I" : "U";
    }

    private void verificarUsuarioLogado(){
        FirebaseUser usuarioAtual = autenticacao.getCurrentUser();
        if(usuarioAtual != null){
            String tipoUsuario = usuarioAtual.getDisplayName();
            abrirTelaPrincipal(tipoUsuario);
        }

    }

    private void abrirTelaPrincipal(String tipoUsuario){

        if(tipoUsuario.equals("I")){//intituicao
            startActivity(new Intent(getApplicationContext(), HomeInstituicaoActivity.class));
<<<<<<< HEAD

=======
>>>>>>> origin/branchleandro
        }else{//usuario
            startActivity(new Intent(getApplicationContext(), HomeUsuarioActivity.class));
        }
    }

    private void inicializarComponentes(){
        campoEmail = findViewById(R.id.editLoginEmail);
        campoSenha = findViewById(R.id.editLoginSenha);
        botaoLogar = findViewById(R.id.buttonCadastrarCadastrar);
        criarConta = findViewById(R.id.textViewCriarConta);
        tipoAcesso = findViewById(R.id.switchLogar);
    }
}
