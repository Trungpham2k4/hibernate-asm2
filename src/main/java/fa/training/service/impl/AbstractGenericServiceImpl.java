package fa.training.service.impl;

import fa.training.dao.GenericDAO;
import fa.training.exception.DatabaseOperationException;
import fa.training.service.GenericService;
import fa.training.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractGenericServiceImpl<T, ID> implements GenericService<T, ID> {
    protected final GenericDAO<T, ID> genericDAO;

    public AbstractGenericServiceImpl(GenericDAO<T, ID> genericDAO) {
        this.genericDAO = genericDAO;
    }

    // Hàm bọc cho các thao tác có trả về kết quả (Read)
    protected <R> R executeWithTransaction(Supplier<R> operation) {
        Transaction tx = null;
        try {
            Session session = HibernateUtils.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            R result = operation.get(); // Kích hoạt DAO chạy ở đây
            tx.commit();
            return result;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new DatabaseOperationException("Error when use database operation", e);
        }
    }

    // Hàm bọc cho các thao tác không trả về kết quả (Insert, Update, Delete)
    protected void executeWithTransactionVoid(Runnable operation) {
        executeWithTransaction(() -> {
            operation.run();
            return null;
        });
    }

    @Override
    public T findById(ID id) {
        return executeWithTransaction(() -> genericDAO.findById(id));
    }

    @Override
    public List<T> findAll() {
        return executeWithTransaction(genericDAO::findAll);
    }

    @Override
    public void save(T t) {
        executeWithTransactionVoid(() -> genericDAO.save(t));
    }

    @Override
    public void update(T t) {
        executeWithTransactionVoid(() -> genericDAO.update(t));
    }

    @Override
    public void delete(ID id) {
        executeWithTransactionVoid(() -> genericDAO.delete(id));
    }
}