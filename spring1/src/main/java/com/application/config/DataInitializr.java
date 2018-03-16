package com.application.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.application.model.User;
import com.application.repository.UserRepository;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
				
		List<User> users = userRepository.findAll();
//CADASTRA UM USUÁRIO SOMENTE SE O BANCO ESTIVER VAZIO
		if (users.isEmpty()) {

			createUser("Rodrigo Vellozo","rodrigovellozosi@gmail.com");
			createUser("Renata Maciel","renata599@gmail.com");
			createUser("Rosimery Vellozo","rosimery.vellozo@hotmail.com");
		}

		User user = userRepository.getOne(2L);
		System.out.println("\n Nome do segundo usuário do bd: "+user.getName()+"\n");
		
		
	}/*ESSE MÉTODO SEMPRE VAI RODAR PRIMEIRO QUANDO SUBIR A APLICAÇÃO*/

	public void createUser(String name, String email) {
		User user = new User(name, email);
		userRepository.save(user);
		
	}
	
}
