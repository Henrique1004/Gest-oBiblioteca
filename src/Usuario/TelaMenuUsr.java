package Usuario;

import Interfaces.GeneralListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaMenuUsr extends JFrame implements GeneralListener, ActionListener{
    private final UsuarioDAO usuarioDAO;
    private final TelaMenuUsrController telaMenuUsrController;
    private JPanel topPanel;
    public JTextField campoPesq;
    private JButton botPesq;
    private DefaultTableModel table;
    private JTable usrTable;
    private JScrollPane resultadoPesq;
    private JPanel painelBot;
    private JButton botAdUsuario;
    private JButton botEditUsuario;
    private JButton botExcUsuario;

    public TelaMenuUsr(){
        this.usuarioDAO = new UsuarioDAO();
        this.telaMenuUsrController = new TelaMenuUsrController(this, usuarioDAO);
        usuarioDAO.subscribe(this);
        setLayout(new BorderLayout());

        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(Color.decode("#adaba3"));
        campoPesq = new JTextField(20);
        campoPesq.setMaximumSize(new Dimension(400, 30));
        campoPesq.setBorder(null);
        botPesq = new JButton("Pesquisar");
        botPesq.setFont(new Font("Comic Sans", Font.BOLD, 12));
        botPesq.setBackground(Color.decode("#8b8c90"));
        botPesq.setFocusable(false);
        botPesq.addActionListener(this);
        botPesq.setActionCommand("pesquisar");
        topPanel.add(campoPesq);
        topPanel.add(botPesq);
        this.add(topPanel, BorderLayout.NORTH);

        table = new DefaultTableModel(new Object[]{"ID", "Nome", "Cargo", "Senha"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        usrTable = new JTable(table);
        resultadoPesq = new JScrollPane(usrTable);
        this.add(resultadoPesq, BorderLayout.CENTER);

        painelBot = new JPanel();
        painelBot.setLayout(new FlowLayout());
        painelBot.setBackground(Color.decode("#adaba3"));
        botAdUsuario = new JButton("Adicionar usuário");
        botAdUsuario.setFont(new Font("Comic Sans", Font.BOLD, 12));
        botAdUsuario.setBackground(Color.decode("#8b8c90"));
        botAdUsuario.setFocusable(false);
        botAdUsuario.addActionListener(this);
        botAdUsuario.setActionCommand("adicionar");
        botEditUsuario = new JButton("Editar usuário");
        botEditUsuario.setFont(new Font("Comic Sans", Font.BOLD, 12));
        botEditUsuario.setBackground(Color.decode("#8b8c90"));
        botEditUsuario.setFocusable(false);
        botEditUsuario.addActionListener(this);
        botEditUsuario.setActionCommand("editar");
        botExcUsuario = new JButton("Excluir usuário");
        botExcUsuario.setFont(new Font("Comic Sans", Font.BOLD, 12));
        botExcUsuario.setBackground(Color.decode("#8b8c90"));
        botExcUsuario.setFocusable(false);
        botExcUsuario.addActionListener(this);
        botExcUsuario.setActionCommand("excluir");
        painelBot.add(botAdUsuario);
        painelBot.add(botEditUsuario);
        painelBot.add(botExcUsuario);
        add(painelBot, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void loadUsers(String chave) {
        table.setRowCount(0);
        List<Usuario> usuarios;
        usuarios = telaMenuUsrController.getUsuarios(chave);
        for (Usuario usuario : usuarios) {
            table.addRow(new Object[]{usuario.getId(), usuario.getNome(), usuario.getCargo(), usuario.getSenha()});
        }
    }

    void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    void showSuccesMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("pesquisar")) {
            String chave = campoPesq.getText();
            if(telaMenuUsrController.getUsuarios(chave).isEmpty()){
                showErrorMessage("Nenhum usuário encontrado!");
            }
            else{
                loadUsers(chave);
            }

        } else if (e.getActionCommand().equals("adicionar")) {
            TelaCadastroUsr telaCadastroUsr = new TelaCadastroUsr(this, campoPesq.getText(), this.usuarioDAO);

        } else if (e.getActionCommand().equals("editar")) {
            int selectedRow = usrTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) table.getValueAt(selectedRow, 0);
                Usuario usuario = telaMenuUsrController.getUsuarioById(id);
                TelaEditUsr telaEditUsr = new TelaEditUsr(this, campoPesq.getText(), usuario, this.usuarioDAO);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um usuário para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getActionCommand().equals("excluir")) {
            int selectedRow = usrTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) table.getValueAt(selectedRow, 0);
                telaMenuUsrController.excUsuario(id);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um usuário para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void updateData() {
        loadUsers(campoPesq.getText());
    }
}
