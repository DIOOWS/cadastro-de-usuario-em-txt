import java.io.*;
import java.util.Scanner;

public class SistemaCadastro {
    // Caminho do arquivo
    private static final String CAMINHO_ARQUIVO = "usuarios.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("\n=== Sistema de Cadastro ===");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Listar usuários");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            if (opcao == 1){
                cadastrarUsuario(scanner);
            }else if (opcao == 2){
                listarUsuarios();
            } else if (opcao == 3) {
                System.out.println("Encerrando o sistema...");
            }else {
                System.out.println("Opção inválida, tente novamente!");
            }
//            scanner.close();
        }
    }

    private static void cadastrarUsuario(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(nome, idade, email);

        try (FileWriter fw = new FileWriter(CAMINHO_ARQUIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(usuario.toString());
            bw.newLine();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o usuário: " + e.getMessage());
        }
    }

    private static void listarUsuarios() {
        try (FileReader fr = new FileReader(CAMINHO_ARQUIVO);
             BufferedReader br = new BufferedReader(fr)) {

            System.out.println("\n=== Lista de Usuários ===");
            String linha;
            while ((linha = br.readLine()) != null) {
                Usuario usuario = Usuario.fromString(linha);
                System.out.println("Nome: " + usuario.nome + ", Idade: " + usuario.idade + ", E-mail: " + usuario.email);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Nenhum usuário cadastrado ainda!");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
