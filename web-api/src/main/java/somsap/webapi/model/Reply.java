package somsap.webapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
public class Reply implements Serializable {
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name="boardCode", referencedColumnName = "boardCode"),
			@JoinColumn(name="postSeq", referencedColumnName = "seq")
	}, foreignKey = @ForeignKey(name="FK_COMMENT_BOARDPOST"))
	private BoardPost parentBoardPost;
	
	@Id
	private long seq;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name="parentCommentBoardCode", referencedColumnName = "boardCode"),
			@JoinColumn(name="parentCommentPostSeq", referencedColumnName = "postSeq"),
			@JoinColumn(name="parentCommentSeq", referencedColumnName = "seq")
	}, foreignKey = @ForeignKey(name="FK_COMMENT_COMMENT"))
	private Reply parentComment;
	
	@Column(columnDefinition = "VARCHAR2(1024) NOT NULL")
	private String content;
	
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
	
	@OneToMany(mappedBy = "parentComment")
	private List<Reply> childComments;
	
	public BoardPost getParentBoardPost() {
		return parentBoardPost;
	}
	
	public Reply setParentBoardPost(BoardPost parentBoardPost) {
		this.parentBoardPost = parentBoardPost;
		return this;
	}
	
	public long getSeq() {
		return seq;
	}
	
	public Reply setSeq(long seq) {
		this.seq = seq;
		return this;
	}
	
	public Reply getParentComment() {
		return parentComment;
	}
	
	public Reply setParentComment(Reply parentComment) {
		this.parentComment = parentComment;
		return this;
	}
	
	public String getContent() {
		return content;
	}
	
	public Reply setContent(String content) {
		this.content = content;
		return this;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public Reply setLikes(int likes) {
		this.likes = likes;
		return this;
	}
	
	public int getHates() {
		return hates;
	}
	
	public Reply setHates(int hates) {
		this.hates = hates;
		return this;
	}
	
	public Boolean getShowing() {
		return isShowing;
	}
	
	public Reply setShowing(Boolean showing) {
		isShowing = showing;
		return this;
	}
	
	public Calendar getCreatedDatetime() {
		return createdDatetime;
	}
	
	public Reply setCreatedDatetime(Calendar createdDatetime) {
		this.createdDatetime = createdDatetime;
		return this;
	}
	
	public String getCreatedMemberEmail() {
		return createdMemberEmail;
	}
	
	public Reply setCreatedMemberEmail(String createdMemberEmail) {
		this.createdMemberEmail = createdMemberEmail;
		return this;
	}
	
	public Calendar getModifiedDatetime() {
		return modifiedDatetime;
	}
	
	public Reply setModifiedDatetime(Calendar modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
		return this;
	}
	
	public String getModifiedMemberEmail() {
		return modifiedMemberEmail;
	}
	
	public Reply setModifiedMemberEmail(String modifiedMemberEmail) {
		this.modifiedMemberEmail = modifiedMemberEmail;
		return this;
	}
	
	public List<Reply> getChildComments() {
		return childComments;
	}
	
	public Reply setChildComments(List<Reply> childComments) {
		this.childComments = childComments;
		return this;
	}
}
