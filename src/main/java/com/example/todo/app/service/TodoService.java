package com.example.todo.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.app.model.ToDo;
import com.example.todo.app.repo.IToDoRepo;

@Service
public class TodoService {
	@Autowired
	private IToDoRepo repo;
	public List<ToDo>getAllTodoItems(){
		ArrayList<ToDo>todoList=new ArrayList<>();
		repo.findAll().forEach(todo->todoList.add(todo));
		return todoList;
	}
	public ToDo getTodoItemById(Long id) {
		return repo.findById(id).get();
	}
	public boolean updateStatus(Long id) {
		ToDo todo=getTodoItemById(id);
		todo.setStatus("Completed");
		return saveOrUpdateToDoItem(todo);
	}
	public boolean saveOrUpdateToDoItem(ToDo todo) {
		ToDo updatedObj=repo.save(todo);
		if(getTodoItemById(updatedObj.getId())!=null)
		{
			return true;
		}
		return false;
	}
	public boolean deleteTodoItem(Long id) {
		repo.deleteById(id);
		if(getTodoItemById(id)==null)
		{
			return true;
		}
		return false;
	}
}
