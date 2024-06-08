import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaEditUsr extends JFrame implements UsrListener, ActionListener {
    private final TelaEditUsrController telaEditUsrController;
    private final UsuarioDAO usuarioDAO;
    private TelaMenuUsr telaMenuUsr;
    private String chave;
    private Usuario usuario;
    private JPanel campos;
    private JTextField nome;
    private JTextField cargo;
    private JTextField senha;
    private JPanel painelBot;
    private JButton botEdit;

    public TelaEditUsr(TelaMenuUsr telaMenuUsr, String chave, Usuario usuario, UsuarioDAO usuarioDAO){
        this.telaMenuUsr = telaMenuUsr;
        this.chave = chave;
        this.usuarioDAO = usuarioDAO;
        this.telaEditUsrController = new TelaEditUsrController(this, usuarioDAO);
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
        nome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            nome.setText("");
            }
        });
        gbl.setConstraints(nome, gbc);
        campos.add(nome);
        gbc.gridy++;
        cargo = new JTextField(usuario.getCargo());
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
        senha = new JTextField(usuario.getSenha());
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

    public void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccesMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        telaEditUsrController.editUsuario(usuario.getId(), nome.getText(), senha.getText(), cargo.getText());
    }

    @Override
    public void updateData() {
        telaMenuUsr.loadUsers(chave);
    }
}