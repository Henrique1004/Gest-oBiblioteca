package Usuario;

public class TelaCadastroUsrController {
    private final TelaCadastroUsr telaCadastroUsr;
    private final UsuarioDAO usuarioDAO;

    public TelaCadastroUsrController(TelaCadastroUsr telaCadastroUsr, UsuarioDAO usuarioDAO) {
        this.telaCadastroUsr = telaCadastroUsr;
        this.usuarioDAO = usuarioDAO;
    }

    private boolean isNull(String campo1, String campo2, String campo3){
        return campo1.trim().isEmpty() || campo2.trim().isEmpty() || campo3.trim().isEmpty();
    }

    void adUsuario(String nome, String senha, String cargo) {
        if (isNull(nome, senha, cargo)) {
            telaCadastroUsr.showErrorMessage("Preencha todos os campos");
        }
        else if (!(cargo.equals("adm") || cargo.equals("usr"))) {
            telaCadastroUsr.showErrorMessage("Preencha com 'adm' para administrador ou 'usr' para usuário");
        }
        else if(usuarioDAO.existeUsuario(nome, cargo)){
             telaCadastroUsr.showErrorMessage("Usuário já cadastrado!");
        }
        else{
            if(usuarioDAO.adUsuario(nome, senha, cargo)){
                telaCadastroUsr.showSuccesMessage("Usuário adicionado com sucesso!");
            }
        }
    }
}

