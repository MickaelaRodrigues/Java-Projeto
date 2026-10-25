package school.sptech;

public class MusicaNacional extends Musica {

    private String estadoOrigem;
    private String genero;

    public MusicaNacional(String titulo, String cantor, String compositor, Double duracao, String descricao, Boolean favorita, String estadoOrigem, String genero) {
        super(titulo, cantor, compositor, duracao, descricao, favorita);
        this.estadoOrigem = estadoOrigem;
        this.genero = genero;
    }

    public String getEstadoOrigem() {
        return estadoOrigem;
    }

    public void setEstadoOrigem(String estadoOrigem) {
        this.estadoOrigem = estadoOrigem;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
