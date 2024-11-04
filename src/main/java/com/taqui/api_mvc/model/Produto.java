package com.taqui.api_mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity

@Table(name = "TAQUI_PRODUTO")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(
            generator = "geradorIds",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "geradorIds",
            sequenceName = "sq_tb_produto",
            allocationSize = 1)
    @Column(name = "pk_id_produto")
    private Long id;

    @Column(name = "nm_exibicao", nullable = false)
    private String nome;

    @Column(name = "ds_descricao",  nullable = false)
    private String descricao;

    @Column(name = "nr_preco", nullable = false)
    private float preco;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pk_id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuario usuario;
}
