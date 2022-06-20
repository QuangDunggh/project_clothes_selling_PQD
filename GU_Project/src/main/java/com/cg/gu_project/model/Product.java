package com.cg.gu_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @Column(name = "deleted")
    private Boolean deleted = false;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "ends_at")
    private Instant endsAt;

    @Column(name = "published_at")
    private Instant publishedAt;

    @Column(name = "slug", length = 100)
    private String slug;

    @Column(name = "starts_at")
    private Instant startsAt;

    @Column(name = "title", nullable = false, length = 75)
    private String title;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private ImageGallery image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subcategory_id", nullable = false)
    private Subcategory subcategory;



}