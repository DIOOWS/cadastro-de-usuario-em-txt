public class Usuario{
        String nome;
        int idade;
        String email;

        public Usuario(String nome, int idade, String email) {
            this.nome = nome;
            this.idade = idade;
            this.email = email;
        }
        @Override
        public String toString(){
            return nome + ";" + idade + ";" +  email;
        }
        public static Usuario fromString(String linha){
            String[] partes = linha.split(";");
            return new Usuario(partes[0], Integer.parseInt(partes[1]),partes[2]);
        }
    }