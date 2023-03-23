import org.bank.api.service.BankServiceImpl;

module jmp.service.api {
    requires jpm.dto;
    exports org.bank.api.service;
    provides org.bank.api.service.BankService with BankServiceImpl;
}