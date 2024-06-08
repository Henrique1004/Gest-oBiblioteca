public class TelaEditUsrController {
    private final TelaEditUsr telaEditUsr;
    private final UsuarioDAO usuarioDAO;

    public TelaEditUsrController(TelaEditUsr telaEditUsr, UsuarioDAO usuarioDAO) {
        this.telaEditUsr = telaEditUsr;
        this.usuarioDAO = usuarioDAO;
    }

    private boolean isNull(String campo1, String campo2, String campo3){
        return campo1.trim().isEmpty() || campo2.trim().isEmpty() || campo3.trim().isEmpty();
    }

    void editUsuario(int id, String nome, String senha, String cargo) {
        if (isNull(nome, senha, cargo)) {
            telaEditUsr.showErrorMessage("Preencha todos os campos");
        }
        else if (!(cargo.equals("adm") || cargo.equals("usr"))) {
            telaEditUsr.showErrorMessage("Preencha com 'adm' para administrador ou 'usr' para usuário");
        }
        else{
            if(usuarioDAO.editUsuario(id, nome, senha, cargo)){
                telaEditUsr.showSuccesMessage("Usuário editado com sucesso!");
            }
        }
    }
}



