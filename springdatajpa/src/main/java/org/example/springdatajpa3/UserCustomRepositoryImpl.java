package org.example.springdatajpa3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final EntityManager entityManager;

    //jpql 을 통해서 쿼리를 생성했어요.
//    CriteriaBuilder 를 이용해서 쿼리를 생성하고,  이 쿼리를 entityManager를 통해서 실행시켜볼께요.


    @Override
    public List<User> findUsersDynamically(String name, String email) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> user = query.from(User.class);

        List<Predicate> predicates = new ArrayList<>();
        if(name != null){
            predicates.add(builder.like(user.get("name"), "%"+name+"%"));
        }
        if(email != null){
            predicates.add(builder.equal(user.get("email"), email));
        }

        query.select(user).where(builder.and(predicates.toArray(new Predicate[0])));
        //사용자가 입력한 값에 따라서,  쿼리가 동적으로 생성되게 될거예요.
//        name = null, email = null   select u from User u;
//        name != null, email = null  select u from User u where u.name = name;
//        name = null, email != null  select u from User u where u.email = email;
//        name != null, email != null  select u from User u where u.name = name and u.email = email

//        query.select(user).where(builder.or(predicates.toArray(new Predicate[0])));
//        name != null, email != null  select u from User u where u.name = name or u.email = email

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<User> findUsersByName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> user = query.from(User.class);
        query.select(user).where(builder.like(user.get("name"),"%"+name+"%"));
        //select u from User u where u.name = name;
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void lionCustom() {
        System.out.println("lionCustom");
    }
}

