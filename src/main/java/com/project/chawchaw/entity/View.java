package com.project.chawchaw.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="views")
public class View {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userFrom_id")
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userTo_id")
    private User toUser;

    public static View createView(User toUser,User fromUser){
        View view=new View();
        view.fromUser=fromUser;
        view.toUser=toUser;
        return view;
    }
}
