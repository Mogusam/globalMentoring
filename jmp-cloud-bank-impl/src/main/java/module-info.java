import org.bank.service.BankImpl;
import org.bank.service.IDummy;

module jmp.cloud.bank.impl {
    requires transitive jmp.bank.api;
    requires jpm.dto;
    requires jmp.service.api;
    provides IDummy with BankImpl;
    exports org.bank.service;
}