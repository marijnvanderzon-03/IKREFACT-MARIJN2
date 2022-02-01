package AfkoAPI.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BlacklistCheckService extends HTTPService<Boolean> {

    public BlacklistCheckService() {
        super(HTTPEndpoint.CKECKBLACKLIST, Boolean.class);
    }

    /**
     * checks if an abbreviation contains words from the blacklist, adds it to the database if it does not otherwise returns HTTPResult FAILURE
     *
     * @param abbr the abbreviation to check and add
     * @return HTTPResponse SUCCESS or FAILURE
     */
    public boolean checkAbbrInBlacklist(String abbr) throws Exception {
        HashMap<String, String> urlBuild = new HashMap<>();
        urlBuild.put("abbreviation", abbr);
        try {
            return getRequest(urlBuild);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

