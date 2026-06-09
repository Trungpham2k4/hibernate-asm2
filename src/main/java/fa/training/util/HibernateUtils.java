package fa.training.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    // Private constructor để ngăn việc khởi tạo đối tượng bên ngoài
    private HibernateUtils() {}

    /**
     * Holder class này sẽ KHÔNG được tải vào bộ nhớ cho đến khi hàm getSessionFactory() được gọi.
     * JVM đảm bảo việc khởi tạo lớp này là Thread-safe.
     */
    private static class Holder {
        private static final SessionFactory INSTANCE;

        static {
            try {
                Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");
                INSTANCE = configuration.buildSessionFactory();
            } catch (Exception e) {
                throw new ExceptionInInitializerError("Không thể tạo SessionFactory: " + e.getMessage());
            }
        }
    }

    public static SessionFactory getSessionFactory() {
        return Holder.INSTANCE;
    }

    /**
     * Sử dụng synchronized để đảm bảo tại một thời điểm chỉ có 1 thread được quyền đóng SessionFactory,
     * tránh tình trạng Race Condition giữa các thread khi tắt ứng dụng.
     */
    public static synchronized void shutdown() {
        SessionFactory sf = Holder.INSTANCE;
        if (sf != null && !sf.isClosed()) {
            sf.close();
        }
    }
}