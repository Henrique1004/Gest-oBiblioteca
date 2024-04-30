import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroUsr extends JFrame implements ActionListener {
    private JPanel campos;
    private JTextField nome;
    private JTextField cargo;
    private JTextField senha;
    private JPanel painelBot;
    private JButton botCad;

    public TelaCadastroUsr(){
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
        nome = new JTextField("Nome");
        nome.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        nome.setBorder(null);
        nome.setPreferredSize(new Dimension(400, 60));
        gbl.setConstraints(nome, gbc);
        campos.add(nome);
        gbc.gridy++;
        cargo = new JTextField("Cargo");
        cargo.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        cargo.setBorder(null);
        cargo.setPreferredSize(new Dimension(400, 60));
        gbl.setConstraints(cargo, gbc);
        campos.add(cargo);
        gbc.gridy++;
        senha = new JTextField("Senha");
        senha.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        senha.setBorder(null);
        senha.setPreferredSize(new Dimension(400, 60));
        gbl.setConstraints(senha, gbc);
        campos.add(senha);
        this.add(campos, BorderLayout.CENTER);

        painelBot = new JPanel();
        painelBot.setBackground(Color.decode("#adaba3"));
        botCad = new JButton("Cadastrar usuário");
        botCad.setMaximumSize(new Dimension(100,20));
        botCad.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        botCad.setBackground(Color.decode("#8b8c90"));
        botCad.setFocusable(false);
        botCad.addActionListener(this);
        painelBot.add(botCad);
        this.add(painelBot, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean isNull(String campo1, String campo2, String campo3){
        if((campo1.isEmpty()) || (campo2.isEmpty()) || (campo3.isEmpty())){
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
        else if (UsuarioBaseDeDados.adUsuario(nome.getText(), cargo.getText(), senha.getText())){
            JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, "Usuário já cadastrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
