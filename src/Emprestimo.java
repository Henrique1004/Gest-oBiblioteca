import java.util.Calendar;
import java.util.Date;

public class Emprestimo {
    private Livro livro;
    private Date dataEmp;
    private int qtdeDias;
    private Date dataDev;
    private String nomePessoa;
    private String raPessoa;
    private boolean estadoDev;

    public Emprestimo(Livro livro, Date dataEmp, int qtdeDias, Date dataDev, String nomePessoa, String raPessoa, boolean estadoDev){
        this.livro = livro;
        this.dataEmp = dataEmp;
        this.qtdeDias = qtdeDias;
        this.dataDev = dataDev;
        this.nomePessoa = nomePessoa;
        this.raPessoa = raPessoa;
        this.estadoDev = estadoDev;
    }
//    private boolean verificaQtdeDias(){
//
//    }
    private Date geraDataDev(int qtdeDias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, qtdeDias);
        return calendar.getTime();
    }
    Livro getLivro(){
        return this.livro;
    }
    Date getDataEmp(){
        return this.dataEmp;
    }
    Date getDataDev(){
        return this.dataDev;
    }
    int getQtdeDias(){
        return this.qtdeDias;
    }
    String getNomePessoa(){
        return this.nomePessoa;
    }
    String getRaPessoa(){
        return this.raPessoa;
    }
    boolean getEstadoDev(){
        return this.estadoDev;
    }

}
