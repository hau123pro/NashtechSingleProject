package repository.format;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Format;
@Repository
public interface IFormatRepository extends JpaRepository<Format, Integer>{

}
