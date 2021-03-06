package somsap.webapi.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
public class MemberType {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "VARCHAR2(16)"/*, length = 16*/)
	private String type;
	
	@Column(columnDefinition = "VARCHAR2(16) NOT NULL"/*, length = 16, nullable = false*/)
	private String name;
	
	@Column(columnDefinition = "NUMBER(1) DEFAULT 1 NOT NULL", insertable = false)
	private Boolean isUsing;
	
	@Column(columnDefinition = "TIMESTAMP(3) DEFAULT SYSTIMESTAMP NOT NULL", insertable = false)
	private Calendar createdDatetime;
	
	@Column(columnDefinition = "VARCHAR2(128)"/*, length = 128*/)
	private String createdMemberEmail;
	
	@Column(columnDefinition = "TIMESTAMP(3) DEFAULT SYSTIMESTAMP NOT NULL", insertable = false)
	private Calendar modifiedDatetime;
	
	public MemberType() {
	}
	
	public MemberType(String type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public MemberType(String type, String name, Boolean isUsing, Calendar createdDatetime, String createdMemberEmail, Calendar modifiedDatetime, String modifiedMemberEmail, List<Member> members) {
		this.type = type;
		this.name = name;
		this.isUsing = isUsing;
		this.createdDatetime = createdDatetime;
		this.createdMemberEmail = createdMemberEmail;
		this.modifiedDatetime = modifiedDatetime;
		this.modifiedMemberEmail = modifiedMemberEmail;
		this.members = members;
	}
	
	public String getType() {
		return type;
	}
	
	public MemberType setType(String type) {
		this.type = type;
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	public MemberType setName(String name) {
		this.name = name;
		return this;
	}
	
	public Boolean getUsing() {
		return isUsing;
	}
	
	public MemberType setUsing(Boolean using) {
		isUsing = using;
		return this;
	}
	
	public Calendar getCreatedDatetime() {
		return createdDatetime;
	}
	
	public MemberType setCreatedDatetime(Calendar createdDatetime) {
		this.createdDatetime = createdDatetime;
		return this;
	}
	
	public String getCreatedMemberEmail() {
		return createdMemberEmail;
	}
	
	public MemberType setCreatedMemberEmail(String createdMemberEmail) {
		this.createdMemberEmail = createdMemberEmail;
		return this;
	}
	
	public Calendar getModifiedDatetime() {
		return modifiedDatetime;
	}
	
	public MemberType setModifiedDatetime(Calendar modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
		return this;
	}
	
	public String getModifiedMemberEmail() {
		return modifiedMemberEmail;
	}
	
	public MemberType setModifiedMemberEmail(String modifiedMemberEmail) {
		this.modifiedMemberEmail = modifiedMemberEmail;
		return this;
	}
	
	public List<Member> getMembers() {
		return members;
	}
	
	public MemberType setMembers(List<Member> members) {
		this.members = members;
		return this;
	}
	
	@Column(columnDefinition = "VARCHAR2(128)"/*, length = 128*/)
	private String modifiedMemberEmail;
	
	@OneToMany(mappedBy = "type"/*fetch=FetchType.EAGER, cascade = CascadeType.ALL*/)
	private List<Member> members;
	
	@Override
	public String toString() {
		return "MemberType{" +
				"type='" + type + '\'' +
				", name='" + name + '\'' +
				", isUsing=" + isUsing +
				", createdDatetime=" + createdDatetime +
				", createdMemberEmail='" + createdMemberEmail + '\'' +
				", modifiedDatetime=" + modifiedDatetime +
				", modifiedMemberEmail='" + modifiedMemberEmail + '\'' +
				", members=" + members +
				'}';
	}
}