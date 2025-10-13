package com.empregamais.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;



@Entity
@Table(name = "tbl_vagas")
public class Vaga {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "VAGAS_SEQ"
    )
    @SequenceGenerator(
            name = "VAGAS_SEQ",
            sequenceName = "VAGAS_SEQ",
            allocationSize = 1
    )
    private Long id;

    @NotBlank(message = "O título é obrigatório.")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres.")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotBlank(message = "O nome da empresa é obrigatório.")
    private String empresa;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vaga vaga = (Vaga) o;
        return Objects.equals(id, vaga.id) &&
                Objects.equals(titulo, vaga.titulo) &&
                Objects.equals(descricao, vaga.descricao) &&
                Objects.equals(empresa, vaga.empresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, empresa);
    }
}