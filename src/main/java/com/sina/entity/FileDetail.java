package com.sina.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Sina Askarnejad
 */
@Data
@Table(name = "FILE_DETAIL")
@Entity
public class FileDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SOURCE")
    private String source;
    @Column(name = "CODE_LIST_CODE")
    private String codeListCode;
    @Column(name = "CODE", unique = true)
    private String code;
    @Column(name = "DISPLAY_VAUE")
    private String displayValue;
    @Column(name = "LONG_DESCRIPTION")
    private String longDescription;
    @Column(name = "FROM_DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate fromDate;
    @Column(name = "TO_DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate toDate;
    @Column(name = "SORTING_PRIORITY")
    private Integer sortingPriority;
}
