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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @ManyToOne
    private Announcement announcement;

    @OneToOne
    private User user;

    private Long price;

    private LocalDateTime publishedAt;



    public Claim() {

    }

    public Claim(Long id, Announcement announcement, User user, Long price, LocalDateTime publishedAt) {
        this.id = id;
        this.announcement = announcement;
        this.user = user;
        this.price = price;
        this.publishedAt = publishedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Claim claim = (Claim) o;
        return Objects.equals(id, claim.id) && Objects.equals(announcement, claim.announcement) && Objects.equals(user, claim.user) && Objects.equals(price, claim.price) && Objects.equals(publishedAt, claim.publishedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, announcement, user, price, publishedAt);
    }

    @Override
    public String toString() {
        return "Claim{" +
                "id=" + id +
                ", announcement=" + announcement +
                ", user=" + user +
                ", price=" + price +
                ", publishedAt=" + publishedAt +
                '}';
    }
}
