package com.fse3.cqrs.core.queries;

import com.fse3.cqrs.core.domain.BaseEntity;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T  extends BaseQuery>{
    List<BaseEntity> handle(T query);
}
