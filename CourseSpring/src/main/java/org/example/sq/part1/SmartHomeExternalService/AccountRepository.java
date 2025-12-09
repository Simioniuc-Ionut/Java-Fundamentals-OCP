package org.example.sq.part1.SmartHomeExternalService;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity,Integer> {
    boolean existsByName(String username);

    @Override
    <S extends AccountEntity> S save(S entity);

}
