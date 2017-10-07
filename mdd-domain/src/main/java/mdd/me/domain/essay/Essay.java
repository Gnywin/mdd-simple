package mdd.me.domain.essay;

import java.util.Date;

public class Essay {
    /**
     * 
     */
    private Long id;

    /**
     * 标题
     */
    private String titile;

    /**
     * 内容
     */
    private String content;

    /**
     * 图片id数组
     */
    private String picArr;

    /**
     * 创建人id
     */
    private Long createrId;

    /**
     * 赞数量
     */
    private Integer praise;

    /**
     * 踩数量
     */
    private Integer tread;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除
     */
    private Integer isDeleted;

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
     * 标题
     * @return titile 标题
     */
    public String getTitile() {
        return titile;
    }

    /**
     * 标题
     * @param titile 标题
     */
    public void setTitile(String titile) {
        this.titile = titile == null ? null : titile.trim();
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
     * 图片id数组
     * @return pic_arr 图片id数组
     */
    public String getPicArr() {
        return picArr;
    }

    /**
     * 图片id数组
     * @param picArr 图片id数组
     */
    public void setPicArr(String picArr) {
        this.picArr = picArr == null ? null : picArr.trim();
    }

    /**
     * 创建人id
     * @return creater_id 创建人id
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人id
     * @param createrId 创建人id
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 赞数量
     * @return praise 赞数量
     */
    public Integer getPraise() {
        return praise;
    }

    /**
     * 赞数量
     * @param praise 赞数量
     */
    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    /**
     * 踩数量
     * @return tread 踩数量
     */
    public Integer getTread() {
        return tread;
    }

    /**
     * 踩数量
     * @param tread 踩数量
     */
    public void setTread(Integer tread) {
        this.tread = tread;
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
     * 是否删除
     * @return is_deleted 
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * 
     * @param isDeleted 
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}