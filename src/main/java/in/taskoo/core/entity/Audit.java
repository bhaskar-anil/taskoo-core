package in.taskoo.core.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Audit {
	
    @Column(name = "sys_create_user")
    private String createUser;

    @Column(name = "sys_create_datetime")
    private LocalDateTime createDateTime;

    @Column(name = "sys_create_program")
    private String createProgram;

    @Column(name = "sys_update_user")
    private String updateUser;

    @Column(name = "sys_update_datetime")
    private LocalDateTime updateDateTime;

    @Column(name = "sys_update_program")
    private String updateProgram;

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateProgram() {
		return createProgram;
	}

	public void setCreateProgram(String createProgram) {
		this.createProgram = createProgram;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public String getUpdateProgram() {
		return updateProgram;
	}

	public void setUpdateProgram(String updateProgram) {
		this.updateProgram = updateProgram;
	}

}