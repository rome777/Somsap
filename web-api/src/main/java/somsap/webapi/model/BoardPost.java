package somsap.webapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
public class BoardPost implements Serializable {
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="boardCode", foreignKey = @ForeignKey(name="FK_BOARDPOST_BOARDCODE"))
	private BoardCode boardCode;
	
	@Id
	private long seq;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns(value = {
		@JoinColumn(name="parentBoardCode", referencedColumnName = "boardCode"),
		@JoinColumn(name="parentBoardSeq", referencedColumnName = "seq")
	}, foreignKey = @ForeignKey(name="FK_BOARDPOST_BOARDPOST"))
	private BoardPost parentBoardPost;
	
	@Column(columnDefinition = "VARCHAR2(16)")
	private String type;
	
	@Column(columnDefinition = "VARCHAR2(256) NOT NULL")
	private String title;
	
	@Column(columnDefinition = "CLOB")
	@Lob
	private String content;
	
	@Column(columnDefinition = "VARCHAR2(256)")
	private String attachmentLocation;
	
	@Column(columnDefinition = "NUMBER DEFAULT 0 NOT NULL")
	private int hits;
	
	@Column(columnDefinition = "NUMBER DEFAULT 0 NOT NULL")
	private int likes;
	
	@Column(columnDefinition = "NUMBER DEFAULT 0 NOT NULL")
	private int hates;
	
	@Column(columnDefinition = "NUMBER(1) DEFAULT 1 NOT NULL")
	private Boolean isShowing;
	
	@Column(columnDefinition = "TIMESTAMP(3) DEFAULT SYSTIMESTAMP NOT NULL")
	private Calendar createdDatetime;
	
	@Column(columnDefinition = "VARCHAR2(128)")
	private String createdMemberEmail;
	
	@Column(columnDefinition = "TIMESTAMP(3) DEFAULT SYSTIMESTAMP NOT NULL")
	private Calendar modifiedDatetime;
	
	@Column(columnDefinition = "VARCHAR2(128)")
	private String modifiedMemberEmail;
	
	public BoardCode getBoardCode() {
		return boardCode;
	}
	
	public BoardPost setBoardCode(BoardCode boardCode) {
		this.boardCode = boardCode;
		return this;
	}
	
	public long getSeq() {
		return seq;
	}
	
	public BoardPost setSeq(long seq) {
		this.seq = seq;
		return this;
	}
	
	public BoardPost getParentBoardPost() {
		return parentBoardPost;
	}
	
	public BoardPost setParentBoardPost(BoardPost parentBoardPost) {
		this.parentBoardPost = parentBoardPost;
		return this;
	}
	
	public String getType() {
		return type;
	}
	
	public BoardPost setType(String type) {
		this.type = type;
		return this;
	}
	
	public String getTitle() {
		return title;
	}
	
	public BoardPost setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public String getContent() {
		return content;
	}
	
	public BoardPost setContent(String content) {
		this.content = content;
		return this;
	}
	
	public String getAttachmentLocation() {
		return attachmentLocation;
	}
	
	public BoardPost setAttachmentLocation(String attachmentLocation) {
		this.attachmentLocation = attachmentLocation;
		return this;
	}
	
	public int getHits() {
		return hits;
	}
	
	public BoardPost setHits(int hits) {
		this.hits = hits;
		return this;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public BoardPost setLikes(int likes) {
		this.likes = likes;
		return this;
	}
	
	public int getHates() {
		return hates;
	}
	
	public BoardPost setHates(int hates) {
		this.hates = hates;
		return this;
	}
	
	public Boolean getShowing() {
		return isShowing;
	}
	
	public BoardPost setShowing(Boolean showing) {
		isShowing = showing;
		return this;
	}
	
	public Calendar getCreatedDatetime() {
		return createdDatetime;
	}
	
	public BoardPost setCreatedDatetime(Calendar createdDatetime) {
		this.createdDatetime = createdDatetime;
		return this;
	}
	
	public String getCreatedMemberEmail() {
		return createdMemberEmail;
	}
	
	public BoardPost setCreatedMemberEmail(String createdMemberEmail) {
		this.createdMemberEmail = createdMemberEmail;
		return this;
	}
	
	public Calendar getModifiedDatetime() {
		return modifiedDatetime;
	}
	
	public BoardPost setModifiedDatetime(Calendar modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
		return this;
	}
	
	public String getModifiedMemberEmail() {
		return modifiedMemberEmail;
	}
	
	public BoardPost setModifiedMemberEmail(String modifiedMemberEmail) {
		this.modifiedMemberEmail = modifiedMemberEmail;
		return this;
	}
	
	public List<BoardPost> getChildPosts() {
		return childPosts;
	}
	
	public BoardPost setChildPosts(List<BoardPost> childPosts) {
		this.childPosts = childPosts;
		return this;
	}
	
	@OneToMany(mappedBy = "parentBoardPost")
	private List<BoardPost> childPosts;
}
