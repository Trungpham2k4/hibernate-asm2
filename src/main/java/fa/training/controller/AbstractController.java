package fa.training.controller;

import fa.training.service.GenericService;
import fa.training.view.AbstractView;
import fa.training.util.InputHandler;

public abstract class AbstractController<T, ID> {

    protected final GenericService<T, ID> service;
    protected final AbstractView<T> view;

    public AbstractController(GenericService<T, ID> service, AbstractView<T> view) {
        this.service = service;
        this.view = view;
    }

    public void handleMenu() {
        view.showMenu();
        int choice = InputHandler.inputValidOption(1, 5);
        switch (choice) {
            case 1 -> add();
            case 2 -> update();
            case 3 -> delete();
            case 4 -> findAll();
            case 5 -> findById();
        }
    }

    // Yêu cầu lớp con định nghĩa cách hỏi ID (vì kiểu ID có thể là Integer, String...)
    protected abstract ID inputId(String prompt);

    protected void beforeSave(T entity) {}

    public void add() {
        try {
            view.showMessage("Adding new record...");
            T entity = view.inputForAdd();
            service.save(entity);
            view.showMessage("Added successfully!");
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }

    public void update() {
        try {
            view.showMessage("Updating record...");
            ID id = inputId("Enter ID to update: ");
            T existing = service.findById(id); // KHẮC PHỤC LỖI 1: Fetch dữ liệu cũ trước

            if (existing == null) {
                view.showMessage("Record with ID " + id + " not found.");
                return;
            }

            view.inputForUpdate(existing); // Áp dụng thay đổi trực tiếp lên đối tượng cũ
            service.update(existing);
            view.showMessage("Updated successfully!");
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }

    public void delete() {
        try {
            view.showMessage("Deleting record...");
            ID id = inputId("Enter ID to delete: ");
            service.delete(id);
            view.showMessage("Deleted successfully!");
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }

    public void findById() {
        view.showMessage("Finding record by ID...");
        ID id = inputId("Enter ID to find: ");
        T entity = service.findById(id);
        view.showSingle(entity);
    }

    public void findAll() {
        view.showMessage("Fetching all records...");
        view.showList(service.findAll());
    }
}