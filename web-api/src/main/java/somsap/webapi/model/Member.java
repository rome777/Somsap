package somsap.webapi.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Entity
public class Member {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "VARCHAR2(128)")
	private String email;
	
	@ManyToOne(/*targetEntity=MemberType.class, */fetch=FetchType.LAZY)
	@JoinColumn(name="membertypeType", foreignKey = @ForeignKey(name="FK_MEMBER_MEMBERTYPE"))
	private MemberType type;
	
	@Column(columnDefinition = "VARCHAR2(16) NOT NULL")
	private String nickName;
	
	@Column(columnDefinition = "VARCHAR2(16) NOT NULL")
	private String password;
	
	@Column(columnDefinition = "NUMBER(1) DEFAULT 1 NOT NULL")
	private Boolean isUsing;
	
	@Column(columnDefinition = "TIMESTAMP(3) DEFAULT SYSTIMESTAMP NOT NULL")
	private Calendar createdDatetime;
	
	@Column(columnDefinition = "VARCHAR2(128)")
	private String createdMemberEmail;
	
	public String getEmail() {
		return email;
	}
	
	public Member setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public MemberType getType() {
		return type;
	}
	
	public Member setType(MemberType type) {
		this.type = type;
		return this;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public Member setNickName(String nickName) {
		this.nickName = nickName;
		return this;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Member setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public Boolean getUsing() {
		return isUsing;
	}
	
	public Member setUsing(Boolean using) {
		isUsing = using;
		return this;
	}
	
	public Calendar getCreatedDatetime() {
		return createdDatetime;
	}
	
	public Member setCreatedDatetime(Calendar createdDatetime) {
		this.createdDatetime = createdDatetime;
		return this;
	}
	
	public String getCreatedMemberEmail() {
		return createdMemberEmail;
	}
	
	public Member setCreatedMemberEmail(String createdMemberEmail) {
		this.createdMemberEmail = createdMemberEmail;
		return this;
	}
	
	public Calendar getModifiedDatetime() {
		return modifiedDatetime;
	}
	
	public Member setModifiedDatetime(Calendar modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
		return this;
	}
	
	public String getModifiedMemberEmail() {
		return modifiedMemberEmail;
	}
	
	public Member setModifiedMemberEmail(String modifiedMemberEmail) {
		this.modifiedMemberEmail = modifiedMemberEmail;
		return this;
	}
	
	public List<MemberSession> getSessions() {
		return sessions;
	}
	
	public Member setSessions(List<MemberSession> sessions) {
		this.sessions = sessions;
		return this;
	}
	
	@Column(columnDefinition = "TIMESTAMP(3) DEFAULT SYSTIMESTAMP NOT NULL")
	private Calendar modifiedDatetime;
	
	@Column(columnDefinition = "VARCHAR2(128)")
	private String modifiedMemberEmail;
	
	@OneToMany(mappedBy = "member")
	private List<MemberSession> sessions;
}
