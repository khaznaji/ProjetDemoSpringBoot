package tn.projetdemo.demo.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.projetdemo.demo.entities.Role;
import tn.projetdemo.demo.entities.User;
import tn.projetdemo.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserServiceInter {

	@Autowired
	UserRepository userRep;


	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		Role defaultRole = new Role();
		defaultRole.setIdrole(1L);

		Set<Role> roles = new HashSet<>();
		roles.add(defaultRole);
		user.setRole(roles);
		return userRep.save(user);

	}


	@Override
	public List<User> addListUser(List<User> listuser) {
		// TODO Auto-generated method stub
		return userRep.saveAll(listuser);
	}



	@Override
	public String addUserWTUN(User user) {
		// TODO Auto-generated method stub
		String ch = "";
		if (userRep.existsByUsername(user.getUsername())) {

			ch = "username already exists";
		} else {

			userRep.save(user);
			ch = "user added successfully !!!";
		}

		return ch;
	}


	@Override
	public User updateUser(Long id, User user) {
		Optional<User> optionalUser = userRep.findById(id);
		if (optionalUser.isPresent()) {

			return userRep.save(user);
		} else {
			throw new RuntimeException("User with id " + id + " not found");
		}
	}

	@Override
	public void deleteUser(Long iduser) {
		// TODO Auto-generated method stub
		userRep.deleteById(iduser);
	}


	@Override
	public List<User> getListUsers() {
		// TODO Auto-generated method stub
		return userRep.findAll();
	}


	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return userRep.findByUsername(username);
	}


	@Override
	public List<User> getUsersSWSUN(String ch) {
		// TODO Auto-generated method stub
		return userRep.getUsersSWSUN(ch);
	}


	@Override
	public Optional<User> getUserById(Long id) {
		return userRep.findById(id);
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		User user = userRep.findByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}


