package dd.projects.ddshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


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

    @OneToMany(mappedBy = "user_id",cascade = CascadeType.ALL)
    private List<Orders> orders;

    public User(String firstname, String lastname, String email, String password, String phone, Address billingAddress, Address deliveryAddress) {
        this.firstname = firstname;
        this.lastname= lastname;
        this.email = email;
        this.password = getMd5(password);
        this.default_billing_address = billingAddress;
        this.default_delivery_address = deliveryAddress;
        this.phone = phone;
        this.orders = new ArrayList<>();
    }

    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into Sig_num representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            StringBuilder hash_text = new StringBuilder(no.toString(16));
            while (hash_text.length() < 32) {
                hash_text.insert(0, "0");
            }
            return hash_text.toString();
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
