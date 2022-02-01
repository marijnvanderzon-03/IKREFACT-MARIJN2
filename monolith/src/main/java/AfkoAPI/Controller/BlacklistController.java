package AfkoAPI.Controller;


import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.BlacklistEntry;
import AfkoAPI.services.BlacklistCheckService;
import AfkoAPI.services.BlacklistService;
import AfkoAPI.services.HTTPEndpoint;
import AfkoAPI.services.HTTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlacklistController {


    @Autowired
    BlacklistCheckService Bservice;

    @Autowired
    BlacklistService blacklistService;



    @PostMapping("/blacklist")
    public HTTPResponse addToBlackList(@RequestBody BlacklistEntry[] entries) {
        blacklistService.addToBlacklist(entries);
        return HTTPResponse.returnSuccess(entries);
    }

    @PutMapping("/blacklist")
    public HTTPResponse editBlacklistEntry(@RequestBody BlacklistEntry[] entries) {
        if (entries.length != 2)
            return HTTPResponse.<BlacklistEntry[]>returnFailure("length of entries is not 2");
        blacklistService.editBlacklistEntry(entries);
        return HTTPResponse.returnSuccess(entries);
    }

    @DeleteMapping("/blacklist")
    public HTTPResponse removeBlacklistEntries(@RequestBody BlacklistEntry[] entries) {
        blacklistService.removeBlacklistEntries(entries);
        return HTTPResponse.returnSuccess(entries);
    }

    @GetMapping("/blacklist/check")
    public boolean inBlacklist(@RequestBody String abbreviation) throws Exception {
       return Bservice.checkAbbrInBlacklist(abbreviation);
    }

}
