package ies.vdm.peliculasejer;

import ies.vdm.peliculasejer.domain.Categoria;
import ies.vdm.peliculasejer.domain.Pelicula;
import ies.vdm.peliculasejer.repository.CategoriaRepository;
import ies.vdm.peliculasejer.repository.PeliculaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

@SpringBootTest
class PeliculaCategoriaTests {

    @Autowired
    PeliculaRepository peliculaRepository;
    @Autowired
    CategoriaRepository categoriaRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void guardarMAnyToMany(){

        Pelicula peli = new Pelicula(0, "Peli1",new HashSet<>(),null, new HashSet<>());
        peliculaRepository.save(peli);

        Categoria categoria1 = new Categoria(0, "Categoria1", new HashSet<>());
        categoriaRepository.save(categoria1);

        Categoria categoria2 = new Categoria(0, "Categoria2", new HashSet<>());
        categoriaRepository.save(categoria2);

        peli.getCategorias().add(categoria1);
        categoria1.getPeliculas().add(peli);
        peli.getCategorias().add(categoria2);
        categoria2.getPeliculas().add(peli);

        peliculaRepository.save(peli);
        categoriaRepository.save(categoria1);
        categoriaRepository.save(categoria2);

    }



}
