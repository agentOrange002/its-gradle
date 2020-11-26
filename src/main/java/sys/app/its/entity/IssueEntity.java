package sys.app.its.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sys.app.its.generator.IDGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "issues")
public class IssueEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3292366992910502585L;
	

	@Id
	@SequenceGenerator(name="issue_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issue_seq")
    @GenericGenerator(
        name = "issue_seq", 
        strategy = "sys.app.its.generator.IDGenerator", 
        parameters = {
            @Parameter(name = IDGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = IDGenerator.VALUE_PREFIX_PARAMETER, value = "IID"),
            @Parameter(name = IDGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;
	
	@Column(name="issue_id",nullable=false)
	private String issueId;	
	
	@Column(name="subject",nullable=false)
	private String subject;
	
	@Column(name="description",nullable=false)
	private String description;
	
	@Column(name="reported_by",nullable=false)
	private String reportedBy;
	
	@Column(name="email_provided",nullable=false)
	private String emailProvided;
	
	@Column(name="date_reported",nullable=false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateReported;	
	
	@Column(name="date_opened")
	private Date dateOpened;
	
	@Column(name="date_closed")
	private Date dateClosed;
	
	@Column(name="issue_status",nullable=false)
	private String issueStatus;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="category_id")	
	private IssueCategoryEntity categoryDetails;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity issueUserDetails;	
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="supports_id")
	private UserEntity supportUserDetails;	
	
	@OneToMany(mappedBy="issueDetails", cascade=CascadeType.ALL)
	private List<IssueLogEntity> issueLogs;
	
	@OneToMany(mappedBy="issueTickets", cascade=CascadeType.ALL)
	private List<TicketEntity> tickets;

}
