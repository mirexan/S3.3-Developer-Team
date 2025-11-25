package com.itAcademy.agenda.task.fakes;

import com.itAcademy.agenda.task.model.Task;
import com.itAcademy.agenda.task.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TEST DOUBLE: Implementa TaskRepository.
 * Sustituye la base de datos (MySQL) con una lista en memoria (storage).
 */
public class FakeTaskRepository extends TaskRepository {
	// El almacenamiento de datos en memoria
	private List<Task> storage = new ArrayList<>();
	// Flags para verificar que los métodos fueron llamados
	public boolean saveCalled = false;
	public boolean deleteCalled = false;

	// Llama al super constructor con null, ya que no usamos el DAO real.
	public FakeTaskRepository() {
		super(null);
	}

	public void loadTasks(List<Task> tasks) {
		this.storage = new ArrayList<>(tasks);
	}

	public void clear() {
		this.storage.clear();
	}

	@Override
	public Optional<Task> getTask(int id) {
		return storage.stream().filter(t -> t.getId() == id).findFirst();
	}

	@Override
	public void createTask(Task task) {
		this.saveCalled = true;
		int newId = this.storage.size() + 1;
		Task taskWithId = TaskFactory.createFakeTask(newId, task.getText(), task.isCompleted());
		storage.add(taskWithId);
	}

	@Override
	public void deleteTask(int id) {
		this.deleteCalled = true;
		storage.removeIf(t -> t.getId() == id);
	}

	@Override
	public List<Task> getAllTasks() {
		return this.storage;
	}

	@Override
	public List<Task> getPendentTasks() {
		return this.storage.stream().filter(t -> !t.isCompleted()).collect(Collectors.toList());
	}

	@Override
	public List<Task> getCompletedTasks() {
		return this.storage.stream().filter(Task::isCompleted).collect(Collectors.toList());
	}
}
