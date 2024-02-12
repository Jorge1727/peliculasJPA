package ies.vdm.peliculasejer.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pelicula {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;

    @ManyToMany
    private Set<Categoria> categorias = new HashSet<>();

    @ManyToOne
    @ToString.Exclude
    private Idioma idioma;

    @ManyToMany
    private Set<Actor> actores = new HashSet<>();

}
