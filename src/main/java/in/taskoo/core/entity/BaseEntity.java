package in.taskoo.core.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import in.taskoo.core.context.AppContext;

@MappedSuperclass
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

	public Audit getAudit() {
		return this.audit;
	}

	public int getUpdateCount() {
		return this.updateCount;
	}

	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}

	public boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

	private String getApiName() {
		return StringUtils.isEmpty(AppContext.api.get()) ? NOT_AVAILABLE :  AppContext.api.get() ;
	}

	private String getUserId() {
		return Objects.isNull(AppContext.userId) ? NOT_AVAILABLE : String.valueOf(AppContext.userId.get());
	}

}
