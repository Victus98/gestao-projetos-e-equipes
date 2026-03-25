import java.io.*;
import java.util.List;

public class GerenciadorArquivo {
    private static final String ARQUIVO = "dados_projeto.txt";

    public static void salvarDados(List<Usuario> usuarios, List<Equipe> equipes, List<Projeto> projetos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(usuarios);
            oos.writeObject(equipes);
            oos.writeObject(projetos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static void carregarDados(List<Usuario> usuarios, List<Equipe> equipes, List<Projeto> projetos) {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return; // Se for a primeira vez, não tenta carregar

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            usuarios.addAll((List<Usuario>) ois.readObject());
            equipes.addAll((List<Equipe>) ois.readObject());
            projetos.addAll((List<Projeto>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }
}