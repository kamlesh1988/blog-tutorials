package de.rieckpil.tutorials;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.querydsl.core.annotations.QueryEntity;

import lombok.Data;

@Data
@Entity
@QueryEntity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstname;

	private String lastname;

	private Instant dob;

	private Integer budget;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "personId")
  private Address address;
}
