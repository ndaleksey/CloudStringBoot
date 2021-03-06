package com.sap.hana.cloud.samples.springboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Calendar;

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
	private Calendar birthday;

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
	private ClientStatus clientStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
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

	public Client() {
	}

	public Client(String firstName, String lastName, String middleName, Gender gender, Calendar birthday,
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
}
