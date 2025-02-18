package pacote;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {
    private FileWriter arqw;
    private BufferedWriter escritor;
    private FileReader arqr;
    private BufferedReader leitor;
    public List<Anabolizante> listaAnabolizante;
    public String nomeArquivo;

    public Arquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        listaAnabolizante = new ArrayList<>();
    }

    public void gravarArquivo(Anabolizante p) {
        try {
            arqw = new FileWriter(nomeArquivo + ".csv", true);
            escritor = new BufferedWriter(arqw);
            // Formato: Código,Descrição,PreçoCusto,PreçoVenda,Estoque
            escritor.write(p.getNome() + "," + 
                           p.getCodigo() + "," + 
                           p.getDescricao() + ","+ 
                           p.getValor()+ ","+ 
                           p.getQuantidade());
            escritor.newLine();
            escritor.close();
            arqw.close();

            System.out.println("Anabolizante salvo no arquivo!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Anabolizante> lerArquivo() {
        System.out.println("Anabolizantes lidos do arquivo:");
        try {
            arqr = new FileReader(nomeArquivo + ".csv");
            leitor = new BufferedReader(arqr);
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] campos = linha.split(",");

                String nome = campos[0];
                String codigo = campos[1];
                String descricao = campos[2];
                String valor = campos[3];
                String quantidade = campos[4];

                Anabolizante anabolizante = new Anabolizante(nome, codigo, descricao,valor, quantidade);
                listaAnabolizante.add(anabolizante);
            }
            leitor.close();
            arqr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaAnabolizante;
    }

}