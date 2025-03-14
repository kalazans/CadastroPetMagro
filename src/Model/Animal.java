package Model;

public class Animal {
    private String firstName;
    private String lastName;
    private TIPO tipo;
    private SEXO sexo;
    private Endereco endereco;
    private double idade;
    private double peso;
    private String raça;
    ;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setIdade(double idade) {
        this.idade = idade;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setRaça(String raça) {
        this.raça = raça;
    }

    public Animal(){
    }

    public Animal(String fName,String lName,TIPO tipo,SEXO sexo,Endereco endereco,double idade,double peso,String raça){
        this.firstName = fName;
        this.lastName = lName;
        this.tipo = tipo;
        this.sexo = sexo;
        this.endereco = endereco;
        this.idade = idade;
        this.peso = peso;
        this.raça =raça;
    }

    public String getFirstName() {
        if(this.firstName.equalsIgnoreCase("nulo")){
            return NaoInformadoSingleton.NÃO_INFORMADO;
        } else{
            return this.firstName;
        }
    }

    public TIPO getTipo() {
        return tipo;
    }

    public String getLastName() {
        if(this.lastName.equalsIgnoreCase("nulo")){
            return NaoInformadoSingleton.NÃO_INFORMADO;
        } else{
            return this.lastName;
        }
    }

    public SEXO getSexo() {
        return sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public <T> T getIdade() {
        if(this.idade ==0.0 ){
            return (T) NaoInformadoSingleton.NÃO_INFORMADO;
        } else{
            return (T) Double.valueOf(this.idade);
        }
    }

    public <T> T getPeso() {
        if(this.peso ==0.0 ){
            return (T) NaoInformadoSingleton.NÃO_INFORMADO;
        } else{
            return (T) Double.valueOf(this.peso);
        }
    }

    public String getRaça() {
        if(this.raça.equalsIgnoreCase("nulo")){
            return NaoInformadoSingleton.NÃO_INFORMADO;
        } else{
            return this.raça;
        }
    }

    @Override
    public String toString() {
        return "Animal{"+
                "firstName='" + firstName + '\'' +
                ",lastName='" + lastName + '\'' +
                ",tipo=" + tipo +
                ",sexo=" + sexo +
                ",endereco=" + endereco +
                ",idade=" + idade +
                ",peso=" + peso +
                ",raça='" + raça + '\'' +
                '}';
    }

    public final static class AnimalBuilder{
        private String fName;
        private String lName;
        private TIPO tipo;
        private SEXO sexo;
        private Endereco endereco;
        private double idade;
        private double peso;
        private String raça;


        public AnimalBuilder firstName(String firstName){
            this.fName = firstName;
            return this;
        }
         public AnimalBuilder lastName(String lastName){
            this.lName = lastName;
            return this;
         }
        public AnimalBuilder Tipo(TIPO tipo){
            this.tipo = tipo;
            return this;
        }
        public AnimalBuilder SEXO(SEXO sexo){
            this.sexo = sexo;
            return this;
        }
        public AnimalBuilder endereco(Endereco endereco){
            this.endereco = endereco;
            return this;
        }
        public AnimalBuilder idade(double idade){
            this.idade = idade;
            return this;
        }
        public AnimalBuilder peso(double peso){
            this.peso =peso; ;
            return this;
        }
        public AnimalBuilder raça(String raça){
            this.raça = raça;
            return this;
        }
        public Animal build(){
            return new Animal(this.fName,this.lName,this.tipo,this.sexo,this.endereco,this.idade,this.peso,this.raça);

        }







    }
}
