package com.example.todo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo.app.model.ToDo;
import com.example.todo.app.service.TodoService;



@Controller
public class ToDoController {
	@Autowired
	private TodoService service;
	
	@GetMapping({"/","viewToDoList"})
	public String viewAllToDoItems(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("list",service.getAllTodoItems());
		model.addAttribute("msg",message);
		return "ViewToDoList";
	}
	
	@PostMapping("/updateToDoStatus/id")
	public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if(service.updateStatus(id)) {
			redirectAttributes.addFlashAttribute("message","UpdateSuccess");
			return "redirect:/viewToDoList";
		}
		redirectAttributes.addFlashAttribute("messgae","Update Failure");
		return "redirect:/viewToDoList";
	}
	
	@GetMapping("/addToDoItem")
	public String addToDoItem(Model model) {
		model.addAttribute("todo", new ToDo());
		return "AddToDoItem";
	}
	
	@PostMapping("/saveToDoItem")
	public String saveToDoItem(ToDo todo, RedirectAttributes redirectAttributes) {
		if(service.saveOrUpdateToDoItem(todo)) {
			redirectAttributes.addFlashAttribute("message","Save Success");
			return "redirect:/viewToDoList";
		}
		redirectAttributes.addFlashAttribute("messgae","Save Failure");
		return "redirect:/addToDoItem";
	}
	
	@GetMapping("/editToDoItem/{id}")
	public String editToDoItem(@PathVariable Long id,Model model) {	
		model.addAttribute("todo",service.getTodoItemById(id));
		return "EditToDoItem";
	}
	
	@PostMapping("/editSaveToDoItem")
	public String editSaveToDoItem( ToDo todo,RedirectAttributes redirectAttributes) {
		if(service.saveOrUpdateToDoItem(todo)) {
			redirectAttributes.addFlashAttribute("message","Edit Success");
			return "redirect:/viewToDoList";
		}
		redirectAttributes.addFlashAttribute("messgae","Edit Failure");
		return "redirect:/editToDoItem/"+todo.getId();
	}
	
	@DeleteMapping("/deleteToDoItem/id")
	public String deleteToDoItem(@PathVariable Long id,RedirectAttributes redirectAttributes) {
		if(service.deleteTodoItem(id)) {
			redirectAttributes.addFlashAttribute("message","Delete Success");
			return "redirect:/viewToDoList";
		}
		redirectAttributes.addFlashAttribute("messgae","Delete Failure");
		return "redirect:/viewToDoList";
	}
}

