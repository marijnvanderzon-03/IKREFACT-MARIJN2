package AfkoAPI.services;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.BlacklistEntry;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BlacklistService extends HTTPService<BlacklistEntry[]>{

    protected BlacklistService() {
        super(HTTPEndpoint.BLACKLIST, BlacklistEntry[].class);
    }

    public void addToBlacklist(BlacklistEntry[] entries){
        try {
            postRequest(entries);
        } catch (Exception e) {
            e.printStackTrace();
            }
    }

    public void editBlacklistEntry(BlacklistEntry[] entries) {
        try {
            putRequest(entries);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeBlacklistEntries(BlacklistEntry[] entries) {
        try {
            deleteRequest(entries);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
