package Model;

public enum TIPO {
    GATO("gato"),
    CACHORRO("cachorro");

    private String tipo;

    TIPO(String animal){
        this.tipo = animal;
    }

    public String getTipo() {
        return tipo.toUpperCase();
    }
}
