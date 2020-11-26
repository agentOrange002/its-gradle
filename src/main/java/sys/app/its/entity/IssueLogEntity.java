package sys.app.its.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "issue_logs")
public class IssueLogEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1434181722410335323L;
	
	@Id
	@SequenceGenerator(name="issuelogs_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issuelogs_seq")
	private Long id;
	
	@Column(name="issuelog_id",nullable=false)
	private String issueLogId;
	
	@Column(name="issue_message",nullable=false)
	private String issueMessage;
	
	@Column(name="log_date",nullable=false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date logDate;	
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity issueLogUserDetails;	
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="issue_id")
	private IssueEntity issueDetails;

}
