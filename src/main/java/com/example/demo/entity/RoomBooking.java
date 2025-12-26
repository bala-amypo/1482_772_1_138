@Entity
public class RoomBooking {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Guest guest;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Boolean active = true;

    @ManyToMany
    private Set<Guest> roommates = new HashSet<>();
}
