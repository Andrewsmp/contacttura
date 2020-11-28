package controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Contactura;
import repository.ContacturaRepository;

@RestController
@RequestMapping({"/contactura"})
public class ContacturaController {

	
	private ContacturaRepository repository;
	
	ContacturaController (ContacturaRepository contacturaRepository){
		this.repository = contacturaRepository;
	}
	
	@GetMapping
	public List findAll() {
		return repository.findAll();
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Contactura create(@RequestBody Contactura contactura) {
		return repository.save(contactura);
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody Contactura contactura) {
		return repository.findById(id)
				.map(record -> {
					record.setName(contactura.getName());
					record.setEmail(contactura.getEmail());
					record.setPhone(contactura.getPhone());
					Contactura update = repository.save(record);
					return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	
	
}
