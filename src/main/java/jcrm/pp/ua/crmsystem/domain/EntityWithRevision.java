package jcrm.pp.ua.crmsystem.domain;

import lombok.Data;

@Data
public class EntityWithRevision <T> {

    private RevisionsEntity revision;
    private T entity;

    public EntityWithRevision(RevisionsEntity revision, T entity) {
        this.revision = revision;
        this.entity = entity;
    }

}
