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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sys.app.its.generator.IDGenerator;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tasks")
public class TaskEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5426001764372306420L;
	
	@Id
	@SequenceGenerator(name="task_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @GenericGenerator(
        name = "task_seq", 
        strategy = "sys.app.its.generator.IDGenerator", 
        parameters = {
            @Parameter(name = IDGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = IDGenerator.VALUE_PREFIX_PARAMETER, value = "TASKID"),
            @Parameter(name = IDGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;
	
	@Column(name="task_id",nullable=false)
	private String taskId;
	
	@Column(name="date_opened",nullable=false)
	private Date dateOpened;
	
	@Column(name="date_closed")
	private Date dateClosed;	
	
	@Column(name="subject",nullable=false)
	private String subject;
	
	@Column(name="description",nullable=false)
	private String description;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="ticket_id")
	private TicketEntity ticketDetails;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="users_id")	
	private UserEntity usertaskDetails;

}
