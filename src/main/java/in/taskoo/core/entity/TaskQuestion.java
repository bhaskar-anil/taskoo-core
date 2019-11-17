package in.taskoo.core.entity;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_task_question")
@AttributeOverride(column = @Column(name = "task_question_id"), name = "id")
@Getter
@Setter
public class TaskQuestion extends GeneratedIdEntity{
	
	@Column(name="create_date_time")
	private LocalDateTime createDateTime;
	
	@Column(name="statement",columnDefinition="TEXT")
	private String statement;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="parent_id")
	private Long parentId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "task_id", referencedColumnName = "task_id", updatable = false)
	private Task task;
	
}
