package Model;

public enum SEXO {
    M("MACHO"),
    F("FEMEA");

    private String sexo;


    SEXO(String s){
       this.sexo=s;
       }


    public String getSexo() {
        return sexo.toUpperCase();
    }




}
