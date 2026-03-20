package fatec.sp.gov.br.jokenpo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Variáveis de controle do placar e estado do jogo
    private int pontosPC = 0;
    private int pontosYOU = 0;
    private boolean jogoAtivo = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Métodos chamados pelos OnClicks das imagens no XML
    public void selecionadoPedra(View view) { this.opcaoSelecionada("pedra"); }
    public void selecionadoPapel(View view) { this.opcaoSelecionada("papel"); }
    public void selecionadoTesoura(View view) { this.opcaoSelecionada("tesoura"); }

    public void opcaoSelecionada(String escolhaUsuario) {
        // Se o jogo já acabou, não processa mais jogadas até reiniciar
        if (!jogoAtivo) return;

        ImageView imageEscolhaApp = findViewById(R.id.imageEscolhaApp);
        TextView textResultado = findViewById(R.id.textResultado);
        TextView textPlacar = findViewById(R.id.textPlacar);

        // Lógica do Computador (Gera 0, 1 ou 2)
        int numero = new Random().nextInt(3);
        String[] opcoes = {"pedra", "papel", "tesoura"};
        String escolhaApp = opcoes[numero];

        // Atualiza a imagem da escolha do PC
        switch (escolhaApp) {
            case "pedra": imageEscolhaApp.setImageResource(R.drawable.pedra); break;
            case "papel": imageEscolhaApp.setImageResource(R.drawable.papel); break;
            case "tesoura": imageEscolhaApp.setImageResource(R.drawable.tesoura); break;
        }

        // Comparação de Regras
        if ((escolhaApp.equals("pedra") && escolhaUsuario.equals("tesoura")) ||
                (escolhaApp.equals("tesoura") && escolhaUsuario.equals("papel")) ||
                (escolhaApp.equals("papel") && escolhaUsuario.equals("pedra"))) {

            pontosPC++;
            textResultado.setText("PC venceu a rodada!");

        } else if ((escolhaUsuario.equals("pedra") && escolhaApp.equals("tesoura")) ||
                (escolhaUsuario.equals("tesoura") && escolhaApp.equals("papel")) ||
                (escolhaUsuario.equals("papel") && escolhaApp.equals("pedra"))) {

            pontosYOU++;
            textResultado.setText("YOU venceu a rodada!");

        } else {
            textResultado.setText("Empate na rodada!");
        }

        // Atualiza o placar e verifica vencedor final
        atualizarEstadoDoJogo(textPlacar, textResultado);
    }

    private void atualizarEstadoDoJogo(TextView textPlacar, TextView textResultado) {
        // Exibe o placar atualizado
        textPlacar.setText(pontosPC + " x " + pontosYOU);

        // Verifica se alguém atingiu 2 pontos (vencedor da melhor de 3)
        if (pontosPC == 2) {
            textResultado.setText("O COMPUTADOR É O GRANDE VENCEDOR!");
            jogoAtivo = false;
        } else if (pontosYOU == 2) {
            textResultado.setText("VOCÊ É O GRANDE VENCEDOR!");
            jogoAtivo = false;
        }
    }


    public void reiniciarJogo(View view) {
        pontosPC = 0;
        pontosYOU = 0;
        jogoAtivo = true;

        TextView textPlacar = findViewById(R.id.textPlacar);
        TextView textResultado = findViewById(R.id.textResultado);
        ImageView imageEscolhaApp = findViewById(R.id.imageEscolhaApp);

        textPlacar.setText("-- x --");
        textResultado.setText("Escolha uma opção para começar");
        imageEscolhaApp.setImageResource(R.drawable.padrao);
    }
}
