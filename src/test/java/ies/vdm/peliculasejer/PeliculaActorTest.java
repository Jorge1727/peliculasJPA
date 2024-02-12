package ies.vdm.peliculasejer;

import ies.vdm.peliculasejer.domain.Actor;
import ies.vdm.peliculasejer.domain.Pelicula;
import ies.vdm.peliculasejer.repository.ActorRepository;
import ies.vdm.peliculasejer.repository.PeliculaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

@SpringBootTest

public class PeliculaActorTest {

    @Autowired
    PeliculaRepository peliculaRepository;
    @Autowired
    ActorRepository actorRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void guardarManyToMany(){

        Actor actor1 = new Actor(0, "Actor1", "apellidos1", LocalDate.of(2024,5,12), new HashSet<>());
        actorRepository.save(actor1);
        Actor actor2 = new Actor(0, "Actor2", "apellidos2", LocalDate.of(2024,5,10), new HashSet<>());
        actorRepository.save(actor2);

        //Lo he echo con localdate, sino habria que usar en el date el calendar, ya que me ponia mal el año por la  suma que hace
        // date de 1900 al año que le pongamos. Ejemplo con calendar para el date:
//        Calendar cal = Calendar.getInstance();
//        cal.set(2024, Calendar.FEBRUARY, 12);
//        Date fecha = cal.getTime();
//        Actor actor1 = new Actor(0, "Actor1", "apellidos1", fecha, new HashSet<>());
//        actorRepository.save(actor1);

        Pelicula pelicula3 = new Pelicula(0, "Pelicula3", new HashSet<>(), null, new HashSet<>());
        peliculaRepository.save(pelicula3);

        pelicula3.getActores().add(actor1);
        actor1.getPeliculas().add(pelicula3);
        pelicula3.getActores().add(actor2);
        actor2.getPeliculas().add(pelicula3);

        peliculaRepository.save(pelicula3);
        actorRepository.save(actor1);
        actorRepository.save(actor2);

        Pelicula pelicula4 = new Pelicula(0, "peli4", new HashSet<>(), null, new HashSet<>());
        peliculaRepository.save(pelicula4);

        actor2.getPeliculas().add(pelicula4);
        pelicula4.getActores().add(actor2);

        peliculaRepository.save(pelicula4);
        actorRepository.save(actor2);

    }

}
