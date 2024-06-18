package Emprestimo;

import Interfaces.GeneralListener;
import TelasIniciaisMain.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TelaMenuEmp extends JFrame implements GeneralListener, ActionListener {
    private final EmprestimoDAO emprestimoDAO;
    private final TelaMenuEmpController telaMenuEmpController;
    private JPanel topPanel;
    private JTextField campoPesq;
    private JButton botPesq;
    private DefaultTableModel table;
    private JTable empTable;
    private JScrollPane resultadoPesq;
    private JPanel painelBot;
    private JButton botDev;
    private JButton botAdEmp;
    private JButton botExcEmp;

    public TelaMenuEmp(){
        this.emprestimoDAO = new EmprestimoDAO();
        this.telaMenuEmpController = new TelaMenuEmpController(this, emprestimoDAO);
        emprestimoDAO.subscribe(this);
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

        table = new DefaultTableModel(new Object[]{"ID", "Livro", "Data do empréstimo", "Qtde de dias", "Devolução", "Mutuário",
                "RA do mutuário", "Devolução"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        empTable = new JTable(table);
        resultadoPesq = new JScrollPane(empTable);
        this.add(resultadoPesq, BorderLayout.CENTER);

        painelBot = new JPanel();
        painelBot.setLayout(new FlowLayout());
        painelBot.setBackground(Color.decode("#adaba3"));
        botDev = new JButton("Realizar devolução");
        botDev.setFont(new Font("Comic Sans", Font.BOLD, 12));
        botDev.setBackground(Color.decode("#8b8c90"));
        botDev.setFocusable(false);
        botDev.addActionListener(this);
        botDev.setActionCommand("devolver");
        botAdEmp = new JButton("Realizar empréstimo");
        botAdEmp.setFont(new Font("Comic Sans", Font.BOLD, 12));
        botAdEmp.setBackground(Color.decode("#8b8c90"));
        botAdEmp.setFocusable(false);
        botAdEmp.addActionListener(this);
        botAdEmp.setActionCommand("adicionar");
        botExcEmp = new JButton("Excluir empréstimo");
        botExcEmp.setFont(new Font("Comic Sans", Font.BOLD, 12));
        botExcEmp.setBackground(Color.decode("#8b8c90"));
        botExcEmp.setFocusable(false);
        botExcEmp.addActionListener(this);
        botExcEmp.setActionCommand("excluir");
        painelBot.add(botDev);
        painelBot.add(botAdEmp);
        painelBot.add(botExcEmp);
        add(painelBot, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        tornaFuncIndisp();
        setVisible(true);
    }

    void loadEmp(String chave) {
        table.setRowCount(0);
        List<Emprestimo> emprestimos;
        emprestimos = telaMenuEmpController.getEmp(chave);
        for (Emprestimo emprestimo : emprestimos) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataEmp = emprestimo.getDataEmp().format(formatter);
            String dataDev = emprestimo.getDataDev().format(formatter);
            table.addRow(new Object[]{emprestimo.getId(), emprestimo.getLivro().getTitulo(), dataEmp, emprestimo.getQtdeDias(),
                    dataDev, emprestimo.getNomePessoa(),
                    emprestimo.getRaPessoa(), emprestimo.getEstadoDev()});
        }
    }

    void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    void showSuccesMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    void tornaFuncIndisp(){
        if(Main.usrLogado.getCargo().equals("usr")){
            botExcEmp.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("pesquisar")) {
            String chave = campoPesq.getText();
            if(telaMenuEmpController.getEmp(chave).isEmpty()){
                showErrorMessage("Nenhum empréstimo encontrado!");
            }
            else{
                loadEmp(chave);
            }
        }
        else if (e.getActionCommand().equals("adicionar")) {
            TelaCadastroEmp telaCadastroEmp = new TelaCadastroEmp(this.emprestimoDAO);
        }
        else if(e.getActionCommand().equals("devolver")){
            int selectedRow = empTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) table.getValueAt(selectedRow, 0);
                telaMenuEmpController.setDevolvido(id);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um empréstimo para devolver.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getActionCommand().equals("excluir")) {
            int selectedRow = empTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) table.getValueAt(selectedRow, 0);
                telaMenuEmpController.excEmp(id);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um empréstimo para excluir.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void updateData() {
        loadEmp(campoPesq.getText());
    }
}
