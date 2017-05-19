package net.java.cargotracker.infrastructure.persistence.jpa;


import net.java.cargotracker.domain.model.flight.Flight;
import net.java.cargotracker.domain.model.flight.FlightRepo;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class JpaFlightRepository implements FlightRepo, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(
            JpaFlightRepository.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Flight find(int number) {
        Flight flight;
        try {
            flight = entityManager.createNamedQuery("Flight.findByFlightNumber", Flight.class)
                    .setParameter("number", number)
                    .getSingleResult();
        } catch (NoResultException e) {
            logger.log(Level.FINE, "JPAflightRepo.Find called on non-existant flight number.", e);
            flight = null;
        }
        return flight;
    }

    @Override
    public void add(Flight flight) { entityManager.persist(flight); }

    @Override
    public void cancel(Flight flight) { entityManager.remove(flight); }

    @Override
    public List<Flight> findAll() {
        return entityManager.createNamedQuery("Flight.findAll", Flight.class)
                .getResultList();
    }

    @Override
    public List<Integer> getAllFlightIds() {
        //Query query = entityManager.createQuery("SELECT id FROM Cargo id");
        //List<TrackingId> listId = query.getResultList();

        List<Integer> listId = null; // = query.getResultList();
        /*
        try {
            listId = entityManager.createNamedQuery("Cargo.getAllTrackingId",
                    TrackingId.class)
                    .getResultList();
        } catch (NoResultException e) {
            logger.log(Level.FINE, "Unable to get all tracking ID", e);
            listId = null;
        }*/

        return listId;
    }

}
