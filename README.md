# CadastroPetMagro
<p>Explicao por pastas e obejtos</p>
<ul><li>
  AnimalRepositoryTXT: responsavel por fazer a busca na pasta bancoTXT e trazer a lista completa ou pelo criterios dos animais;
</li>
<li>BancoTXT: ficam os animais cadastrados em txt</li>
<li>CadastroPetException: ficam todas as exceptions do projeto</li>
<li>MenuTXT: ficam todos os menus do projeto</li></li>
<li>Model: 1 - Animal/Endereco: objetos instaciados para informaçoes do pet<br>
2 - SEXO/TIPO: enums do pet<br>
3 - BancoTxtSingleton: faz a leitura da pasta do BancoTXT e retorna um arraylist de Path dos arquivos
4 - NaoInformadoSingleton: guarda um valor static final para quando as informaçoes forem nulas e no lugar por: NAOINFORMADO</li>
<li>Services: 1 - FormularioTXT:  cria e modifica os menusTXT do projeto, faz toda a operaçoes de ler e escrever e modificar<br>
2 - SalverEmTXT: faz as operaçoes de salvar no bancoTXT e atualzar as informaçoes do pet<nr></br>
3 - ValidarService: faz as operaços de validar inputs do usuario durante o cadastro com regex para cada uma das informaçoes</li>
<li>Principal.java: faz as operaçoes verificar para qual sistema ira o usuario e validar os input dessa escolha e terminar o programa</li></ul>
  <br>
<p>OBS: sei que nao ta bomzao kkkk ta um frankestein remendado, nem todas as convençoes foram seguidas pq esqueci de mudar cedo, só tem isso nos nomes das classes<br>
talvez de alguns erros que pare o programa, obrigado quem for conferir! dito isto: teria menos dor de cabeça com o bancoSql kkkk txt é coisa de louco</p>
