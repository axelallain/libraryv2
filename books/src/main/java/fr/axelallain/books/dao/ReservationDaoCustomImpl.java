package fr.axelallain.books.dao;

import fr.axelallain.books.model.Reservation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ReservationDaoCustomImpl implements ReservationDaoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Reservation> findByBookIdOrderByCreationDateDesc(Long id) {
        Query query = entityManager.createQuery("SELECT r FROM Reservation r WHERE r.book.id=:id ORDER BY r.creationDate DESC").setParameter("id", id);

        return (List<Reservation>) query.getResultList();
    }

    @Override
    public List<Reservation> findByTokenuserid(String tokenuserid) {
        Query query = entityManager.createQuery("SELECT r FROM Reservation r WHERE r.tokenuserid=:tokenuserid ORDER BY r.creationDate DESC").setParameter("tokenuserid", tokenuserid);

        return (List<Reservation>) query.getResultList();
    }

    @Override
    public Reservation findById(Long id) {
        return entityManager.find(Reservation.class, id);
    }
}
