package in.taskoo.core.entity;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import in.taskoo.core.constant.EstimateType;
import in.taskoo.core.constant.TaskStatus;
import in.taskoo.core.constant.TaskType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_task_hist")
@AttributeOverride(column = @Column(name = "task_hist_id"), name = "id")
@Getter
@Setter
public class TaskHistory extends GeneratedIdEntity{
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "task_id", referencedColumnName = "task_id", updatable = false)
	private Task task;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description",columnDefinition="TEXT")
	private String description;
	
	@Column(name="task_type")
	private TaskType taskType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id", updatable = false)
	private Category category;
	
	@Column(name="location")
	private String location;
	
	@Column(name="task_date_time")
	private LocalDateTime taskDateTime;
	
	@Column(name="task_due_date_time")
	private LocalDateTime taskDueDateTime;
	
	@Column(name="seeker_id")
	private Long seekerId;
	
	@Column(name="estimate_amount",columnDefinition="INT")
	private Long estimateAmount;
	
	@Column(name="estimate_type")
	@Enumerated(EnumType.ORDINAL)
	private EstimateType estimateType;
	
	@Column(name="task_status")
	@Enumerated(EnumType.ORDINAL)
	private TaskStatus taskStatus;

}
