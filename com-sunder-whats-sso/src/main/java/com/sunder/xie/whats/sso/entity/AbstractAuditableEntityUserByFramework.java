package com.sunder.xie.whats.sso.entity;

import com.cpic.caf.core.entity.supports.AbstractUndeletableEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by xieshengrong on 2017/5/16.
 */
@MappedSuperclass
public abstract class AbstractAuditableEntityUserByFramework<ID extends Serializable> extends AbstractUndeletableEntity<ID> {
    private static final long serialVersionUID = -8790453784740403209L;
    protected Date createdTime;
    protected GenericUser createdBy = new GenericUser();
    protected Date lastModifiedTime;
    protected GenericUser lastModifiedBy = new GenericUser();

    public AbstractAuditableEntityUserByFramework() {
    }

    @Column(
            name = "CREATED_BY"
    )
    public Long getCreatedById() {
        return this.createdBy != null ? this.createdBy.getId() : null;
    }

    public void setCreatedById(Long createdById) {
        this.createdBy.setId(createdById);
    }

    @Column(
            name = "LAST_MODIFIED_BY"
    )
    public Long getLastModifiedById() {
        return this.lastModifiedBy != null ? this.lastModifiedBy.getId() : null;
    }

    public void setLastModifiedById(Long lastModifiedById) {
        this.lastModifiedBy.setId(lastModifiedById);
    }

    @Column(
            name = "CREATED_TIME"
    )
    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Transient
    public GenericUser getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(GenericUser createdBy) {
        this.createdBy = createdBy;
    }

    @Column(
            name = "LAST_MODIFIED_TIME"
    )
    public Date getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    @Transient
    public GenericUser getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(GenericUser lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
