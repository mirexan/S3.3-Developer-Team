package com.itAcademy.agenda.task.service;

import com.itAcademy.agenda.common.exception.InvalidTaskException;
import com.itAcademy.agenda.common.exception.TaskNotFoundException;
import com.itAcademy.agenda.task.dto.TaskOutputDTO;
import com.itAcademy.agenda.task.fakes.FakeTaskRepository;
import com.itAcademy.agenda.task.fakes.TaskFactory;
import com.itAcademy.agenda.task.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Unitary Tests for Task Service")
class TaskServiceUnitaryTest {

	private TaskService taskService;
	private FakeTaskRepository fakeRepository;
	private TaskBuilder builder;

	@BeforeEach
	void setUp() {
		fakeRepository = new FakeTaskRepository();
		builder = new TaskBuilder();
		taskService = new TaskService(fakeRepository, builder);
	}

	@Nested
	@DisplayName("Task Creation")
	class CreationTests {

		@Test
		@DisplayName("Should create and save a task when inputs are valid")
		void shouldCreateAndSaveTask_WhenDataIsValid() {
			String validText = "Buy bread";
			LocalDateTime futureDate = LocalDateTime.now().plusDays(1);
			taskService.createTask(validText, futureDate);
			assertThat(fakeRepository.saveCalled).isTrue();
			assertThat(fakeRepository.getAllTasks())
					.hasSize(1)
					.first().extracting(Task::getText).isEqualTo(validText);
		}

		@Test
		@DisplayName("Should throw an exception when title is null or empty")
		void shouldThrowException_WhenTitleIsInvalid() {
			String emptyTitle = "";
			String blankTitle = "   ";
			LocalDateTime validDate = LocalDateTime.now().plusDays(1);
			assertThatThrownBy(() -> taskService.createTask(null, validDate))
					.isInstanceOf(InvalidTaskException.class)
					.hasMessageContaining("Text can't be empty");
			assertThatThrownBy(() -> taskService.createTask(emptyTitle, validDate))
					.isInstanceOf(InvalidTaskException.class);
			assertThatThrownBy(() -> taskService.createTask(blankTitle, validDate))
					.isInstanceOf(InvalidTaskException.class);
		}

		@Test
		@DisplayName("Should throw an exception if date is before now")
		void shouldThrowException_WhenDateIsPast() {
			String validText = "Invalid task";
			LocalDateTime pastDate = LocalDateTime.now().minusSeconds(1);
			assertThatThrownBy(() -> taskService.createTask(validText, pastDate))
					.isInstanceOf(InvalidTaskException.class)
					.hasMessageContaining("Expiration Date can't be before");
		}
	}

	@Nested
	@DisplayName("List in progress and completed tasks")
	class ListingTests {

		@Test
		@DisplayName("List in progress: only return completed = false tasks ")
		void shouldReturnOnlyPendentTasks() {
			fakeRepository.loadTasks(List.of(
					TaskFactory.createFakeTask(1, "In progress", false),
					TaskFactory.createFakeTask(2, "Completed", true)
			));
			List<TaskOutputDTO> result = taskService.listPendentTasks();
			assertThat(result)
					.hasSize(1)
					.first().extracting(TaskOutputDTO::isCompleted).isEqualTo(false);
		}

		@Test
		@DisplayName("List Completed: Only return completed = true tasks")
		void shouldReturnOnlyCompletedTasks() {
			fakeRepository.loadTasks(List.of(
					TaskFactory.createFakeTask(1, "In progress", false),
					TaskFactory.createFakeTask(2, "Completed", true)
			));
			List<TaskOutputDTO> result = taskService.listCompletedTasks();
			assertThat(result).hasSize(1);
			assertThat(result.get(0).isCompleted()).isTrue();
		}

		@Test
		@DisplayName("If repository is empty, return an empty list (not null)")
		void shouldReturnEmptyList_WhenRepositoryIsEmpty() {
			fakeRepository.clear();
			List<TaskOutputDTO> result = taskService.listPendentTasks();
			assertThat(result).isNotNull().isEmpty();
		}

		@Test
		@DisplayName("Mapping Check: DTO data matches with Entity data")
		void shouldMapEntityToDtoCorrectly() {
			Task entity = TaskFactory.createFakeTask(10, "Mapping Check", false);
			fakeRepository.loadTasks(List.of(entity));
			List<TaskOutputDTO> result = taskService.listPendentTasks();
			TaskOutputDTO dto = result.get(0);
			assertThat(dto.id()).isEqualTo(entity.getId());
			assertThat(dto.title()).isEqualTo(entity.getText());
			assertThat(dto.deadline()).isEqualTo(entity.getExpirationDate());
			assertThat(dto.priority()).isEqualTo(entity.getPriority());
		}
	}

	@Nested
	@DisplayName("Delete Task")
	class DeletionTests {

		@Test
		@DisplayName("Should delete a task if ID exists")
		void shouldDeleteTask_WhenIdExists() {
			int existingId = 5;
			fakeRepository.loadTasks(List.of(
					TaskFactory.createFakeTask(existingId, "To Delete", false)
			));
			taskService.deleteTask(existingId);
			assertThat(fakeRepository.deleteCalled).isTrue();
			assertThat(fakeRepository.getAllTasks()).isEmpty();
		}

		@Test
		@DisplayName("Should throw TaskNotFoundException if ID doesn't exist")
		void shouldThrowException_WhenIdDoesNotExist() {
			int nonExistingId = 99;
			fakeRepository.clear();
			assertThatThrownBy(() -> taskService.deleteTask(nonExistingId))
					.isInstanceOf(TaskNotFoundException.class)
					.hasMessageContaining("not found");
			assertThat(fakeRepository.deleteCalled).isFalse();
		}
	}
}