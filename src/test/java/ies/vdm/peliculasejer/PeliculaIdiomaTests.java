package ies.vdm.peliculasejer;

import ies.vdm.peliculasejer.domain.Idioma;
import ies.vdm.peliculasejer.domain.Pelicula;
import ies.vdm.peliculasejer.repository.IdiomaRepository;
import ies.vdm.peliculasejer.repository.PeliculaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.HashSet;

/**
 * TEST ONETOMANY EAGER
 */
@SpringBootTest
public class PeliculaIdiomaTests {

    @Autowired
    IdiomaRepository idiomaRepository;
    @Autowired
    PeliculaRepository peliculaRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
    @BeforeEach
    public void setUp() {
        transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Test
    @Order(1)
    void grabarMultiplesPeliculasIdioma() {

        Idioma idioma1 = new Idioma(0, "español", new Date(1900, 1, 1), new HashSet<>());
        idiomaRepository.save(idioma1);

        Pelicula pelicula1 = new Pelicula(0, "Pelicula1", new HashSet<>(), idioma1, new HashSet<>());
        idioma1.getPeliculas().add(pelicula1);
        peliculaRepository.save(pelicula1);

        Pelicula pelicula2 = new Pelicula(0, "Pelicula2", new HashSet<>(), idioma1, new HashSet<>());
        idioma1.getPeliculas().add(pelicula2);
        peliculaRepository.save(pelicula2);


//        Idioma idioma2 = new Idioma(0, "inglés", new HashSet<>());
//        idiomaRepository.save(idioma2);
//
//        Pelicula pelicula3 = new Pelicula(0, "Pelicula3", idioma2);
//        idioma2.getPeliculas().add(pelicula3);
//        peliculaRepository.save(pelicula3);

    }

    @Test
    @Order(2)
    void actualizarIdiomaPeliculaNull() {

        Pelicula pelicula1 = peliculaRepository.findById(1L).orElse(null);
        pelicula1.setIdioma(null);
        peliculaRepository.save(pelicula1);

    }

    @Test
    @Order(3)
    void actualizarIdiomaPeliculaAOtroIdioma() {

        Idioma idioma2 = idiomaRepository.findById(2L).orElse(null);
        Pelicula pelicula2 = peliculaRepository.findById(2L).orElse(null);
        pelicula2.setIdioma(idioma2);
        idioma2.getPeliculas().add(pelicula2);

        peliculaRepository.save(pelicula2);

    }

    @Test
    @Order(4)
    void eliminarPelicula() {
        Pelicula pelicula1 = peliculaRepository.findById(1L).orElse(null);
        peliculaRepository.delete(pelicula1);
    }

    @Test
    @Order(5)
    void eliminarPeliculasAsociadasAIdioma() {
        
        Idioma idioma2 = idiomaRepository.findById(2L).orElse(null);

        idioma2.getPeliculas().forEach(pelicula -> {//pelicula.setIdioma(null);
                                                    peliculaRepository.delete(pelicula);
        });
        //idiomaRepository.delete(idioma2);


    }
    @Test
    @Order(6)
    void eliminarPeliculasAsociadasAIdiomaEIdioma() {


        Idioma idioma1 = idiomaRepository.findById(1L).orElse(null);

            idioma1.getPeliculas().forEach(pelicula -> {//pelicula.setIdioma(null);
                peliculaRepository.delete(pelicula);
            });

            //ESTE 2o FIND HAY QUE HACERLO
        idioma1 = idiomaRepository.findById(1L).orElse(null);
        idiomaRepository.delete(idioma1);

    }
}
