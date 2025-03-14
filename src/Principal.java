import AnimalRepositoryTXT.AnimalRepositoryTXT;
import CadastroPetExceptions.EscolhaMenuInvalida;
import Model.Animal;
import Model.Endereco;
import SERVICES.SalvarEmTXT;
import SERVICES.ValidadorService;
import SERVICES.FormularioTxT;


import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Principal {
    public void start(){
        this.verificarFileFormularioEMenusExiste();
        boolean on =true;
        while(on ==true) {
            switch (this.validarEscolhaSistemas()) {
                case 1:
                    this.startSistemaCadastroPet();
                    break;
                case 2:
                    this.startSistemaAlterarFormulario();

                    break;
                case 3:on=false;
                break;
            }
        }

    }

    public void startSistemaCadastroPet(){
        Boolean on = true;

        while(on==true){
            switch (this.validarEscolhaMenuApp()){
                case 1: SalvarEmTXT.addBancotxt(new Animal.AnimalBuilder()
                        .firstName(ValidadorService.validarFirstName())
                        .lastName(ValidadorService.validarLastName())
                        .Tipo(ValidadorService.validarTipo())
                        .SEXO(ValidadorService.validarSexo())
                        .endereco(new Endereco(ValidadorService.validarRua(),ValidadorService.validarCidade(),ValidadorService.validaNumeroCasa()))
                        .idade(ValidadorService.validarIdade())
                        .peso(ValidadorService.validarPeso())
                        .raça(ValidadorService.validarRaça())
                        .build());
                break;
                case 2: AnimalRepositoryTXT.atualizarInfoPet(AnimalRepositoryTXT.buscaCriterio());
                break;
                case 3: AnimalRepositoryTXT.deletarAnimal(AnimalRepositoryTXT.buscaCriterio());
                break;
                case 4:AnimalRepositoryTXT.todosCadastrosNoBD();;
                break;
                case 5:AnimalRepositoryTXT.buscaCriterio().forEach(animal->System.out.println(animal.get()));
                break;
                case 6: on=false;
                        System.out.println("saindo");break;
                default:on =false;
                        System.out.println("saindo");
            }
        }
    }
    private int validarEscolhaMenuApp(){
        FormularioTxT formularioTxT = new FormularioTxT();
        formularioTxT.lerMenuAPP();
        Scanner leitura = new Scanner(System.in);
        String escolha = leitura.next();
        String regex = "([0-6])?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(escolha);
        try{
            if(matcher.matches()==false) throw new EscolhaMenuInvalida("SOMENTE NUMEROS E DE 0-6!Voce digitou -> "+escolha);

        }catch (EscolhaMenuInvalida e){
            System.out.println(e.getMessage());
        }
        return Integer.parseInt(escolha);
    }

    public void startSistemaAlterarFormulario(){
        boolean on = true;
        FormularioTxT formularioTxT = new FormularioTxT();
        while (on == true) {
            switch (this.validarEscolhaAlterarFormulario()){
                case 1: formularioTxT.novaPerguntaFormulario();
                break;
                case 2:
                break;
                case 3:formularioTxT.deletarPerguntaFormulario();
                break;
                case 4: on =false;
                break;
                case 5: on =false;
            }
        }
    }
    public int validarEscolhaAlterarFormulario(){
        FormularioTxT formularioTxT = new FormularioTxT();
        formularioTxT.lerMenuAlterarFormulario();
        Scanner leitura = new Scanner(System.in);
        String escolha = leitura.next();
        String regex = "([1-5])?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(escolha);
        try{
            if(matcher.matches()==false) throw  new EscolhaMenuInvalida("somente numeros E de 1 até 5! vode digitou -> "+escolha);

        } catch (EscolhaMenuInvalida e) {
            System.out.println(e.getMessage());
        }
        return Integer.parseInt(escolha);
    }

    public int validarEscolhaSistemas(){
        try {
            System.out.println("1 - Sistemas de cadastros Pets\n2 - Sistema alterar formulario\n3 - SAIR");
            Scanner leitura = new Scanner(System.in);
            String regex = "([1-3])?";
            String escolha = leitura.next();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(escolha);
            if(matcher.matches()==false) throw  new EscolhaMenuInvalida("ESCOLHA ENTRE 1 E 2");
            return  Integer.parseInt(escolha);
        }catch (EscolhaMenuInvalida e){
            System.out.println(e.getMessage());
            return this.validarEscolhaSistemas();
        }

    }

    private void verificarFileFormularioEMenusExiste(){
        try{
            File banco = new File("C:\\Users\\wende\\OneDrive\\Documentos\\aluraspringsemweb\\ProjetoJava2025\\br.com.PetDevMagro\\CadastroPet\\src\\MenuTXT");
            List<File> arquivos = Arrays.asList(Objects.requireNonNull(banco.listFiles()));
            if(arquivos.isEmpty()){
                System.out.println("criando os menu para aplicaçao");
                FormularioTxT formularioTxT = new FormularioTxT();
                formularioTxT.criarFormulatioTxT();
                formularioTxT.criarMenuAppTXT();
                formularioTxT.criarMenuAlterarFormulario();
            } else{
                System.out.println("menus da aplicaçao ja existem");
            }
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

}
