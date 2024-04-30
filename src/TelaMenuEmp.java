//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//public class TelaMenuEmp extends JFrame implements ActionListener{
//    private JPanel topPanel;
//    private JTextField campoPesq;
//    private JButton botPesq;
//    private JScrollPane resultadoPesq;
//    private JPanel painelBot;
//    private JButton botDev;
//    private JButton botAdEmp;
//    private JButton botExcEmp;
//
//    public TelaMenuEmp(){
//        setLayout(new BorderLayout());
//
//        topPanel = new JPanel();
//        topPanel.setLayout(new FlowLayout());
//        topPanel.setBackground(Color.decode("#adaba3"));
//        campoPesq = new JTextField(20);
//        campoPesq.setMaximumSize(new Dimension(400, 30));
//        campoPesq.setBorder(null);
//        botPesq = new JButton("Pesquisar");
//        botPesq.setFont(new Font("Comic Sans", Font.BOLD, 12));
//        botPesq.setBackground(Color.decode("#8b8c90"));
//        botPesq.setFocusable(false);
//        botPesq.addActionListener(this);
//        botPesq.setActionCommand("pesquisar");
//        topPanel.add(campoPesq);
//        topPanel.add(botPesq);
//        this.add(topPanel, BorderLayout.NORTH);
//
//        resultadoPesq = new JScrollPane();
//        resultadoPesq.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        resultadoPesq.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        resultadoPesq.setOpaque(true);
//        resultadoPesq.setBackground(Color.WHITE);
//        //resultadoPesq.add
//        this.add(resultadoPesq, BorderLayout.CENTER);
//
//        painelBot = new JPanel();
//        painelBot.setLayout(new FlowLayout());
//        painelBot.setBackground(Color.decode("#adaba3"));
//        botDev = new JButton("Realizar devolução");
//        botDev.setFont(new Font("Comic Sans", Font.BOLD, 12));
//        botDev.setBackground(Color.decode("#8b8c90"));
//        botDev.setFocusable(false);
//        botDev.addActionListener(this);
//        botDev.setActionCommand("devolver");
//        botAdEmp = new JButton("Realizar empréstimo");
//        botAdEmp.setFont(new Font("Comic Sans", Font.BOLD, 12));
//        botAdEmp.setBackground(Color.decode("#8b8c90"));
//        botAdEmp.setFocusable(false);
//        botAdEmp.addActionListener(this);
//        botAdEmp.setActionCommand("adicionar");
//        botExcEmp = new JButton("Excluir empréstimo");
//        botExcEmp.setFont(new Font("Comic Sans", Font.BOLD, 12));
//        botExcEmp.setBackground(Color.decode("#8b8c90"));
//        botExcEmp.setFocusable(false);
//        botExcEmp.addActionListener(this);
//        botExcEmp.setActionCommand("excluir");
//        painelBot.add(botDev);
//        painelBot.add(botAdEmp);
//        painelBot.add(botExcEmp);
//        add(painelBot, BorderLayout.SOUTH);
//
//        pack();
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//    //    void tornaFuncIndisp(Usuario UsrLogado){
////
////    }
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getActionCommand().equals("pesquisar")){
//            String busca = campoPesq.getText();
//            List<JPanel> resultadosDaBusca = Main.livroBaseDeDados.peLivroPanel(busca);
//            if(resultadosDaBusca.isEmpty()){
//                JPanel resultados = new JPanel(new GridLayout(0, 1));
//                resultados.setBackground(Color.WHITE);
//                resultados.setOpaque(true);
//                resultadoPesq.setViewportView(resultados);
//                resultadoPesq.revalidate();
//                resultadoPesq.repaint();
//
//                JOptionPane.showMessageDialog(null, "Empréstimo não encontrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
//
//            }
//            else{
//                JPanel resultados = new JPanel(new GridLayout(0, 1));
//                resultados.setBackground(Color.WHITE);
//                resultados.setOpaque(true);
//
//                for (JPanel panel : resultadosDaBusca) {
//                    panel.setBackground(Color.WHITE);
//                    resultados.add(panel);
//                }
//                resultadoPesq.setViewportView(resultados);
//                resultadoPesq.revalidate();
//                resultadoPesq.repaint();
//            }
//        }
//        else if(e.getActionCommand().equals("adicionar")){
//            TelaCadastroLivro telaCadastroLivro = new TelaCadastroLivro();
//        }
//        else if (e.getActionCommand().equals("editar")) {
//            String editString = JOptionPane.showInputDialog("Isbn do livro a ser editado: ");
//            if(Main.livroBaseDeDados.existeLivro(editString)){
//                TelaEditLivro telaEditLivro = new TelaEditLivro(Main.livroBaseDeDados.peLivro(editString));
//            }
//            else{
//                JOptionPane.showMessageDialog(null, "Livro não encontrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
//            }
//        }
//        else if(e.getActionCommand().equals("excluir")){
//            String excString = JOptionPane.showInputDialog("Isbn do livro a ser excluído: ");
//            if(Main.livroBaseDeDados.excLivro(excString)){
//                JOptionPane.showMessageDialog(null, "Livro excluído com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
//            }
//            else{
//                JOptionPane.showMessageDialog(null, "Livro não encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
//            }
//        }
//    }
//}
//
//
