package service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	private @Id @GeneratedValue @Column(name = "Id", nullable = false) Long id;
	
	@Column(name = "Name", length = 64, nullable = false)
	private String name;
	
	@Column(name = "Role", length = 64, nullable = false)
	private String role;
	
	protected Employee() {}

	public Employee(String name, String role) {
		this.name = name;
		this.role = role;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	
	public String toString() {
		return String.format(
				"Employee[id=%d, Name='%s', Role='%s']",
				id, name, role);
	}
	


}
