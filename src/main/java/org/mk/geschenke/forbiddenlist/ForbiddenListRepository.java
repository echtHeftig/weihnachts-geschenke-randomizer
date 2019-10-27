package org.mk.geschenke.forbiddenlist;

import org.mk.geschenke.domain.ForbiddenList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface ForbiddenListRepository extends JpaRepository<ForbiddenList, Long> {
}
