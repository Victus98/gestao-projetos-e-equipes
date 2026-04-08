# Sistema de Gestão de Projetos e Equipes

Um sistema de gerenciamento desenvolvido em **Java (Console)** com foco no paradigma de Orientação a Objetos. O projeto permite a administração completa de usuários, equipes, tarefas e projetos, incluindo a persistência automática dos dados em arquivo.

## 🚀 Funcionalidades

- **Gestão de Usuários:** Cadastro de usuários com controle de níveis de acesso (Administrador, Gerente, Colaborador).
- **Gestão de Projetos:** Criação de projetos definindo datas de início/término, status e a obrigatoriedade de um gerente responsável.
- **Gestão de Equipes:** Criação de equipes e vinculação de usuários (membros).
- **Gestão de Tarefas:** Criação de tarefas atreladas a um projeto específico e a um usuário responsável.
- **Alocação:** Capacidade de alocar múltiplas equipes em diferentes projetos.
- **Relatórios:** Geração de relatórios consolidados exibindo os projetos, equipes alocadas, membros e tarefas.
- **Persistência de Dados:** Salvamento automático de todas as informações utilizando Serialização de Objetos em um arquivo `.txt` (`dados_projeto.txt`).

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java
- **Paradigma:** Programação Orientada a Objetos (POO)
- **Estruturas de Dados:** `List`, `ArrayList`, `Enum`
- **Manipulação de Datas:** `java.time.LocalDate`
- **Persistência:** `java.io.Serializable`, `ObjectOutputStream`, `ObjectInputStream`

## 📂 Estrutura do Projeto

O projeto está estruturado com as seguintes classes e enums principais:

- `Main.java`: Interface de console e menu interativo.
- `Usuario.java`: Modelo de usuário e credenciais.
- `Projeto.java`: Entidade central que agrupa equipes e tarefas.
- `Equipe.java`: Agrupamento de usuários.
- `Tarefa.java`: Atividades a serem realizadas dentro de um projeto.
- `GerenciadorArquivo.java`: Classe utilitária responsável pela leitura e gravação do arquivo `.txt`.
- `PerfilUsuario.java` (Enum): Define as permissões (ADMINISTRADOR, GERENTE, COLABORADOR).
- `StatusProjeto.java` (Enum): Define o andamento (PLANEJADO, EM_ANDAMENTO, CONCLUIDO, CANCELADO).

## 💻 Como Executar

### Pré-requisitos
- **Java Development Kit (JDK)** versão 17 ou superior instalada.
- Uma IDE (como IntelliJ IDEA, Eclipse) ou acesso ao terminal.

### Passos para execução via Terminal:
1. Clone este repositório:
   ```bash
   git clone [https://github.com/Victus98/gestao-projetos-e-equipes.git](https://github.com/Victus98/gestao-projetos-e-equipes.git)
