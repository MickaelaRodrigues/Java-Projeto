package school.sptech;

public class MusicaInternacional extends Musica {

    private String paisOrigem;
    private String idioma;
    private Boolean possuiTraducao;

    public MusicaInternacional(String titulo, String cantor, String compositor, Double duracao, String descricao, Boolean favorita) {
        super(titulo, cantor, compositor, duracao, descricao, favorita);
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Boolean getPossuiTraducao() {
        return possuiTraducao;
    }

    public void setPossuiTraducao(Boolean possuiTraducao) {
        this.possuiTraducao = possuiTraducao;
    }


}
