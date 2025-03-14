package AnimalRepositoryTXT;

import CadastroPetExceptions.EscolhaMenuInvalida;
import CadastroPetExceptions.NomeInvalidoException;
import CadastroPetExceptions.TIPOInvalidoException;
import Model.Animal;
import Model.BancoTxtSingleton;
import Model.Endereco;
import SERVICES.SalvarEmTXT;
import SERVICES.ValidadorService;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnimalRepositoryTXT {

    public static List<Optional<Animal>> buscarPorNomeOuSobrenome() {
        System.out.println("digite o primeiro nome OU o sobrenome ou  nome completo");
        Scanner leitura = new Scanner(System.in);
        String nome = leitura.nextLine();
        String regex = "([a-zA-Z\\s])+";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(nome);
        Gson gson = new Gson();
        List<Optional<Animal>> animal = new ArrayList<>();

        try {
            if (match.matches() == false) {
                throw new NomeInvalidoException("nome invalido -> " + nome);
            } else {
                BancoTxtSingleton.atualizarSingleton();
                for (Path path : BancoTxtSingleton.getBancotxt()) {
                    if (path.getFileName().toString().contains(nome.trim().toUpperCase())) {
                        List<String> dados = Files.readAllLines(path);
                        String json = "";
                        for (int i = 0; i < dados.size(); i++) {
                            json += dados.get(i);
                        }
                        animal.add(Optional.of(gson.fromJson(json, Animal.class)));
                    }
                }
                return animal;
            }
        } catch (NomeInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return animal;
    }

    public static List<Optional<Animal>> buscarPorSexo() {
        Scanner leitura = new Scanner(System.in);
        System.out.println("digite o sexo M OU F");
        String sexo = leitura.next().toUpperCase();
        String regex = "([mfMF])?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sexo);
        Gson gson = new Gson();
        List<Optional<Animal>> animal = new ArrayList<>();
        try {
            if (matcher.matches() == false) {
                throw new TIPOInvalidoException("digite somente a letra do genero! voce digitou -> " + sexo);
            } else {
                BancoTxtSingleton.atualizarSingleton();
                for (Path path : BancoTxtSingleton.getBancotxt()) {
                    List<String> dados = Files.readAllLines(path);
                    if (dados.get(4).contains("\"" + sexo + "\"")) {
                        System.out.println(dados.get(4));
                        String json = "";
                        for (int i = 0; i < dados.size(); i++) {
                            json += dados.get(i);
                        }
                        //System.out.println(json);
                        animal.add(Optional.of(gson.fromJson(json, Animal.class)));
                    }
                }
            }
            return animal;
        } catch (TIPOInvalidoException | IOException e) {
            System.out.println(e.getMessage());
        }
        return animal;
    }

    public static List<Optional<Animal>> buscarPorIdade() {
        double idade = ValidadorService.validarIdade();
        List<Optional<Animal>> animal = new ArrayList<>();
        Gson gson = new Gson();
        String json = "";
        try {
            BancoTxtSingleton.atualizarSingleton();
            for (Path path : BancoTxtSingleton.getBancotxt()) {
                List<String> dados = Files.readAllLines(path);
                if ((dados.get(10).contains(Double.toString(idade)))) {
                    for (int i = 0; i < dados.size(); i++) {
                        json += dados.get(i);
                    }
                    animal.add(Optional.of(gson.fromJson(json, Animal.class)));
                    json = "";
                }
            }
            return animal;
        } catch (IOException e) {
            System.out.println((e.getMessage()));
        }
        return animal;
    }

    public static List<Optional<Animal>> buscarPorPeso() {
        double peso = ValidadorService.validarPeso();
        List<Optional<Animal>> animal = new ArrayList<>();
        Gson gson = new Gson();
        String json = "";
        try {
            BancoTxtSingleton.atualizarSingleton();
            for (Path path : BancoTxtSingleton.getBancotxt()) {
                List<String> dados = Files.readAllLines(path);
                if ((dados.get(11).contains(Double.toString(peso)))) {
                    for (int i = 0; i < dados.size(); i++) {
                        json += dados.get(i);
                    }
                    animal.add(Optional.of(gson.fromJson(json, Animal.class)));
                    json = "";
                }
            }
            return animal;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return animal;
    }

    public static List<Optional<Animal>> buscarPorRaça() {
        String raça = ValidadorService.validarRaça();
        List<Optional<Animal>> animal = new ArrayList<>();
        Gson gson = new Gson();
        String json = "";
        try {
            BancoTxtSingleton.atualizarSingleton();
            for (Path path : BancoTxtSingleton.getBancotxt()) {
                List<String> dados = Files.readAllLines(path);
                if (dados.get(12).contains(raça)) {
                    for (int i = 0; i < dados.size(); i++) {
                        json += dados.get(i);
                    }
                    animal.add(Optional.of(gson.fromJson(json, Animal.class)));
                    json = "";
                }
            }
            return animal;
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
        return animal;
    }

    public static void atualizarInfoPet(List<Optional<Animal>> lista) {
        System.out.println("escolhe o numero do pet que deseja atualizar a info");
        for (int i = 0; i < lista.size(); i++) {
            System.out.print((i + 1) + " - " + lista.get(i));
        }
        Optional<Animal> animal;
        Scanner leitura = new Scanner(System.in);
        String escolha = leitura.next();
        String regex = "([0-9])+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(escolha);
        String atualizarInfo = """
                DIGITE O NUMERO CAMPO QUE DESEJA ATUALIZAR;
                1 - NOME;
                2 - SOBRENOME;
                3 - IDADE;
                4 - PESO;
                5 - ENDERECO;
                6 - RAÇA""";

        try {
            if (matcher.matches() == false) {
                throw new EscolhaMenuInvalida("só pode numeroes! voce digitou -> " + escolha);
            } else if (Integer.parseInt(escolha) == 0 || Integer.parseInt(escolha) > lista.size()) {
                throw new EscolhaMenuInvalida("escolha nao poder igual a 0 ou maior " + lista.size() + "\nVoce digitou -> " + escolha);
            } else {
                System.out.println(atualizarInfo);
                int opcao = leitura.nextInt();
                switch (opcao) {
                    case 1:
                        animal = lista.get(Integer.parseInt(escolha) - 1);
                        SalvarEmTXT.updateFirstNametxt(animal, ValidadorService.validarFirstName());
                        break;
                    case 2:
                        animal = lista.get(Integer.parseInt(escolha) - 1);
                        SalvarEmTXT.updateLastName(animal, ValidadorService.validarLastName());
                        break;
                    case 3:
                        animal = lista.get(Integer.parseInt(escolha) - 1);
                        SalvarEmTXT.updateIdade(animal, ValidadorService.validarIdade());
                        break;
                    case 4:
                        animal = lista.get(Integer.parseInt(escolha) - 1);
                        SalvarEmTXT.updatePeso(animal, ValidadorService.validarPeso());
                        break;
                    case 5:
                        animal = lista.get(Integer.parseInt(escolha) - 1);
                        SalvarEmTXT.updateEndereço(animal, new Endereco(ValidadorService.validarRua(), ValidadorService.validarCidade(), ValidadorService.validaNumeroCasa()));
                        break;
                    case 6:
                        animal = lista.get(Integer.parseInt(escolha) - 1);
                        SalvarEmTXT.updateRaça(animal, ValidadorService.validarRaça());
                        break;
                }
            }

        } catch (EscolhaMenuInvalida e) {
            System.out.print(e.getMessage());

        }
    }

    public static void deletarAnimal(List<Optional<Animal>> lista) {
        System.out.println("escolhe o numero do animal que quer apagar");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i));
        }
        Scanner leitura = new Scanner(System.in);
        String escolha = leitura.next();
        String regex = "([0-9])+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(escolha);
        if (matcher.matches() == false) throw new EscolhaMenuInvalida("somente numero!voce digitou -> " + escolha);
        if (Integer.parseInt(escolha) > lista.size() || Integer.parseInt(escolha) == 0)
            throw new EscolhaMenuInvalida("nao pode 0 ou numero maior q a lista! voce digitou ->" + escolha);
        if (matcher.matches()) {
            int escolhaInt = Integer.parseInt(escolha);
            Optional<Animal> animal = lista.get(escolhaInt - 1);
            if (ValidadorService.confimarDeletar(animal, escolhaInt) == false) {
                deletarAnimal(lista);
            } else {
                BancoTxtSingleton.atualizarSingleton();
                for (Path path : BancoTxtSingleton.getBancotxt()) {
                    if (path.getFileName().toString().contains(animal.get().getFirstName().toUpperCase() + animal.get().getLastName().toUpperCase()) == false) {
                    } else {
                        File file = new File(String.valueOf(path.toFile()));
                        file.delete();
                        System.out.println("animal deletado do bancotxt");
                    }
                }
                BancoTxtSingleton.getBancotxt().remove(escolhaInt - 1);

            }
        }

    }

    public static List<Optional<Animal>> buscaCriterio(){
        String criterio= """
                1 - Nome
                2 - sobrenome
                3 - Sexo
                4 - Idade
                5 - Peso
                6 - Raça
                7 - sair
                """;
        List<Optional<Animal>> listaAnimal =  new ArrayList<>();
        System.out.println(criterio);
        try{
            Scanner leitura = new Scanner(System.in);
            int escolha = leitura.nextInt();
            if(escolha<1||escolha>7){
                throw  new EscolhaMenuInvalida("nao pode 0, nem numero maior que 7");
            } else{
                switch (escolha){
                    case 1: listaAnimal = buscarPorNomeOuSobrenome();
                            break;
                    case 2: listaAnimal = buscarPorNomeOuSobrenome();
                            break;
                    case 3: listaAnimal = buscarPorSexo();
                            break;
                    case 4: listaAnimal =  buscarPorIdade();
                            break;
                    case 5: listaAnimal = buscarPorPeso();
                            break;
                    case 6 : listaAnimal = buscarPorRaça();
                    case 7: break;
                }
            }
        } catch (InputMismatchException e){
            System.out.println(e.getMessage()+"só pode numeros");
        } catch (EscolhaMenuInvalida e){
            System.out.println(e.getMessage());
        }
        return listaAnimal;
    }

    public static void todosCadastrosNoBD(){
        try{
            BancoTxtSingleton.atualizarSingleton();
            for(Path path: BancoTxtSingleton.getBancotxt()){
                String json="";
                List<String> petInfo = Files.readAllLines(path);
                 Iterator iterator = petInfo.iterator();
                 while(iterator.hasNext()){
                     json+=iterator.next()+"\n";
                 }
                 System.out.println(json);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

