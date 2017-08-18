package somsap.webapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
public class MemberSession implements Serializable {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="memberEmail", foreignKey = @ForeignKey(name="FK_MEMBERSESSION_MEMBER"))
	private Member member;
	
	@Column(columnDefinition = "VARCHAR2(128)")
	private String memberEmail;
	
	@Column(columnDefinition = "VARCHAR2(128)")
	private String sessionId;
	
	@Column(columnDefinition = "TIMESTAMP(3) DEFAULT SYSTIMESTAMP NOT NULL", insertable = false)
	private Calendar createdDatetime;
	
	@Column(columnDefinition = "VARCHAR2(128)")
	private String createdMemberEmail;
	
	@Column(columnDefinition = "TIMESTAMP(3) DEFAULT SYSTIMESTAMP NOT NULL", insertable = false)
	private Calendar modifiedDatetime;
	
	@Column(columnDefinition = "VARCHAR2(128)")
	private String modifiedMemberEmail;
	
	public Member getMember() {
		return member;
	}
	
	public MemberSession setMember(Member member) {
		this.member = member;
		return this;
	}
	
	public String getMemberEmail() {
		return memberEmail;
	}
	
	public MemberSession setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
		return this;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public MemberSession setSessionId(String sessionId) {
		this.sessionId = sessionId;
		return this;
	}
	
	public Calendar getCreatedDatetime() {
		return createdDatetime;
	}
	
	public MemberSession setCreatedDatetime(Calendar createdDatetime) {
		this.createdDatetime = createdDatetime;
		return this;
	}
	
	public String getCreatedMemberEmail() {
		return createdMemberEmail;
	}
	
	public MemberSession setCreatedMemberEmail(String createdMemberEmail) {
		this.createdMemberEmail = createdMemberEmail;
		return this;
	}
	
	public Calendar getModifiedDatetime() {
		return modifiedDatetime;
	}
	
	public MemberSession setModifiedDatetime(Calendar modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
		return this;
	}
	
	public String getModifiedMemberEmail() {
		return modifiedMemberEmail;
	}
	
	public MemberSession setModifiedMemberEmail(String modifiedMemberEmail) {
		this.modifiedMemberEmail = modifiedMemberEmail;
		return this;
	}
	
	@Override
	public String toString() {
		return "MemberSession{" +
				"member=" + member +
				", memberEmail='" + memberEmail + '\'' +
				", sessionId='" + sessionId + '\'' +
				", createdDatetime=" + createdDatetime +
				", createdMemberEmail='" + createdMemberEmail + '\'' +
				", modifiedDatetime=" + modifiedDatetime +
				", modifiedMemberEmail='" + modifiedMemberEmail + '\'' +
				'}';
	}
}
