package Livro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaEditLivro extends JFrame implements ActionListener {
    private final TelaEditLivroController telaEditLivroController;
    private final LivroDAO livroDAO;
    private Livro livro;
    private JPanel campos;
    private JTextField titulo;
    private JTextField autor;
    private JTextField categoria;
    private JTextField isbn;
    private JTextField qtde;
    private JTextField qtdeDiasEmp;
    private JCheckBox disponivel;
    private JPanel painelBot;
    private JButton botEdit;

    public TelaEditLivro(TelaMenuLivro telaMenuLivro, Livro livro, LivroDAO livroDAO){
        this.livroDAO = livroDAO;
        this.telaEditLivroController = new TelaEditLivroController(this, livroDAO);
        this.livro = livro;
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
        titulo = new JTextField(livro.getTitulo());
        titulo.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        titulo.setBorder(null);
        titulo.setPreferredSize(new Dimension(400, 60));
        titulo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                titulo.setText("");
            }
        });
        gbl.setConstraints(titulo, gbc);
        campos.add(titulo);
        gbc.gridy++;
        autor = new JTextField(livro.getAutor());
        autor.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        autor.setBorder(null);
        autor.setPreferredSize(new Dimension(400, 60));
        autor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                autor.setText("");
            }
        });
        gbl.setConstraints(autor, gbc);
        campos.add(autor);
        gbc.gridy++;
        categoria = new JTextField(livro.getCategoria());
        categoria.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        categoria.setBorder(null);
        categoria.setPreferredSize(new Dimension(400, 60));
        categoria.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                categoria.setText("");
            }
        });
        gbl.setConstraints(categoria, gbc);
        campos.add(categoria);
        gbc.gridy++;
        isbn = new JTextField(livro.getIsbn());
        isbn.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        isbn.setBorder(null);
        isbn.setPreferredSize(new Dimension(400, 60));
        isbn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isbn.setText("");
            }
        });
        gbl.setConstraints(isbn, gbc);
        campos.add(isbn);
        gbc.gridy++;
        qtde = new JTextField(String.valueOf(livro.getQtde()));
        qtde.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        qtde.setBorder(null);
        qtde.setPreferredSize(new Dimension(400, 60));
        qtde.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                qtde.setText("");
            }
        });
        gbl.setConstraints(qtde, gbc);
        campos.add(qtde);
        gbc.gridy++;
        qtdeDiasEmp = new JTextField(String.valueOf(livro.getQtdeDiasEmp()));
        qtdeDiasEmp.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        qtdeDiasEmp.setBorder(null);
        qtdeDiasEmp.setPreferredSize(new Dimension(400, 60));
        qtdeDiasEmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                qtdeDiasEmp.setText("");
            }
        });
        gbl.setConstraints(qtdeDiasEmp, gbc);
        campos.add(qtdeDiasEmp);
        gbc.gridy++;
        boolean disponivelBool = livro.getDisponivel().equals("Disponível")?true:false;
        disponivel = new JCheckBox("Disponível");
        disponivel.setSelected(disponivelBool);
        disponivel.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        disponivel.setBackground(Color.decode("#adaba3"));
        gbl.setConstraints(disponivel, gbc);
        campos.add(disponivel);
        this.add(campos, BorderLayout.CENTER);

        painelBot = new JPanel();
        painelBot.setBackground(Color.decode("#adaba3"));
        botEdit = new JButton("Editar livro");
        botEdit.setMaximumSize(new Dimension(100,20));
        botEdit.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        botEdit.setBackground(Color.decode("#8b8c90"));
        botEdit.setFocusable(false);
        botEdit.addActionListener(this);
        painelBot.add(botEdit);
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
        String disponivelString = disponivel.isSelected()?"Disponível":"Indisponível";
        telaEditLivroController.editLivro(livro.getId(), titulo.getText(), autor.getText(), categoria.getText(), isbn.getText(),
                qtde.getText(), qtdeDiasEmp.getText(), disponivelString);
        dispose();
    }
}
