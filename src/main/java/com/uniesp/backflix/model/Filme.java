package com.uniesp.backflix.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data   // importando a notacion data através do lombok.
@NoArgsConstructor //construtor sem argumentos através do lombok.
@AllArgsConstructor //construtor com argumentos através do lombok.
@Entity //mapeando a entidade do banco de dados utilizando o entity.
@Table(name = "filmes") //indentificando a tabela do banco de dados.
public class Filme {

    @Id //identificando o id da tabela no banco.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ultiliza o id de forma incremental.
    private Long id;

    @Column(nullable = false, length = 100) //utiliza o column para definir os atributos das tabelas.
    private String titulo;

    @Column(columnDefinition = "TEXT")  //utiliza o column para definir os atributos das tabelas.
    private String sinopse;

    @Column(name = "data_lancamento")  //utiliza o column para definir os atributos das tabelas.
    private LocalDate dataLancamento;

    @Column(length = 50)  //utiliza o column para definir os atributos das tabelas.
    private String genero;

    @Column(name = "duracao_minutos")  //utiliza o column para definir os atributos das tabelas.
    private Integer duracaoMinutos;

    @Column(name = "classificacao_indicativa", length = 10)  //utiliza o column para definir os atributos das tabelas.
    private String classificacaoIndicativa;
}
