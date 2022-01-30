package ro.home;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.home.repository.ProductRepository;
import ro.home.config.MainConfig;
import ro.home.model.Product;
import ro.home.repository.ProductRepositoryImpl;

import java.time.LocalDate;

public class Main {

           /*
        Exercitiu:
         Intr-un proiect nou, creaza o entitate Produs care are nume, pret, si data expirare.
         Configureaza EntityManagerFactory, TransactionManager si SpringDataJpaRepositories.
         Creaza un repository (Spring Data Repo) care implementeaza urmatoarele query-uri:
          - returneaza un produs dupa id (vezi JpaRepository#findById)
          - returneaza un produs dupa nume

          - returneata produsul cel mai scump (mai greu *)
          - retunreaza produsele aflate intr-o gama de pret data
          - returneaza produsele care expira in luna curenta
          - returneaza toate produsele expirate
         */

    public static void main(String[] args) {
        var context =  new AnnotationConfigApplicationContext(MainConfig.class);
        var productRepository = context.getBean(ProductRepository.class);
        var product = new Product();

        product.setName("milk");
        product.setPrice(15);
        product.setExpiryDate(LocalDate.of(2022, 1, 31));
        product = productRepository.save(product);
        System.out.println(product);

        var product1 = new Product();
        product1.setName("oil");
        product1.setPrice(60);
        product1.setExpiryDate(LocalDate.of(2022, 2, 8));
        product1 = productRepository.save(product1);
        System.out.println(product1);

        var product2 = new Product();
        product2.setName("sugar");
        product2.setPrice(5);
        product2.setExpiryDate(LocalDate.of(2021, 3, 8));
        product2 = productRepository.save(product2);
        System.out.println(product2);

        var product3 = new Product();
        product3.setName("wine");
        product3.setPrice(15);
        product3.setExpiryDate(LocalDate.of(2021, 3, 8));
        product3 = productRepository.save(product3);
        System.out.println(product3);

        var productRepositoryImpl = context.getBean(ProductRepositoryImpl.class);

        System.out.println( " =====================  ");
        System.out.println("findProductByName: " + productRepositoryImpl.findProductByName("milk"));
        System.out.println("findProductByName: " + productRepositoryImpl.findProductByName("oil"));
        System.out.println("findProductByName: " + productRepositoryImpl.findProductByName("sugar"));
        System.out.println( " =====================  ");

        System.out.println("findProductById: " + productRepositoryImpl.findProductById(35));
        System.out.println("findProductByIdIn: " + productRepositoryImpl.findById(35L));

        System.out.println( " =====================  ");

        System.out.println("findProductByBiggestPrice: " + productRepositoryImpl.findProductByBiggestPrice());

        System.out.println( " =====================  ");

        System.out.println("findProductsByPriceRange: " + productRepositoryImpl.findProductsByPriceRange(5, 60));

        System.out.println( " =====================  ");
        System.out.println("findProductsByExpiryDate: " + productRepositoryImpl.findProductsByExpiryDate());

        System.out.println( " =====================  ");
        System.out.println("findProductsByOutOfDate: " + productRepositoryImpl.findProductsByOutOfDate(LocalDate.of(2022, 1, 31)));


    }
}
