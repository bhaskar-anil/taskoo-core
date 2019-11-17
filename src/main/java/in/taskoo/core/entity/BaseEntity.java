package in.taskoo.core.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import in.taskoo.core.context.AppContext;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements CrudPersistable {

	static final String NOT_AVAILABLE = "N.A.";

	@Embedded
	@JsonIgnore
	private Audit audit = new Audit();

	@Version
	@Column(name = "sys_update_count")
	@JsonIgnore
	private int updateCount;

	@Column(name = "sys_delete_flag")
	private boolean deleteFlag;

	@PrePersist
	public void prePersist() {
		this.audit.setCreateUser(this.getUserId());
		this.audit.setCreateProgram(this.getApiName());
		this.audit.setCreateDateTime(LocalDateTime.now());
	}

	@PreUpdate
	public void preUpdate() {
		this.audit.setUpdateUser(this.getUserId());
		this.audit.setUpdateProgram(this.getApiName());
		this.audit.setUpdateDateTime(LocalDateTime.now());
	}

	private String getApiName() {
		return Objects.isNull(AppContext.api) ? NOT_AVAILABLE :  AppContext.api.get() ;
	}

	private String getUserId() {
		return Objects.isNull(AppContext.userId) ? NOT_AVAILABLE : String.valueOf(AppContext.userId.get());
	}

}
