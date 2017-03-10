package conqueror.security.service;


import conqueror.user.domain.UserAccount;

public interface SecurityContextService {
    UserAccount currentUserAccount();
}
