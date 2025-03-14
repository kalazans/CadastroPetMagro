package SERVICES;

import CadastroPetExceptions.*;
import Model.Animal;
import Model.NaoInformadoSingleton;
import Model.SEXO;
import Model.TIPO;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorService {
    private ValidadorService(){};
    private static Scanner leitura = new Scanner(System.in);
    private static FormularioTxT formularioTxT = new FormularioTxT();
    private static List<String> perguntaFormulario = formularioTxT.lerFormularioAsList();

    public  static String validarFirstName(){
        System.out.println(perguntaFormulario.get(0));
        String firstName = leitura.nextLine();
        String regex = "([a-zA-Z\\s])+";
        Pattern pattern = Pattern.compile(regex);
        Matcher match =pattern.matcher(firstName);
        try{
            if(!match.matches()){
                throw new NomeInvalidoException("nome invalidoo,NAO PODE CARACTER ESPECIAIS SÓ LETRAS DE A-Z! vc digitou -> "+firstName);
            }
        }catch (NomeInvalidoException e){
            System.out.println(e.getMessage());
             return validarFirstName();
        }
        if(firstName.equalsIgnoreCase("Nulo")){
            return NaoInformadoSingleton.NÃO_INFORMADO;
        } else{
            return firstName;
        }
    }

    public static String validarLastName(){
        System.out.println(perguntaFormulario.get(1));
        String lastName = leitura.nextLine();
        String regex = "([a-zA-Z])+";
        Pattern pattern = Pattern.compile(regex);
        Matcher match =pattern.matcher(lastName);
        try{
            if(!match.matches()){
                throw new SobreNomeInvalidoException("nome invalidoo,NAO PODE CARACTER ESPECIAIS SÓ LETRAS DE A-Z! vc digitou -> "+lastName);
            }
        }catch (SobreNomeInvalidoException e){
            System.out.println(e.getMessage());
            return validarLastName();
        }
        if(lastName.equalsIgnoreCase("Nulo")){
            return NaoInformadoSingleton.NÃO_INFORMADO;
        } else{
            return lastName;
        }
    }

    public static double validarIdade(){
        System.out.println(perguntaFormulario.get(4));
        String idade = leitura.nextLine();
        String regex = "([0-9])?([.,])?([0-9])+";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(idade);
        try{
            if(!match.matches()){
                throw new IdadeInvalidaException("idade invalida!\nEXEMPLO: 1,65\nVOCE DIGITOU: "+idade);
            } else if(Double.parseDouble(idade.replace(",","."))>20){
                throw new IdadeInvalidaException("idade maior q 20 anos");
            }
        }catch (IdadeInvalidaException e){
            System.out.println(e.getMessage());
            return validarIdade();
        }
        double idadeFormatada = Double.parseDouble(idade.replace(",","."));
        return idadeFormatada;
    }
    public static double validarPeso(){
        System.out.println(perguntaFormulario.get(5));
        String peso = leitura.nextLine();
        String regex = "([0-9])+([.,])?([0-9])+";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(peso);
        // CRIA EXCEPTIONS PARA CADA ERROR AKI
        try{
            if(Double.parseDouble(peso.replace(",","."))>60||Double.parseDouble(peso.replace(",","."))<0.5){
                throw new PesoInvalidoException("peso tem q ser menor q 60kg e maior que 0,5kg!\nVOCE DIGITOU -> "+Double.parseDouble(peso.replace(",",".")));
            }
            else if(!match.matches()){
                throw new PesoInvalidoException("peso invalido!\nEXEMPLO: 350g ou 5,200kg\nVOCE DIGITOU: "+peso);
            }
        }catch (PesoInvalidoException e){
            System.out.println(e.getMessage());
            return validarPeso();
        }
            return Double.parseDouble(peso.replace(",", "."));
    }

    public static String  validarRaça(){
        System.out.println(perguntaFormulario.get(6));
        String raça = leitura.nextLine();
        String regex = "([a-zA-Z\\s])+";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(raça);
        try{
            if(match.matches()==false){
                throw new RaçaInvalidaException("NAO pode usar caracteres especiais!!\nVOCE DIGITOU -> "+raça);
            }
        } catch (RaçaInvalidaException e){
            System.out.println(e.getMessage());
            return validarRaça();
        }
        return raça;
    }
    public static TIPO validarTipo(){
        System.out.println(perguntaFormulario.get(2));
        String genero = leitura.nextLine();
        try{
            switch (genero){
                case "cachorro": return  TIPO.CACHORRO;
                case "gato": return TIPO.GATO;
                default:
                    throw  new TIPOInvalidoException("animal ano existe");
            }
        } catch (TIPOInvalidoException e) {
            System.out.println(e.getMessage());
            return validarTipo();
        }
    }

    public static SEXO validarSexo(){
        System.out.println(perguntaFormulario.get(3));
        String sexo = leitura.nextLine().toUpperCase();
        String regex = "([mfMF])?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(sexo);
           try{
               if(matcher.matches()==false){
                   throw new SEXOInvalidoException("dado invalido");
               }
               else{
                   return (sexo.equals("M"))? SEXO.M:SEXO.F;
               }
           }catch (SEXOInvalidoException e){
               System.out.println(e.getMessage());
               return validarSexo();
           }
    }

    public static String validarRua(){
        System.out.println("digite a rua");
        String rua = leitura.nextLine();
        String regex = "([a-zA-z\\s])+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rua);
        try{
            if(matcher.matches()==false) throw new RuaInvalidaException("somente letras, voce digitou: "+rua);

        }catch (RuaInvalidaException e){
            System.out.println(e.getMessage());
            return  validarRua();
        }
        return rua;

    }
    public static String validarCidade(){
        System.out.println("digite a cidade");
        String cidade = leitura.nextLine();
        String regex = "([a-zA-z\\s])+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cidade);
        try{
            if(matcher.matches()==false) throw new CidadeInvalidaException("somente letras, voce digitou: "+cidade);

        }catch (CidadeInvalidaException e){
            System.out.println(e.getMessage());
            return validarCidade();
        }
        return cidade;

    }
    public static int validaNumeroCasa(){
        System.out.println("digite o numero da casa OU 0 caso nao saiba");
        String numero = leitura.nextLine();
        String regex = "([0-9])+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numero);
        try{
            if(matcher.matches()==false) throw new NumeroCasaInvalidoException("somente numeros, voce digitou: "+numero);

        }catch (NumeroCasaInvalidoException e){
            System.out.println(e.getMessage());
            return validaNumeroCasa();
        }

        return Integer.parseInt(numero);

    }

    public static boolean confimarDeletar(Optional<Animal> animal,int escolha){
        System.out.println("deseja deletar -> "+escolha+"-"+animal.get()+"\nS/N");
        String opcao = leitura.next().toUpperCase();
        String regex ="([SN])?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(opcao);
        try{
            if(matcher.matches()==false){
                throw new ConfirmarSNExeception("comando invalido! digite S OU N! voce digitou -> "+opcao);
            }
        } catch (ConfirmarSNExeception e){
            System.out.println(e.getMessage());
            confimarDeletar(animal,escolha);

        }
        if(matcher.matches()&&opcao.contains("N")){
            return false;
        } else{
            return true;
        }
    }

}
