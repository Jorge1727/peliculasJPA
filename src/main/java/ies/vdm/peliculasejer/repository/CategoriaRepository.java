package ies.vdm.peliculasejer.repository;

import ies.vdm.peliculasejer.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
