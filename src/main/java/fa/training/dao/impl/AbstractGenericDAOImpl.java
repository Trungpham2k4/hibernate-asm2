package fa.training.dao.impl;

import fa.training.dao.GenericDAO;
import fa.training.util.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

/**
 * Class abstract chứa toàn bộ logic CRUD chung (Boilerplate code).
 * Các DAO con chỉ việc kế thừa mà không cần viết lại.
 */
public abstract class AbstractGenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    // Lưu trữ Class type của Entity để cung cấp cho Hibernate ở runtime
    private final Class<T> entityClass;

    public AbstractGenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    // Dùng getCurrentSession() thay vì openSession().
    // Hibernate sẽ tự động lấy Session đang được Service quản lý trong Thread này.
    protected Session getSession() {
        return HibernateUtils.getSessionFactory().getCurrentSession();
    }

    @Override
    public T findById(ID id) {
        return getSession().get(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        String hql = "FROM " + entityClass.getSimpleName();
        return getSession().createQuery(hql, entityClass).stream().toList();
    }

    @Override
    public void save(T t) {
        getSession().persist(t); // Không còn try-catch hay Transaction ở đây nữa
    }

    @Override
    public void update(T t) {
        getSession().merge(t);
    }

    @Override
    public void delete(ID id) {
        T entity = getSession().get(entityClass, id);
        if (entity != null) {
            getSession().remove(entity);
        }
    }
}