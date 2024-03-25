package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override

    public User findUserAndCar(String model, int series) {
        String hql = " From Car car where Car.model = :model and Car.series = :series";
        Car car = sessionFactory.getCurrentSession().createQuery(hql, Car.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .uniqueResult(); //getSingleResult
        return car.getUser();
    }
}
