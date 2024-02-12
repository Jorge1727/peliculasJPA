package ies.vdm.peliculasejer.repository;

import ies.vdm.peliculasejer.domain.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
}
