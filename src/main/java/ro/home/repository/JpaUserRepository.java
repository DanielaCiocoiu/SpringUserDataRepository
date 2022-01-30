package ro.home.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.home.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("jpaUserRepository")
public class JpaUserRepository implements ProductRepository {

    @PersistenceContext // @Autowired e echivalent
    private EntityManager entityManager;

    @Override
    @Transactional
    // echivalent cu entityManager.getTransaction().begin() / commit() / rollback()
    // folosim Transaction din org.springframework.transaction
    public Product save(Product product) {
        entityManager.persist(product);
        return product;
    }
}
