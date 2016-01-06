package demo.prem.dao.repo;

import demo.prem.model.Account;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<Account, Long>{ 
    								//Zero lines
}