package com.fpis.fontazija.kokteli.specification;

import com.fpis.fontazija.kokteli.entity.Kategorija;
import com.fpis.fontazija.kokteli.entity.Koktel;
import com.fpis.fontazija.kokteli.entity.KoktelSastojak;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class KoktelSpecifications implements Specification<Koktel> {

    private List<String> categoryArray;
    private List<String> ingredientArray;
    private String startingStringArray;

    public KoktelSpecifications(List<String> categoryArray, List<String> ingredientArray, String startingStringArray) {
        this.categoryArray = categoryArray;
        this.ingredientArray = ingredientArray;
        this.startingStringArray = startingStringArray;
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
        List<Predicate> predicates = new ArrayList<>();

        Join<Koktel, Kategorija> joinKategorija = root.join("kategorija");
        // Category condition
        if (categoryArray != null && !categoryArray.isEmpty()) {
            predicates.add(joinKategorija.get("nazivKategorije").in(categoryArray));
        }

        Join<Koktel, KoktelSastojak> joinSastojak = root.join("sastojci");
        // Ingredient condition
        if (ingredientArray != null && !ingredientArray.isEmpty()) {
            predicates.add(joinSastojak.get("sastojak").get("naziv").in(ingredientArray));
        }

        // Starting string condition
        if (startingStringArray != null && !startingStringArray.isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("naziv"), startingStringArray + "%"));
        }

        query.groupBy(root.get("id"), root.get("naziv"), root.get("kategorija").get("nazivKategorije"),
                root.get("casa").get("slika"), root.get("casa").get("naziv"));

        query.orderBy(criteriaBuilder.asc(criteriaBuilder.countDistinct(root.get("sastojci"))));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
