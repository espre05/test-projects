package demo.prem.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUST_ACCOUNTS")
public class Account {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	//private Account parent;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	
	@Column(name = "type")
	private AccountType accountType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

}
