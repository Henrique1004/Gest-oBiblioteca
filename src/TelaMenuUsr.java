import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaMenuUsr extends JFrame implements ActionListener{
    private JPanel topPanel;
    private JTextField campoPesq;
    private JButton botPesq;
    private static DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane resultadoPesq;
    private JPanel painelBot;
    private JButton botAdUsuario;
    private JButton botEditUsuario;
    private JButton botExcUsuario;

    public TelaMenuUsr(){
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

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Cargo", "Senha"}, 0);
        table = new JTable(tableModel);
        resultadoPesq = new JScrollPane(table);
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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    //    void tornaFuncIndisp(Usuario UsrLogado){
//
//    }
    private void loadUsers(List<Usuario> usuarios) {
        tableModel.setRowCount(0);
        for (Usuario usuario : usuarios) {
            tableModel.addRow(new Object[]{usuario.getId(), usuario.getNome(), usuario.getCargo(), usuario.getSenha()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("pesquisar")){
            String chave = campoPesq.getText();
            List<Usuario> resultadosDaBusca = UsuarioBaseDeDados.getUsuarios(chave);
            if(resultadosDaBusca.isEmpty()){
                JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                loadUsers(resultadosDaBusca);
            }
        }
        else if(e.getActionCommand().equals("adicionar")){
            TelaCadastroUsr telaCadastroUsuario = new TelaCadastroUsr();
        }
        else if (e.getActionCommand().equals("editar")) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                List<Usuario> umUsuario = UsuarioBaseDeDados.getUsuarios(String.valueOf(id));
                Usuario usuario = umUsuario.getFirst();
                TelaEditUsr telaEditUsr = new TelaEditUsr(usuario);
            }
            else {
                JOptionPane.showMessageDialog(null, "Selecione um usuário para editar.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(e.getActionCommand().equals("excluir")){
            String excString = JOptionPane.showInputDialog("ID do usuário a ser excluído: ");
            //if(Main.usuarioBaseDeDados.excUsuario(excString)){
                JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Usuário não encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
}



