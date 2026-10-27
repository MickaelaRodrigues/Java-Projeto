package school.sptech;

import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.Exceptions.DadoInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Musica {

    private String titulo;
    private String cantor;
    private String compositor;
    private Double duracao;
    private String descricao;
    private Boolean favorita;


    public Musica(String titulo, String cantor, String compositor, Double duracao, String descricao, Boolean favorita) {


        if (titulo == null ||titulo.isBlank()){
            throw new DadoInvalidoException("Titulo inválido");
        }

        if (cantor == null ||cantor.isBlank()){
            throw new DadoInvalidoException("Cantor inválido");
        }
        if (compositor == null ||compositor.isBlank()){
            throw new DadoInvalidoException("Compositor inválido");
        }
        if (duracao == null ||duracao<=0 ){
            throw new DadoInvalidoException("Duração inválido");
        }
        if (favorita == null ){
            throw new DadoInvalidoException("Favorita inválido");
        }
        if (descricao == null|| descricao.isBlank()){
            throw new DadoInvalidoException("Duração inválido");
        }

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

    public void cadastrarMusica(Musica musica){

        if (musica == null){
            throw new DadoInvalidoException("Musica inválida");
        }


        DBConnectionProvider db = new DBConnectionProvider();

        JdbcTemplate conexao = db.getConnection();

        String sql = """
            INSERT INTO musica
            (titulo, duracao, cantor, compositor, descricao, favorita)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        conexao.update(
                sql,
                musica.getTitulo(),
                musica.getDuracao(),
                musica.getCantor(),
                musica.getCompositor(),
                musica.getDescricao(),
                musica.getFavorita()
        );

        System.out.println("Musica Cadastrada com sucesso");


    }

    public List<Musica> listarMusicas() {

        DBConnectionProvider db = new DBConnectionProvider();
        JdbcTemplate conexao = db.getConnection();

        List<Musica> musicas = new ArrayList<>();

        String sql = """
            SELECT titulo, duracao, cantor, compositor, descricao, favorita,
                   estadoOrigem, genero, paisOrigem, idioma, possuiTraducao
            FROM musica
            """;

        conexao.query(sql, (musica, rowNum) -> {

            String titulo = musica.getString("titulo");
            String cantor = musica.getString("cantor");
            String compositor = musica.getString("compositor");
            Double duracao = musica.getDouble("duracao");
            String descricao = musica.getString("descricao");
            Boolean favorita = musica.getBoolean("favorita");

            String estadoOrigem = musica.getString("estadoOrigem");
            String genero = musica.getString("genero");

            String paisOrigem = musica.getString("paisOrigem");
            String idioma = musica.getString("idioma");
            Boolean possuiTraducao = musica.getBoolean("possuiTraducao");

            if (paisOrigem != null) {

                MusicaInternacional internacional = new MusicaInternacional(
                        titulo, cantor, compositor, duracao, descricao,
                        favorita, paisOrigem, idioma, possuiTraducao
                );

                musicas.add(internacional);

            } else if (estadoOrigem != null) {

                MusicaNacional nacional = new MusicaNacional(
                        titulo, cantor, compositor, duracao, descricao,
                        favorita, estadoOrigem, genero
                );

                musicas.add(nacional);

            } else {

                Musica comum = new Musica(
                        titulo, cantor, compositor, duracao, descricao, favorita
                );

                musicas.add(comum);
            }

            return null;
        });

        return musicas;
    }


    @Override
    public String toString() {
        return "Musica{" +
                "titulo='" + titulo + '\'' +
                ", cantor='" + cantor + '\'' +
                ", compositor='" + compositor + '\'' +
                ", duracao=" + duracao +
                ", descricao='" + descricao + '\'' +
                ", favorita=" + favorita +
                '}';
    }
}
