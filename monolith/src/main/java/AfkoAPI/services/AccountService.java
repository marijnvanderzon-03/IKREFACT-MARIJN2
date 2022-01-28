package AfkoAPI.services;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Account;
import AfkoAPI.Repository.AccountRepository;

import java.util.Optional;

public class AccountService {

    public static HTTPResponse getAccountIfIdNotNull(AccountRepository accountRepository, String accountId) {
        Account account = null;
        if (!accountId.equals("null")) {
            Optional<Account> optionalAccount = accountRepository.findById(accountId);
            if (optionalAccount.isEmpty())
                return HTTPResponse.<Account>returnFailure("account with id: " + accountId + " does not exist");
            else account = optionalAccount.get();
        }
        return HTTPResponse.<Account>returnSuccess(account);
    }
}
