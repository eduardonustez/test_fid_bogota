package local.test.enr.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import local.test.enr.models.CuentaBancariaModel;


@Repository
public interface AccountRepository extends CrudRepository<CuentaBancariaModel,Long> {

    //public abstract ArrayList<CuentaBancariaModel> findByPrioridad(Integer prioridad);
    
} 
