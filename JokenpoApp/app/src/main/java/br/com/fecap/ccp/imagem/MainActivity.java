package br.com.fecap.ccp.imagem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private boolean pontuacaoFinal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void selectPapel(View view){
        this.opcaoSelecionada("papel");
    }
    public void selectPedra(View view){
        this.opcaoSelecionada("pedra");
    }
    public void selectTesoura(View view){
        this.opcaoSelecionada("tesoura");
    }
    public void opcaoSelecionada(String opcaoSelecionada){



        // Instanciamento dos Objetos ImagemView e TextView
        ImageView imagemResultado = findViewById(R.id.imagePadrao);
        TextView textResultado = findViewById(R.id.textResultado);
        TextView textResultcpu = findViewById(R.id.textResultadocpu);
        TextView textResultSelecao = findViewById(R.id.textResultSelecao);
        TextView textPontuacao = findViewById(R.id.textPontuacao);

        //transforma os numeros de textos em string
        String resultApp = textResultcpu.getText().toString();
        String resultSelecao = textResultSelecao.getText().toString();
        String Pontuacao = textPontuacao.getText().toString();

        // converte para inteiro a sting
        int resApp = Integer.parseInt(resultApp);
        int resSelecao = Integer.parseInt(resultSelecao);
        int resPontuacao = Integer.parseInt(Pontuacao);

        // Logica da Máquina de seleção
        int numero = new Random().nextInt(3);
        String[] opcoes = {"pedra", "papel", "tesoura"};
        String opcaoApp = opcoes[numero];

        // Apresentando resultadopela cpu
        switch (opcaoApp){
            case "pedra":
                imagemResultado.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imagemResultado.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imagemResultado.setImageResource(R.drawable.tesoura);
                break;
        }

        // Logica do Jogo
        if (
                (opcaoApp.equals("tesoura") && opcaoSelecionada.equals("papel")) ||
                (opcaoApp.equals("papel") && opcaoSelecionada.equals("pedra")) ||
                (opcaoApp.equals("pedra") && opcaoSelecionada.equals("tesoura"))
        ){
            // Mensagem derrota
            textResultado.setText("Perdeu Parceiro");

            if (!pontuacaoFinal && resApp < 3) {
                resApp += 1;
                textResultcpu.setText(String.valueOf(resApp));
            }
            // Zera a pontuação quando
            resPontuacao =0;
            textPontuacao.setText(String.valueOf(resPontuacao));

        } else if (
                (opcaoSelecionada.equals("tesoura") && opcaoApp.equals("papel")) ||
                (opcaoSelecionada.equals("papel") && opcaoApp.equals("pedra")) ||
                (opcaoSelecionada.equals("pedra") && opcaoApp.equals("tesoura"))
        ){
            //mensagem ganhou
            textResultado.setText("Ganhou");
            if (!pontuacaoFinal && resSelecao < 3) {
                resSelecao += 1;
                textResultSelecao.setText(String.valueOf(resSelecao));
            }
            resPontuacao +=1;
            textPontuacao.setText(String.valueOf(resPontuacao));

        } else {
            //empate
            textResultado.setText("Empatou");
        }
        // Verifica se ja acabou a melhor de 3 e finaliza a contagem de pontos naquele contador especifico
        if (resApp >= 3 || resSelecao >= 3) {
            pontuacaoFinal = true;
            if (resApp >= 3) {
                textResultcpu.setTextColor(getResources().getColor(R.color.corderrota));

            } else {
                textResultSelecao.setTextColor(getResources().getColor(R.color.corderrota));
            }
        }
    }

    public void reiniciar(View view){
        //instanciamento
        ImageView imagemResultado = findViewById(R.id.imagePadrao);
        TextView textResultApp = findViewById(R.id.textResultadocpu);
        TextView textResultado = findViewById(R.id.textResultado);
        TextView textResultSelecao = findViewById(R.id.textResultSelecao);
        TextView textPontuacao = findViewById(R.id.textPontuacao);
        TextView appName = findViewById(R.id.textcpu);
        TextView nameVoce = findViewById(R.id.textVoce);
        TextView textMelhorDe3 = findViewById(R.id.textMelhorDe3);
        TextView textX = findViewById(R.id.textX);
        TextView textNamePont = findViewById(R.id.texttituloPontuacao);

        imagemResultado.setImageResource(R.drawable.padrao);
        textResultado.setText("Escolha o que deseja:");
        textResultApp.setText("0");
        textResultSelecao.setText("0");
        textPontuacao.setText("0");

        //exibir os modos de jogo
        textResultSelecao.setTextColor(getResources().getColor(R.color.white));
        textResultApp.setTextColor(getResources().getColor(R.color.white));
        textPontuacao.setVisibility(View.VISIBLE);
        textResultApp.setVisibility(View.VISIBLE);
        textResultSelecao.setVisibility(View.VISIBLE);
        appName.setVisibility(View.VISIBLE);
        nameVoce.setVisibility(View.VISIBLE);
        textMelhorDe3.setVisibility(View.VISIBLE);
        textX.setVisibility(View.VISIBLE);
        textNamePont.setVisibility(View.VISIBLE);
        textPontuacao.setVisibility(View.VISIBLE);
        textResultApp.setVisibility(View.VISIBLE);

        // Reativa
        pontuacaoFinal = false;

    }

    // logica para definir o modo do jogo
    public void modoJogo(View view) {

        // instanciando
        TextView textResultado = findViewById(R.id.textResultado);
        TextView textPontuacao = findViewById(R.id.textPontuacao);
        TextView textResultApp = findViewById(R.id.textResultadocpu);
        TextView textResultSelecao = findViewById(R.id.textResultSelecao);
        TextView textNamePont = findViewById(R.id.texttituloPontuacao);
        TextView appName = findViewById(R.id.textcpu);
        TextView nameVoce = findViewById(R.id.textVoce);
        TextView textMelhorDe3 = findViewById(R.id.textMelhorDe3);
        TextView textX = findViewById(R.id.textX);


        textResultApp.setText("0");
        textResultSelecao.setText("0");
        textPontuacao.setText("0");
        textResultado.setText("Escolha o que deseja:");
        textResultSelecao.setTextColor(getResources().getColor(R.color.white));
        textResultApp.setTextColor(getResources().getColor(R.color.white));

        // Reativa a contagem de pontos
        pontuacaoFinal = false;

        //logica de qual botao foi escolhido.
        if (view.getId() == R.id.btnMelhorDe3) {

        // adicona o modo melhor de tres, que funciona no sentido de ocultar a parte não usada, e deixar a mostra o que será utilizado
            textPontuacao.setVisibility(View.GONE);
            textNamePont.setVisibility(View.GONE);
            textResultApp.setVisibility(View.VISIBLE);
            textPontuacao.setVisibility(View.GONE);
            textResultApp.setVisibility(View.VISIBLE);
            textResultSelecao.setVisibility(View.VISIBLE);
            appName.setVisibility(View.VISIBLE);
            nameVoce.setVisibility(View.VISIBLE);
            textMelhorDe3.setVisibility(View.VISIBLE);
            textX.setVisibility(View.VISIBLE);

        } else if (view.getId() == R.id.btnPontosCorrida) {

            // adicona o modo corrido, que funciona no sentido de ocultar a parte não usada, e deixar a mostra o que será utilizado
            textNamePont.setVisibility(View.VISIBLE);
            textPontuacao.setVisibility(View.VISIBLE);
            textResultApp.setVisibility(View.GONE);
            textResultSelecao.setVisibility(View.GONE);
            appName.setVisibility(View.GONE);
            nameVoce.setVisibility(View.GONE);
            textMelhorDe3.setVisibility(View.GONE);
            textX.setVisibility(View.GONE);

        }
    }

}



