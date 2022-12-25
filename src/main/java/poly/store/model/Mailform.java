
package poly.store.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class chua thong tin gui mail
 * 
 * @author khoa-ph
 * @version 1.00
 * 
 */
@Data
@NoArgsConstructor

public class Mailform {
    String from;
    String to;
    String subject;
    String body;

    public Mailform(String to, String subject, String body) {
        this.from = "NongSanSach.Contact <nongsansach.contacts@gmail.com>";
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}
