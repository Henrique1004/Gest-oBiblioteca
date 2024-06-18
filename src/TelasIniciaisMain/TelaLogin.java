package TelasIniciaisMain;

import Usuario.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame implements ActionListener{
    private final UsuarioDAO usuarioDAO;
    private final TelaLoginController telaLoginController;
    private JPanel campos;
    private JTextField nome;
    private JPasswordField senha;
    private JPanel painelBot;
    private JPanel painelNome;
    private JLabel labelNome;
    private JPanel painelSenha;
    private JLabel labelSenha;
    private JButton botCad;

    public TelaLogin(){
        this.usuarioDAO = new UsuarioDAO();
        this.telaLoginController = new TelaLoginController(this, usuarioDAO);
        setLayout(new BorderLayout());

        campos = new JPanel();
        campos.setBackground(Color.decode("#adaba3"));
        campos.setLayout(new BoxLayout(campos, BoxLayout.Y_AXIS));

        painelNome = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelNome.setBackground(Color.decode("#adaba3"));
        labelNome = new JLabel("Nome:");
        labelNome.setFont(new Font("Comic Sans", Font.BOLD, 16));
        painelNome.add(labelNome);
        nome = new JTextField();
        nome.setFont(new Font("Comic Sans", Font.BOLD, 14));
        nome.setBorder(null);
        nome.setPreferredSize(new Dimension(300, 30));
        painelNome.add(nome);
        campos.add(painelNome);

        painelSenha = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelSenha.setBackground(Color.decode("#adaba3"));
        labelSenha = new JLabel("Senha:");
        labelSenha.setFont(new Font("Comic Sans", Font.BOLD, 16));
        painelSenha.add(labelSenha);
        senha = new JPasswordField();
        senha.setBorder(null);
        senha.setPreferredSize(new Dimension(300, 30));
        painelSenha.add(senha);
        campos.add(painelSenha);

        add(campos, BorderLayout.CENTER);

        painelBot = new JPanel();
        painelBot.setBackground(Color.decode("#adaba3"));
        botCad = new JButton("Login");
        botCad.setMaximumSize(new Dimension(100,20));
        botCad.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        botCad.setBackground(Color.decode("#8b8c90"));
        botCad.setFocusable(false);
        botCad.addActionListener(this);
        painelBot.add(botCad);

        add(painelBot, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(telaLoginController.logUsuario(nome.getText(), senha.getText())){
            TelaMenuGeral telaMenuGeral = new TelaMenuGeral();
        }
    }
}

