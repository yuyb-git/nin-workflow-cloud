package cn.netinnet.ninauthorization.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * 构建 Vue路由
 */
public class VueRouter implements Serializable {

    private static final long serialVersionUID = -3327478146308500708L;

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Long parentId;

    @JsonIgnore
    private Integer seq;

    private String path;

    private String name;

    private String component;

    private String redirect;

    private String selfLayout;

    private String meta;

    private boolean hidden;

    private List<VueRouter> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getSelfLayout() {
        return selfLayout;
    }

    public void setSelfLayout(String selfLayout) {
        this.selfLayout = selfLayout;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public List<VueRouter> getChildren() {
        return children;
    }

    public void setChildren(List<VueRouter> children) {
        this.children = children;
    }

}
