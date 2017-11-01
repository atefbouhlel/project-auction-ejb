package entreprise.entity_bean_entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "users")
public class User implements Serializable{
    private static final long serialVersionUID = 1789635L;

    private int id;
    private String pseudo;
    private String firstname;
    private String lastname;
    private String email;
    private String adress;
    /**
     * the collection of objects.
     */
	private Collection<Objet> objects = new ArrayList<Objet>();

	private Collection<Auction> auctions = new ArrayList<Auction>();



    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    @Column(name = "PSEUDO",unique = true, nullable = false)
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "EMAIL",unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "ADRESS")
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @OneToMany(cascade = ALL, fetch=EAGER, mappedBy = "user")
    public Collection<Objet> getObjects() {
        return objects;
    }

    public void setObjects(Collection<Objet> objects) {
        this.objects = objects;
    }

    @OneToMany(cascade = ALL, fetch=EAGER, mappedBy = "user")
    public Collection<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(Collection<Auction> auctions) {
        this.auctions = auctions;
    }

    public String toString() {
        return "User{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
