package com.spring.tut.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/*
 * Javax validation constraints documentation
 */
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.spring.tut.validators.FormValidationGroup;
import com.spring.tut.validators.PersistenceValidationGroup;

@Entity
@Table(name="Offers")
@Component("offer")
public class Offers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(min = 1, max = 100, message = "Text Field exceeds max size permitted",groups={PersistenceValidationGroup.class,FormValidationGroup.class})
	@Column(name="text")
	private String text;
	@ManyToOne
	@JoinColumn(name="username")
	private User user;

	public Offers() {
		this.user=new User();
	}

	public Offers(int id, String text, User user) {
		this.id = id;
		this.text = text;
		this.setUser(user);
	}

	public Offers(String text, User user) {
		this.text = text;
		this.setUser(user);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername(){
		return user.getUsername();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offers other = (Offers) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Offers [id=" + id + ", text=" + text + ", user=" + user + "]";
	}
}
