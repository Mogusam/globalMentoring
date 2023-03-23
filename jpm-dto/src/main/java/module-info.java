import org.example.dto.BankCard;
import org.example.dto.DebitBankCard;

module jpm.dto {
    requires java.persistence;
    requires java.se;
    exports org.example.dto;
    opens org.example.dto to org.hibernate.orm.core;
    provides BankCard with DebitBankCard;
}