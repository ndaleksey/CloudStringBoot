package com.sap.hana.cloud.samples.springboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sap.hana.cloud.samples.springboot.model.check.Check;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shishkov A.V. on 08.06.18.
 */
@Entity
@Table(name = "client")
public class Client {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "gender")
	private Gender gender;

	@Column(name = "birthday")
	@JsonFormat(pattern = "dd.MM.yyy")
	private LocalDate birthday;

	@Column(name = "email", nullable = false)
	@Email(message = "Please provide a valid e-mail")
	private String email;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "city")
	private String city;

	@Column(name = "card_id")
	private String cardId;

	@Column(name = "status", columnDefinition = "smallint")
	@Enumerated
	private ClientStatus status;

	@Column(name = "score")
	private double score;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Check.class)
	private Set<Check> checks = new HashSet<>();

	public Client() {
	}

	public Client(String firstName, String lastName, String middleName, Gender gender, LocalDate birthday,
				  @Email(message = "Please provide a valid e-mail") String email, String phone, String city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.gender = gender;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public ClientStatus getStatus() {
		return status;
	}

	public void setStatus(ClientStatus status) {
		this.status = status;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Set<Check> getChecks() {
		return checks;
	}
}
