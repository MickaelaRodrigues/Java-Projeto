package school.sptech;

public class Musica {

    private String titulo;
    private String cantor;
    private String compositor;
    private Double duracao;
    private String descricao;
    private Boolean favorita;

    public Musica(String titulo, String cantor, String compositor, Double duracao, String descricao, Boolean favorita) {
        this.titulo = titulo;
        this.cantor = cantor;
        this.compositor = compositor;
        this.duracao = duracao;
        this.descricao = descricao;
        this.favorita = favorita;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCantor() {
        return cantor;
    }

    public void setCantor(String cantor) {
        this.cantor = cantor;
    }

    public String getCompositor() {
        return compositor;
    }

    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }

    public Double getDuracao() {
        return duracao;
    }

    public void setDuracao(Double duracao) {
        this.duracao = duracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getFavorita() {
        return favorita;
    }

    public void setFavorita(Boolean favorita) {
        this.favorita = favorita;
    }
}
