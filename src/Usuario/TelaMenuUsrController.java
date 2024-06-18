package Usuario;

import java.util.List;

public class TelaMenuUsrController {
    private final TelaMenuUsr telaMenuUsr;
    private final UsuarioDAO usuarioDAO;

    public TelaMenuUsrController(TelaMenuUsr telaMenuUsr, UsuarioDAO usuarioDAO) {
        this.telaMenuUsr = telaMenuUsr;
        this.usuarioDAO = usuarioDAO;
    }

    List<Usuario> getUsuarios(String chave){
        List<Usuario> resultadosDaBusca = usuarioDAO.getUsuarios(chave);
        return resultadosDaBusca;
    }

    Usuario getUsuarioById(int id){
        Usuario usuario = usuarioDAO.getUsuarioById(id);
        if(usuario == null){
            return null;
        }
        else{
            return usuario;
        }
    }

    void excUsuario(int id){
        if(usuarioDAO.excUsuario(id)){
            telaMenuUsr.showSuccesMessage("Usuário excluído com sucesso!");
        }
        else{
            telaMenuUsr.showErrorMessage("Erro ao excluir o usuário");
        }
    }
}

