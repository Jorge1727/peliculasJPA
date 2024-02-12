package ies.vdm.peliculasejer.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    //jointable hijo!!!!!!!!!!!!!!!!
    //Mapped en padre!!!!!!!!!!!!!
    @ManyToMany(mappedBy = "categorias")
    Set<Pelicula> peliculas = new HashSet<>();


}
