package com.bluetech.issuemanagement.entity;
/*
 * Created by yasinkilinc on 2.11.2020
 */


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "issue_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class IssueHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "issue_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Issue issue;

    @Column(name = "description", length = 1000)
    private String desctiption;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "issue_status")
    @Enumerated(EnumType.STRING)
    private IssueStatus issueStatus;

    @Column(name = "details", length = 4000)
    private String details;

    @JoinColumn(name = "assignee_user_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private User assignee;

}
