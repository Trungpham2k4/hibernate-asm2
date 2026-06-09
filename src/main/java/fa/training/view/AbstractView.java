package fa.training.view;

import java.util.List;

public abstract class AbstractView<T> {

    public abstract void showMenu();

    // Abstract methods để View con tự định nghĩa cách nhập liệu
    public abstract T inputForAdd();
    public abstract void inputForUpdate(T existingEntity);

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showList(List<T> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("No records found.");
        } else {
            list.forEach(System.out::println);
        }
    }

    public void showSingle(T entity) {
        if (entity != null) {
            System.out.println("Detail: " + entity);
        } else {
            System.out.println("Entity not found!");
        }
    }
}