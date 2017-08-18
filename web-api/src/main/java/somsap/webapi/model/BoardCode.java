package somsap.webapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Calendar;
import java.util.List;

@Entity
public class BoardCode {
	@Id
	@Column(columnDefinition = "VARCHAR2(16)")
	private String code;
	
	@Column(columnDefinition = "VARCHAR2(16) NOT NULL")
	private String name;
	
	public String getCode() {
		return code;
	}
	
	public BoardCode setCode(String code) {
		this.code = code;
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	public BoardCode setName(String name) {
		this.name = name;
		return this;
	}
	
	public Boolean getUsing() {
		return isUsing;
	}
	
	public BoardCode setUsing(Boolean using) {
		isUsing = using;
		return this;
	}
	
	public Calendar getCreatedDatetime() {
		return createdDatetime;
	}
	
	public BoardCode setCreatedDatetime(Calendar createdDatetime) {
		this.createdDatetime = createdDatetime;
		return this;
	}
	
	public String getCreatedMemberEmail() {
		return createdMemberEmail;
	}
	
	public BoardCode setCreatedMemberEmail(String createdMemberEmail) {
		this.createdMemberEmail = createdMemberEmail;
		return this;
	}
	
	public Calendar getModifiedDatetime() {
		return modifiedDatetime;
	}
	
	public BoardCode setModifiedDatetime(Calendar modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
		return this;
	}
	
	public String getModifiedMemberEmail() {
		return modifiedMemberEmail;
	}
	
	public BoardCode setModifiedMemberEmail(String modifiedMemberEmail) {
		this.modifiedMemberEmail = modifiedMemberEmail;
		return this;
	}
	
	public List<BoardPost> getPosts() {
		return posts;
	}
	
	public BoardCode setPosts(List<BoardPost> posts) {
		this.posts = posts;
		return this;
	}
	
	@Column(columnDefinition = "NUMBER(1) DEFAULT 1 NOT NULL")
	private Boolean isUsing;
	
	@Column(columnDefinition = "TIMESTAMP(3) DEFAULT SYSTIMESTAMP NOT NULL")
	private Calendar createdDatetime;
	
	@Column(columnDefinition = "VARCHAR2(128)"/*, length = 128*/)
	private String createdMemberEmail;
	
	@Column(columnDefinition = "TIMESTAMP(3) DEFAULT SYSTIMESTAMP NOT NULL")
	private Calendar modifiedDatetime;
	
	@Column(columnDefinition = "VARCHAR2(128)"/*, length = 128*/)
	private String modifiedMemberEmail;
	
	@OneToMany(mappedBy = "boardCode")
	private List<BoardPost> posts;
}
