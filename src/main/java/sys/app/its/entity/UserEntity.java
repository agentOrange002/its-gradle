package sys.app.its.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1441957085019368L;
	
	@Id
	@SequenceGenerator(name = "user_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	private Long id;
	
	@Column(name="user_id",nullable = false)
	private String userId;
	
	@Column(name="first_name",nullable = false, length = 50)
	private String firstName;
	
	@Column(name="middle_name",nullable = false, length = 50)
	private String middleName;
	
	@Column(name="last_name",nullable = false, length = 50)
	private String lastName;
	
	@Column(name="suffix_name", length = 50)
	private String suffixName;
	
	@Column(name="full_name",nullable = false, length = 150, unique = true)
	private String fullName;

	@Column(name="email",nullable = false, length = 150, unique = true)
	private String email;

	@Column(name="encrypted_password",nullable = false)
	private String encryptedPassword;

	@Column(name="email_verification_token",length=300)
	private String emailVerificationToken;

	@Column(name="email_verification_status",nullable = false, columnDefinition = "boolean default false")
	private Boolean emailVerificationStatus;

	@JsonManagedReference
	@OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
	private List<UserAddressEntity> addresses;		
	
	@JsonManagedReference
	@OneToOne(mappedBy = "userImageDetails", cascade = CascadeType.ALL)
	private UserImageEntity userImage;
	 
}
