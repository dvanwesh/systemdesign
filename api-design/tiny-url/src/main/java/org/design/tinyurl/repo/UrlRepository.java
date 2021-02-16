package org.design.tinyurl.repo;

import org.design.tinyurl.model.Url;
import org.springframework.data.repository.CrudRepository;

//@RepositoryRestResource(exported = false)
public interface UrlRepository extends CrudRepository<Url, String> {
}
