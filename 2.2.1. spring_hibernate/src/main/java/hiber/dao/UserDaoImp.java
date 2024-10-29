package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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
   public List<User> getListUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(
              "select distinct u from User u join fetch u.car",
              User.class);
      return query.getResultList();
   }

   @Override
   public List<User> findBySeriesAndModel(int series, String model) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
              "select u from User u join fetch u.car c where c.model = :model and c.series = :series",
                  User.class);
      query.setParameter("series",series);
      query.setParameter("model",model);
      return query.getResultList();
   }

}
