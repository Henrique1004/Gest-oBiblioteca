import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaMenuLivro extends JFrame implements GeneralListener, ActionListener{
    private final LivroDAO livroDAO;
    private final TelaMenuLivroController telaMenuLivroController;
    private JPanel topPanel;
    public JTextField campoPesq;
    private JButton botPesq;
    private DefaultTableModel table;
    private JTable livroTable;
    private JScrollPane resultadoPesq;
    private JPanel painelBot;
    private JButton botAdLivro;
    private JButton botEditLivro;
    private JButton botExcLivro;

    public TelaMenuLivro(){
        this.livroDAO = new LivroDAO();
        this.telaMenuLivroController = new TelaMenuLivroController(this, livroDAO);
        livroDAO.subscribe(this);
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

        table = new DefaultTableModel(new Object[]{"ID", "Título", "Autor", "Categoria", "ISBN", "Qtde", "Qtde de dias de empréstimo", "Disponibilidade"}, 0);
        //table.
        livroTable = new JTable(table);
        resultadoPesq = new JScrollPane(livroTable);
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
    public void loadLivros(String chave) {
        table.setRowCount(0);
        List<Livro> livros;
        livros = telaMenuLivroController.getLivros(chave);
        for (Livro livro : livros) {
            table.addRow(new Object[]{livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getCategoria(), livro.getIsbn(), livro.getQtde(),
            livro.getQtdeDiasEmp(), livro.getDisponivel()});
        }
    }

    public void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccesMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
//    void tornaFuncIndisp(Livro UsrLogado){
//
//    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("pesquisar")) {
            String chave = campoPesq.getText();
            if(telaMenuLivroController.getLivros(chave).isEmpty()){
                showErrorMessage("Nenhum livro encontrado!");
            }
            else{
                loadLivros(chave);
            }
        }
        else if (e.getActionCommand().equals("adicionar")) {
            TelaCadastroLivro telaCadastroLivro = new TelaCadastroLivro(this.livroDAO);
        }
        else if (e.getActionCommand().equals("editar")) {
            int selectedRow = livroTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) table.getValueAt(selectedRow, 0);
                Livro livro = telaMenuLivroController.getLivroById(id);
                TelaEditLivro telaEditLivro = new TelaEditLivro(this, livro, this.livroDAO);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um livro para editar.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (e.getActionCommand().equals("excluir")) {
            int selectedRow = livroTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) table.getValueAt(selectedRow, 0);
                telaMenuLivroController.excLivro(id);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um livro para excluir.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void updateData() {
        loadLivros(campoPesq.getText());
    }
}

