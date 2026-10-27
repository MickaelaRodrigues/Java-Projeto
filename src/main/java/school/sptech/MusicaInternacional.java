package school.sptech;

import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.Exceptions.DadoInvalidoException;

import java.util.List;

public class MusicaInternacional extends Musica {

    private String paisOrigem;
    private String idioma;
    private Boolean possuiTraducao;

    public MusicaInternacional(String titulo, String cantor, String compositor, Double duracao, String descricao, Boolean favorita, String paisOrigem, String idioma, Boolean possuiTraducao) {
        super(titulo, cantor, compositor, duracao, descricao, favorita);

        if (paisOrigem == null || paisOrigem.isBlank()){
            throw new DadoInvalidoException("País de Origem inválido");
        }

        if (idioma == null || idioma.isBlank()){
            throw new DadoInvalidoException("Idioma inválido");
        }
        if (possuiTraducao == null ){
            throw new DadoInvalidoException("Possui Tradução inválido");
        }

        this.paisOrigem = paisOrigem;
        this.idioma = idioma;
        this.possuiTraducao = possuiTraducao;
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

    @Override
    public void cadastrarMusica(Musica musica) {


            if (musica == null){
                throw new DadoInvalidoException("Musica inválida");
            }


            DBConnectionProvider db = new DBConnectionProvider();

            JdbcTemplate conexao = db.getConnection();

            String sql = """
            INSERT INTO musica
            (titulo, duracao, cantor, compositor, descricao, favorita, paisOrigem, idioma, possuiTraducao)
            VALUES (?, ?, ?, ?, ?, ?,?,?,?)
            """;

            conexao.update(
                    sql,
                    musica.getTitulo(),
                    musica.getDuracao(),
                    musica.getCantor(),
                    musica.getCompositor(),
                    musica.getDescricao(),
                    musica.getFavorita(),
                    getPaisOrigem(),
                    getIdioma(),
                    getPossuiTraducao()
            );

            System.out.println("Musica Internacional cadastrada com sucesso");


        }

    @Override
    public List<Musica> listarMusicas() {
        DBConnectionProvider db = new DBConnectionProvider();

        JdbcTemplate conexao = db.getConnection();

        String sql = """
                SELECT titulo, duracao, cantor, compositor, descricao, favorita
                FROM musica
                """;

        return conexao.query(sql, (musica, rowNum) -> new Musica(
                musica.getString("titulo"),
                musica.getString("cantor"),
                musica.getString("compositor"),
                musica.getDouble("duracao"),
                musica.getString("descricao"),
                musica.getBoolean("favorita")
        ));    }
}
