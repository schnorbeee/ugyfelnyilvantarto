package com.codingmentorteam3.controllers.general;

import java.util.List;

/**
 *
 * @author istvan.mosonyi
 * @param <T>
 */
public abstract class PageableEntityController<T> implements JSFController {

    private final static Integer DEFAULT_LIMIT = 25;
    private Integer limit = DEFAULT_LIMIT;
    private Integer offset = 0;
    
    private Long entityId;
    private T entity;
    
    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
    
    public String saveEntity() {
        if (getEntityId() != null){
            doUpdateEntity();
        } else {
            doPersistEntity();
        }
        return getListPage();
    }
    
    @Override
    public void onPreRender(){
        setEntity(loadEntity(getEntityId()));
    }
    
    @Override
    public String getDetailHeader() {
        if (getEntity() != null){
            return "Edit a " + getEntity().getClass().getSimpleName();
        }
        return "Create a new data";
    }
    
    public abstract List<T> getEntities();
    protected abstract T loadEntity(Long entityId);
    protected abstract T doUpdateEntity();
    protected abstract void doPersistEntity();
    protected abstract String getNoEntityMessage();
    
}
