package gym_fit.domain;

public class Client {
    private int id;
    private String name;
    private String lastName;
    private int membership;

    public Client() {
    }

    public Client(int id) {
        this.id = id;
    }

    public Client(String name, String lastName, int membership) {
        this.name = name;
        this.lastName = lastName;
        this.membership = membership;
    }

    public Client(int id, String name, String lastName, int membership) {
        this(name, lastName, membership);
        this.membership = membership;
    }
}
