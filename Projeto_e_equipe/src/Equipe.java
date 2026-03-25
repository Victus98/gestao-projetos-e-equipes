import java.util.ArrayList;
import java.util.List;

public class Equipe {
    private final String nome;
    private final List<Usuario> membros;

    public Equipe(String nome) {
        this.nome = nome;
        this.membros = new ArrayList<>();
    }

    public List<Usuario> getMembros() {
        return membros;
    }

    public String getNome() { return nome; }

    public void adicionarMembro(Usuario usuario) {
        this.membros.add(usuario);
    }
}