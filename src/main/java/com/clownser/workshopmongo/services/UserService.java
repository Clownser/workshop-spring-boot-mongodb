package com.clownser.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clownser.workshopmongo.domain.User;
import com.clownser.workshopmongo.dto.UserDTO;
import com.clownser.workshopmongo.repository.UserRepository;
import com.clownser.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findbyId(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findbyId(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findbyId(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	public User formDTO(UserDTO objDto){
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
}
