package com.fpis.fontazija.kokteli.specification;

import com.fpis.fontazija.kokteli.entity.*;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class KoktelSpecifications implements Specification<Koktel> {

    private Set<String> categoryArray;
    private Set<String> ingredientArray;
    private String startingString;

    public KoktelSpecifications(Set<String> categoryArray, Set<String> ingredientArray, String startingString) {
        this.categoryArray = categoryArray;
        this.ingredientArray = ingredientArray;
        this.startingString = startingString;
    }

    @Override
    public Specification<Koktel> and(Specification<Koktel> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Koktel> or(Specification<Koktel> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Koktel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Join<Koktel, Kategorija> kategorijaJoin = root.join("kategorija");
        Join<Koktel, Casa> casaJoin = root.join("casa");
        Join<Koktel, KoktelSastojak> koktelSastojakJoin = root.join("sastojci");
        Join<KoktelSastojak, Sastojak> sastojakJoin = koktelSastojakJoin.join("sastojak");

        Predicate categoryPredicate = kategorijaJoin.get("nazivKategorije").in(categoryArray);
        Predicate ingredientPredicate = sastojakJoin.get("naziv").in(ingredientArray);
        Predicate startingStringPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("naziv")), startingString.toLowerCase() + "%");

        query.groupBy(root.get("id"));

        query.orderBy(criteriaBuilder.desc(
                criteriaBuilder.countDistinct(sastojakJoin.get("id")))
        );

        if ( categoryArray == null || categoryArray.isEmpty()){
            if ( ingredientArray == null || ingredientArray.isEmpty()){
                return criteriaBuilder.and(startingStringPredicate);
            }
        }

        if ( categoryArray == null || categoryArray.isEmpty()){
            if ( ingredientArray != null && !ingredientArray.isEmpty()){
                return criteriaBuilder.and(ingredientPredicate, startingStringPredicate);
            }
        }

        if ( categoryArray != null && !categoryArray.isEmpty()){
            if ( ingredientArray == null || ingredientArray.isEmpty()){
                return criteriaBuilder.and(categoryPredicate, startingStringPredicate);
            }
        }
        return criteriaBuilder.and(categoryPredicate, ingredientPredicate, startingStringPredicate);
    }
}
