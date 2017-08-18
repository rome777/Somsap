package somsap.webapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Record implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_RECORD")
	@SequenceGenerator(name="SEQ_RECORD", sequenceName="SEQ")
	private long seq;
	
	@Id
	@Column(columnDefinition = "TIMESTAMP(3) DEFAULT SYSTIMESTAMP")
	private Calendar createdDatetime;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="createdMemberEmail", foreignKey = @ForeignKey(name="FK_RECORD_MEMBER"))
	private Member member;
	
	@Column(columnDefinition = "VARCHAR2(128)")
	private String location;
	
	@Column(columnDefinition = "VARCHAR2(1024)")
	private String beforeData;
	
	@Column(columnDefinition = "VARCHAR2(1024)")
	private String afterData;
	
	public long getSeq() {
		return seq;
	}
	
	public Record setSeq(long seq) {
		this.seq = seq;
		return this;
	}
	
	public Calendar getCreatedDatetime() {
		return createdDatetime;
	}
	
	public Record setCreatedDatetime(Calendar createdDatetime) {
		this.createdDatetime = createdDatetime;
		return this;
	}
	
	public Member getMember() {
		return member;
	}
	
	public Record setMember(Member member) {
		this.member = member;
		return this;
	}
	
	public String getLocation() {
		return location;
	}
	
	public Record setLocation(String location) {
		this.location = location;
		return this;
	}
	
	public String getBeforeData() {
		return beforeData;
	}
	
	public Record setBeforeData(String beforeData) {
		this.beforeData = beforeData;
		return this;
	}
	
	public String getAfterData() {
		return afterData;
	}
	
	public Record setAfterData(String afterData) {
		this.afterData = afterData;
		return this;
	}
}
