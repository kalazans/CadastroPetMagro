package Model;

import java.util.Scanner;

public class Endereco {
    private String rua;
    private String cidade;
    private int numeroCasa;

    public Endereco(){
    }

    public Endereco(String rua,String cidade,int numeroCasa){
        this.rua = rua;
        this.cidade =cidade;
        this.numeroCasa = numeroCasa;
    }

    public String getRua() {
        return rua;
    }

    public String getCidade() {
        return cidade;
    }

    public <T>T getNumeroCasa() {
        if(this.numeroCasa==0){
            return (T) NAOINFORMADOSINGLETON.NÃO_INFORMADO;
        } else{
            return (T)Integer.valueOf(this.numeroCasa);
        }
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }
    public void atualizarInfoEnderecoCompleta(String cidade,String rua,int numero){
        this.cidade =cidade;
        this.rua =rua;
        this.numeroCasa= numero;

    }

    @Override
    public String toString() {
        return "{" +
                "rua='" + rua + '\'' +
                ", cidade='" + cidade + '\'' +
                ", numeroCasa=" + numeroCasa +
                '}';
    }
}
