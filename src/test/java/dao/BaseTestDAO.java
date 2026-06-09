package dao;

import fa.training.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTestDAO {

    protected Session session;
    protected Transaction transaction;

    @BeforeEach
    public void setUp() {
        // Lấy session và bắt đầu transaction trước MỖI test case
        session = HibernateUtils.getSessionFactory().getCurrentSession();
        transaction = session.beginTransaction();

        // Bạn có thể override hàm này ở các lớp con để setup data mặc định nếu cần
        // nhưng nhớ gọi super.setUp() đầu tiên.
    }

    @AfterEach
    public void tearDown() {
        // Rollback lại toàn bộ transaction sau khi test xong.
        // Đây là cách "Clean Data" hoàn hảo nhất: DB không bị ghi đè dữ liệu rác.
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }

        // Session tự động đóng sau khi transaction kết thúc (vì current_session_context_class = thread)
    }

    @AfterAll
    public static void tearDownAll() {
        // Tắt hẳn Hibernate khi chạy xong TẤT CẢ các test classes
        HibernateUtils.shutdown();
    }
}