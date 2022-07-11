package dd.projects.ddshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="default_delivery_address", referencedColumnName = "id" )
    private Address default_delivery_address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="default_billing_address", referencedColumnName = "id")
    private Address default_billing_address;


    public User(String firstname, String lastname, String email, String password, String phone, Address billingAddress, Address deliveryAddress) {
        this.firstname = firstname;
        this.lastname= lastname;
        this.email = email;
        this.password = password;
        this.default_billing_address = billingAddress;
        this.default_delivery_address = deliveryAddress;
        this.phone = phone;
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    public static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
}
