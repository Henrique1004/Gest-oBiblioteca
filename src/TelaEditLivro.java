import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEditLivro extends JFrame implements ActionListener {
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

    public TelaEditLivro(Livro livro){
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
        gbl.setConstraints(titulo, gbc);
        campos.add(titulo);
        gbc.gridy++;
        autor = new JTextField(livro.getAutor());
        autor.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        autor.setBorder(null);
        autor.setPreferredSize(new Dimension(400, 60));
        gbl.setConstraints(autor, gbc);
        campos.add(autor);
        gbc.gridy++;
        categoria = new JTextField(livro.getCategoria());
        categoria.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        categoria.setBorder(null);
        categoria.setPreferredSize(new Dimension(400, 60));
        gbl.setConstraints(categoria, gbc);
        campos.add(categoria);
        gbc.gridy++;
        isbn = new JTextField(livro.getIsbn());
        isbn.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        isbn.setBorder(null);
        isbn.setPreferredSize(new Dimension(400, 60));
        gbl.setConstraints(isbn, gbc);
        campos.add(isbn);
        gbc.gridy++;
        qtde = new JTextField(String.valueOf(livro.getQtde()));
        qtde.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        qtde.setBorder(null);
        qtde.setPreferredSize(new Dimension(400, 60));
        gbl.setConstraints(qtde, gbc);
        campos.add(qtde);
        gbc.gridy++;
        qtdeDiasEmp = new JTextField(String.valueOf(livro.getQtdeDiasEmp()));
        qtdeDiasEmp.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        qtdeDiasEmp.setBorder(null);
        qtdeDiasEmp.setPreferredSize(new Dimension(400, 60));
        gbl.setConstraints(qtdeDiasEmp, gbc);
        campos.add(qtdeDiasEmp);
        gbc.gridy++;
        disponivel = new JCheckBox("Disponível", livro.getDisponivel());
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
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean isNumeric(String str) {
        return (str != null && str.matches("\\d+"));
    }
    private boolean isNull(String campo1, String campo2, String campo3, String campo4){
        if((campo1 == null) || (campo2 == null) || (campo3 == null) || (campo4 == null)){
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (!isNumeric(qtde.getText()) || !isNumeric(qtdeDiasEmp.getText())) {
            JOptionPane.showMessageDialog(null, "Qtde e Qtde de dias do empréstimo devem ser números inteiros positivos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(!isNumeric(isbn.getText())){
            JOptionPane.showMessageDialog(null, "O isbn deve ser numérico e sem traços", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(isNull(titulo.getText(), autor.getText(), categoria.getText(), isbn.getText())){
            JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            livro.setTitulo(titulo.getText());
            livro.setAutor(autor.getText());
            livro.setCategoria(categoria.getText());
            livro.setIsbn(isbn.getText());
            livro.setQtde(Integer.parseInt(qtde.getText()));
            livro.setQtdeDiasEmp(Integer.parseInt(qtdeDiasEmp.getText()));
            livro.setDisponivel(disponivel.isSelected());
            JOptionPane.showMessageDialog(null, "Livro editado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
