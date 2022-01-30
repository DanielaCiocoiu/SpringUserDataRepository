package ro.home.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ro.home.model.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("productRepositoryImpl")
@Primary
public interface ProductRepositoryImpl extends ProductRepository, JpaRepository<Product, Long> {

    @Override
    Product save(Product product);

    // conventie
    // cand metoda incepe cu findBy, trebuie sa returnam un Optional (ne asteptam ca obiectul sa nu existe)
    // cand metoda incepe cu getBy, returnam direct entitate (e o eroare daca nu exista)

    // @Query - definim un JPQL care va fi executat la apelul metodeo
    // @Param - specificam cum se numeste placeholderul din JPQL care va fi inlocuit de valoarea data prin parametru
    @Query("SELECT p FROM Product p WHERE p.name = :name_placeholder")
    Optional<Product> findProductByName(@Param("name_placeholder") String name); //returneaza un produs dupa nume

    // Query method - o metoda prin care query-ul este generat de numele metodei
    // generaza: SELECT u FROM User u WHERE u.id IN (:ids)

    @Query("SELECT p FROM Product p WHERE p.id = :id_placeholder")  //ret un produs dupa id
    Optional<Product> findProductById(@Param("id_placeholder") long id);

    @Query("SELECT p FROM Product p WHERE p.price = (SELECT max(p.price) FROM Product p )") // returneata produsul cel mai scump
    List<Product> findProductByBiggestPrice();

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceMin AND :priceMax")
    List<Product> findProductsByPriceRange(@Param("priceMin") int min, @Param("priceMax") int max);  //retunreaza produsele aflate intr-o gama de pret data

     @Query("SELECT p FROM Product p WHERE month(p.expiryDate) = month(current_date)")
     List<Product> findProductsByExpiryDate();  //returneaza produsele care expira in luna curenta

    @Query("SELECT p FROM Product p WHERE p.expiryDate < :expiryDate_placeholder")
    List<Product> findProductsByOutOfDate(@Param("expiryDate_placeholder") LocalDate expiryDate); //returneaza toate produsele expirate
}
