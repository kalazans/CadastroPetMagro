package SERVICES;

import Model.Animal;
import Model.BancoTxtSingleton;
import Model.Endereco;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class SalvarEmTXT {
    public  static void addBancotxt(Animal animal){
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("ddMMyyyy hhmm");
        String data = LocalDateTime.now().format(dt).replace("\s","T");
        String nomeArquivo = data+"-"+animal.getFirstName().toUpperCase()+animal.getLastName().toUpperCase()+".TXT";
        String bancotxt = "C:\\Users\\wende\\OneDrive\\Documentos\\aluraspringsemweb\\ProjetoJava2025\\br.com.PetDevMagro\\CadastroPet\\src\\BancoTxT\\"+nomeArquivo;
        File file = new File(bancotxt);
        try{
            if(file.createNewFile()){
                Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
                BufferedWriter writer = Files.newBufferedWriter(file.toPath());
                writer.write(gson.toJson(animal));
                writer.flush();
                writer.close();
            } else{
                throw new RuntimeException("ja existe este animal do banco de dados");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /*public static void addBancotxtCustom(Animal animal){
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("ddMMyyyy hhmm");
        String data = LocalDateTime.now().format(dt).replace("\s","T");
        String nomeArquivo = data+"-"+animal.getFirstName().toUpperCase()+animal.getLastName().toUpperCase()+".TXT";
        String bancotxt = "C:\\Users\\wende\\OneDrive\\Documentos\\aluraspringsemweb\\ProjetoJava2025\\br.com.PetDevMagro\\CadastroPet\\src\\TXT\\BancoTxT\\"+nomeArquivo;
        File file = new File(bancotxt);
        FormularioTxT formulario = new FormularioTxT();
        List<String> perguntas = formulario.lerFormularioAsList();
        try{
            if(file.createNewFile()){
                Scanner leitura = new Scanner(System.in);
                BufferedWriter writer = Files.newBufferedWriter(file.toPath());
                String infoCustom = """
                        {
                          "firstName": "%s",
                           "lastName": "%s",
                           "tipo": "%s",
                           "sexo": "%s",
                           "endereco": {
                           "rua": "%s",
                           "cidade": "%s",
                           "numeroCasa": %d
                           },
                           "idade": %s,
                           "peso": %s,
                           "raça": "%s",
                        """.formatted(animal.getFirstName(),animal.getLastName(),
                        animal.getTipo(),animal.getSexo(),animal.getEndereco().getRua(),
                        animal.getEndereco().getCidade(),
                        animal.getEndereco().getNumeroCasa(),
                        Double.toString(animal.getIdade()).replace(",","."),Double.toString(animal.getPeso()).replace(",","."),animal.getRaça());
                String infoNova="";
                for(int i=7;i<perguntas.size();i++){
                    System.out.println(perguntas.get(i));
                    infoNova += "\""+"extra"+"\":"+"\""+perguntas.get(i)+ "Resposta" +leitura.next()+"\""+","+"\n";
                }
                writer.write(infoCustom.concat(infoNova+"}"));
                writer.flush();
                writer.close();
            } else{
                throw new RuntimeException("ja existe este animal do banco de dados");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
    public  static void updateFirstNametxt(Optional<Animal> animal,String firstNameUpdate){
        String bancotxt = "C:\\Users\\wende\\OneDrive\\Documentos\\aluraspringsemweb\\ProjetoJava2025\\br.com.PetDevMagro\\CadastroPet\\src\\BancoTxT\\";
        if(animal.isPresent()){
            for(Path path :BancoTxtSingleton.bancotxt){
                if(path.getFileName().toString().contains(animal.get().getFirstName().toUpperCase()));
               String fileNameUpdate = path.getFileName().toString().replace(animal.get().getFirstName().toUpperCase(),firstNameUpdate.toUpperCase());
               //System.out.println(fileNameUpdate);
               try{
                   animal.get().setFirstName(firstNameUpdate);
                   Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
                   Files.delete(path);
                   File file = new File(bancotxt+fileNameUpdate);
                   BufferedWriter writer = Files.newBufferedWriter(file.toPath());
                   writer.write(gson.toJson(animal.get()));
                   writer.flush();
                   writer.close();
                   System.out.println("Nome alterado para -> "+fileNameUpdate);
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }

            }
        }
    }

    public static void updateLastName(Optional<Animal> animal,String lastNameUpdate){
        String bancotxt = "C:\\Users\\wende\\OneDrive\\Documentos\\aluraspringsemweb\\ProjetoJava2025\\br.com.PetDevMagro\\CadastroPet\\src\\BancoTxT\\";
        try{
            if(animal.isEmpty()) throw new RuntimeException("optional veio vazio");
            if (animal.isPresent()) {
                for(Path path:BancoTxtSingleton.bancotxt){
                    if(path.getFileName().toString().contains(animal.get().getLastName().toUpperCase())){
                        String fileNameUpdate = path.getFileName().toString().replace(animal.get().getLastName().toUpperCase(),lastNameUpdate.toUpperCase());
                        animal.get().setLastName(lastNameUpdate);
                        Gson gson =  new Gson().newBuilder().setPrettyPrinting().create();
                        Files.delete(path);
                        Files.createFile(Path.of(bancotxt+fileNameUpdate));
                        BufferedWriter writer =  Files.newBufferedWriter(Path.of(bancotxt+fileNameUpdate));
                        writer.write(gson.toJson(animal.get()));
                        writer.flush();
                        writer.close();
                        System.out.println("Sobrenome alterado para -> "+lastNameUpdate);
                    }
                }
            }
        } catch (RuntimeException e){
            System.out.print(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void updateIdade(Optional<Animal>animal,double idade){
        try{
            if(animal.isEmpty()) throw new RuntimeException("optinal vazio");
            if(animal.isPresent()) {
                for (Path path : BancoTxtSingleton.bancotxt) {
                    if(path.getFileName().toString().contains(animal.get().getFirstName().toUpperCase()+animal.get().getLastName().toUpperCase())) {
                        animal.get().setIdade(idade);
                        Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
                        BufferedWriter writer = Files.newBufferedWriter(path);
                        writer.write(gson.toJson(animal.get()));
                        writer.flush();
                        writer.close();
                        System.out.println("idade alterada para -> "+idade);
                    }
                }
            }

        }catch (RuntimeException e){} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static  void updatePeso(Optional<Animal>animal ,double peso){
        try{
            if(animal.isEmpty()) throw new RuntimeException("optinal vazio");
            if (animal.isPresent()) {
                for(Path path:BancoTxtSingleton.bancotxt){
                    if(path.getFileName().toString().contains(animal.get().getFirstName().toUpperCase()+animal.get().getLastName().toUpperCase()));
                    animal.get().setPeso(peso);
                    Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
                    BufferedWriter writer = Files.newBufferedWriter(path);
                    writer.write(gson.toJson(animal.get()));
                    writer.flush();
                    writer.close();
                    System.out.println("Peso alteerado para ->"+peso);
                }
            }
        }catch (RuntimeException e){} catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void updateRaça(Optional<Animal>animal ,String raça){
        try{
            if(animal.isEmpty()) throw new RuntimeException("optinal vazio");
            if (animal.isPresent()) {
                for(Path path:BancoTxtSingleton.bancotxt){
                    if(path.getFileName().toString().contains(animal.get().getFirstName().toUpperCase()+animal.get().getLastName().toUpperCase()));
                    animal.get().setRaça(raça);
                    Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
                    BufferedWriter writer = Files.newBufferedWriter(path);
                    writer.write(gson.toJson(animal.get()));
                    writer.flush();
                    writer.close();
                    System.out.println("Raça alterada apra -> "+raça);
                }
            }
        }catch (RuntimeException e){} catch (IOException e) {
        throw new RuntimeException(e);
        }
    }

    public static void updateEndereço(Optional<Animal> animal, Endereco endereço){
        try{
            if(animal.isEmpty()) throw new RuntimeException("optinal vazio");
            if (animal.isPresent()) {
                for(Path path:BancoTxtSingleton.bancotxt){
                    if(path.getFileName().toString().contains(animal.get().getFirstName().toUpperCase()+animal.get().getLastName().toUpperCase()));
                    animal.get().getEndereco().setCidade(endereço.getCidade());
                    animal.get().getEndereco().setRua(endereço.getRua());
                    animal.get().getEndereco().setNumeroCasa(endereço.getNumeroCasa());
                    Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
                    BufferedWriter writer = Files.newBufferedWriter(path);
                    writer.write(gson.toJson(animal.get()));
                    writer.flush();
                    writer.close();
                    System.out.println("Endereco alterado para ->"+ endereço);
                }
            }
        }catch (RuntimeException e){} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
