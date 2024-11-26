package tn.projetdemo.demo.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	private  String firstname;
	private String lastname;
	private String email;
	private String username;
	private String password;
	private String address;




	@JsonIgnore
	@ManyToMany
	@JoinTable(name="userrole",joinColumns = @JoinColumn(name="id"), inverseJoinColumns = @JoinColumn(name="idrole"))
	private Set<Role> role  = new HashSet<>();



@JsonIgnore

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Post> posts = new HashSet<>();

}
