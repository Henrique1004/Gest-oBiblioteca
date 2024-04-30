public class Main {
    static LivroBaseDeDados livroBaseDeDados = new LivroBaseDeDados();
    public static void main(String[] args) {
        DBManager.createDatabase();
        //TelaCadastroLivro telaCadastroLivro = new TelaCadastroLivro();
        TelaMenuUsr telaMenuUsuario = new TelaMenuUsr();
    }
}
