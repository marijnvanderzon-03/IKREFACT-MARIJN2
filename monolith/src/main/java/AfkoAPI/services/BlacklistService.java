package AfkoAPI.services;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.BlacklistEntry;
import AfkoAPI.Repository.AbbreviationRepository;
import AfkoAPI.Repository.BlacklistRepository;

import java.util.HashMap;
import java.util.List;

public class BlacklistService {

    /** checks if an abbreviation contains words from the blacklist, adds it to the database if it does not otherwise returns HTTPResult FAILURE
     * @param blacklistRepository the blacklistRepository
     * @param abbreviationRepository the abbreviationRepository
     * @param abbr the abbreviation to check and add
     * @return HTTPResponse SUCCESS or FAILURE
     */
    public boolean checkAbbrInBlacklist(String abbr) throws Exception {
        HashMap<String, String> urlBuild = new HashMap<>();
        urlBuild.put("abbreviation", abbr);
        return getRequest(urlBuild);
    }
}
