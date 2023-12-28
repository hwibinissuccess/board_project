package com.hwibin.boardproject.repository;

import com.hwibin.boardproject.domain.Article;
import com.hwibin.boardproject.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> {

    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        // 선택적으로 검색하게 하기
        // excludeUnlistedProperties -> 리스팅을 하지 않은 프로퍼티는 검색에서 제외
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.hashtag, root.createdAt, root.createdBy, root.content);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%${v}%'
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase); // like '%${v}%'
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // like '%${v}%'
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // like '%${v}%'
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%${v}%'

    }
}
