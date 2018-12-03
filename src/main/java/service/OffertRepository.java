package service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import model.Offert;

@Repository
public interface OffertRepository extends JpaRepository<Offert, Long> {

}
