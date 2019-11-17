package in.taskoo.core.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import in.taskoo.core.constant.CommissionType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "m_category_hist")
@AttributeOverride(column = @Column(name = "category_hist_id"), name = "id")
@Getter
@Setter
public class CategoryHistory extends GeneratedIdEntity{
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id", updatable = false)
	private Category category;
	
	@Column(name="name")
	private String name;
	
	@Column(name="commission")
	private Long commission;
	
	@Column(name="commission_type")
	private CommissionType commissionType;
	
	@Column(name="description",columnDefinition="TEXT")
	private String description;
	
}
