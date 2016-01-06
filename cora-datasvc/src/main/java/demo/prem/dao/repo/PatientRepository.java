package demo.prem.dao.repo;

import demo.prem.model.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends BaseRepository<Patient, Long>{ //extends BaseRepository<Acccount, Long> {
    		//Zero lines
}