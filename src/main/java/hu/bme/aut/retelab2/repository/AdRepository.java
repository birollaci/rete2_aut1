package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.Note;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Repository
public class AdRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Ad save(Ad feedback) {
        return em.merge(feedback);
    }

    public Ad findById(long id) {
        return em.find(Ad.class, id);
    }

    public List<Ad> findAll() {
        return em.createQuery("SELECT n FROM Ad n", Ad.class).getResultList();
    }

    public List<Ad> findByPrice(int min, int max) {
        return em.createQuery("SELECT n FROM Ad n WHERE n.ar >= ?1 AND n.ar <= ?2 ", Ad.class).setParameter(1, min).setParameter(2, max).getResultList();
    }

    public List<Ad> findByTag(String tag) {
        return em.createQuery("SELECT n FROM Ad n WHERE ?1 IN (n.tags) ", Ad.class).setParameter(1, "" + tag).getResultList();
    }

    @Transactional
    public void deleteById(long id) {
        Ad todo = findById(id);
        em.remove(todo);
    }

    // 5. feladat
    @Transactional
    @Scheduled(fixedDelay = 6000)
    public void scheduleFixedDelayTask() {
        LocalDateTime most = LocalDateTime.now();
        List<Ad> res = findAll();
        for (Ad a : res) {
            if(a.getLejarat() != null) {
                if (a.getLejarat().isBefore(most)) {
                    deleteById(a.getId());
                }
            }
        }
    }
}
