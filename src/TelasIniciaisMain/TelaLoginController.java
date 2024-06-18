package TelasIniciaisMain;

import Usuario.Usuario;
import Usuario.UsuarioDAO;

public class TelaLoginController {
    private final TelaLogin telaLogin;
    private final UsuarioDAO usuarioDAO;

    public TelaLoginController(TelaLogin telaLogin, UsuarioDAO usuarioDAO) {
        this.telaLogin = telaLogin;
        this.usuarioDAO = usuarioDAO;
    }

    private boolean isNull(String campo1, String campo2){
        return campo1.trim().isEmpty() || campo2.trim().isEmpty();
    }

    boolean logUsuario(String nome, String senha) {
        if (isNull(nome, senha)) {
            telaLogin.showErrorMessage("Preencha os campos");
        }
        else{
            Usuario usuario = usuarioDAO.getUsuarioForLogin(nome, senha);
            if(usuario == null){
                telaLogin.showErrorMessage("Usuário ou senha incorretos!");
            }
            else{
                Main.usrLogado = usuario;
                return true;
            }
        }
        return false;
    }
}



