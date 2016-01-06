package demo.prem.dao.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.google.common.base.Optional;

@NoRepositoryBean
interface BaseRepository<T, ID> extends CrudRepository<T, Long> {

	Optional<T> findById(Long id);
}