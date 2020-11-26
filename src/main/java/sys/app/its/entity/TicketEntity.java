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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sys.app.its.generator.IDGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tickets")
public class TicketEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3549060465372802979L;
	@Id
	@Getter	@Setter	
	@SequenceGenerator(name="ticket_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_seq")
    @GenericGenerator(
        name = "ticket_seq", 
        strategy = "sys.app.its.generator.IDGenerator", 
        parameters = {
            @Parameter(name = IDGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = IDGenerator.VALUE_PREFIX_PARAMETER, value = "TID"),
            @Parameter(name = IDGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;
	
	@Column(name="ticket_id",nullable=false)
	private String ticketId;
	
	@Column(name="date_opened",nullable=false)
	private Date dateOpened;
	
	@Column(name="date_closed")
	private Date dateClosed;	

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="users_id")	
	private UserEntity userticketDetails;
	
	@OneToMany(mappedBy="ticketDetails", cascade=CascadeType.ALL)
	private List<TaskEntity> tasks;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="issue_id")
	private IssueEntity issueTickets;	

}
