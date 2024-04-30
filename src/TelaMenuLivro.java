import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaMenuLivro extends JFrame implements ActionListener{
    private JPanel topPanel;
    private JTextField campoPesq;
    private JButton botPesq;
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

        resultadoPesq = new JScrollPane();
        resultadoPesq.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        resultadoPesq.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        resultadoPesq.setOpaque(true);
        resultadoPesq.setBackground(Color.WHITE);
        //resultadoPesq.add
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
//    void tornaFuncIndisp(Usuario UsrLogado){
//
//    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("pesquisar")){
            String busca = campoPesq.getText();
            List<JPanel> resultadosDaBusca = Main.livroBaseDeDados.peLivroPanel(busca);
            if(resultadosDaBusca.isEmpty()){
                JPanel resultados = new JPanel(new GridLayout(0, 1));
                resultados.setBackground(Color.WHITE);
                resultados.setOpaque(true);
                resultadoPesq.setViewportView(resultados);
                resultadoPesq.revalidate();
                resultadoPesq.repaint();

                JOptionPane.showMessageDialog(null, "Livro não encontrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);

            }
            else{
                JPanel resultados = new JPanel(new GridLayout(0, 1));
                resultados.setBackground(Color.WHITE);
                resultados.setOpaque(true);

                for (JPanel panel : resultadosDaBusca) {
                    panel.setBackground(Color.WHITE);
                    resultados.add(panel);
                }
                resultadoPesq.setViewportView(resultados);
                resultadoPesq.revalidate();
                resultadoPesq.repaint();
            }
        }
        else if(e.getActionCommand().equals("adicionar")){
            TelaCadastroLivro telaCadastroLivro = new TelaCadastroLivro();
        }
        else if (e.getActionCommand().equals("editar")) {
            String editString = JOptionPane.showInputDialog("Isbn do livro a ser editado: ");
            if(Main.livroBaseDeDados.existeLivro(editString)){
                TelaEditLivro telaEditLivro = new TelaEditLivro(Main.livroBaseDeDados.peLivro(editString));
            }
            else{
                JOptionPane.showMessageDialog(null, "Livro não encontrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(e.getActionCommand().equals("excluir")){
            String excString = JOptionPane.showInputDialog("Isbn do livro a ser excluído: ");
            if(Main.livroBaseDeDados.excLivro(excString)){
                JOptionPane.showMessageDialog(null, "Livro excluído com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Livro não encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}

