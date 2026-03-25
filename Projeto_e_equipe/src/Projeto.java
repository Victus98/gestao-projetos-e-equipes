import java.util.ArrayList;
import java.util.List;

public class Projeto {
    private final String nome;
    private final List<Equipe> equipesAlocadas;
    private final List<Tarefa> tarefas;

    public Projeto(String nome, Usuario gerenteResponsavel) {
        if (gerenteResponsavel.perfil() != PerfilUsuario.GERENTE && gerenteResponsavel.perfil() != PerfilUsuario.ADMINISTRADOR) {
            throw new IllegalArgumentException("O responsável deve ser um Gerente ou Administrador.");
        }

        this.nome = nome;
        this.equipesAlocadas = new ArrayList<>();
        this.tarefas = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public List<Equipe> getEquipes() { return equipesAlocadas; }
    public List<Tarefa> getTarefas() { return tarefas; }

    public void alocarEquipe(Equipe equipe) {
        this.equipesAlocadas.add(equipe);
    }

    public void adicionarTarefa(Tarefa tarefa) {
        this.tarefas.add(tarefa);
    }
}