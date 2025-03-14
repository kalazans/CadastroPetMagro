package SERVICES;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormularioTxT {
    private final String formularioPadrao = """
            Qual o nome? ou Nulo caso nao saiba!
            Qual o sobrenome? Nulo caso nao saiba!
            Qual o tipo do pet (Cachorro/Gato)?
            Qual o sexo do animal? M/F
            Qual a idade aproximada do pet? 0, caso nao saiba!
            Qual o peso aproximado do pet? 0, caso nao saiba!
            Qual a raça do pet? ou Nulo caso nao saiba!
            """;

    public void criarFormulatioTxT(){
        File file = new File("C:\\Users\\wende\\OneDrive\\Documentos\\aluraspringsemweb\\ProjetoJava2025\\br.com.PetDevMagro\\CadastroPet\\src\\MenuTXT\\formulario.txt");
        try{
            if(file.createNewFile()){
                BufferedWriter writer = Files.newBufferedWriter(file.toPath());
                writer.write(formularioPadrao);
                writer.flush();
                writer.close();
                System.out.println("arquivo criado");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void criarMenuAppTXT(){
        String menuAPP = """
                1 - Cadastrar um novo pet
                2 - Alterar os dados do pet cadastrado
                3 - Deletar um pet cadastrado
                4 - Listar todos os pets cadastrados
                5 - Listar pets por algum critério (idade, nome, raça)
                6 - Sair
                """;
        File file = new File("C:\\Users\\wende\\OneDrive\\Documentos\\aluraspringsemweb\\ProjetoJava2025\\br.com.PetDevMagro\\CadastroPet\\src\\MenuTXT\\menuapp.txt");
       try {
           if (file.createNewFile()) {
               BufferedWriter writer = Files.newBufferedWriter(file.toPath());
               writer.write(menuAPP);
               writer.flush();
               writer.close();

           }
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }

    public void criarMenuAlterarFormulario(){
        String alterarFormulario = """
                1 - Criar nova pergunta
                2 - Alterar pergunta existente
                3 - Excluir pergunta existente
                4 - Voltar para o menu inicial
                5 - Sair
                """;
        File file = new File("C:\\Users\\wende\\OneDrive\\Documentos\\aluraspringsemweb\\ProjetoJava2025\\br.com.PetDevMagro\\CadastroPet\\src\\MenuTXT\\menuAlterarFormulario.txt");
        try {
            if (file.createNewFile()) {
                BufferedWriter writer = Files.newBufferedWriter(file.toPath());
                writer.write(alterarFormulario);
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> lerFormularioAsList(){
        Path path = Path.of("C:\\Users\\wende\\OneDrive\\Documentos\\aluraspringsemweb\\ProjetoJava2025\\br.com.PetDevMagro\\CadastroPet\\src\\MenuTXT\\formulario.txt");
        try {
            List<String> formulario = Files.readAllLines(path);
            return formulario;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void lerFormulario(){
        Path path = Path.of("C:\\Users\\wende\\OneDrive\\Documentos\\aluraspringsemweb\\ProjetoJava2025\\br.com.PetDevMagro\\CadastroPet\\src\\MenuTXT\\formulario.txt");
        try {
            List<String> formulario = Files.readAllLines(path);
            for(int i=0;i<formulario.size();i++){
                System.out.println((i+1)+" - "+formulario.get(i));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void lerMenuAPP(){
        Path path = Path.of("C:\\Users\\wende\\OneDrive\\Documentos\\aluraspringsemweb\\ProjetoJava2025\\br.com.PetDevMagro\\CadastroPet\\src\\MenuTXT\\menuapp.txt");
        try{
            List<String> menu = Files.readAllLines(path);
            menu.forEach(p->System.out.println(p));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void lerMenuAlterarFormulario(){
        Path path = Path.of("C:\\Users\\wende\\OneDrive\\Documentos\\aluraspringsemweb\\ProjetoJava2025\\br.com.PetDevMagro\\CadastroPet\\src\\MenuTXT\\menuAlterarFormulario.txt");
        try{
            List<String> formulario= Files.readAllLines(path);
            formulario.forEach(s->System.out.println(s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void novaPerguntaFormulario(){
        try{
            Scanner leitura = new Scanner(System.in);
            List<String> perguntas = this.lerFormularioAsList();
            System.out.println("digite a nova pergunta");
            String perguntaNova = leitura.nextLine();
            perguntas.add(perguntaNova);
            BufferedWriter writer = Files.newBufferedWriter(Path.of("C:\\Users\\wende\\OneDrive\\Documentos\\aluraspringsemweb\\ProjetoJava2025\\br.com.PetDevMagro\\CadastroPet\\src\\MenuTXT\\formulario.txt"));
            for(int i=0;i<perguntas.size();i++){
                writer.write(perguntas.get(i)+"\n");
                writer.flush();
            }
            writer.close();
            System.out.println("pergunta adicionada");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarPerguntaFormulario(){
        try{
            List<String> perguntas =this.lerFormularioAsList();
            Scanner leitura = new Scanner(System.in);
            this.lerFormulario();
            System.out.println("digite o numero da pergunta que quer apagar");
            String escolha = leitura.next();
            String regex = "([1-9])+";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(escolha);
            if(matcher.matches()==false||
                    (Integer.parseInt(escolha)-1)>perguntas.size()||
                    (Integer.parseInt(escolha))-1<6) throw new RuntimeException("SOMENTE NUMEROS, NUMERO NAO PODE SER MAIOR QUE O INDICE DA LISTA, NEM OS 6 PRIMEIROSS");

            if(matcher.matches() && this.validarSimouNao(leitura)){
                perguntas.remove(Integer.parseInt(escolha)-1);
                BufferedWriter writer = Files.newBufferedWriter(Path.of("C:\\Users\\wende\\OneDrive\\Documentos\\aluraspringsemweb\\ProjetoJava2025\\br.com.PetDevMagro\\CadastroPet\\src\\MenuTXT\\formulario.txt"));
                for(int i=0;i<perguntas.size();i++){
                    writer.write(perguntas.get(i)+"\n");
                    writer.flush();
                }
                writer.close();
            } else{
                System.out.println("pergunta nao apagada");
            }


        }catch (RuntimeException e){
            System.out.println(e.getMessage());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

   private boolean validarSimouNao(Scanner leitura){

        System.out.println("QUER APAGAR A PERGUNTA? S/N");
        String escolha = leitura.next().toUpperCase();
        String regex = "([SN])?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(escolha);
        if(matcher.matches()==false) {
            throw  new RuntimeException("digite somente S->(SIM) OU N->(NAO)! voce digitou -> "+leitura);
        }
        if(matcher.matches()&&matcher.equals("S")){
            return true;
        } else{
            return false;
        }

    }


}
