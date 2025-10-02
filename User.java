import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean verified = false;

    @Column(unique = true)
    private String email;

    
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String fullname;

    @Column(unique = true)
    private long phonenumber;

    private String imageurl;

    @CreationTimestamp
    private LocalDateTime toc;

    @CreationTimestamp
    private LocalDateTime tod;

    @OneToMany(mappedBy = "instructor")
    private List<Course> totalcourses;

    @OneToMany(mappedBy = "user")
    private List<Enrollment> totalenrolls;
}
