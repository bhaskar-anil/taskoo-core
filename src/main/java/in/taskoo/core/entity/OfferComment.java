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
@Table(name = "t_offer_comment")
@AttributeOverride(column = @Column(name = "offer_comment_id"), name = "id")
@Getter
@Setter
public class OfferComment extends GeneratedIdEntity{
	
	@Column(name="comment_date_time")
	private LocalDateTime commentDateTime;
	
	@Column(name="comment",columnDefinition="TEXT")
	private String comment;
	
	@Column(name="commenter_id")
	private Long commenterId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "offer_id", referencedColumnName = "offer_id", updatable = false)
	private Offer offer;
	
}
