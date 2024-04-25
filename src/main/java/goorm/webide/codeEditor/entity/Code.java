package goorm.webide.codeEditor.entity;

import goorm.webide.user.entity.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@ToString
@Where(clause = "deleted = false")
@SQLDelete(sql = "update example set deleted = true where id = ?")
@Entity
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeNo;

    @ManyToOne @JoinColumn(name = "user_No")
    private User user;

    @Setter @Column(nullable = false, length = 10000)
    private String text;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    private Boolean deleted = Boolean.FALSE;

    protected Code(){ }

    private Code(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public static Code of(User user, String text) {
        return new Code(user, text);
    }



}
