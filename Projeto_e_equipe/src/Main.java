private static final List<Usuario> usuarios = new ArrayList<>();
private static final List<Equipe> equipes = new ArrayList<>();
private static final List<Projeto> projetos = new ArrayList<>();
private static final Scanner scanner = new Scanner(System.in);

void main() {
    // Carrega os dados salvos no arquivo txt ao iniciar o programa
    GerenciadorArquivo.carregarDados(usuarios, equipes, projetos);

    int opcao = 0;
    do {
        IO.println("\n=== Gestão de Projetos e Equipes ===");
        IO.println("1. Cadastrar Usuário");
        IO.println("2. Cadastrar Equipe");
        IO.println("3. Cadastrar Projeto");
        IO.println("4. Alocar Membro na Equipe");
        IO.println("5. Alocar Equipe no Projeto");
        IO.println("6. Cadastrar Tarefa no Projeto");
        IO.println("7. Gerar Relatório de Projetos");
        IO.println("8. Sair");
        IO.print("Escolha: ");

        try {
            opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> cadastrarEquipe();
                case 3 -> cadastrarProjeto();
                case 4 -> alocarMembroNaEquipe();
                case 5 -> alocarEquipeNoProjeto();
                case 6 -> cadastrarTarefaNoProjeto();
                case 7 -> gerarRelatorio();
                case 8 -> {
                    // Salva os dados no arquivo txt antes de fechar
                    GerenciadorArquivo.salvarDados(usuarios, equipes, projetos);
                    IO.println("Dados salvos com sucesso. Encerrando o sistema...");
                }
                default -> IO.println("Opção inválida.");
            }
        } catch (NumberFormatException e) {
            IO.println("Por favor, digite um número válido.");
        }
    } while (opcao != 8);
}

private static void cadastrarUsuario() {
    IO.println("\n--- Novo Usuário ---");
    IO.print("Nome Completo: ");
    scanner.nextLine();
    IO.print("CPF: ");
    scanner.nextLine();
    IO.print("E-mail: ");
    scanner.nextLine();
    IO.print("Cargo: ");
    scanner.nextLine();
    IO.print("Login: ");
    String login = scanner.nextLine();
    IO.print("Senha: ");
    scanner.nextLine();

    IO.print("Perfil (ADMINISTRADOR, GERENTE, COLABORADOR): ");
    try {
        PerfilUsuario perfil = PerfilUsuario.valueOf(scanner.nextLine().toUpperCase());
        usuarios.add(new Usuario(login, perfil));
        IO.println("Usuário cadastrado com sucesso!");
    } catch (IllegalArgumentException e) {
        IO.println("Perfil inválido. Cadastro cancelado.");
    }
}

private static void cadastrarEquipe() {
    IO.println("\n--- Nova Equipe ---");
    IO.print("Nome da equipe: ");
    String nome = scanner.nextLine();
    IO.print("Descrição: ");
    scanner.nextLine();

    equipes.add(new Equipe(nome));
    IO.println("Equipe cadastrada com sucesso!");
}

private static void cadastrarProjeto() {
    IO.println("\n--- Novo Projeto ---");
    boolean temGerente = usuarios.stream().anyMatch(u ->
            u.perfil() == PerfilUsuario.GERENTE || u.perfil() == PerfilUsuario.ADMINISTRADOR);

    if (!temGerente) {
        IO.println("Erro: Cadastre um Usuário com perfil GERENTE ou ADMINISTRADOR primeiro.");
        return;
    }

    try {
        IO.print("Nome do projeto: ");
        String nome = scanner.nextLine();
        IO.print("Descrição: ");
        scanner.nextLine();
        IO.print("Data de Início (dd/MM/yyyy): ");
        scanner.nextLine();
        IO.print("Data de Término (dd/MM/yyyy): ");
        scanner.nextLine();

        IO.println("Selecione o ID do Responsável:");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.perfil() == PerfilUsuario.GERENTE || u.perfil() == PerfilUsuario.ADMINISTRADOR) {
                IO.println("[" + i + "] " + u.perfil() + " - " + u.login());
            }
        }

        int idGerente = Integer.parseInt(scanner.nextLine());
        Usuario gerente = usuarios.get(idGerente);

        projetos.add(new Projeto(nome, gerente));
        IO.println("Projeto cadastrado com sucesso!");

    } catch (DateTimeParseException e) {
        IO.println("Formato de data inválido. Use dd/MM/yyyy.");
    } catch (IndexOutOfBoundsException e) {
        IO.println("ID de usuário inválido.");
    }
}

private static void alocarMembroNaEquipe() {
    IO.println("\n--- Alocar Membro na Equipe ---");
    if (equipes.isEmpty() || usuarios.isEmpty()) {
        IO.println("Erro: Cadastre pelo menos uma equipe e um usuário primeiro.");
        return;
    }

    try {
        IO.println("Selecione a Equipe:");
        for (int i = 0; i < equipes.size(); i++) {
            IO.println("[" + i + "] " + equipes.get(i).getNome());
        }
        int idEquipe = Integer.parseInt(scanner.nextLine());
        Equipe equipeSelecionada = equipes.get(idEquipe);

        IO.println("Selecione o Usuário:");
        for (int i = 0; i < usuarios.size(); i++) {
            IO.println("[" + i + "] " + usuarios.get(i).login());
        }
        int idUsuario = Integer.parseInt(scanner.nextLine());
        Usuario usuarioSelecionado = usuarios.get(idUsuario);

        equipeSelecionada.adicionarMembro(usuarioSelecionado);
        IO.println("Usuário alocado na equipe com sucesso!");

    } catch (IndexOutOfBoundsException | NumberFormatException e) {
        IO.println("ID inválido. Operação cancelada.");
    }
}

private static void alocarEquipeNoProjeto() {
    IO.println("\n--- Alocar Equipe no Projeto ---");
    if (projetos.isEmpty() || equipes.isEmpty()) {
        IO.println("Erro: Cadastre pelo menos um projeto e uma equipe primeiro.");
        return;
    }

    try {
        IO.println("Selecione o Projeto:");
        for (int i = 0; i < projetos.size(); i++) {
            IO.println("[" + i + "] " + projetos.get(i).getNome());
        }
        int idProjeto = Integer.parseInt(scanner.nextLine());
        Projeto projetoSelecionado = projetos.get(idProjeto);

        IO.println("Selecione a Equipe:");
        for (int i = 0; i < equipes.size(); i++) {
            IO.println("[" + i + "] " + equipes.get(i).getNome());
        }
        int idEquipe = Integer.parseInt(scanner.nextLine());
        Equipe equipeSelecionada = equipes.get(idEquipe);

        projetoSelecionado.alocarEquipe(equipeSelecionada);
        IO.println("Equipe alocada no projeto com sucesso!");

    } catch (IndexOutOfBoundsException | NumberFormatException e) {
        IO.println("ID inválido. Operação cancelada.");
    }
}

private static void cadastrarTarefaNoProjeto() {
    IO.println("\n--- Nova Tarefa ---");
    if (projetos.isEmpty() || usuarios.isEmpty()) {
        IO.println("Erro: É necessário ter pelo menos um projeto e um usuário cadastrados.");
        return;
    }

    try {
        IO.println("Selecione o Projeto:");
        for (int i = 0; i < projetos.size(); i++) {
            IO.println("[" + i + "] " + projetos.get(i).getNome());
        }
        int idProjeto = Integer.parseInt(scanner.nextLine());
        Projeto projetoSelecionado = projetos.get(idProjeto);

        IO.print("Título da Tarefa: ");
        String titulo = scanner.nextLine();
        IO.print("Descrição da Tarefa: ");
        scanner.nextLine();

        IO.println("Selecione o Usuário Responsável:");
        for (int i = 0; i < usuarios.size(); i++) {
            IO.println("[" + i + "] " + usuarios.get(i).login());
        }
        int idUsuario = Integer.parseInt(scanner.nextLine());
        Usuario usuarioSelecionado = usuarios.get(idUsuario);

        projetoSelecionado.adicionarTarefa(new Tarefa(titulo, usuarioSelecionado));
        IO.println("Tarefa adicionada ao projeto com sucesso!");
    } catch (IndexOutOfBoundsException | NumberFormatException e) {
        IO.println("ID inválido. Operação cancelada.");
    }
}

private static void gerarRelatorio() {
    IO.println("\n=== Relatório de Projetos ===");
    if (projetos.isEmpty()) {
        IO.println("Nenhum projeto cadastrado.");
        return;
    }

    for (Projeto p : projetos) {
        IO.println("\nProjeto: " + p.getNome());

        IO.println("Equipes Alocadas:");
        for (Equipe eq : p.getEquipes()) {
            IO.println("  - " + eq.getNome());
            for (Usuario membro : eq.getMembros()) {
                IO.println("    * Membro: " + membro.login());
            }
        }

        IO.println("Tarefas do Projeto:");
        if (p.getTarefas().isEmpty()) {
            IO.println("  - Nenhuma tarefa cadastrada.");
        } else {
            for (Tarefa t : p.getTarefas()) {
                IO.println("  - " + t.titulo() + " (Responsável: " + t.responsavel().login() + ")");
            }
        }
    }
}