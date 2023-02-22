package auctioneer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nameProduct;
    private String description;
    private Long minPrice;
    private Boolean isActive;

    private String imageProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    @JsonIgnore
    private User user;

    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "announcement")
    @JsonIgnore
    private Set<Claim> claimList;


    public Announcement() {

    }

    public Announcement(Long id, String nameProduct, String description, Long minPrice, Boolean isActive, String imageProduct, User user, LocalDateTime createdAt) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.description = description;
        this.minPrice = minPrice;
        this.isActive = isActive;
        this.imageProduct = imageProduct;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return Objects.equals(id, that.id) && Objects.equals(nameProduct, that.nameProduct) && Objects.equals(description, that.description) && Objects.equals(minPrice, that.minPrice) && Objects.equals(isActive, that.isActive) && Objects.equals(imageProduct, that.imageProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameProduct, description, minPrice, isActive, imageProduct);
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                ", description='" + description + '\'' +
                ", minPrice=" + minPrice +
                ", isActive=" + isActive +
                ", imageProduct='" + imageProduct + '\'' +
                '}';
    }

}
