package mdd.me.domain.reply;

import java.util.Date;

public class Reply {
    /**
     * 
     */
    private Long id;

    /**
     * 文章id
     */
    private Long essayId;

    /**
     * 内容
     */
    private String content;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Long createrId;

    /**
     * 
     */
    private Byte isDeleted;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 文章id
     * @return essay_id 文章id
     */
    public Long getEssayId() {
        return essayId;
    }

    /**
     * 文章id
     * @param essayId 文章id
     */
    public void setEssayId(Long essayId) {
        this.essayId = essayId;
    }

    /**
     * 内容
     * @return content 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 
     * @return create_time 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     * @return creater_id 
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 
     * @param createrId 
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 
     * @return is_deleted 
     */
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * 
     * @param isDeleted 
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}