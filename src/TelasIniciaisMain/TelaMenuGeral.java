package TelasIniciaisMain;

import Emprestimo.TelaMenuEmp;
import Livro.TelaMenuLivro;
import Usuario.TelaMenuUsr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenuGeral extends JFrame implements ActionListener{
    private JPanel painelUsrLogado;
    private JLabel nomeUsrLogado;
    private JLabel cargoUsrLogado;
    private JPanel painelBot;
    private JButton botLivros;
    private JButton botEmprestimos;
    private JButton botUsuarios;

    public TelaMenuGeral(){
        setLayout(new BorderLayout());

        painelUsrLogado = new JPanel();
        painelUsrLogado.setLayout(new BorderLayout());
        nomeUsrLogado = new JLabel("Usuário: " + Main.usrLogado.getNome());
        nomeUsrLogado.setFont(new Font("Comic Sans", Font.BOLD, 20));
        cargoUsrLogado = new JLabel("Cargo: " + Main.usrLogado.getCargo());
        cargoUsrLogado.setFont(new Font("Comic Sans", Font.BOLD, 18));
        painelUsrLogado.add(nomeUsrLogado, BorderLayout.NORTH);
        painelUsrLogado.add(cargoUsrLogado, BorderLayout.CENTER);
        add(painelUsrLogado, BorderLayout.NORTH);

        painelBot = new JPanel();
        painelBot.setLayout(new FlowLayout());
        painelBot.setBackground(Color.decode("#adaba3"));
        botLivros = new JButton("Menu de Livros");
        botLivros.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        botLivros.setBackground(Color.decode("#8b8c90"));
        botLivros.setPreferredSize(new Dimension(300, 40));
        botLivros.setFocusable(false);
        botLivros.addActionListener(this);
        botLivros.setActionCommand("livros");
        botEmprestimos = new JButton("Menu de Empréstimos");
        botEmprestimos.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        botEmprestimos.setBackground(Color.decode("#8b8c90"));
        botEmprestimos.setPreferredSize(new Dimension(300, 40));
        botEmprestimos.setFocusable(false);
        botEmprestimos.addActionListener(this);
        botEmprestimos.setActionCommand("emprestimos");
        botUsuarios = new JButton("Menu de Usuários");
        botUsuarios.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        botUsuarios.setBackground(Color.decode("#8b8c90"));
        botUsuarios.setPreferredSize(new Dimension(300, 40));
        botUsuarios.setFocusable(false);
        botUsuarios.addActionListener(this);
        botUsuarios.setActionCommand("usuarios");
        painelBot.add(botLivros);
        painelBot.add(botEmprestimos);
        painelBot.add(botUsuarios);
        add(painelBot, BorderLayout.CENTER);

        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        tornaFuncIndisp();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void tornaFuncIndisp(){
        if(Main.usrLogado.getCargo().equals("usr")){
            botUsuarios.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("livros")) {
            TelaMenuLivro telaMenuLivro = new TelaMenuLivro();
        }
        else if (e.getActionCommand().equals("emprestimos")) {
            TelaMenuEmp telaMenuEmp = new TelaMenuEmp();
        }
        else if (e.getActionCommand().equals("usuarios")) {
            TelaMenuUsr telaMenuUsr = new TelaMenuUsr();
        }
    }
}



