import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaMenuLivro extends JFrame implements ActionListener{
    private JPanel topPanel;
    public static JTextField campoPesq;
    private JButton botPesq;
    private static DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane resultadoPesq;
    private JPanel painelBot;
    private JButton botAdLivro;
    private JButton botEditLivro;
    private JButton botExcLivro;

    public TelaMenuLivro(){
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

        tableModel = new DefaultTableModel(new Object[]{"ID", "Título", "Autor", "Categoria", "ISBN", "Qtde", "Qtde de dias de empréstimo", "Disponibilidade"}, 0);
        table = new JTable(tableModel);
        resultadoPesq = new JScrollPane(table);
        this.add(resultadoPesq, BorderLayout.CENTER);

        painelBot = new JPanel();
        painelBot.setLayout(new FlowLayout());
        painelBot.setBackground(Color.decode("#adaba3"));
        botAdLivro = new JButton("Adicionar livro");
        botAdLivro.setFont(new Font("Comic Sans", Font.BOLD, 12));
        botAdLivro.setBackground(Color.decode("#8b8c90"));
        botAdLivro.setFocusable(false);
        botAdLivro.addActionListener(this);
        botAdLivro.setActionCommand("adicionar");
        botEditLivro = new JButton("Editar livro");
        botEditLivro.setFont(new Font("Comic Sans", Font.BOLD, 12));
        botEditLivro.setBackground(Color.decode("#8b8c90"));
        botEditLivro.setFocusable(false);
        botEditLivro.addActionListener(this);
        botEditLivro.setActionCommand("editar");
        botExcLivro = new JButton("Excluir livro");
        botExcLivro.setFont(new Font("Comic Sans", Font.BOLD, 12));
        botExcLivro.setBackground(Color.decode("#8b8c90"));
        botExcLivro.setFocusable(false);
        botExcLivro.addActionListener(this);
        botExcLivro.setActionCommand("excluir");
        painelBot.add(botAdLivro);
        painelBot.add(botEditLivro);
        painelBot.add(botExcLivro);
        add(painelBot, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void loadBooks(List<Livro> livros) {
        tableModel.setRowCount(0);
        for (Livro livro : livros) {
            tableModel.addRow(new Object[]{livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getCategoria(), livro.getIsbn(), livro.getQtde(), livro.getQtdeDiasEmp(), livro.getDisponivel()});
        }
    }
//    void tornaFuncIndisp(Livro UsrLogado){
//
//    }
@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("pesquisar")) {
            String chave = campoPesq.getText();
            List<Livro> resultadosDaBusca = LivroBaseDeDados.getLivros(chave);
            if (resultadosDaBusca.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Livro não encontrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                loadBooks(resultadosDaBusca);
            } else {
                loadBooks(resultadosDaBusca);
            }
        } else if (e.getActionCommand().equals("adicionar")) {
            TelaCadastroLivro telaCadastroLivro = new TelaCadastroLivro();
        } else if (e.getActionCommand().equals("editar")) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                List<Livro> umLivro = LivroBaseDeDados.getLivros(String.valueOf(id));
                Livro livro = umLivro.getFirst();
                TelaEditLivro telaEditLivro = new TelaEditLivro(livro);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um livro para editar.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getActionCommand().equals("excluir")) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                LivroBaseDeDados.excLivro(id);
                JOptionPane.showMessageDialog(null, "Livro excluído com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                List<Livro> update = LivroBaseDeDados.getLivros(campoPesq.getText());
                loadBooks(update);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um livro para excluir.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}

