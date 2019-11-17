package in.taskoo.core.entity;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import in.taskoo.core.constant.OfferStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_offer_hist")
@AttributeOverride(column = @Column(name = "offer_hist_id"), name = "id")
@Getter
@Setter
public class OfferHistory extends GeneratedIdEntity{
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "task_id", referencedColumnName = "task_id", updatable = false)
	private Task task;
	
	@Column(name="tasker_id")
	private Long  taskerId;
	
	@Column(name="offer_amount",columnDefinition="INT")
	private Long  offerAmount;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "offer_id", referencedColumnName = "offer_id", updatable = false)
	private Offer  offer;
	
	@Column(name="offer_date_time")
	private LocalDateTime offerDateTime;
	
	@Column(name="status")
	private OfferStatus offerStatus;
	
	@Column(name="note",columnDefinition="TEXT")
	private String note;
}
