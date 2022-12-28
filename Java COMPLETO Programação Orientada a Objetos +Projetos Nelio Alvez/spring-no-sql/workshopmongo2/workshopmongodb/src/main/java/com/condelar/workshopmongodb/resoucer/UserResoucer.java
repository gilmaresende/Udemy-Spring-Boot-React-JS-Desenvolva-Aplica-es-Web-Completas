package com.condelar.workshopmongodb.resoucer;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.condelar.workshopmongodb.domain.Post;
import com.condelar.workshopmongodb.domain.User;
import com.condelar.workshopmongodb.dto.UserDTO;
import com.condelar.workshopmongodb.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResoucer {

	@Autowired
	UserService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> listDto = service.findAll().stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> finfById(@PathVariable String id) {
		User obj = service.findById(id);
		UserDTO tdo = new UserDTO(obj);
		return ResponseEntity.ok().body(tdo);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto) {
		User obj = service.fromDTO(dto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO dto, @PathVariable String id) {
		User obj = service.fromDTO(dto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}/post", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> finPost(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPost());
	}

}
