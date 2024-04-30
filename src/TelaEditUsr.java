import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEditUsr extends JFrame implements ActionListener {
    private Usuario usuario;
    private JPanel campos;
    private JTextField nome;
    private JTextField cargo;
    private JTextField senha;
    private JPanel painelBot;
    private JButton botEdit;

    public TelaEditUsr(Usuario usuario){
        this.usuario = usuario;
        setLayout(new BorderLayout());

        campos = new JPanel();
        campos.setBackground(Color.decode("#adaba3"));
        GridBagLayout gbl = new GridBagLayout();
        campos.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        nome = new JTextField(usuario.getNome());
        nome.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        nome.setBorder(null);
        nome.setPreferredSize(new Dimension(400, 60));
        gbl.setConstraints(nome, gbc);
        campos.add(nome);
        gbc.gridy++;
        cargo = new JTextField(usuario.getCargo());
        cargo.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        cargo.setBorder(null);
        cargo.setPreferredSize(new Dimension(400, 60));
        gbl.setConstraints(cargo, gbc);
        campos.add(cargo);
        gbc.gridy++;
        senha = new JTextField(usuario.getSenha());
        senha.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        senha.setBorder(null);
        senha.setPreferredSize(new Dimension(400, 60));
        gbl.setConstraints(senha, gbc);
        campos.add(senha);
        this.add(campos, BorderLayout.CENTER);

        painelBot = new JPanel();
        painelBot.setBackground(Color.decode("#adaba3"));
        botEdit = new JButton("Editar usuário");
        botEdit.setMaximumSize(new Dimension(100,20));
        botEdit.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        botEdit.setBackground(Color.decode("#8b8c90"));
        botEdit.setFocusable(false);
        botEdit.addActionListener(this);
        painelBot.add(botEdit);
        this.add(painelBot, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean isNumeric(String str) {
        return (str != null && str.matches("\\d+"));
    }
    private boolean isNull(String campo1, String campo2, String campo3){
        if((campo1 == null) || (campo2 == null) || (campo3 == null)){
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(isNull(nome.getText(), cargo.getText(), senha.getText())){
            JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (!(cargo.getText().equals("adm") || cargo.getText().equals("usr"))){
            JOptionPane.showMessageDialog(null, "Preencha com 'adm' para administrador ou 'usr' para usuário", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(UsuarioBaseDeDados.editUsuario(usuario.getId(), nome.getText(), cargo.getText(), senha.getText())){
            JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

