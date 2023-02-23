package com.aptech.student1328430.services;

import com.aptech.student1328430.models.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
@ApplicationScoped
public class ProductBean {
    private final EntityManager entityManager;

    public ProductBean() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        entityManager = emf.createEntityManager();
    }
    public List<Product> findAll(){
        return entityManager.createQuery("select p from Product p", Product.class).getResultList();
    }
    public boolean deleteProduct(int id){
        try {
            entityManager.getTransaction().begin();
            Product product = entityManager.find(Product.class, id);
            entityManager.remove(product);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
