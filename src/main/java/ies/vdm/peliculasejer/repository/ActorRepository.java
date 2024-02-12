package ies.vdm.peliculasejer.repository;

import ies.vdm.peliculasejer.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
