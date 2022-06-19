package com.example.demo.repos;


import com.example.demo.basicModels.Strumento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StrumentoRepo extends CrudRepository<Strumento, Long> {


}
