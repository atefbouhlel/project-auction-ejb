package entreprise.entity_bean_entity;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "auctions")
public class Auction implements Serializable{
    private static final long serialVersionUID = 78794621411L;

    private int id;
    private String name;
    private double startPrice;
    private double finalPrice;
    private int duration;
    private String state;

    private User user;
    private Objet object;


    @Id
    @Column(name = "AUCTION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "STARTPRICE")
    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    @Column(name = "FINALPRICE")
    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Column(name = "DURATION")
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Column(name = "STATE")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne
    public Objet getObject() {
        return object;
    }

    public void setObject(Objet object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startPrice=" + startPrice +
                ", finalPrice=" + finalPrice +
                ", duration=" + duration +
                ", state='" + state + '\'' +
                ", user=" + user +
                ", object=" + object +
                '}';
    }


}
