package com.example.todo.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.app.model.ToDo;

@Repository
public interface IToDoRepo extends JpaRepository<ToDo,Long>{

}
