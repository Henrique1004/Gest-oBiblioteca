package Emprestimo;

import Livro.Livro;
import Livro.LivroDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaCadastroEmp extends JFrame implements ActionListener {
    private final EmprestimoDAO emprestimoDAO;
    private final LivroDAO livroDAO;
    private final TelaCadastroEmpController telaCadastroEmpController;
    private Livro livro;
    private String estadoDevString;
    private JPanel campos;
    private JTextField isbnLivro;
    private JLabel dataEmp;
    private JLabel qtdeDias;
    private JLabel dataDev;
    private JTextField nomePessoa;
    private JTextField raPessoa;
    private JCheckBox estadoDev;
    private JPanel painelBot;
    private JButton botCad;
    int vezesPressionado = 0;

    public TelaCadastroEmp(EmprestimoDAO emprestimoDAO) {
        this.emprestimoDAO = new EmprestimoDAO();
        this.livroDAO = new LivroDAO();
        this.telaCadastroEmpController = new TelaCadastroEmpController(this, emprestimoDAO, livroDAO);
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
        isbnLivro = new JTextField("Isbn do livro");
        isbnLivro.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        isbnLivro.setBorder(null);
        isbnLivro.setPreferredSize(new Dimension(400, 60));
        isbnLivro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isbnLivro.setText("");
            }
        });
        gbl.setConstraints(isbnLivro, gbc);
        campos.add(isbnLivro);
        gbc.gridy++;
        dataEmp = new JLabel("Data do empréstimo");
        dataEmp.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        dataEmp.setBorder(null);
        dataEmp.setPreferredSize(new Dimension(400, 60));
        dataEmp.setOpaque(true);
        dataEmp.setBackground(Color.gray);
        gbl.setConstraints(dataEmp, gbc);
        campos.add(dataEmp);
        gbc.gridy++;
        qtdeDias = new JLabel("QTDE de dias");
        qtdeDias.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        qtdeDias.setBorder(null);
        qtdeDias.setPreferredSize(new Dimension(400, 60));
        qtdeDias.setOpaque(true);
        qtdeDias.setBackground(Color.gray);
        gbl.setConstraints(qtdeDias, gbc);
        campos.add(qtdeDias);
        gbc.gridy++;
        dataDev = new JLabel("Data de devolução");
        dataDev.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        dataDev.setBorder(null);
        dataDev.setPreferredSize(new Dimension(400, 60));
        dataDev.setOpaque(true);
        dataDev.setBackground(Color.gray);
        gbl.setConstraints(dataDev, gbc);
        campos.add(dataDev);
        gbc.gridy++;
        nomePessoa = new JTextField("Mutuário");
        nomePessoa.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        nomePessoa.setBorder(null);
        nomePessoa.setPreferredSize(new Dimension(400, 60));
        nomePessoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nomePessoa.setText("");
            }
        });
        gbl.setConstraints(nomePessoa, gbc);
        campos.add(nomePessoa);
        gbc.gridy++;
        raPessoa = new JTextField("RA do mutuário");
        raPessoa.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        raPessoa.setBorder(null);
        raPessoa.setPreferredSize(new Dimension(400, 60));
        raPessoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                raPessoa.setText("");
            }
        });
        gbl.setConstraints(raPessoa, gbc);
        campos.add(raPessoa);
        this.add(campos, BorderLayout.CENTER);

        painelBot = new JPanel();
        painelBot.setBackground(Color.decode("#adaba3"));
        botCad = new JButton("Cadastrar empréstimo");
        botCad.setMaximumSize(new Dimension(100, 20));
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

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate dataEmpC = null;
    LocalDate dataDevC = null;
    int qtdeDiasF = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        vezesPressionado += 1;

        if(vezesPressionado == 1){
            livro = livroDAO.getLivroByIsbnForEmp(isbnLivro.getText());

            if(livro == null){
                showErrorMessage("Livro.Livro não encontrado!");
            }
            else if(livro.getQtde() == 0 || livro.getDisponivel().equals("Indisponível")){
                showErrorMessage("Livro.Livro indisponível");
            }

            qtdeDiasF = livro.getQtdeDiasEmp();
            qtdeDias.setText("QTDE de dias do empréstimo: " + qtdeDiasF);
            dataEmpC = LocalDate.now();
            dataDevC = dataEmpC.plusDays(qtdeDiasF);
            String dataEmpF = dataEmpC.format(formatter);
            String dataDevF = dataDevC.format(formatter);
            dataEmp.setText("Data do empréstimo: " + dataEmpF);
            dataDev.setText("Data de devolução " + dataDevF);

            estadoDevString = "Não devolvido";
            botCad.setText("Confirma");
        }
        else if(vezesPressionado == 2){
            telaCadastroEmpController.adEmp(livro, dataEmpC, qtdeDiasF, dataDevC,
                    nomePessoa.getText(), raPessoa.getText(), estadoDevString);
            dispose();
        }
    }
}