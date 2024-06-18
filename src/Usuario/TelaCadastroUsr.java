package Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastroUsr extends JFrame implements ActionListener{
    private final UsuarioDAO usuarioDAO;
    private final TelaCadastroUsrController telaCadastroUsrController;
    private JPanel campos;
    private JTextField nome;
    private JTextField cargo;
    private JTextField senha;
    private JPanel painelBot;
    private JButton botCad;

    public TelaCadastroUsr(TelaMenuUsr telaMenuUsr, String chave, UsuarioDAO usuarioDAO){
        this.usuarioDAO = new UsuarioDAO();
        this.telaCadastroUsrController = new TelaCadastroUsrController(this, usuarioDAO);
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
        nome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nome.setText("");
            }
        });
        gbl.setConstraints(nome, gbc);
        campos.add(nome);
        gbc.gridy++;
        cargo = new JTextField("Cargo");
        cargo.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        cargo.setBorder(null);
        cargo.setPreferredSize(new Dimension(400, 60));
        cargo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargo.setText("");
            }
        });
        gbl.setConstraints(cargo, gbc);
        campos.add(cargo);
        gbc.gridy++;
        senha = new JTextField("Senha");
        senha.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        senha.setBorder(null);
        senha.setPreferredSize(new Dimension(400, 60));
        senha.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                senha.setText("");
            }
        });
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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    void showSuccesMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        telaCadastroUsrController.adUsuario(nome.getText(), senha.getText(), cargo.getText());
        dispose();
    }

}
