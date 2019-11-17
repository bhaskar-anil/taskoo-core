package in.taskoo.core.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import in.taskoo.core.constant.CommissionType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "m_category")
@AttributeOverride(column = @Column(name = "category_id"), name = "id")
@Getter
@Setter
public class Category extends GeneratedIdEntity{
	
	@Column(name="name")
	private String name;
	
	@Column(name="commission")
	private Long commission;
	
	@Column(name="commission_type")
	private CommissionType commissionType;
	
	@Column(name="description",columnDefinition="TEXT")
	private String description;
	
}
